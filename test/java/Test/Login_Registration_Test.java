package Test;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Pages.Login_Page;
import Pages.Registration_Page;

public class Login_Registration_Test 
{
	WebDriver driver; FileInputStream fin; XSSFWorkbook wb; XSSFSheet sh;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	LocalDateTime now = LocalDateTime.now(); 

	@BeforeTest
	public void Launching_Browser() 
	{
		System.setProperty("webdriver.chrome.driver","D:\\ChromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://172.16.47.91/");
	}
	@Test(enabled=false)
	public void Login_Test() throws InterruptedException, IOException
	{	   
		fin = new FileInputStream("D:\\Vlead Ticketing System\\Vlead_inputs2.xlsx"); wb = new XSSFWorkbook(fin); sh = wb.getSheetAt(0);
		int last_row =sh.getLastRowNum();	
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Login_Page LP = new Login_Page(driver);
		for(int i=1; i<=last_row;i++)
		{
			LP.Employee_ID_TB(sh.getRow(i).getCell(0).getStringCellValue());
			String x = (sh.getRow(i).getCell(1).getRawValue().toString());
			LP.Password_TB(x);
			LP.Submit_BTN();
			if(driver.getPageSource().contains("user name doesn't exist"))
			{
				sh.getRow(i).createCell(3).setCellValue("Incorrect User_ID");
				sh.getRow(i).createCell(4).setCellValue(dtf.format(now));
				FileOutputStream o =new FileOutputStream(new File("D:\\Vlead Ticketing System\\Vlead_inputs2.xlsx"));
				wb.write(o);	o.close();
			}
			else if(driver.getPageSource().contains("password does not match"))
			{
				sh.getRow(i).createCell(3).setCellValue("Incorrect Password");
				sh.getRow(i).createCell(4).setCellValue(dtf.format(now));
				FileOutputStream o =new FileOutputStream(new File("D:\\Vlead Ticketing System\\Vlead_inputs2.xlsx"));
				wb.write(o);	o.close();
			}
			else
			{
				LP.Logout_BTN();
				sh.getRow(i).createCell(3).setCellValue("Login Successful");
				sh.getRow(i).createCell(4).setCellValue(dtf.format(now));
				FileOutputStream o =new FileOutputStream(new File("D:\\Vlead Ticketing System\\Vlead_inputs2.xlsx"));
				wb.write(o);	o.close();
			}
		}
	}

	@Test(enabled=false)
	public void Registration_Page_Test() throws IOException
	{
		fin = new FileInputStream("D:\\Vlead Ticketing System\\Vlead_inputs2.xlsx"); wb = new XSSFWorkbook(fin); sh = wb.getSheetAt(1);
		int last_row =sh.getLastRowNum();	
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Registration_Page RPG = new Registration_Page(driver);
		for(int i=1; i<=last_row;i++)
		{
			RPG.Registration_BTN();
			RPG.Employee_ID(sh.getRow(i).getCell(0).getStringCellValue());
			RPG.First_Name(sh.getRow(i).getCell(1).getStringCellValue());
			RPG.Last_Name(sh.getRow(i).getCell(2).getStringCellValue());
			RPG.Department(sh.getRow(i).getCell(3).getStringCellValue()); 
			RPG.email(sh.getRow(i).getCell(4).getStringCellValue());
			String y = (sh.getRow(i).getCell(5).getRawValue().toString());
			RPG.Phone_Number(y);
			String z = (sh.getRow(i).getCell(6).getRawValue().toString());
			RPG.Password(z); 
			RPG.Confirm_Password(z);
			RPG.Submit_BTN();
			if(driver.getPageSource().contains("EmployeeID Already Exists"))
			{
				RPG.Login_BTN();
				sh.getRow(i).createCell(7).setCellValue("EmployeeID Already Exists");
				sh.getRow(i).createCell(8).setCellValue(dtf.format(now));
				FileOutputStream o =new FileOutputStream(new File("D:\\Vlead Ticketing System\\Vlead_inputs2.xlsx"));
				wb.write(o);	o.close();
			}
			else if(driver.getPageSource().contains("EmailID Already Exists"))
			{
				RPG.Login_BTN();
				sh.getRow(i).createCell(7).setCellValue("EmailID Already Exists");
				sh.getRow(i).createCell(8).setCellValue(dtf.format(now));
				FileOutputStream o =new FileOutputStream(new File("D:\\Vlead Ticketing System\\Vlead_inputs2.xlsx"));
				wb.write(o);	o.close();
			}
			else
			{
				sh.getRow(i).createCell(7).setCellValue("User created successsfully");
				sh.getRow(i).createCell(8).setCellValue(dtf.format(now));
				FileOutputStream o =new FileOutputStream(new File("D:\\Vlead Ticketing System\\Vlead_inputs2.xlsx"));
				wb.write(o);	o.close();
			}
		}	
	}
	@AfterTest
	public void Close_Browser()
	{
		driver.quit();
	}
}
