package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {
	
	
	//Constructor:-

	public HomePage(WebDriver driver) {
		
		super(driver);
	}
	
	
	//Finding Locators:- 
	
	@FindBy (xpath="//a[@aria-label='Login to Your Account']//*[name()='svg']")
	WebElement MyAccount;
	
	@FindBy (xpath="//button[normalize-space()='Create Account']")
	WebElement CreateAccount;
	
	
	//Action Methods:-
	
	public void clickOnMyAccount() {
		
		MyAccount.click();
	}
	
	public void clickOnCreateAccount() {
		
	CreateAccount.click();
	}
	
	
}
