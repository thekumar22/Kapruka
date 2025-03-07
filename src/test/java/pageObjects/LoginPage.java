package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	//Constructor:-
	
	public LoginPage(WebDriver driver) {
		
		super(driver);
	}
	
	
	//Finding Locators:- 
	
	@FindBy (xpath="//input[@id='exampleInputEmail1']")
	WebElement Email;
	
	@FindBy (xpath="//input[@id='exampleInputPassword1']")
	WebElement Password;
	
	@FindBy (xpath="//input[@name='Login']")
	WebElement btnLogin;
	
	
	//Action Methods:-
	
	public void setEmail(String emailAddress) {
		
		Email.clear();
		Email.sendKeys(emailAddress);
	}
	
	public void setPassword(String createPassword) {
		
		Password.clear();
		Password.sendKeys(createPassword);		
	}
	
	public void clickOnLoginButton() {
			
			btnLogin.click();
	}	

}
