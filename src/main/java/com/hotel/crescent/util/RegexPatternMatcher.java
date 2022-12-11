package com.hotel.crescent.util;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.hotel.crescent.security.AuthorizedUrls;

@Service
public class RegexPatternMatcher implements PatternMatcher{

	@Override
	public boolean match(String str) {
		for(Pattern p : AuthorizedUrls.authorizedRegexUrls) {
			if(p.matcher(str).matches()) {
				return true;
			}
		}
		return false;
	}

}
