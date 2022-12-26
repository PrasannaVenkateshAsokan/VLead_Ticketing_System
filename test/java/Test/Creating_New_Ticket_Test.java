package Test;

import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import Pages.Create_New_Ticket;
import Pages.Login_Page;

public class Creating_New_Ticket_Test 
{
	WebDriver driver; FileInputStream fin; XSSFWorkbook wb; XSSFSheet sh,sh1;
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
	
	@Test
	public void Creating_New_Ticket() throws IOException
	{
		fin = new FileInputStream("D:\\Vlead Ticketing System\\Vlead_inputs2.xlsx"); wb = new XSSFWorkbook(fin); sh = wb.getSheetAt(0);
		sh1 = wb.getSheetAt(2);
		int last_row =sh1.getLastRowNum();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Login_Page LP = new Login_Page(driver);
		Create_New_Ticket CRN = new Create_New_Ticket(driver);
		LP.Employee_ID_TB(sh.getRow(1).getCell(0).getStringCellValue());
		String x = (sh.getRow(1).getCell(1).getRawValue().toString());
		LP.Password_TB(x);
		LP.Submit_BTN();
		for(int i=1;i<=last_row;i++)
		{
			CRN.Create_New_BTN();
			CRN.Issue(sh1.getRow(i).getCell(0).getStringCellValue());
			String Y = (sh.getRow(i).getCell(1).getRawValue().toString());
			CRN.Bay_Number(Y);
			CRN.Subject(sh1.getRow(i).getCell(2).getStringCellValue());
			CRN.Content(sh1.getRow(i).getCell(3).getStringCellValue());
			CRN.Priority(sh1.getRow(i).getCell(4).getStringCellValue());
			CRN.Upload_File();
			CRN.Submit_BTN();
			sh1.getRow(i).createCell(5).setCellValue("New ticket created");
			sh1.getRow(i).createCell(6).setCellValue(dtf.format(now));
			FileOutputStream o =new FileOutputStream(new File("D:\\Vlead Ticketing System\\Vlead_inputs2.xlsx"));
			wb.write(o);	o.close();
		}		
	}
	
	@AfterTest
	public void Close_Browser()
	{
		driver.quit();
	}
}

