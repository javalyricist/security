package com.resource.config;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

public class CustomTokenExtractor implements TokenExtractor {

	@Override
	public Authentication extract(HttpServletRequest request) {
		Enumeration<String> headers = request.getHeaders("Authorization");
		while (headers.hasMoreElements()) { // typically there is only one (most servers enforce that)
			String value = headers.nextElement();
			if ((value.toLowerCase().startsWith(OAuth2AccessToken.BEARER_TYPE.toLowerCase()))) {
				String authHeaderValue = value.substring(OAuth2AccessToken.BEARER_TYPE.length()).trim();
				// Add this here for the auth details later. Would be better to change the
				// signature of this method.
				request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_TYPE,"Bearer");
				request.setAttribute(OAuth2AuthenticationDetails.ACCESS_TOKEN_VALUE,
						authHeaderValue);
		        List<GrantedAuthority> grantedAuths =
		        		AuthorityUtils.createAuthorityList("ROLE_USER");
				if (authHeaderValue != null) {
				    PreAuthenticatedAuthenticationToken authentication = new PreAuthenticatedAuthenticationToken(authHeaderValue, "",grantedAuths);
				    return authentication;
				  }
				
			}
		}

		return null;
	}
	
}