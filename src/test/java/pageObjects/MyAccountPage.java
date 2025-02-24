package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	//Constructor:-

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	
	//Finding Locators:- 
	
	@FindBy (xpath="//h1[@class='acc_name acN']")
	WebElement AccountName;
	
	@FindBy (xpath="")
	WebElement abc;
	
	
	//Action Methods:-
	
	public boolean isAccountNameExists() {
		
		try {
			
			System.out.println("Account Name Display Status is : "+ AccountName.isDisplayed());
			return AccountName.isDisplayed();	
		}
		catch(Exception e) {
			
			return false;
		}
	}

}
