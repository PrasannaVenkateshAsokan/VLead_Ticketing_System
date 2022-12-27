package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Create_New_Ticket 
{
	WebDriver driver;
	public Create_New_Ticket(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//a[contains(text(),'Create New')]")
	WebElement cnb;
	public void Create_New_BTN()
	{
		cnb.click();
	}

	public void Issue(String iss)
	{
		Select dropdown = new Select(driver.findElement(By.xpath("//select[@id=\"IssuesId\"]"))); 
		dropdown.selectByVisibleText(iss);  
	}

	@FindBy(xpath="//input[@id=\"BayNumber\"]")
	WebElement bnm;
	public void Bay_Number(String bay)
	{
		bnm.sendKeys(bay);
	}

	@FindBy(xpath="//input[@id=\"subject\"]")
	WebElement sub;
	public void Subject(String sjb)
	{
		sub.sendKeys(sjb);
	}

	@FindBy(xpath="//input[@id=\"content\"]")
	WebElement con;
	public void Content(String ctn)
	{
		con.sendKeys(ctn);
	}

	public void Priority(String pro)
	{
		Select dropdown1 = new Select(driver.findElement(By.xpath("//select[@id=\"ticketPriority\"]"))); 
		dropdown1.selectByVisibleText(pro);  
	}

	public void Upload_File()
	{
		WebElement chooseFile = driver.findElement(By.xpath("//input[@id=\"FileContent\"]"));
		chooseFile.sendKeys("D:\\Vlead Ticketing System\\Notes.txt");
	}

	@FindBy(xpath="//input[@id=\"submit\"]")
	WebElement sub1;
	public void Submit_BTN()
	{
		sub1.click();;
	}
}

// Example
