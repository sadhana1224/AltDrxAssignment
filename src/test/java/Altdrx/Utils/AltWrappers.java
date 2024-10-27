package Altdrx.Utils;

import Altdrx.Pages.*;
import org.openqa.selenium.support.PageFactory;


public class AltWrappers extends SelWrappers {
	SelWrappers se= new SelWrappers();
	
	public void AltSignUp(String name,String num,String otp,String mail) throws InterruptedException
	{

		SignupPage bbPage=PageFactory.initElements(driver, SignupPage.class);
		bbPage.altdrxSignUp(name, num, otp, mail);
			
	}
public void AltLogin(String Num,String Otpp) throws InterruptedException {
	LoginPage bbPage=PageFactory.initElements(driver, LoginPage.class);
	bbPage.altdrxLogin( Num, Otpp);
}

public void  AltKYC(String PanN, String AadNum) throws InterruptedException {
	KYCVerifyPage bbPage=PageFactory.initElements(driver, KYCVerifyPage.class);
	bbPage.AltdrxKYC( PanN, AadNum);
}
}
