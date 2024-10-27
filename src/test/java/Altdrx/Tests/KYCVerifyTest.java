package Altdrx.Tests;

import Altdrx.Utils.AltWrappers;
import Altdrx.Utils.Reports;
import Altdrx.Utils.SelWrappers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class KYCVerifyTest extends SelWrappers {
	SelWrappers se=new SelWrappers();
	AltWrappers aw= new AltWrappers();


	@Test
	public void KycPage()
	{
		try
		{
			//Reports.setTCDesc("Validating the Browser Launch");
			//aw.launchBrowser();
		//	Reports.setTCDesc("Validating SignUp functionality with valid credentials");
		//   aw.brokenLinkAltDrx();
			aw.AltKYC("JHgsd65654h","65498465465");
			screenshot("KYC_Verify");
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
