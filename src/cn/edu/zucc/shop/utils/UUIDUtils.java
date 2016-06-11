package cn.edu.zucc.shop.utils;

import java.util.UUID;

/*
 * Éú³ÉËæ»ú×Ö·û´®
 */
public class UUIDUtils {
	
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
