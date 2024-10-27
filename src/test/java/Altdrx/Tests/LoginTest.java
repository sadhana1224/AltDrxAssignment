package Altdrx.Tests;

import Altdrx.Utils.AltWrappers;
import Altdrx.Utils.Reports;
import Altdrx.Utils.SelWrappers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class LoginTest extends SelWrappers {
	SelWrappers se=new SelWrappers();
	AltWrappers aw= new AltWrappers();


	@Test
	public void LoginPage()
	{
		try
		{
			Reports.setTCDesc("Validating the Browser Launch");
			aw.launchBrowser();
			Reports.setTCDesc("Validating SignUp functionality with valid credentials");
		   aw.brokenLinkAltDrx();
			aw.AltLogin("6382622724","56777");
			screenshot("Login_Verify");
		}
		catch(Exception ex)
		{
			Reports.reportStep("FAIL", "SignUp failed");
			ex.printStackTrace();
		}	
	}
	@AfterMethod
	public void closeBrowsers()
	{
		closeAllBrowsers();
	}
	

}
