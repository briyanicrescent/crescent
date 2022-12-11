package com.hotel.crescent.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.crescent.security.AuthorizedUrls;

@Service
public class SimplePatternMatcher implements PatternMatcher{
	
	@Autowired
	RegexPatternMatcher matcher;

	@Override
	public boolean match(String str) {
		if(AuthorizedUrls.authorizedUrls.contains(str)) {
			return true;
		}
		return matcher.match(str);
	}

}
