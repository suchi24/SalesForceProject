package K12Arch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class UserManagement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","H:\\LeanData\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/admin/viewSystemUsers?userId=6");
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
		driver.findElement(By.id("menu_admin_viewAdminModule")).click();
		
		// User Management
		WebElement eleUser = driver.findElement(By.id("searchSystemUser_userName"));
		eleUser.clear();
		eleUser.sendKeys("suchi");
		
		WebElement userRole = driver.findElement(By.id("searchSystemUser_userType"));
		//userRole.click();
		Select select = new Select(userRole);
		select.selectByIndex(0);
		
		WebElement eleStatus = driver.findElement(By.id("searchSystemUser_status"));
		select = new Select(eleStatus);
		select.selectByIndex(0);
		
		// Click Search
		
		WebElement eleSearch = driver.findElement(By.id("searchBtn"));
		eleSearch.click();
		
		WebElement eleResults = driver.findElement(By.linkText("suchi"));
		Assert.assertEquals(eleResults.getText(), "suchi"); 
		
		driver.close();
	}

}
