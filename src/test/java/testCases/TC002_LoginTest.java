package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups={"Sanity", "Master"})
	public void verifyLogin() {
		
		HomePage homePage=new HomePage(driver);
		LoginPage loginPage=new LoginPage(driver);
		MyAccountPage myAcc=new MyAccountPage(driver);

		homePage.clickOnMyAccount();
		loginPage.setEmail(prop.getProperty("email"));
		loginPage.setPassword(prop.getProperty("password"));
		loginPage.clickOnLoginButton();
		
		boolean expected=true;
		boolean actual=myAcc.isAccountNameExists();
		
		Assert.assertEquals(actual, expected, "Login Failed...");
	}
	
}
