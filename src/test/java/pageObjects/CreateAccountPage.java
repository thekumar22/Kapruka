package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateAccountPage extends BasePage{

	
	//Constructor:-
	
	public CreateAccountPage(WebDriver driver) {
		super(driver);

	}
	
	
	//Finding Locators:-
	
	@FindBy (xpath="//input[@name='firstName']")
	WebElement FirstName;
	
	@FindBy (xpath="//input[@name='lastName']")
	WebElement LastName;
	
	@FindBy (xpath="//input[@name='email']")
	WebElement EmailAddress;
;
	
	@FindBy (xpath="//input[@name='password']")
	WebElement CreatePassword;
;
	
	@FindBy (xpath="//input[@name='passwordReConfirm']")
	WebElement ConfirmPassword;
	
	@FindBy (xpath="//button[normalize-space()='Create Account']")
	WebElement btnCreateAccount;

	@FindBy (xpath="//h3[normalize-space()='Congratulations! Your account has been created.']")
	WebElement msgConfirmation;
	
	
	
	
	//Action Methods:-
	
	public void setFirstName(String firstName) {
		FirstName.sendKeys(firstName);
		
	}
	
	public void setLastName(String lastName) {
		
		LastName.sendKeys(lastName);	
	}
	
	public void setEmailAddress(String emailAddress) {
		
		EmailAddress.sendKeys(emailAddress);
	}
	
	public void setCreatePassword(String createPassword) {
		
		CreatePassword.sendKeys(createPassword);		
	}
	
	public void setConfirmPassword(String confirmPassword) {
		
		ConfirmPassword.sendKeys(confirmPassword);
	}
	
	public void clickOnCreateAccountButton() {
			
			btnCreateAccount.click();
	}
	
	
	
	public String getConfirmationMsg() {
		
		
		try {
			
			String msg=msgConfirmation.getText();
			System.out.println("Confirmation Message: " + msg);
			return msg;
		}
		catch(Exception e) {
			
			
			return e.getMessage();
		}
	}

}
