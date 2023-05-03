package com.lumen.ebonding.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.lumen.ebonding.dto.PasscodeGeneratorDTO;
import com.lumen.ebonding.dto.UsersDTO;
import com.lumen.ebonding.model.PasscodeGenerator;
import com.lumen.ebonding.model.Users;
import com.lumen.ebonding.repository.PasscodeGenerationRepository;
import com.lumen.ebonding.repository.UsersRepository;
import com.lumen.ebonding.service.LoginService;
import com.lumen.ebonding.util.ConfigPortalEmail;

@Service
public class LoginServiceImpl extends GenericServiceImpl<Users, Integer, UsersDTO> implements LoginService{
	
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private PasscodeGenerationRepository passcodeGenerationRepository;
	@Autowired
	ConfigPortalEmail email;

	private ModelMapper mapper = new ModelMapper();
	
	private Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Override
	public Users toEntity(UsersDTO dto) {
		Users entity = new Users();
		mapper.map(dto, entity);
		return entity;
	}

	@Override
	public UsersDTO toDTO(Users entity) {
		UsersDTO dto = new UsersDTO();
		mapper.map(entity, dto);
		return dto;
	}

	@Override
	public JpaRepository getRepository() {
		return usersRepository;
	}

	@Override
	public UsersDTO getById(Integer userId) {
		Users user= usersRepository.findByUserId(userId);
		return toDTO(user);
	}
	@Override
	public UsersDTO getByCuId(String cuId) {
		Users user= usersRepository.getByCuId(cuId);
		return toDTO(user);
	}

	@Override
	public PasscodeGenerator generatePasscode(String cuId) {
		log.info("passcode generation started");
		Random rnd = new Random();
	    int number = rnd.nextInt(999999);
	    String passcode = String.format("%06d", number);
	    PasscodeGenerator passcodeGeneration = new PasscodeGenerator(cuId,passcode,new Date());
	    PasscodeGenerator generatedEntity = passcodeGenerationRepository.save(passcodeGeneration);
	    log.info("passcode saved to database");
	    UsersDTO user = getByCuId(cuId);
	    email.sendMailPasscode(cuId, passcode, user.getEmailId());
	    log.info("passcode sent to user via mail");
		return generatedEntity;
	}

	@Override
	public int validatePasscode(Map<String, String> details) {
		int value = passcodeGenerationRepository.validate(details.get("username"),details.get("passcode"));
		if(value>0) {
			return passcodeGenerationRepository.getValidationId(details.get("username"),details.get("passcode"));
		}
		return 0;
	}

	@Override
	public void validationUpdate(int validationId) {
		passcodeGenerationRepository.updateValidation(validationId);
	}

	@Override
	public int validateSessionTime(String validationId) {
		log.info("validating session time for new password");
		int id = Integer.parseInt(validationId);
		int valid = passcodeGenerationRepository.validateSession(id);
		return valid;
	}

	@Override
	public void updatePassword(String username, String password) {
		log.info("updating password...");
		usersRepository.updatePassword(username,password);
	}

	@Override
	public Boolean searchUser(String cuId) {
		log.info("Searching user... "+ cuId);
		int value = usersRepository.searchUser(cuId);
		return value>0;
	}

	@Override
	public String getPasswordHash(String cuId) {
		log.info("Retrieving password hash from db...");
		String password = usersRepository.getPasswordHash(cuId);
		return password;
	}

	@Override
	public void deletePasscodes(String cuId) {
		log.info("Deleting the generated passcodes after validation for "+ cuId);
		passcodeGenerationRepository.deletePasscodes(cuId);
		
	}

}
