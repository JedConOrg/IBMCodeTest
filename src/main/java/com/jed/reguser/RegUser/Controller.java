package com.jed.reguser.RegUser;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
	
	@GetMapping("/api/test")
	public String getString() {
		return "here is a String!";
	}
	
	@GetMapping("/api/HelloWorld")
	public String helloWorld() {
		return "Hello World!";
	}

}
