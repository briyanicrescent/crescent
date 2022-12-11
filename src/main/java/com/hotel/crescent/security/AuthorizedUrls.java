package com.hotel.crescent.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class AuthorizedUrls {
	
	public static final Set<String> authorizedUrls = new HashSet<String>();
	public static final List<Pattern> authorizedRegexUrls = new ArrayList<Pattern>();
	
	static {
		addUserUrls();
		addAuthUrls();
		addAddressUrls();
	}
	
	public static void addUserUrls() {
		authorizedUrls.add("/user/details");
		authorizedUrls.add("/user");
	}
	
	public static void addAuthUrls() {
		authorizedUrls.add("/auth/getRefreshedToken");
	}
	
	public static void addAddressUrls() {
		authorizedUrls.add("/address");
		authorizedRegexUrls.add(Pattern.compile("/address/.*"));
	}
	
	/*public static String[] getAuthorizedUrls() {
		return authorizedUrls.toArray(new String[authorizedUrls.size()]);
	}*/

}
