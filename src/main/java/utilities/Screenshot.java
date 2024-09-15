package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import baseClasses.PageBase;

public class Screenshot extends PageBase {

	// constructor
	public Screenshot(WebDriver driver) {
		super();
	}

	//static Logger log = Logger.getLogBuilder(null);
	private static final Logger logger = (Logger) LogManager.getLogger(Screenshot.class);
	
	// take screenshot
	public static void takeScreenshot() {
		// including datetime stamp to avoide overwrithg of screenshots
		Date currentDate = new Date();
		String screenshotfilename = currentDate.toString().replace(" ", "-").replace(":", "-");
		TakesScreenshot screenshotFile = ((TakesScreenshot) driver);
		File source = screenshotFile.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\screenshots\\Screenshot" + screenshotfilename + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(source, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Screenshot of Date-Time Captured");
	}
}
