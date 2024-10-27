package Altdrx.Pages;

import Altdrx.Utils.SelWrappers;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends SelWrappers {
	// signup button
	@FindBy(xpath="(//v-button[@id='signin'])[1]")
	WebElement SignupButton;

@FindBy(xpath = "//input[@id='phno']")
WebElement phnum;

//send otp
	@FindBy(xpath ="(//button[@type='submit'])[1]")
	WebElement otpButton;
//enter otp
	@FindBy(xpath = "//input[@placeholder='Enter OTP']")

	WebElement otp;
	//verify Otp
	@FindBy(xpath = "//button[@id='verifyCheck']")
	WebElement verifyB;

	public void altdrxLogin(String Num,String Otpp) throws InterruptedException {
		click(SignupButton);
		typeText(phnum,Num);
		Thread.sleep(4000);
		click(otpButton);
        Thread.sleep(9000);
		typeText(otp,Otpp);

		click(verifyB);


	}

		
	}

