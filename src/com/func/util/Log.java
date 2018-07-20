package com.func.util;

import java.io.File;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

import com.func.domain.ServerConfig;

/**
 * Common util for log.
 * 
 * @author helloplanet
 * @create 2018.5.9
 *
 */
public class Log {

	public static void writeLog(String filepath, String msg_title, String msg_body) {
		String log_content = formatLog(msg_body);
		if (new File(filepath).exists()) {

		} else {

		}
	}

	public static void printLog(String msg_title, String msg_body) {

	}

	public static String formatLog(String raw_msg) {
		String msg = "";

		return msg;
	}

	/**
	 * Common log for exception
	 * 
	 */
	public static void warningLog(String msg_title, String msg_body, boolean writeOrNot, boolean printOrNot) {
		if (writeOrNot) {
			String logFilePath = ServerConfig.LogFolderPath + new Date().getTime() + "-Warning";
			writeLog(logFilePath, msg_title, msg_body);
		}
		if (printOrNot) {
			printLog(msg_title, msg_body);
		}
	}

	/**
	 * Common log for error
	 * 
	 */
	public static void errorLog(String msg, boolean writeOrNot) {

	}

	/**
	 * Common log for user registration, nodes discovery etc.
	 * 
	 */
	public static void infoLog(String msg, boolean writeOrNot) {

	}

	/**
	 * Common log for transaction
	 * 
	 */
	public static void contextLog(String msg, boolean writeOrNot) {

	}

	/**
	 * Common log for extra usage
	 * 
	 */
	public static void extraLog(String msg, boolean writeOrNot) {

	}
	
	public static String formatDate(){
//		String date_str = new DateFormat().format(new Date());
		
		return null;
	}
}
