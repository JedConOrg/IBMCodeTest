package com.jed.reguser.RegUser;

import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.util.JSONPObject;

@RestController
public class Controller {
	
	@PostMapping ("/api/reguser"
			)
	@ResponseBody
	public String RegOfTheUser(
									@RequestBody User user
			                  ){
		String reply;
		reply =         "<p>Your Name is: " + user.getUserName() + "</p>";
		reply = reply + "<p>Your password is: " + user.getPassword() + "</p>";
		reply = reply + "<p>Your IP is: " + user.getIpAddress() + "</p>";
	    return reply;
	}
    
	
	
}

