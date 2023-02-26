package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class SslServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}
}

@RestController
class ServerController {
	private static final String HASH_TYPE = "SHA-256";
	private static final String HELLO_WORLD_CHECK_SUM = "Hello World Check Sum!";

	@RequestMapping("/hash")
	public String myHash() {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(HASH_TYPE);
			messageDigest.update(HELLO_WORLD_CHECK_SUM.getBytes());
			byte[] digest = messageDigest.digest();

			StringBuilder hexData = new StringBuilder();
			for (byte b : digest) {
				hexData.append(Integer.toHexString(0xFF & b));
			}

			return "data:" + HELLO_WORLD_CHECK_SUM + "<p> Name of Cipher Used: " + HASH_TYPE + " Value: " + hexData;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}