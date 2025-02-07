package Altdrx.Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class SelWrappers {

	public static WebDriver driver=null;

	static String browsername;

	@BeforeClass
	@Parameters("browser")
	public void setUp(String browser)
	{

		this.browsername = browser;
	}
	public void launchBrowser()
	{
		try
		{
			if(browsername.equalsIgnoreCase("chrome"))
			{
				ChromeOptions opt=new ChromeOptions();
				opt.addArguments("--disable-notifications");
				opt.addArguments("--remote-allow-origins=*");

				
				driver=new ChromeDriver(opt);
			}
			else if(browsername.equalsIgnoreCase("edge"))
			{
				EdgeOptions opt=new EdgeOptions();
				opt.addArguments("--disable-notifications");
				driver=new EdgeDriver(opt);
			}
			else
			{
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--disable-notifications");
				driver = new FirefoxDriver(options);
			}


			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
			driver.get("https://altdrx.com/");
			Reports.reportStep("PASS", "The chrome browser launched successfully with the given url ("+browsername+")");

		}
		catch(Exception ex)
		{
			System.out.println("Problem in launching the browser");
			Reports.reportStep("FAIL", "Problem while launching the chrome browser with the given url ("+browsername+")");
			ex.printStackTrace();
		}
	}

	//method to close all the browsers
	//@AfterMethod
	public void closeAllBrowsers()
	{
		try
		{
			driver.quit();
			Reports.reportStep("PASS", "Browsers closed successfully");
		}
		catch(Exception ex)
		{
			System.out.println("Problem in closing all the browsers");
			Reports.reportStep("FAIL", "Problem in closing all the browsers");
			ex.printStackTrace();
		}
	}

	//methods to close the current active browser
	public void closeBrowser()
	{
		try
		{
			driver.close();
			Reports.reportStep("PASS", "Browser closed successfully");
		}
		catch(Exception ex)
		{
			System.out.println("Problem in closing the browser");
			Reports.reportStep("FAIL", "Problem in closing the browser");
			ex.printStackTrace();
		}
	}
	//sendkeys	
	public void typeText(WebElement ele, String input)
	{
		try
		{
			waitForMe(ele,20);
			ele.clear();
			ele.sendKeys(input);
			Reports.reportStep("PASS", "Entered the given text ("+input+") successfully ");

		}
		catch(Exception ex)
		{
			System.out.println("problem in sendkeys method");
			Reports.reportStep("FAIL", "Problem while entering the given text ("+input+")");

			ex.printStackTrace();
		}

	}
	//click 
	public boolean click(WebElement ele)
	{
		try
		{
			//waitForMe(ele,20);
			ele.click();
		//Reports.reportStep("PASS", "Clicked on the given webelement successfully ");
			

		}
		catch(Exception ex)
		{
			System.out.println("problem in click option");
			Reports.reportStep("FAIL", "Problem while clicking on the webElement ");
			Reports.reportStep("FAIL", ex.toString());

			ex.printStackTrace();
			return false;

		}
		return true;
	}
	//navigate forward
	public void navigateforward()
	{
		try
		{
			driver.navigate().forward();
		}
		catch(Exception ex)
		{
			System.out.println("problem in navigate forward");
			ex.printStackTrace();
		}
	}
	//navigate back
	public void navigateBack()
	{
		try
		{
			driver.navigate().back();
			Reports.reportStep("PASS", "Navigate back successfully");
		}
		catch(Exception ex)
		{
			System.out.println("problem in navigate back");
			Reports.reportStep("FAIL", "Problem while Navigating back ");
			ex.printStackTrace();
		}
	}
	//navigate refresh
	public void navigateRefresh()
	{
		try
		{
			driver.navigate().refresh();
		}
		catch(Exception ex)
		{
			System.out.println("problem in navigate refresh");
			ex.printStackTrace();
		}
	}


	//explicit wait
	public void waitForMe(WebElement ele, int timeout)
	{
		try
		{
			WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(ele));


		}
		catch(Exception ex)
		{
			System.out.println("problem in explicit wait");
			ex.printStackTrace();
		}
	}
	//explicit wait
	public void waitForMe1(WebElement ele, int timeout)
	{
		try
		{
			WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.elementToBeClickable(ele));


		}
		catch(Exception ex)
		{
			System.out.println("problem in explicit wait");
			ex.printStackTrace();
		}
	}
	//fluent wait
	public void pollingWait(WebElement ele, int timeout, int pollingFrequency)
	{
		try
		{
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(timeout)) 
					.pollingEvery(Duration.ofSeconds(pollingFrequency));
			wait.until(ExpectedConditions.visibilityOf(ele));

		}
		catch(Exception ex)
		{
			System.out.println("problem in fluent wait");
			ex.printStackTrace();
		}
	}

	//selectby visibletext
	public void selectByVisibleText(WebElement ele, String visibleText)
	{
		try
		{
			Select sel = new Select(ele);
			sel.selectByVisibleText(visibleText);
			
		}
		catch(Exception ex)
		{
			System.out.println("problem in selectByVisible text");
			ex.printStackTrace();
		}

	}

	//SelectbyIndex
	public void selectByIndex(WebElement ele, int value)
	{
		try
		{
			Select sel = new Select(ele);
			sel.selectByIndex(value);
			Reports.reportStep("PASS", "Successfully SelectByIndex value ");
		}
		catch(Exception ex)
		{
			System.out.println("problem in selectBy index");
			Reports.reportStep("FAIL", "Problem in SeleceByIndex ");
			ex.printStackTrace();
		}
	}
	//SelectByValue
	public void selectByValue(WebElement ele, String value)
	{
		try
		{
			Select sel = new Select(ele);
			sel.selectByValue(value);
		}
		catch(Exception ex)
		{
			System.out.println("problem in selectBy value");
			ex.printStackTrace();
		}
	}
	//acceptAlert
	public void acceptAlert()
	{
		try
		{
			Alert promptAlert= driver.switchTo().alert();
			promptAlert.accept();
		}
		catch(Exception ex)
		{
			System.out.println("problem in accept alert");
			ex.printStackTrace();
		}
	}
	//dismissAlert
	public void dismissAlert()
	{
		try
		{
			Alert promptAlert= driver.switchTo().alert();
			promptAlert.dismiss();
		}
		catch(Exception ex)
		{
			System.out.println("problem in dismiss alert");
			ex.printStackTrace();
		}
	}
	//getTxtFromAlert
	public void getTextmethod()
	{
		try
		{
			Alert alert= driver.switchTo().alert();

			String alertText=alert.getText();
			System.out.println(alertText);

		}
		catch(Exception ex)
		{
			System.out.println("problem in getText method");
			ex.printStackTrace();
		}
	}
	//typeTextInAlert
	public void typeTextmethod(String text)
	{
		try
		{
			Alert alert= driver.switchTo().alert();
			alert.sendKeys(text);
			alert.accept();

		}
		catch(Exception ex)
		{
			System.out.println("problem in typeText in alert");
			ex.printStackTrace();
		}
	}

	//Frames
	public void frameByWebElement(WebElement ele)
	{
		try
		{
			driver.switchTo().frame(ele);

		}
		catch(Exception ex)
		{
			System.out.println("problem in frames");
			ex.printStackTrace();
		}
	}
	//FrameByIndex
	public void frameByIndex(int frame)
	{
		try
		{
			driver.switchTo().frame(frame);

		}
		catch(Exception ex)
		{
			System.out.println("problem in frameBy index");
			ex.printStackTrace();
		}
	}

	//FrameByName
	public void frameByname(String nameOrId)
	{
		try
		{
			driver.switchTo().frame(nameOrId);

		}
		catch(Exception ex)
		{
			System.out.println("problem in frameBy name");
			ex.printStackTrace();
		}
	}
	//isSelected method	
	public boolean verifySelected(WebElement ele)
	{
		boolean retVal=false;
		try
		{
			if(ele.isSelected())
			{
				retVal=true;
				System.out.println("Element is selected");
			}
			else
			{
				retVal=false;
				System.out.println("Element is not selected");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return retVal;
	}

	//isEnabled
	public boolean verifyEnabled(WebElement ele)
	{
		boolean	retVal=false;
		try
		{
			if(ele.isEnabled())
			{
				retVal=true;
				System.out.println("element is enabled");	
			}
			else
			{
				retVal=false;
				System.out.println("element is not enabled");
			}

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return retVal;
	}
	//isDisplayed
	public boolean verifyDisplayed(WebElement ele)
	{
		boolean	retVal=false;
		try
		{
			if(ele.isDisplayed())
			{
				retVal=true;
				System.out.println("element is displayed");	
			}
			else
			{
				retVal=false;
				System.out.println("element is not displayed");
			}

		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return retVal;
	}
	//action-click
	public void actionClick(WebElement ele)
	{
		try
		{
			Actions act = new Actions(driver);
			act.click(ele).build().perform();
			Reports.reportStep("PASS", "Action click Successfully ");

		}
		catch(Exception ex)
		{
			System.out.println("problem in action click");
			Reports.reportStep("FAIL", "Problem in Action Click ");

			ex.printStackTrace();
		}
	}
	//right click	
	public void rightClick(WebElement ele)
	{
		try
		{
			Actions act = new Actions(driver);
			act.contextClick(ele).build().perform();
		}
		catch(Exception ex)
		{
			System.out.println("problem in rightclick method");
			ex.printStackTrace();
		}
	}
	//dragAndDrop
	public void dragAndDrop(WebElement source, WebElement target)
	{
		try
		{
			Actions act = new Actions(driver);
			act.dragAndDrop(source, target).build().perform();
		}
		catch(Exception ex)
		{
			System.out.println("problem in drag and drop method");
			ex.printStackTrace();
		}
	}

	//clickAndHold
	public void clickAndhold(WebElement ele)
	{
		try
		{
			Actions act=new Actions(driver);
			act.clickAndHold(ele).build().perform();
		}
		catch(Exception ex)
		{
			System.out.println("problem in click And Hold method");
			ex.printStackTrace();
		}
	}
	//docubleClick
	public void doubleClick(WebElement ele)
	{
		try
		{
			Actions act=new Actions(driver);
			act.doubleClick(ele).build().perform();
			Reports.reportStep("PASS", "DoubleClicked on the given webelement successfully ");
		}
		catch(Exception ex)
		{
			System.out.println("problem in doubleclick method");
			Reports.reportStep("FAIL", "Problem while Doubleclicking on the webElement ");
			ex.printStackTrace();
		}
	}
	//moveToElement
	public void MoveToelement(WebElement ele)
	{
		try
		{
			Actions act=new Actions(driver);
			act.moveToElement(ele).build().perform();
		}
		catch(Exception ex)
		{
			System.out.println("problem in moveToElement method");
			ex.printStackTrace();
		}
	}
	//Keyboard actions
	public void keyboardActions(WebElement ele,String text)
	{
		try
		{
			Actions act=new Actions(driver);
			act.keyDown(ele,Keys.SHIFT).sendKeys(text).perform();
		}
		catch(Exception ex)
		{
			System.out.println("problem in keyBoard actions");
			ex.printStackTrace();
		}
	}
	//key board 
	public void enterKeyword(WebElement ele,String text)
	{
		try
		{
			Actions act=new Actions(driver);
			act.keyDown(ele,Keys.ENTER).sendKeys(text).perform();
		}
		catch(Exception ex)
		{
			System.out.println("problem in keyBoard actions");
			ex.printStackTrace();
		}
	}

	//javascriptexecutor--vertical scroll
	public void jsVerticalScroll(int y)
	{
		try
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0,"+y+";)");
			Reports.reportStep("PASS", " JavascriptExecutor vertical scroll Success ");


		}
		catch(Exception ex)
		{
			System.out.println("problem in javascript executor vertical scroll");
			Reports.reportStep("FAIL", "Problem in JavascriptExecutor vertical scroll ");

			ex.printStackTrace();
		}
	}
	//horizontalscroll	
	public void jsHorizontalScroll(int x)
	{
		try
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo("+x+",0);");

		}
		catch(Exception ex)
		{
			System.out.println("problem in javascript executor horizontal scroll");
			ex.printStackTrace();
		}
	}
	//scroll down to the bottom of the page
	public void jsscrollDownBottom()
	{
		try
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
			Reports.reportStep("PASS", " JavascriptExecutor ScrollDown to the Bottom of the Page Success ");


		}
		catch(Exception ex)
		{
			System.out.println("problem in  scroll down to the bottom of the page");
			Reports.reportStep("FAIL", "Problem in JavascriptExecutor ScrollDown to the Bottom of the Page ");

			ex.printStackTrace();
		}
	}
	//scroll to the top of the page
	public void jsScrollUp()
	{
		try
		{
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0,0);");
		}
		catch(Exception ex)
		{
			System.out.println("problem in  scroll  to the top of the page");
			ex.printStackTrace();
		}
	}
	//jsClick
	public void jsClick(WebElement ele)
	{
		try
		{
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();",ele);
			Reports.reportStep("PASS", " JavascriptExecutor Click Success ");

		}
		catch(Exception ex)
		{
			System.out.println("problem in javascript executor click method");
			Reports.reportStep("FAIL", "Problem in  JavascriptExecutor Click  ");

			ex.printStackTrace();
		}
		
	}
	//jstitle
	public void jsTitle()
	{
		try
		{
			JavascriptExecutor js=(JavascriptExecutor)driver;
			System.out.println(js.executeScript("return document.title();"));

		}
		catch(Exception ex)
		{
			System.out.println("problem in javascript executor title method");
			ex.printStackTrace();
		}
	}
	//typeTextwithout snedkeys
	public void typewithoutSendKeys(WebElement ele,String text)
	{
		try
		{
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].value='+text+';",ele);
		}
		catch(Exception ex)
		{
			System.out.println("problem in javascriptexecutor typewithout sendkeys method");
			ex.printStackTrace();
		}
	}
	//scrollintoview
	public void scrollintoView(WebElement editt)
	{
		try
		{
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true);",editt);
		}
		catch(Exception ex)
		{
			System.out.println("problem in scrollIntoview method");
			ex.printStackTrace();
		}
	}

	//window handling

	public void switchWindows()
	{
		try
		{
			String parentWindow= driver.getWindowHandle();
			Set<String> allWindows= driver.getWindowHandles();

			for(String eachWindow:allWindows)
			{
				if(! parentWindow.equals(eachWindow))
				{
					driver.switchTo().window(eachWindow);
				}
			}
			Reports.reportStep("PASS", "Successfully switched the window   ");


		}
		catch(Exception ex)
		{
			System.out.println("problem in window handling");
			Reports.reportStep("FAIL", "Problem in  Window handling  ");

			ex.printStackTrace();
		}
	}
	//get Title
	public String getTitle()
	{
		String title="";
		try
		{
			title=driver.getTitle();
			System.out.println(title);
			Reports.reportStep("PASS", "Successfully get the Title   ");

		}
		catch(Exception ex)
		{
			System.out.println("problem in getTitle method");
			Reports.reportStep("FAIL", "problem in get Title ");

			ex.printStackTrace();
		}
		return title;
	}
	//getCurrentUrl
	public void getCurrentUrl()
	{
		try
		{
			String currentUrl=driver.getCurrentUrl();
			System.out.println(currentUrl);
		}
		catch(Exception ex)
		{
			System.out.println("problem in getCurrent Url method");
			ex.printStackTrace();
		}
	}
	//screenshots
	public void screenshot(String screenshotName)
	{
		try
		{
//			File source= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//			File dest= new File(System.getProperty("user.dir")+"/screenshots/"+screenshotName+".png");
//			FileUtils.copyFile(source, dest);	
			Thread.sleep(4000);
			Reports.takeScreeenShot(driver,screenshotName);
			Reports.reportStep("PASS", "Successfully take the Screenshot");

		}
		catch(Exception ex)
		{
			Reports.reportStep("FAIL", "problem in Screenshot");

			ex.printStackTrace();
		}
	}
	
	//calender
	public void calender(String month1,String year1,String date1)
	{
		try
		{
			WebElement ele=driver.findElement(By.xpath(" //div[contains(@data-testid,'month-"+month1+"-"+year1+"')]//div[@data-testid='undefined-calendar-day-"+date1+"']/div"));
			ele.click();
		}
		catch(Exception ex) 
		{
			ex.printStackTrace();
		}
	}
	//click country()
	public void chooseCountry()
	{
		try
		{
		WebElement ele1=driver.findElement(By.xpath("//div[@class='country-selection']//h4[contains(text(),'United States')]"));
		ele1.click();
		Reports.reportStep("PASS", "Successfully click the United States Country");

		}
		catch(Exception ex)
		{
			Reports.reportStep("FAIL", "Problem while choosing the country");

			ex.printStackTrace();
		}
	}

//broken link
	public void brokenLinkAltDrx()
	{
		 String urlToCheck = "https://altdrx.com/";

	        try {
	            URL url = new URL(urlToCheck);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("HEAD");
	            int responseCode = connection.getResponseCode();

	            if (responseCode != HttpURLConnection.HTTP_OK) {
	                System.out.println("The URL '" + urlToCheck + "' is broken.");
					Reports.reportStep("FAIL", "Successfully validate the given URL link is broken or not");

	            } else {
	                System.out.println("The URL '" + urlToCheck + "' is not broken.");
					Reports.reportStep("PASS", "Successfully validate the given URL link is broken or not");

	            }
 
	        } 
	        catch (IOException e) {
				Reports.reportStep("FAIL", "Problem while validating the given URL");

	            System.out.println(e);
	        }
	}
}
