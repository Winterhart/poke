package org.soen387.util;


import org.apache.commons.lang3.StringUtils;

public class UrlParser {
	
	public static String getIdInUR(String url) {
		String[] splittedURL = url.split("/");
		for(String s: splittedURL) {
			if(StringUtils.isNumeric(s)) {
				return s;
			}
		}
		
		return null;
		
	}
	
	public static String getLastWord(String url) {
		String[] split = url.split("/");
		return split[split.length-1];
	}

}
