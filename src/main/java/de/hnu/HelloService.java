package de.hnu;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloService {
    
	@GetMapping("/greeting/{name}")
	public String sayHello(@PathVariable(value = "name") String name) {
		return "Hello, "+name;
	}
}