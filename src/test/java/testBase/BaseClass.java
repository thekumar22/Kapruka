package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Properties prop;
	
	@BeforeClass(groups={"Regression", "Sanity", "Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String browser) throws IOException {
		
		
		//Loading Config.properties File
		
		FileReader file= new FileReader("./src/test/resources/Config.properties");
		prop=new Properties();
		prop.load(file);
		
		
		// Code for Remote Environment (Selenium Grid)
		// Command for running selenium grid on single machine->
		// java -jar selenium-server-4.28.1.jar standalone   
		// standalone means Making same machine as your HUB and NODE 
		
		if(prop.getProperty("executuionEnvironment").equalsIgnoreCase("remote")){
			
			System.out.println("Running on Remote...");
			System.out.println("Hub URL is: " + "http://localhost:4444/wd/hub");

			DesiredCapabilities cap=new DesiredCapabilities();
			
			//Setting Operating System
			if(os.equalsIgnoreCase("mac")) {
				
				cap.setPlatform(Platform.MAC);
				System.out.println("On Remote Environment OS Name is: " + os);

			}
			else if(os.equalsIgnoreCase("windows")) {
				
				cap.setPlatform(Platform.WIN11);
				System.out.println("On Remote Environment OS Name is: " + os);


			}
			else if(os.equalsIgnoreCase("linux")) {
				
				cap.setPlatform(Platform.LINUX);
				System.out.println("On Remote Environment OS Name is: " + os);


			}
			else {
				
				System.out.println("No Matching OS");
				return;
			}	
			

			//Setting Browser
			switch(browser.toLowerCase()){
			
			case "chrome" :	cap.setBrowserName("chrome");
							System.out.println("On Remote Environment Browser Name is: " + browser);
							break;
			
			case "edge" :	cap.setBrowserName("MicrosoftEdge");
							System.out.println("On Remote Environment Browser Name is: " + browser);
							break;

			case "firefox" :cap.setBrowserName("firefox");
							System.out.println("On Remote Environment Browser Name is: " + browser);
							break;
							
			default :		System.out.println("Invalid Browser Name.! Please Enter Valid Input...");
							return;
			}
			
			String hubURL= "http://localhost:4444/wd/hub";
			driver=new RemoteWebDriver(new URL(hubURL),cap);
			
		}
		
		
	
		
		//Code for Local Environment 
		//Launching Browser as per given input for Local System
		
		if(prop.getProperty("executuionEnvironment").equalsIgnoreCase("local")){

			System.out.println("Running on Local...");
			switch(browser.toLowerCase()){
				
				case "chrome" :	driver=new ChromeDriver();
								System.out.println("On Local Environment Browser Name is: " + browser);
								break;
				
				case "edge" :	driver=new EdgeDriver();
								System.out.println("On Local Environment Browser Name is: " + browser);
								break;
	
				case "firefox" :driver=new FirefoxDriver();
								System.out.println("On Local Environment Browser Name is: " + browser);
								break;
								
				default :		System.out.println("Invalid Browser Name.! Please Enter Valid Input...");
								return;
			}
		}
			
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		
		driver.get(prop.getProperty("appURL"));  //Reading APP URL Form Properties File
		driver.manage().window().maximize();
		
			
	}
	
	
	@AfterClass(groups={"Regression", "Sanity", "Master"})
	public void tearDown() {
		
		driver.close();
		System.gc();  
	}
	
	
	
	//Code to Generate Random String with RandomStringUtils Class
		
	public String randomString() {
		
		
		return RandomStringUtils.randomAlphabetic(10);
	}
	
	public String randomNumber() {
		
		
		return RandomStringUtils.randomNumeric(10);
	}


	//Capturing  ScreenShot
	public String captureScreen(String tName) {
		

		String currentDateTimeSpam= new  SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //Picking Current Date and Time in String Variable 
		System.out.println("Current Date & Time is: " + currentDateTimeSpam);

		
		TakesScreenshot takCcreenshot= (TakesScreenshot) driver;
		File sourceFile=takCcreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir") + "/screenshots/" + tName + "_" + currentDateTimeSpam + ".png";
		System.out.println("Target File Path is : " + targetFilePath);
		File targetFile=new File(targetFilePath);
				
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}

}
