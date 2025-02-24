package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DataSourceResolver;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String reportName;
	
	public void onStart(ITestContext testContext) {
		
		/*
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date date=new Date();
		String currentDateTimeSpam= df.format(date);
		*/
		
		String currentDateTimeSpam= new  SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //Picking Current Date and Time in String Variable 
		System.out.println("Current Date & Time is: " + currentDateTimeSpam);
		
		//Configuration 
		
		reportName= "Automation Test Report on- " + currentDateTimeSpam + ".html";
		System.out.println("Report Name is: "+ reportName);
		
        String reportPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + reportName;
        sparkReporter = new ExtentSparkReporter(reportPath);  // Location of Report
        System.out.println("Report Path: " + reportPath);


		sparkReporter.config().setDocumentTitle("Kapruka Automation Testing Report"); //setting Title of Report Document
		sparkReporter.config().setReportName("Kapruka Functional Testing"); //Setting Report Name
		sparkReporter.config().setTheme(Theme.DARK); //Setting Report Theme
		
		
		//Setting Project related information will be shown on Report
		
		extent= new ExtentReports();
		extent.attachReporter(sparkReporter);  
		System.out.println("Report Attached...");

		extent.setSystemInfo("Application", "Kapruka");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		
		//Getting and Setting Operating System Information
		String os= testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);

		//Getting and Setting Browser Information
		String browser= testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		
		//Getting and Setting Group Names Information on Report
		
		try {
			
			List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();
			if(!includeGroups.isEmpty()) {
				
				extent.setSystemInfo("Groups", includeGroups.toString());
				System.out.println("Group Name Checked...");
			}	
			
		}catch(Exception e) {
			
			e.printStackTrace();

		}
		
		System.out.println("On Start Fininshed");

	}
	
	
	public void onTestSuccess(ITestResult result) {
		
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " Got Successfully Executed.!");
		
		
		System.out.println("In Success");

	}
	
	
	public void onTestFailure(ITestResult result) {
		
		test= extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName() + " Got Failed...");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		//Capturing Screenshot of failed test cases and attaching to the report
		try {
			
			String imgPath=new BaseClass().captureScreen(result.getName());
			System.out.println("Image Path is: "+ imgPath);
			test.addScreenCaptureFromPath(imgPath);
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		
		test= extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+ " Got Skipped!!!");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
	}
		
	
	public void onFinish(ITestContext testContext) {
		
		
		extent.flush();
		
		//Opening Report Automatically
		String extentReportPath=System.getProperty("user.dir") + "//reports//" +reportName;
		File extentReport=new File(extentReportPath);
		
		try {
			
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		/*
		//Sending Report on E-Mail Automatically
		try {
			
			URL url=new URL("file:'///" + System.getProperty("user.dir"+"\\reports"+ reportName));
			
			//Create the E-Mail Message
			ImageHtmlEmail email= new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.google.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("thekumarchavan@gmail.com", "Kumar@0125"));
			email.setSSLOnConnect(true);
			email.setFrom("thekumarchavan@gmail.com"); //Sender
			email.setSubject("Test Report by QA Kumar");
			email.setMsg("Please find attached testing repot.!");
			email.addTo("rukhsartamboli11@gmail.com"); //Receiver
			email.attach(url, "Extent Report", "Please Check Report");
			email.send(); //Send the E-Mail
			
			System.out.println("E-Mail Sent Successfully.!");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		*/
	}
}
