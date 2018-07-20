package com.func.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.codec.binary.Hex;

/**
 * For common usage of hashing, encrypting and decoding.<br>
 * 
 * @author helloplanet
 * @create 2018.4.28
 * 
 */
public class Encoder {

	public static String sha1Text(String rawText) {
		MessageDigest messageDigest;
		String hashedStr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-1");
			byte[] hash = messageDigest.digest(rawText.getBytes("UTF-8"));
			hashedStr = Hex.encodeHexString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return hashedStr;
	}

	public static String sha1Text(List<String> rawText) {
		if (rawText.isEmpty() || rawText == null) {
			return null;
		} else {
			StringBuilder raw = new StringBuilder();
			for (String data : rawText) {
				raw.append(data);
			}
			return sha1Text(raw.toString());
		}
	}

	public static String encodeText(String rawText, String key) {

		return null;
	}

	public static String decodeText(String cypherText, String key) {

		return null;
	}
}
