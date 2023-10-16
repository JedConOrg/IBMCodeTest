package com.jed.reguser.RegUser;

import java.util.regex.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
	
	@PostMapping ("/api/reguser"
			)
	@ResponseBody
	public String RegOfTheUser(
									@RequestBody User user
			                  ){

//		String reply;
//		reply =         "<p>Your Name is: " + user.getUserName() + "</p>";
//		reply = reply + "<p>Your password is: " + user.getPassword() + "</p>";
//		reply = reply + "<p>Your IP is: " + user.getIpAddress() + "</p>";

		
		if (PasswordIsGood(user.getPassword())) {
			return "Yes!";
		} else {
			return "NO!";			
		}
	}
    
	private boolean PasswordIsGood (String pW) {

		// Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[_#$%.])"
                       + "(?=\\S+$).{8,}$";
 
        Pattern p = Pattern.compile(regex);
 
        // If the password is empty
        // return false
        if (pW == null) {
            return false;
        }
 
        // Check string for matches
        Matcher m = p.matcher(pW);
 
        // return if it found matches or not.
        return m.matches();

	}
	
}

