package K12Arch;

import org.testng.Assert;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;



public class AutomationScripts extends ReUsableMethods  {
	WebDriver driver;
	
	String orPath = "H:\\LeanData\\SF_ObjectRepository.xls";
	String tdPath = "H:\\LeanData\\TestData_SF.xls";
	
	// Login SalesForce
	//@Test
	public void SFDCLogin(){
		
		try{
		System.setProperty("webdriver.chrome.driver", "H:\\LeanData\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	

		readLocators(orPath);
		readTestData(tdPath);
		
	    String username = (String)data[1][1];
		String password = (String)data[2][1];
		
		//Locate Username
		setValue(1);
		By byUsername = getBy(locatorType,value);
		WebElement ele_userName = driver.findElement(byUsername);	
		clear(ele_userName);
		enterText(ele_userName,username, obj_Name);	
		
		//Locate Password
		setValue(2);
		By bypassword = getBy(locatorType,value);
		WebElement ele_password =driver.findElement(bypassword);
		clear(ele_password);
		enterText(ele_password,password,obj_Name);
		
		setValue(4);
		By byRemember = getBy(locatorType,value);
		WebElement clickRemember = driver.findElement(byRemember);
		clickElement(clickRemember,obj_Name);
		
		//Locate Login
		setValue(3);
		By byLogin = getBy(locatorType,value);
		WebElement clickLogin = driver.findElement(byLogin);
		clickElement(clickLogin,obj_Name);
		
		setValue(27);
		By byLexDialog = getBy(locatorType,value);
		WebElement LexDialog = driver.findElement(byLexDialog);
		
		if (LexDialog.isDisplayed()){
			setValue(28);
			By byDialogClose = getBy(locatorType,value);
			WebElement clickDialogClose = driver.findElement(byDialogClose);
			clickElement(clickDialogClose,obj_Name);
		}
		
		String title = driver.getTitle();
		
		Assert.assertTrue(title.contains("Enterprise Edition"));
		}catch(Exception e){
			
		}		
		
	}
	
	// Create a New Contact	
	//@Test
	public void sfContact_Create(){

		HomeToContacts(driver);
		int contact_size = find_no_of_contacts(driver); // Save the total contacts before add
		try{
		setValue(8);
		By byHome = getBy(locatorType,value);
		WebElement clickHome = driver.findElement(byHome);
		clickElement(clickHome,obj_Name);
		
		setValue(9);
		By byCreate = getBy(locatorType,value);
		WebElement clickCreate = driver.findElement(byCreate);
		clickElement(clickCreate,obj_Name);
		
		setValue(10);
		By byContact = getBy(locatorType,value);
		WebElement clickContact = driver.findElement(byContact);
		clickElement(clickContact,obj_Name);
		
		setValue(11);
		By byContactType = getBy(locatorType,value);
		String contactType = selectIndex(driver,byContactType, 2);
		
		setValue(12);
		By byContinue = getBy(locatorType,value);
		WebElement clickContinue = driver.findElement(byContinue);
		clickElement(clickContinue,obj_Name);
		
		Thread.sleep(3000);
		// Enter Salutation
		setValue(13);
		By bySalutation = getBy(locatorType,value);
		String salutaion = selectIndex(driver,bySalutation, 1);
		Thread.sleep(2000);
		
		// Enter First Name
		String firstName = (String) data[3][1];
		int rand_int1 = getRandomNumber();
		firstName = firstName + rand_int1;
		setValue(15);
		By byFirstname = getBy(locatorType,value);	
		WebElement ele_FirstName = driver.findElement(byFirstname);	
		clear(ele_FirstName);
		enterText(ele_FirstName,firstName, obj_Name);
		
		// Enter Last Name
		String lastName = (String) data[4][1];
		setValue(16);
		By byLastname = getBy(locatorType,value);
		WebElement ele_LastName = driver.findElement(byLastname);	
		clear(ele_LastName);
		enterText(ele_LastName,lastName, obj_Name);
		
		// Enter Gender
		String Gender = (String) data[5][1];
		setValue(14);
		By byGender = getBy(locatorType,value);
		String gender = selectIndex(driver,byGender, 2);
		
		// Click Save
		setValue(17);
		By bySave = getBy(locatorType,value);
		WebElement clickSave = driver.findElement(bySave);
		clickElement(clickSave,obj_Name);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		HomeToContacts(driver);
		int contact_size_afterAdd = find_no_of_contacts(driver); // Save the total contacts after add
		
		// Verify after adding new contact, the total contact has been increased
		Assert.assertEquals(contact_size_afterAdd, contact_size+1);
	}
	
	// Update the Contact
	
	//@Test
	public void sfContact_Update(){
		try{
		setValue(8);
		By byHome = getBy(locatorType,value);
		WebElement clickHome = driver.findElement(byHome);
		clickElement(clickHome,obj_Name);
		
		setValue(18);
		By byContacts = getBy(locatorType,value);
		WebElement clickContacts = driver.findElement(byContacts);
		clickElement(clickContacts,obj_Name);
		
		// Select Contacts dropdown	
		setValue(23);
		By byCont_DropDwn = getBy(locatorType,value);
		String contact = selectIndex(driver,byCont_DropDwn, 0);
		
		setValue(24);
		By byGo = getBy(locatorType,value);
		WebElement clickGo = driver.findElement(byGo);
		clickElement(clickGo,obj_Name);
		
		setValue(25);
		By byEdit = getBy(locatorType,value);
		WebElement clickEdit = driver.findElement(byEdit);
		clickElement(clickEdit,obj_Name);		
	
		scrollDown(driver);  // Scroll the page down
		
		setValue(26);
		By byEthnity = getBy(locatorType,value);		
		String ethnity = selectIndex(driver, byEthnity,2);
	
		setValue(17);
		By bySave = getBy(locatorType,value);
		WebElement clickSave = driver.findElement(bySave);
		clickElement(clickSave,obj_Name);
		Thread.sleep(2000);
		
		// Get the Contact name of the modified
		setValue(31);
		By byFullName = getBy(locatorType,value);
		WebElement ele_FullName = driver.findElement(byFullName);
		clickElement(ele_FullName,obj_Name);
		
		scrollDown(driver);
		
		setValue(32);
		By byEthnityText = getBy(locatorType,value);
		WebElement GetEthnity = driver.findElement(byEthnityText);
	
		// Verify ethnity has been updated after doing modification.
		Assert.assertEquals(GetEthnity.getText(),ethnity) ;
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
	
	// Delete a Contact
	//@Test
	public void sfContactsDelete(){
		
		HomeToContacts(driver); // Navigate from Home page to Contacts Page
		int contact_size = find_no_of_contacts(driver); // Save the total contacts before delete
	
		setValue(29);
		By byDel = getBy(locatorType,value);
		WebElement clickDel = driver.findElement(byDel);
		clickElement(clickDel,obj_Name);	
		
		String mainPage = driver.getWindowHandle();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		driver.switchTo().window(mainPage);
		
		int contact_size_afterDelete = find_no_of_contacts(driver); // Save the total contacts after delete
		
		Assert.assertEquals(contact_size_afterDelete, contact_size-1); // Verify Total contacts has been decreased

	}
	
	// Create Course
	@Test
	public void sfCourseCreate(){
		try {
		HomeToCourses(driver);   // Navigate from Home page to Course Page
		int TotalCourseBefore = find_no_of_contacts(driver);
		setValue(35);
		By byNew = getBy(locatorType,value);
		WebElement clickNew = driver.findElement(byNew);
		clickElement(clickNew,obj_Name);
		
		String course = (String) data[6][1];
		int rand_int = getRandomNumber();
		course = course + rand_int;
		setValue(36);
		By byCourseName = getBy(locatorType,value);
		WebElement eleCourseName = driver.findElement(byCourseName);
		clear(eleCourseName);
		enterText(eleCourseName,course, obj_Name);
		
		setValue(37);
		By byDept = getBy(locatorType,value);
		WebElement eleDept = driver.findElement(byDept);
		clickElement(eleDept,obj_Name);
		
		String mainWind = switchToPopupWindow(driver);
		
		Thread.sleep(2000);

		setValue(38);
		driver.switchTo().frame(1);
		By byAccount = getBy(locatorType,value);
		WebElement eleAccount = driver.findElement(byAccount);
		clickElement(eleAccount,obj_Name);
		driver.switchTo().window(mainWind);
		
		setValue(39);
		By bySave = getBy(locatorType,value);
		WebElement eleSave = driver.findElement(bySave);
		clickElement(eleSave,obj_Name);
		
		HomeToCourses(driver);
		int TotalCourseAfterAdd = find_no_of_contacts(driver);
		
		// Verify after adding new course the total course has been increased
		Assert.assertEquals(TotalCourseAfterAdd, TotalCourseBefore+1);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void sfLogout(){
		setValue(41);
		By byUserDropDown = getBy(locatorType,value);
		WebElement eleUser = driver.findElement(byUserDropDown);
		clickElement(eleUser,obj_Name);
		
		setValue(42);
		By byLogout = getBy(locatorType,value);
		WebElement eleLogout = driver.findElement(byLogout);
		clickElement(eleLogout,obj_Name);
	}
	public void close(){
		closeBrowser(driver);
	}
}
	


