package de.hnu.data;


import jakarta.persistence.*;
import java.util.Objects;

import java.util.List;
import java.util.Map;
import java.util.HashMap; // import the HashMap class

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



@Entity
@Table(name = "courses")
public class Course {   

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private String instructor;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer durationInHours;

    @Column(name = "ai_features", nullable = true)
    private String aiFeatures; // Features related to AI assistance for the course

    @Lob
    private String materialsJson;

    @Transient
    private Map<String, String> materials = new HashMap<>();

    @PrePersist
    @PreUpdate
    private void serializeMaterials() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            materialsJson = objectMapper.writeValueAsString(materials);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@PostLoad
    private void deserializeMaterials() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            materials = objectMapper.readValue(materialsJson, new TypeReference<Map<String, String>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @ManyToMany(mappedBy = "courses")
    private List<Student> students;


    public Course() {
    }

    public Course(String title, String description, String instructor, Double price, Integer durationInHours, String aiFeatures, String materialsJson) {
        this.title = title;
        this.description = description;
        this.instructor = instructor;
        this.price = price;
        this.durationInHours = durationInHours;
        this.aiFeatures = aiFeatures;
        this.materialsJson= materialsJson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getDurationInHours() {
        return durationInHours;
    }

    public void setDurationInHours(Integer durationInHours) {
        this.durationInHours = durationInHours;
    }

    public String getAiFeatures() {
        return aiFeatures;
    }

    public void setAiFeatures(String aiFeatures) {
        this.aiFeatures = aiFeatures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) && Objects.equals(title, course.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", instructor='" + instructor + '\'' +
                ", price=" + price +
                ", durationInHours=" + durationInHours +
                ", aiFeatures='" + aiFeatures + '\'' +
                '}';
    }


    public Map<String, String> reloadMaterialFromDB() {
        deserializeMaterials();
        return materials;
    }

    public void addMaterial(String materialName, String materialUrl){
        this.reloadMaterialFromDB().put(materialName, materialUrl);
        serializeMaterials();    
    }

    public String getMaterialLinkByName(String materialName){
         
        String materialLink = reloadMaterialFromDB().get(materialName);
        if(materialLink!=null){
            return materialLink;
        }else{
            return "No material with this name is found for this course";
        }
    }

    public Course deleteMaterialByName(String materialName){
         
        String materialLink = reloadMaterialFromDB().remove(materialName);
        serializeMaterials();
        return this;
        /*
        if(materialLink!=null){
            return materialName + " and its link: " + materialLink +" has been successfully removed";
        }else{
            return "No material with this name is found for this course";
        }*/
    }

}
