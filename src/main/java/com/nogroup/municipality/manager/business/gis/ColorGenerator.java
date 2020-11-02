package com.nogroup.municipality.manager.business.gis;

import java.util.Random;

public class ColorGenerator {

	public static String randomColor() {
		Random random = new Random();
        // create a big random number - maximum is ffffff (hex) = 16777215 (dez)
        int nextInt = random.nextInt(0xffffff + 1);
        // format it as hexadecimal string (with hashtag and leading zeros)
        return String.format("#%06x", nextInt);
	}
}
