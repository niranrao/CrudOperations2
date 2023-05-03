package com.lumen.ebonding.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lumen.ebonding.dto.UsersDTO;
import com.lumen.ebonding.service.LoginService;
import com.lumen.ebonding.util.ConfigPortalEmail;
import com.lumen.ebonding.util.EncryptionUtil;



@RestController
@RequestMapping(value = "/users")
public class UsersController {
	
	private Logger log = LoggerFactory.getLogger(LoginController.class);
	
	
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private ConfigPortalEmail configPortalEmail;
	
	@Autowired
	private EncryptionUtil encryptionUtil;
	
	@PostMapping()
	public ResponseEntity<UsersDTO> save(@RequestBody UsersDTO users)
	{
		log.info("Starting of save method");
		try {
			users = loginService.saveOrUpdate(users);
			loginService.updatePassword(users.getCuId().toUpperCase(),encryptionUtil.encryptPassword("Lumen123!"));
			configPortalEmail.sendPassword(users.getCuId(),users.getEmailId(),"Lumen123!");
			return new ResponseEntity<UsersDTO>(users, HttpStatus.CREATED);
			
		} catch (Exception ex) {
			return new ResponseEntity<UsersDTO>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = "/{userId}")
	public ResponseEntity<UsersDTO> getById(@PathVariable Integer userId) {
		try {
			log.info("Get user by Id");
			UsersDTO user = loginService.getById(userId);
			if (user != null) {
				return new ResponseEntity<UsersDTO>(user, HttpStatus.OK);
			}
	
			log.info("No user found");
			return new ResponseEntity<UsersDTO>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e) {
			log.error("Get user by Id failed "+ ExceptionUtils.getStackTrace(e));
			return new ResponseEntity<UsersDTO>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping()
	public ResponseEntity<List<UsersDTO>> getAll() {
		try {
		List<UsersDTO> usersList = loginService.getAll();
		if (usersList != null && !usersList.isEmpty()) {
			return new ResponseEntity<List<UsersDTO>>(usersList, HttpStatus.OK);
		}

		log.info("No users found");
		return new ResponseEntity<List<UsersDTO>>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e) {
			log.error("Get users failed "+ ExceptionUtils.getStackTrace(e));
			return new ResponseEntity<List<UsersDTO>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable Integer userId){
		try
		{
			if (loginService.get(userId) == null) {
				return new ResponseEntity<Boolean>(false,HttpStatus.NO_CONTENT);
			}
	
			boolean delete = loginService.delete(userId);
			return new ResponseEntity<Boolean>(delete,HttpStatus.OK);
		}
		catch (Exception e) {
			log.error("Deleting User failed "+ ExceptionUtils.getStackTrace(e));
			return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping(path = "editUsers/{userId}")
	public ResponseEntity<UsersDTO> update(@PathVariable Integer userId ,@RequestBody UsersDTO users)
	{
		try
		{
			if(loginService.get(userId)!=null)
			{
				loginService.saveOrUpdate(users);
			}
			else
			{
				log.info("Given data is not present");
			}
		}
		catch (Exception e) {
			return new ResponseEntity<UsersDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<UsersDTO>(HttpStatus.CREATED);
	}
	
	@PostMapping(path="/password")
	public ResponseEntity<Map<String,Boolean>> changePassword(@RequestBody Map<String,String> details){
		Map<String,Boolean> changePasswordMap = new HashMap<String,Boolean>();
		log.info("password change for cuid : "+ details.get("cuId"));
		try {
			String password = loginService.getPasswordHash(details.get("cuId").toUpperCase());
			boolean isOldPasswordValid = encryptionUtil.authenticatePassword(details.get("oldPassword"),password);
			if(isOldPasswordValid) {
				log.info("old password validated successfully");
				changePasswordMap.put("isOldPasswordCorrect", true);
				loginService.updatePassword(details.get("cuId"),encryptionUtil.encryptPassword(details.get("newPassword")));
				log.info("password changed successfully");
				changePasswordMap.put("isPasswordUpdated", true);
				return new ResponseEntity<Map<String,Boolean>>(changePasswordMap,HttpStatus.OK);
			}
			else {
				log.info("old password validation failed");
				changePasswordMap.put("isOldPasswordCorrect", false);
				changePasswordMap.put("isPasswordUpdated", false);
				return new ResponseEntity<Map<String,Boolean>>(changePasswordMap,HttpStatus.OK);
			}
		}
		catch(Exception e) {
			log.error("Password update failed "+ ExceptionUtils.getStackTrace(e));
			return new ResponseEntity<Map<String,Boolean>>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	
	
	
	

}
