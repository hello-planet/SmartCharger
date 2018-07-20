package com.func.util;

import com.func.service.CommonManager;

/**
 * For users to sign up to the SmartCharger system.<br>
 * To check the form validation.<br>
 * To insert the application data into MYSQL server.<br>
 * 
 * @author helloplanet
 * @create 2018.4.4
 *
 */
public class UserManager extends CommonManager{
	public static final int SIGN_UP_SUCCESSED = 1;
	public static final int SIGN_UP_FAILED = 2;
	public static final int SIGN_UP_IILEGAL = 0;

	public UserManager() {

	}

	public int signUp(){
		
		return SIGN_UP_FAILED;
	}
	
	public void getForm() {

	}

	public boolean checkForm() {
		return false;
	}

	public void insertData() {

	}

}
