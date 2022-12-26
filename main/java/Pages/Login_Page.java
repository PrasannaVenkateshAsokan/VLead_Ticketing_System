package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Page 
{
	WebDriver driver;
	public Login_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//input[@id=\"username\"]")
	WebElement emp;
	public void Employee_ID_TB(String name)
	{
		emp.clear();
		emp.sendKeys(name);
	}
	
	@FindBy (xpath="//input[@id=\"password\"]")
	WebElement pass;
	public void Password_TB(String i)
	{
		pass.clear();
		pass.sendKeys(i);
	}
	
	@FindBy (xpath="//input[@type=\"submit\"]")
	WebElement sub;
	public void Submit_BTN()
	{
		sub.click();
	}
	
	@FindBy (xpath="//a[contains(text(),'logout')]")
	WebElement lot;
	public void Logout_BTN()
	{
		lot.click();
	}

	@FindBy (xpath="//a[contains(text(),'Register')]")
	WebElement reb;
	public void Register_BTN()
	{
		reb.click();
	}
}
