package linear.framework;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginLogout 
{
	WebDriver driver;
	@Test(priority=1)
	public void TC1() throws InterruptedException    //login and logout
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/V4/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td[2]/input")).sendKeys("mngr383447");
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td[2]/input")).sendKeys("Aqegeru");
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[3]/td[2]/input[1]")).click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[15]/a")).click();
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(3000);
		driver.quit();
	}
	//Register, login and logout
	@Test(priority=2)
	public void TC2() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://demo.guru99.com/V4/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[4]/ol/li[1]/a")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/htm	l/body/form/table/tbody/tr[5]/td[2]/input")).sendKeys("garlictrader@gmail.com");
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[6]/td[2]/input")).click();
		Thread.sleep(3000);
		driver.navigate().to("https://demo.guru99.com/V4/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[1]/td[2]/input")).sendKeys("mngr383455");
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[2]/td[2]/input")).sendKeys("UpymEmA");
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/form/table/tbody/tr[3]/td[2]/input[1]")).click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[15]/a")).click();
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(3000);
		driver.quit();
	}
}
