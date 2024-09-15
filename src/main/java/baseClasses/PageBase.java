package baseClasses;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import pages.GiftCards;
import pages.GiftCardsCorporate;
import pages.Home;

public class PageBase {

	public static WebDriver driver; 
	public Properties prop;
	Logger log = LogManager.getLogger(PageBase.class);

	
	@FindBy(xpath="//body/div[@data-cy='rootPage']/div/div/div/div[@data-cy='cabsSW']/div/div/div/div/div/div/div/div[@lang='en']/div/div/div[1]/div[1]/div")
	public static WebElement calendarMonthYear;

	@FindBy(xpath="//span[@aria-label='Next Month']")
	public static WebElement nextMonth;

	//This method will invoke the browser(Chrome, Firefox, Edge)
	public void invokeBrowser(String browserName) {

		try {
			// creating object of properties file
			prop = new Properties();
			FileInputStream file = new FileInputStream(System.getProperty("user.dir")
					+ "\\resource\\config.properties");
			prop.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String browserProp = prop.getProperty(browserName);
		try {
			if (browserProp.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "//drivers//chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserProp.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "//drivers//geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browserProp.equalsIgnoreCase("MSEdge")) {
				System.setProperty("webdriver.edge.driver",
						System.getProperty("user.dir") + "//drivers//msedgedriver.exe");
				driver = new EdgeDriver();
			} else {
				System.out.println("Check the given input.Only these browsers are accepted:Chrome/Firefox/Edge");
			}
		} catch (Exception e) {
			log.error("Unable to invoke Driver");
		}
        //Handling Page synchronization issues
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		log.info("Driver invoked successfully");
	}
	
	//method to open MakeMyTrip website
	public Home OpenApplication() {
		try {
			// creating object of properties file
			prop = new Properties();
			FileInputStream file = new FileInputStream(System.getProperty("user.dir")
					+ "\\resource\\config.properties");
			prop.load(file);
			driver.get(prop.getProperty("URL"));
			log.info("Navigated to 'Make My Trip' website");
		}catch(Exception e) {
			log.error("Unable to Open 'Make My Trip' website ");
		}
		//instantiating the Page classes
		return PageFactory.initElements(driver, Home.class);  
	}
	
	//method to verify the page title
	public void getTitle(String expectedTitle) {
		Assert.assertEquals(driver.getTitle(), expectedTitle);
		log.info("Page title Verified");

	}	

	//method to open GiftCards page
	public GiftCards OpenApplication3() {
		driver.get("https://www.makemytrip.com/gift-cards/");
		return PageFactory.initElements(driver, GiftCards.class);
	}


	public GiftCardsCorporate OpenApplication4() {
		driver.get("https://www.makemytrip.com/gift-cards/details/?gcid=59");
		return PageFactory.initElements(driver, GiftCardsCorporate.class);
	}

	public static void dateTimePicker() {


		try {
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, 11);// get next date
			// assigning target values to variables
			int targetDay = calendar.get(Calendar.DAY_OF_MONTH);
			int targetMonth = calendar.get(Calendar.MONTH);
			int targetYear = calendar.get(Calendar.YEAR);
			// identify the title of calendar eg.March 2022 -->convert it in date formate
			// -->assign it to variables
			String actualDate = calendarMonthYear.getText();
			calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));
			int actualMonth = calendar.get(Calendar.MONTH);
			int actualYear = calendar.get(Calendar.YEAR);

			/*** condition if we want to get future dates in calendar ***/
			while (targetMonth > actualMonth || targetYear > actualYear) {
				nextMonth.click();
				actualDate = calendarMonthYear.getText();
				calendar.setTime(new SimpleDateFormat("MMM yyyy").parse(actualDate));

				actualMonth = calendar.get(Calendar.MONTH);
				actualYear = calendar.get(Calendar.YEAR);

			}
			/*
			 * This is customised xpath to get the day in calendar which is inside a
			 * selected month i.e 'targetDay'
			 */
			driver.findElement(By.xpath(
					"//div[@class='datePickerContainer']//div[@class='DayPicker-Month'][1]//div[@class='DayPicker-Body']//div[@class='DayPicker-Day' and text()="+targetDay+"]")).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void tearDown() {
		driver.quit();
	}	
}


