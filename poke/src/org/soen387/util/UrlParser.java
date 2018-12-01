package org.soen387.util;

import java.net.URL;

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

}
