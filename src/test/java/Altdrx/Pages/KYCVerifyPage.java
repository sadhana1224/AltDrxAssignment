package Altdrx.Pages;

import Altdrx.Utils.SelWrappers;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KYCVerifyPage extends SelWrappers {


	//click on complete KYC
	@FindBy(xpath = "(//button[@type='submit'])[1]")
	WebElement completebutton;

	//pan number
	@FindBy(xpath = "(//input[@placeholder='PAN Number'])[1]")
	WebElement pannum;

	//verify pan number
	@FindBy(xpath = "//span[contains(text(),'Verify')]")
	WebElement verifyPan;
//aadhar number
	@FindBy(xpath ="(//input[@placeholder='Aadhaar Number'])[1]")
	WebElement aadharnum;



	public void AltdrxKYC(String Pnum, String Anum) throws InterruptedException {
		click(completebutton);
		Thread.sleep(4000);
		typeText(pannum,Pnum);
		click(verifyPan);
		Thread.sleep(4000);
		typeText(aadharnum,Anum);


	}

		
	}

