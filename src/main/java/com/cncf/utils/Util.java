package com.cncf.utils;

import com.cncf.entity.Admin;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	public static String getMd5(String str) {
		byte[] buf = str.getBytes();
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
		md5.update(buf);
		byte[] tmp = md5.digest();
		StringBuilder sb = new StringBuilder();
		for (byte b : tmp) {
			System.out.println("b:"+b);
			int bt = b & 0xff;
			System.out.println("bt:"+bt);
			if (bt < 16) {
				sb.append(0);
			}
			sb.append(Integer.toHexString(bt));
		}
		return sb.toString();
	}

	public static boolean isPhoneNumber(String phoneNumber) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9]))\\d{8}$");
		Matcher matcher = p.matcher(phoneNumber);
		return matcher.matches();
	}

	public static boolean setNewAccessToken(Admin admin) {
		admin.setAccessToken(Util.getToken());
		admin.setExpireTime(new Date());
		return true;
	}

	public static String getRandomCode() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			stringBuilder.append((int) (Math.random() * 10));
		}
		return stringBuilder.toString();
	}

	public static String getToken() {
		String raw = getRandomCode();
		return getMd5(raw);
	}// 得到盐
}
