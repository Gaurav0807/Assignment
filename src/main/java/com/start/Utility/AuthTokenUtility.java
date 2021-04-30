package com.start.Utility;

import javax.servlet.http.HttpServletRequest;

public class AuthTokenUtility {

	public static void secretTokenCheck(HttpServletRequest request)
	{
	
		String authToken = request.getHeader("authToken");
		if(authToken == null|| authToken.isEmpty())
		{
			System.out.println("token is null or empty");
			return;
		}else if(!authToken.equals("secretValue")) {
			System.out.println("invalid authToken value");
			return;
		}
	}
}
