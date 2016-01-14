package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IPConversionServlet extends HttpServlet
{
	private static final long serialVersionUID = -8681116412340516442L;
	 private Pattern pattern;
	    private Matcher matcher;
	 
	    private static final String IPADDRESS_PATTERN = 
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
		  

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException
	{
		//response page starts
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		  pattern = Pattern.compile(IPADDRESS_PATTERN);
		  
		  
		String str = req.getParameter("ipaddress");
		if (str == null || str.length() <= 0) {
			out.println("plesase enter valid IP Address");
		} else if(!validate(str)){
			out.println("plesase enter valid IP Address");
		}else {
			out.println("IP Address to int: " + ipAddressConvert(str));
		}
		
	}
	

		public  long ipAddressConvert(String ipAddress) {

			String[] ipAddressInArray = ipAddress.split("\\.");
			long result = 0;
			for (int i = 0; i < ipAddressInArray.length; i++) {
				int power = 3 - i;
				int ip = Integer.parseInt(ipAddressInArray[i]);
				result += ip * Math.pow(256, power);
			}

			return result;

		}
		
		
		
		   
		    public boolean validate(final String ip){		  
			  matcher = pattern.matcher(ip);
			  return matcher.matches();	    	    
		    }


		

}
