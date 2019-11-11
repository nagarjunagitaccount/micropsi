package com.api.resources;

public class resources {
	public static String getscoreonebyaddressresource()
	{
		String res="/v1/score/score_one/by_address";
		return res;
		
	}
	
	public static String getfindonebyaddressbybasic()
	{
		String res="/v1/profile/find_one/by_address/basic";
		return res;
		
	}
	public static String getfindonebyaddressbyfull()
	{
		String res="/v1/profile/find_one/by_address/full";
		return res;

	}
	public static String getcreatesession()
	{
		String res="/v1/session/create";
		return res;

	}
	public static String getfindonebyemailbasic()
	{
		String res="/v1/profile/find_one/by_email/basic";

		return res;

	}
	public static String getfindonebyemailfull()
	{
		String res="/v1/profile/find_one/by_email/full";
		return res;

	}
	public static String getfindonebyphonefull()
	{
		String res="/v1/profile/find_one/by_phone/full";
		return res;

	}
	public static String getfindonebyphonebasic()
	{
		String res="/v1/profile/find_one/by_phone/basic";
		return res;

	}

	public static String getsummarybasic()
	{
		String res="/v1/profile/summary/basic";
		return res;

	}
	public static String getsummaryfull()
	{
		String res="/v1/profile/summary/full";
		return res;

	}
	
	
}
