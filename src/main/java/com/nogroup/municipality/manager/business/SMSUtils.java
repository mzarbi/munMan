package com.nogroup.municipality.manager.business;

import java.net.URI;
import java.util.Arrays;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class SMSUtils {

	public static final String ACCOUNT_SID = "AC668ca83c3cd4eabd36c4a80dd8840de0";
	public static final String AUTH_TOKEN = "b0c3250acc893943516b25efc017cba9";
	public static final String TWILIO_PHONE = "+12569527224" ;

	public static void sendSMS(String msg,String phone) {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message = Message.creator(new com.twilio.type.PhoneNumber(phone),
				new com.twilio.type.PhoneNumber(TWILIO_PHONE), "" + msg)
				.setMediaUrl(
						Arrays.asList(URI.create("https://c1.staticflickr.com/3/2899/14341091933_1e92e62d12_b.jpg")))
				.create();
	}
}
