package com.lumen.ebonding.service;

import java.util.Map;

import com.lumen.ebonding.dto.UsersDTO;
import com.lumen.ebonding.model.PasscodeGenerator;
import com.lumen.ebonding.model.Users;

public interface LoginService extends GenericService<Users, Integer, UsersDTO>{

		public UsersDTO getById(Integer userId);
		public UsersDTO getByCuId(String cuId);
		public PasscodeGenerator generatePasscode(String cuId);
		public int validatePasscode(Map<String, String> details);
		public void validationUpdate(int validationId);
		public int validateSessionTime(String string);
		public void updatePassword(String username, String string);
		public Boolean searchUser(String cuId);
		public String getPasswordHash(String cuId);
		public void deletePasscodes(String string);
		
}
