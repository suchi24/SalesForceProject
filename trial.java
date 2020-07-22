package K12Arch;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class trial {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","H:\\LeanData\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.phptravels.net/home");
		driver.findElement(By.xpath("(//a[@id='dropdownCurrency'])[2]")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.name("username")).sendKeys("user@phptravels.com");
		driver.findElement(By.name("password")).sendKeys("demouser");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		/*driver.findElement(By.id("btnLogin")).click();
		
		driver.findElement(By.id("menu_admin_UserManagement")).click();*/
		
	}

}
