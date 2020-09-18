package com.kingdee.inte.teamwork.google;


import java.io.File;
import java.io.IOException;

/**
 * description:
 *
 * @author Andy
 * @version 1.0
 * @date 05/30/2020 15:31
 */
public class SignedJwt {

	public static void main(String[] args) {
		GoogleCredential googleCredential = new GoogleCredential(null);
		googleCredential.getSingleton().generateToken();
	}

}
