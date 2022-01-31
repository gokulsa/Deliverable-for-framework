package data.driven.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DDF 
{
	WebDriver driver;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFCell cell;
	
	@BeforeTest
	public void initialization()
	{
		try 
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("https://www.saucedemo.com/");
			driver.manage().window().maximize();
			Thread.sleep(3000);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void DemoGuruWebTest() throws IOException, InterruptedException
	{
		try
		{
			File src = new File("src\\test\\java\\data\\driven\\framework\\DDFData.xlsx");
			FileInputStream fis = new FileInputStream(src);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{
				cell = sheet.getRow(i).getCell(0);
				cell.setCellType(CellType.STRING);
				
			
				driver.findElement(By.xpath("//*[@id=\"user-name\"]")).clear();
				driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys(cell.getStringCellValue());
				Thread.sleep(3000);
				cell = sheet.getRow(i).getCell(1);
				cell.setCellType(CellType.STRING);
				
				//driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/form/table/tbody/tr[4]/td/table/tbody/tr[3]/td[2]/input")).clear();
				driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(cell.getStringCellValue());
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
				Thread.sleep(3000);
				
				
				Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).isDisplayed());
				Thread.sleep(3000);
				driver.findElement(By.xpath("//*[@id=\"react-burger-menu-btn\"]")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("logout_sidebar_link")).click();
				Thread.sleep(3000);
			}
		}
		catch(Exception e)
       	{
			e.printStackTrace();
       	}
	}
	@AfterTest
	public void closebrowser() throws InterruptedException
	{
		driver.quit();
	}
}



