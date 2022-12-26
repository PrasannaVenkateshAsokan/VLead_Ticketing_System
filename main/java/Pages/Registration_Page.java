package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class Registration_Page 
{
	WebDriver driver;
	public Registration_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[contains(text(),'Register')]")
	WebElement rgp;
	public void Registration_BTN()
	{
		rgp.click();
	}

	@FindBy (xpath="//input[@id=\"EmployeeId\"]")
	WebElement Emp;
	public void Employee_ID(String emi)
	{
		Emp.sendKeys(emi);
	}

	@FindBy (xpath="//input[@id=\"Firstname\"]")
	WebElement frn;
	public void First_Name(String fir)
	{
		frn.sendKeys(fir);
	}

	@FindBy (xpath="//input[@id=\"Lastname\"]")
	WebElement lsn;
	public void Last_Name(String lna)
	{
		lsn.sendKeys(lna);
	}

	public void Department(String dept)
	{
		Select dropdown = new Select(driver.findElement(By.xpath("//select[@id=\"DepartmentsId\"]"))); 
		dropdown.selectByVisibleText(dept);  
	}

	@FindBy (xpath="//input[@id=\"Email\"]")
	WebElement mail;
	public void email(String ema)
	{
		mail.sendKeys(ema);
	}

	@FindBy (xpath="//input[@id=\"phoneno\"]")
	WebElement phn;
	public void Phone_Number(String pnu)
	{
		phn.sendKeys(pnu);
	}

	@FindBy (xpath="//input[@id=\"Password\"]")
	WebElement pass;
	public void Password(String pwe)
	{
		pass.sendKeys(pwe);
	}

	@FindBy (xpath="//input[@id=\"Confirm_Password\"]")
	WebElement pass2;
	public void Confirm_Password(String pw2)
	{
		pass2.sendKeys(pw2);
	}

	@FindBy (xpath="//input[@type=\"submit\"]")
	WebElement subt;
	public void Submit_BTN()
	{
		subt.click();
	}

	@FindBy (xpath="//a[contains(text(),'Login')]")
	WebElement log12;
	public void Login_BTN()
	{
		log12.click();
	}

	//Asserting User Name
	@FindBy (xpath="//span[contains(text(),'EmployeeID Already Exists')]")
	WebElement asrt;
	public void User_Name_Assert()
	{
		asrt.getText();
		System.out.println("+++++"+asrt+"+++++");
		
	}
	
	//Asserting Email ID
		@FindBy (xpath="//span[contains(text(),'EmailID Already Exists')]")
		WebElement asr;
		public void Email_Assert()
		{
			asr.getText();
			System.out.println("+++++"+asr+"+++++");
			
		}
}
