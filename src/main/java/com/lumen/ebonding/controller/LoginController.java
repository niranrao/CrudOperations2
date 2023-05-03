package com.lumen.ebonding.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lumen.ebonding.dto.UsersDTO;
import com.lumen.ebonding.model.PasscodeGenerator;
import com.lumen.ebonding.service.LoginService;
import com.lumen.ebonding.util.EncryptionUtil;

@RestController
@RequestMapping(value = "/login")
public class LoginController {
	
	private Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private EncryptionUtil encryptionUtil;
	
	@GetMapping(path="/check/{cuId}")
	public ResponseEntity<Map<String,Boolean>> checkUser(@PathVariable String cuId) {
		try {
			log.info("finding user...");
			Map<String,Boolean> userValidation = new HashMap<String,Boolean>();
			cuId = cuId.toUpperCase();
			log.info("cuId : "+cuId);
			if(loginService.searchUser(cuId))
			{
			UsersDTO user = loginService.getByCuId(cuId);
			if (user != null && user.getStatus()) {
				log.info("user found and active");
				userValidation.put("userExist", true);
				userValidation.put("isActive", true);
				return new ResponseEntity<Map<String,Boolean>>(userValidation, HttpStatus.OK);
			}
			else if(user!=null && !user.getStatus()) {
				log.info("user found but inactive");
				userValidation.put("userExist", true);
				userValidation.put("isActive", false);
				return new ResponseEntity<Map<String,Boolean>>(userValidation, HttpStatus.OK);
			}
			}
			log.info("user not found");
			userValidation.put("userExist", false);
			userValidation.put("isActive", false);
			return new ResponseEntity<Map<String,Boolean>>(userValidation,HttpStatus.OK);
		}
		catch(Exception e) {
			log.error("checking user failed "+ ExceptionUtils.getStackTrace(e));
			return new ResponseEntity <Map<String,Boolean>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping()
	public ResponseEntity<Boolean> userAuthentication(@RequestBody Map<String, String> details)
	{
		
		log.info("Getting users details by cuid");
		try
		{
			System.out.println(details.get("username"));
			String username=details.get("username").toUpperCase();
			
		    if(!details.get("username").isEmpty())
		    {
		    	String password = loginService.getPasswordHash(username);
		    	if(!details.get("password").isEmpty() && encryptionUtil.authenticatePassword(details.get("password"),password))
		    	{
		    		log.info("password validated successfully");
		    		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		    	}
		    }
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity <Boolean>(HttpStatus.NO_CONTENT);
		}
		
	}
	@GetMapping(path="/{cuId}")
	public ResponseEntity<UsersDTO> getById(@PathVariable String cuId) {
		try {
			cuId = cuId.toUpperCase();
			log.info("cuId : "+cuId);
			UsersDTO user = loginService.getByCuId(cuId);
			if (user != null) {
				log.info("user found");
				return new ResponseEntity<UsersDTO>(user, HttpStatus.OK);
			}
	
			log.info("No users found");
			return new ResponseEntity<UsersDTO>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e) {
			log.error("get user by Id failed "+ ExceptionUtils.getStackTrace(e));
			return new ResponseEntity <UsersDTO>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping(path="/search/{cuId}")
	public ResponseEntity<Boolean> searchUser(@PathVariable String cuId){
    	cuId = cuId.toUpperCase();
    	log.info("search user with cuId : "+cuId);
		try {
			Boolean userFound = loginService.searchUser(cuId);
			if(userFound) {
				log.info("User found");
				return new ResponseEntity <Boolean>(true,HttpStatus.OK);
			}
			else {
				log.info("User not found");
				return new ResponseEntity <Boolean>(false,HttpStatus.OK);
			}
		}
		catch(Exception e) {
			log.error("User search failed "+ ExceptionUtils.getStackTrace(e));
			return new ResponseEntity <Boolean>(HttpStatus.NO_CONTENT);
		}
	}
    @GetMapping(path="/forgot/{cuId}")
	public ResponseEntity<Boolean> generatePasscode(@PathVariable String cuId){
    	cuId = cuId.toUpperCase();
    	log.info("forgot password for cuId : "+cuId);
		try {
			PasscodeGenerator generatedEntity = loginService.generatePasscode(cuId);
			if(generatedEntity!=null && generatedEntity.getId()>0) {
				log.info("Passcode generated successfully");
				return new ResponseEntity <Boolean>(true,HttpStatus.OK);
			}
			else {
				log.info("Passcode generation failed");
				return new ResponseEntity <Boolean>(false,HttpStatus.OK);
			}
		}
		catch(Exception e) {
			log.error("Passcode generation failed "+ ExceptionUtils.getStackTrace(e));
			return new ResponseEntity <Boolean>(HttpStatus.NO_CONTENT);
		}
	}
    @PostMapping(path="/verify")
    public ResponseEntity<Map<String,Integer>> validatePasscode(@RequestBody Map<String, String> details){
    	Map<String,Integer> validationMap = new HashMap<String,Integer>();
    	log.info("passcode validation for cuId : "+details.get("username"));
    	try {
			int validationId = loginService.validatePasscode(details);
			validationMap.put("validationId",validationId);
			if(validationId>0) {
				log.info("passcode validated successfully");
				loginService.validationUpdate(validationId);
				validationMap.put("validationSuccess",1);
				return new ResponseEntity <Map<String,Integer>>(validationMap,HttpStatus.OK);
			}
			else {
				log.info("passcode validation failed");
				validationMap.put("validationSuccess",0);
				return new ResponseEntity <Map<String,Integer>>(validationMap,HttpStatus.OK);
			}
		}
		catch(Exception e) {
			log.error("Passcode validation failed "+ ExceptionUtils.getStackTrace(e));
			return new ResponseEntity <Map<String,Integer>>(HttpStatus.NO_CONTENT);
		}
    }
    @PostMapping(path="/password")
    public ResponseEntity<Boolean> updatePassword(@RequestBody Map<String, String> details){
    	log.info("Password update for cuId : "+ details.get("username"));
    	try {
			int isValid = loginService.validateSessionTime(details.get("validationId"));
			if(isValid==1) {
				log.info("session validated successfully");
				loginService.updatePassword(details.get("username"),encryptionUtil.encryptPassword(details.get("password")));
				log.info("Password updated successfully");
				loginService.deletePasscodes(details.get("username"));
				return new ResponseEntity <Boolean>(true,HttpStatus.OK);
			}
			else {
				log.info("session validation failed");
				return new ResponseEntity <Boolean>(false,HttpStatus.OK);
			}
		}
		catch(Exception e) {
			log.error("Password update failed "+ ExceptionUtils.getStackTrace(e));
			return new ResponseEntity <Boolean>(HttpStatus.NO_CONTENT);
		}
    }
    
	
	

	

}
