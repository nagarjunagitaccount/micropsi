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
	public static String getellucian()
	{
		String res="/v1/profile/find_one/ellucian/full";
		return res;

	}
	public static String getscoreonebyaddress()
	{
		String res="/v1/score/score_one/by_address";
		return res;

	}
	public static String getscoreonebyemail()
	{
		String res="/v1/score/score_one/by_email";
		return res;

	}
	public static String getscoreonebyphone()
	{
		String res="/v1/score/score_one/by_phone";
		return res;

	}
	public static String getscorebyprofile()
	{
		String res="/v1/score/profile";
		return res;

	}
	public static String getscorebyprofiles()
	{
		String res="/v1/score/profiles";
		return res;

	}
	public static String getfindmanybasic()
	{
		String res="/v1/profile/find_many/basic";
		return res;

	}
	public static String getfindmanyfull()
	{
		String res="/v1/profile/find_many/full";
		return res;

	}


	public static String getjobstatus()
	{
		String res="/v1/job/status/{batch_id}";
		return res;

	}

	public static String getfindmanyresults()
	{
		String res="/v1/profile/find_many/results/{batch_id}";
		return res;

	}
	public static String getscoreprofileresults()
	{
		String res="/v1/score/profiles/{batch_id}";
		return res;
	}
}
