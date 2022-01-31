package modular.driven.framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
public class MDF {
 
	WebDriver driver ;
	@BeforeClass
	public void instantiateDriver()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	@Test(enabled=false,priority=1,description="Scenario1 Verify the Service Type available in the html table")
	public void validateServiceTypeFunc()
	{
 
		invokeBrowser();
		login();
		navigateToServiceTypeTable();
		boolean result = verifyServiceType("Obstetrics");
		Assert.assertTrue(result);
	}
	@Test(priority=2,description="Scenario2 Delete the Service Type and Verify the ServiceType is not available in the html table")
	public void deleteServiceTypeFunc() throws InterruptedException
	{
		invokeBrowser();
		login();
		navigateToServiceTypeTable();
		SoftAssert sa = new SoftAssert();
		boolean result = deleteServiceType("Oncology");
		sa.assertTrue(result);
		navigateToHomePage();
		navigateToServiceTypeTable();
		result = verifyServiceType("Oncology");
		sa.assertFalse(result);
		sa.assertAll();
		
	}
	public void navigateToServiceTypeTable()
	{
		//AppointmentScheduling
		driver.findElement(By.id("appointmentschedulingui-homeAppLink-appointmentschedulingui-homeAppLink-extension")).click();
		//ServiceTypes
		driver.findElement(By.id("appointmentschedulingui-manageAppointmentTypes-app")).click();
 
	}
	public void navigateToHomePage()
	{
		driver.findElement(By.xpath("//body/ul[@id='breadcrumbs']/li[1]/a[1]/i[1]")).click();
	}
	public void invokeBrowser()
	{
 
		driver.get("https://demo.openmrs.org/openmrs/login.htm");
	}
	public void login()
	{
		driver.findElement(By.id("username")).sendKeys("Admin");
		driver.findElement(By.id("password")).sendKeys("Admin123");
		driver.findElement(By.id("Inpatient Ward")).click();
		driver.findElement(By.id("loginButton")).click();
	}
	public boolean verifyServiceType(String serviceType)
	{
		boolean result = false;
		List<WebElement> pageList  = driver.findElements(By.xpath("//div[@id='appointmentTypesTable_paginate']/span/a"));
		List<WebElement> serviceTypeList=driver.findElements(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[1]"));
	
		outerloop:
		for(int i =0 ;i<pageList.size();i++)
		{
			pageList = driver.findElements(By.xpath("//div[@id='appointmentTypesTable_paginate']/span/a"));
			pageList.get(i).click();
			serviceTypeList=driver.findElements(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[1]"));
		
			for(int j=0;j<serviceTypeList.size();j++)
			{
				if(serviceTypeList.get(j).getText().equals(serviceType))
				{
					System.out.println("ServiceType Found:::" + serviceTypeList.get(j).getText());
					result = true;
					break outerloop;
				}
			}
		}
		return result;
	}
	public boolean deleteServiceType(String serviceType) throws InterruptedException
	{
		boolean result = false;
		
		List<WebElement> pageList  = driver.findElements(By.xpath("//div[@id='appointmentTypesTable_paginate']/span/a"));
		List<WebElement> serviceTypeList=driver.findElements(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[1]"));
	
		outerloop:
		for(int i =0 ;i<pageList.size();i++)
		{
			pageList = driver.findElements(By.xpath("//div[@id='appointmentTypesTable_paginate']/span/a"));
			pageList.get(i).click();
			serviceTypeList=driver.findElements(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr/td[1]"));
		
			for(int j=0;j<serviceTypeList.size();j++)
			{
				if(serviceTypeList.get(j).getText().equals(serviceType))
				{
					System.out.println("ServiceType Found:::" + serviceTypeList.get(j).getText());
					//Click on delete icon
					driver.findElement(By.xpath("//table[@id='appointmentTypesTable']/tbody/tr["+(j+1)+"]/td[text()='"+serviceType+"']/following-sibling::td/span/i[@title='Delete']")).click();
					//Click on Yes button in the simple modal container
					driver.findElement(By.xpath("//body/div[@id='simplemodal-container']/div[1]/div[1]/div[2]/button[1]")).click();
					result = true;
					Thread.sleep(2000);
					break outerloop;
				}
			}
		}
		return result;
	}
	@AfterClass
	public void quitbrowser()
	{
		driver.quit();
	}
	
}