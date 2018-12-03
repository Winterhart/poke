package org.soen387.util;


import org.apache.commons.lang3.StringUtils;
import java.util.*;

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

	public static List<Long> getThoseId(String pathInfo) {
		String[] splittedURL = pathInfo.split("/");
		List<Long> ids = new ArrayList<Long>();
		for(String s: splittedURL) {
			if(StringUtils.isNumeric(s)) {
				ids.add(Long.parseLong(s));
			}
		}
		
		return ids;
	}

}
