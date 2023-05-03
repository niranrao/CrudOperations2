package com.lumen.ebonding.util;

import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil {
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(13,new SecureRandom());
	public String encryptPassword(String password) {
		return encoder.encode(password);
	}
	public boolean authenticatePassword(String password,String encryptedPassword) {
		return encoder.matches(password, encryptedPassword);
	}
}
