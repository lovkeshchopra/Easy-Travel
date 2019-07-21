package com.easytravel.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomNumberGenerator {
	public String random() {
		Random random = new Random();
		String generatedPassword = String.format("%04d", random.nextInt(10000));
		return generatedPassword;
	}
}
