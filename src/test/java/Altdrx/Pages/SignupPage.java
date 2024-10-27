package Altdrx.Pages;

import Altdrx.Utils.Reports;
import Altdrx.Utils.SelWrappers;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignupPage extends SelWrappers {

// signup button
	@FindBy(xpath="(//v-button[@id='signin'])[1]")
	WebElement SignupButton;

	@FindBy(xpath="(//a[contains(text(),'Sign up')])[1]")
	WebElement popSignup;

	//Fullname
	@FindBy(xpath = "//input[@placeholder='Full Name']")
	WebElement name;
/*
	@FindBy(xpath = "((//div[@aria-label='Residential Status'])[1]")
	WebElement resi;
	@FindBy(xpath = "(//div[@aria-label='Resident Indian'])[1]")
	WebElement resiIndian;
	*/

//number
	@FindBy(xpath = "(//input[@placeholder='Mobile Number'])[1]")
	WebElement num;
//otp
	@FindBy(xpath="(//input[@placeholder='Mobile Number OTP'])[1]")
	WebElement otp;

	//mail
	@FindBy(xpath="(//input[@placeholder='Email ID'])[1]")
	WebElement email;
	//checkbox 1
	@FindBy(xpath="(//input[@type='checkbox'])[1]")
	WebElement firstCB;

	@FindBy(xpath="(//input[@type='checkbox'])[2]")
	WebElement secCB;

	public void altdrxSignUp(String fname, String pnum, String Motp, String mail) throws InterruptedException {
		click(SignupButton);
		Thread.sleep(4000);
		click(popSignup);
        Thread.sleep(4000);
		typeText(name,fname);
		//click(resi);
		//click(resiIndian);
		Thread.sleep(4000);
		typeText(num,pnum);

		typeText(otp, Motp);
		typeText(email,mail);

		click(firstCB);
		click(secCB);

	}

		
	}

