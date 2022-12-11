package com.hotel.crescent.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hotel.crescent.constants.Constants;
import com.hotel.crescent.exception.AuthException;
import com.hotel.crescent.exception.TokenExpiredException;
import com.hotel.crescent.model.Response;
import com.hotel.crescent.model.auth.AuthInfo;
import com.hotel.crescent.service.auth.OAuthService;
import com.hotel.crescent.service.exception.ExceptionService;
import com.hotel.crescent.util.SimplePatternMatcher;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OAuthFilter extends OncePerRequestFilter{
	
	Logger logger = Logger.getLogger(OAuthFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String accessToken = getAccessTokenFromHeader(request);
		if(StringUtils.hasText(accessToken)) {
			OAuthService oAuthService = (OAuthService)getBeanFromContext(request,OAuthService.class);
			try {
				AuthInfo authInfo = oAuthService.validateToken(accessToken);
				if(authInfo!=null && !oAuthService.isTokenBlacklisted(authInfo, accessToken)) {
					List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
					authorities.add(new SimpleGrantedAuthority(Constants.SPRING_ROLE_PREFIX+authInfo.getRole()));
					UsernamePasswordAuthenticationToken authentication = 
	            			new UsernamePasswordAuthenticationToken(authInfo,null, authorities);
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			} catch (AuthException e) {
				ExceptionService exceptionService = (ExceptionService)getBeanFromContext(request,ExceptionService.class);
				String errorId = exceptionService.generateErrorId();
				logger.error(errorId + e);
				response.getWriter().write(new Response<>().technicalError(errorId).toString());
				return;
			} catch(TokenExpiredException e) {
				logger.debug(e);
				response.getWriter().write(new Response<>().authError(Constants.ERROR_CODE_AUTH).toString());
				return;
			}
		}
		filterChain.doFilter(request, response);
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		return !(getBeanFromContext(request, SimplePatternMatcher.class).match(request.getRequestURI()));		
	}
	
	private <T> T getBeanFromContext(HttpServletRequest request, Class<T> clasz) {
		ServletContext servletContext = request.getServletContext();
		WebApplicationContext cc = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		return cc.getBean(clasz);
	}
	
	private String getAccessTokenFromHeader(HttpServletRequest request) {
		String header = request.getHeader(Constants.AUTH_HEADER);
		if (header != null && header.startsWith(Constants.AUTH_TOKEN_PREFIX)) {
			return header.replace(Constants.AUTH_TOKEN_PREFIX, "");
        }
		return null;
	}
	
}
