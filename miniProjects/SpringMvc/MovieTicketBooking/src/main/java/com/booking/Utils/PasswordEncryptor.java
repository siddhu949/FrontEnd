package com.booking.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptor {
	private static final BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();
	

	public static String encrypt(String rawPassword) {
		return encoder.encode(rawPassword);
	}
	public static boolean matches(String rawPassword ,String encodedPassword) {
		return encoder.matches(rawPassword,encodedPassword);
	}
}
//🔐 How BCrypt Works (Spring Security)
//1️ BCrypt = Hashing, not simple encryption
//
//It does not decrypt (one-way hashing).
//
//Every time you encode the same password, it gives a different hash — 
//that’s normal and secure.
//
//When checking a login password,
//BCryptPasswordEncoder.matches() hashes
//the input password again and verifies it matches the stored hash. 

//2️⃣ Why it’s used
//
//It automatically adds a random salt (extra data to prevent rainbow-table attacks).
//
//It’s slow by design — making brute-force attacks very hard.