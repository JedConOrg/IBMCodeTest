package com.jed.reguser.RegUser;

import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.util.JSONPObject;

@RestController
public class Controller {
	
	@GetMapping("/api/test")
	public String getString() {
		return "here is a String!";
	}
	
	@GetMapping("/api/HelloWorld")
	public String helloWorld() {
		return "Hello <b>World!</b>";
	}

	@GetMapping("/api/error")
	public String error() {
		return "<b>ERROR!</b>";
	}

	@GetMapping("/api/foos")
	@ResponseBody
	public String getFoos(@RequestParam(required = false) String id, @RequestParam(required = false) String id2) { 
	    return "ID: " + id+"  ID2: "+id2;
	}
	
	@GetMapping("/api/parmtest")
	public String parmtest(@RequestParam("name") String name)	 {
		return "<b>"+name+"</b>";
	}
	
	@GetMapping("/hello1")
	public String hello1(@RequestParam(name="name", required = false, defaultValue = "Ahmed") String name)
	{
		return "Hello " + name;
	}
	
//	@GetMapping("/hello2")
//	public String hello2(User parameters) {	
//	
//	    //implement the setter and getter of the com.jed.reguser.RegUser class.
//	    return "Hello " + parameters.a + " " + parameters.b;
//	}
	
	@PostMapping ("/api/reguser1"
			)
	@ResponseBody
	public String RegOfTheUser1(
			                    @RequestParam(name="user",     required = true, defaultValue = "") String name
			                   ,@RequestParam(name="password", required = true, defaultValue = "") String password
			                   ,@RequestParam(name="ipAddress",required = true, defaultValue = "") String ipAddress
			                  ){
		String reply;
		reply =         "<p>Your Name is: " + name + "</p>";
		reply = reply + "<p>Your password is: " + password + "</p>";
		reply = reply + "<p>Your IP is: " + ipAddress + "</p>";
	    return reply;
	}
    
	
	@PostMapping ("/api/reguser"
			)
	@ResponseBody
	public String RegOfTheUser(
			                    @RequestParam(name="user",     required = true, defaultValue = "") JSONPObject jsonObject
			                  ){
		String reply;
		reply =         jsonObject.getValue();
		reply = reply + "<p>Your password is: " + password + "</p>";
		reply = reply + "<p>Your IP is: " + ipAddress + "</p>";
	    return reply;
	}
    
	
	
}

