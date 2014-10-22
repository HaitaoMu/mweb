package com.mweb.repository.security.dao;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticateHandler implements AuthenticationFailureHandler
{

	private static final Log log = LogFactory.getLog(AuthenticateHandler.class);
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException
	{
		log.info("[message]"+exception.getLocalizedMessage());
		HttpSession session = request.getSession();
		session.setAttribute("msg", "please logout first");
		response.sendRedirect("/mweb/login");  
	}

}
