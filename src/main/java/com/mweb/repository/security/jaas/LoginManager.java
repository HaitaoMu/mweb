package com.mweb.repository.security.jaas;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;

public class LoginManager
{
	public static boolean login(HttpServletRequest request)
	{
		try
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");

			LoginContext lc = new LoginContext("Sample",
					new SampleCallbackHandler(username, password));
			lc.login();// 如果验证失败会抛出异常

			return true;
		} catch (LoginException e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
