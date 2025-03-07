package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviderClass;

public class TC003_LoginDDT extends BaseClass{
	
    @Test(dataProvider = "LoginData", dataProviderClass = DataProviderClass.class, groups = {"Regression", "Master"})
	public void verifyLoginDDT(String email, String password, String result) {
		
		HomePage homePage=new HomePage(driver);
		LoginPage loginPage=new LoginPage(driver);
		MyAccountPage myAcc=new MyAccountPage(driver);

		homePage.clickOnMyAccount();
        loginPage.setEmail(email); // ✅ Directly pass email (remove prop.getProperty)
        loginPage.setPassword(password); // ✅ Directly pass password
        loginPage.clickOnLoginButton();
		
		boolean expected=true;
		boolean actual=myAcc.isAccountNameExists();
		
		if(actual==true) {
			
			myAcc.clickOnLogoutButton();
		}
		Assert.assertEquals(actual, expected, "Login Failed...");
	}

}
