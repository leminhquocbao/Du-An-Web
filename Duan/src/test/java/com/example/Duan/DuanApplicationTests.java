package com.example.Duan;

import jakarta.xml.bind.DatatypeConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.PasswordAuthentication;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
class DuanApplicationTests {

	@Test
	/*void hash() throws NoSuchAlgorithmException {
		String password ="123455";

		MessageDigest md = MessageDigest.getInstance("md5");
		md.update(password.getBytes());

		byte[] digest = md.digest();
		//E10ADC3949BA59ABBE56E057F20F883E
		String md5Hash = DatatypeConverter.printHexBinary(digest);

		log.info("MD5 round 1: {}",md5Hash);

		md.update(password.getBytes());
		digest = md.digest();
		md5Hash = DatatypeConverter.printHexBinary(digest);

		log.info("MD5 round 2: {}",md5Hash);

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

		log.info("BCrypt round 1: {}", passwordEncoder.encode(password));
		log.info("BCrypt round 2: {}", passwordEncoder.encode(password));

	}*/
	void contextLoads() {
	}

}
