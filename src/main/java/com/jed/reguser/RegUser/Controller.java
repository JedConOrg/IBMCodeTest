package com.jed.reguser.RegUser;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.*;

import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@RestController
public class Controller {
	
	@PostMapping ("/api/reguser")
	@ResponseBody
	public String RegOfTheUser(
									@RequestBody User user
			                  ){

//		String reply;
//		reply =         "<p>Your Name is: " + user.getUserName() + "</p>";
//		reply = reply + "<p>Your password is: " + user.getPassword() + "</p>";
//		reply = reply + "<p>Your IP is: " + user.getIpAddress() + "</p>";

		if (user.getUserName().equals(null)) {
			return "<p>please suply a username.</p>";
		}
		if (!PasswordIsGood(user.getPassword())) {
			return "<p>Invalid Password</p>";
		}
		
		GeoIP geoIP;
		try {
			geoIP = GetGeoLoc (user.getIpAddress());
		} catch (Exception e) {
			return "<p>There is something wron with your IPaddress </p> <p>" + e.getMessage() + "</p>";
		}

		if (!geoIP.getCountry().equals("Canada")) {
			return "<p>I'm sorry this application is only avaliable to Canadain Users.</p> ";
		}
		UUID uuid = UUID.randomUUID();
		
		return "<p>Good Day " + user.getUserName() +" Welcome to the application.</p><p>I see you are located in " + geoIP.getCity() +
				", " + geoIP.getCountry() + "</p><p> Please make note of your UUID for later refrence: " + uuid + "</p><p>";
	}
    
	
	private GeoIP GetGeoLoc (String ip) throws Exception {
		
		GeoIP myLoc = new GeoIP();
		
		String geoLocURL = "http://ip-api.com/json/"+ip;
		
		URL url = new URL (geoLocURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();

		//Getting the response code
		int responsecode = conn.getResponseCode();
		if (responsecode != 200) {
		    throw new RuntimeException("HttpResponseCode: " + responsecode);
		}

		RestTemplate restTemplate = new RestTemplate();
		try {
			String response = restTemplate.getForObject(geoLocURL, String.class);
			JsonElement jsonElement = JsonParser.parseString(response);
			myLoc.setStatus(jsonElement.getAsJsonObject().get("status").getAsString());
			myLoc.setCountry(jsonElement.getAsJsonObject().get("country").getAsString());
			myLoc.setCity(jsonElement.getAsJsonObject().get("city").getAsString());
			myLoc.setQuery(jsonElement.getAsJsonObject().get("query").getAsString());
			myLoc.setCountryCode(jsonElement.getAsJsonObject().get("countryCode").getAsString());
			myLoc.setRegion(jsonElement.getAsJsonObject().get("region").getAsString());
			myLoc.setRegionName(jsonElement.getAsJsonObject().get("regionName").getAsString());
			myLoc.setZip(jsonElement.getAsJsonObject().get("zip").getAsString());
			myLoc.setLat(jsonElement.getAsJsonObject().get("lat").getAsString());
			myLoc.setLon(jsonElement.getAsJsonObject().get("lon").getAsString());
			myLoc.setTimezone(jsonElement.getAsJsonObject().get("timezone").getAsString());
			myLoc.setIsp(jsonElement.getAsJsonObject().get("isp").getAsString());
			myLoc.setOrg(jsonElement.getAsJsonObject().get("org").getAsString());
			myLoc.setAs(jsonElement.getAsJsonObject().get("as").getAsString());
		} catch (Exception e) {
			throw new RuntimeException("JSON Parser: " + responsecode);
		}
		

		return myLoc;
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

