package Altdrx.Tests;

import Altdrx.Utils.AltWrappers;
import Altdrx.Utils.Reports;
import Altdrx.Utils.SelWrappers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SignUpTest extends SelWrappers {
	SelWrappers se=new SelWrappers();
	AltWrappers aw= new AltWrappers();


	@Test
	public void KYCVerify()
	{
		try
		{
			Reports.setTCDesc("Validating the Browser Launch");
			aw.launchBrowser();
			Reports.setTCDesc("Validating SignUp functionality with valid credentials");
		   aw.brokenLinkAltDrx();
			aw.AltSignUp("sadhu","8529637410","56777", "cicotib507@acroins.com");
			screenshot("signup_Verify");
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
