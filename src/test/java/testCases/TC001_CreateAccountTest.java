	package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CreateAccountPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_CreateAccountTest extends BaseClass{
	
	
	HomePage homePage;
	CreateAccountPage createAccountPage;
	
	
	@Test (groups={"Regression", "Master"})
	public void verifyAccoountCreation() {
		
		homePage=new HomePage(driver);
		homePage.clickOnMyAccount();
		homePage.clickOnCreateAccount();
		
		createAccountPage= new CreateAccountPage(driver);
		
		createAccountPage.setFirstName(randomString().toUpperCase());
		createAccountPage.setLastName(randomString().toUpperCase());
		createAccountPage.setEmailAddress(randomString() + "@gmail.com");
		
		String Password="abcd@1234";
		createAccountPage.setCreatePassword(Password);
		createAccountPage.setConfirmPassword(Password);
		createAccountPage.clickOnCreateAccountButton();
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

		
		String msgActual=createAccountPage.getConfirmationMsg();
		String msgExpected= "Congratulations! Your account has been created.";
		
		Assert.assertEquals(msgActual, msgExpected);		
	}

	
	
	
	
	
}
