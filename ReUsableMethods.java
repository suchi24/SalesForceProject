package K12Arch;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.Random;

//import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ReUsableMethods {
	Object[][] locator;
	Object[][] data;
	String[][] matrix;
	String locatorType;
	String value;
	String obj_Name;
	String data1;
	WebDriver wd;


	public void setValue(int row) {
		obj_Name=(String) locator[row][0];  // Name of the object - username, password, login
		locatorType = (String) locator[row][1]; // Locator type - id, name, xpath, className, etc.
		value=(String) locator[row][2]; // value of the locator
	}
	
	// locator type and value and return By
	public By getBy(String type, String value){
		switch (type){
		case "id":
			return By.id(value);
		case "xpath":
			return By.xpath(value);
		case "className":
			return By.className(value);
		case "name":
			return By.name(value);
		case "linkText":
			return By.linkText(value);
		case "partialLinkText":
			return By.partialLinkText(value);
		case "cssSelector":
			return By.cssSelector(value);
		case "tagName":
			return By.tagName(value);
		default:
			System.out.println("Unknown type");
	 		return null;
		}
	}
	
	//Clear text box
		public void clear(WebElement textBox) {
			textBox.clear();
		}
		
		// Enter the text in the WebElement text box
		public void enterText(WebElement obj, String textData, String objName) {
			if(obj.isDisplayed()) {
				obj.sendKeys(textData);
				System.out.println("Pass: "+textData+" is entered in "+objName);
			}
			else {
				System.out.println("Fail: "+objName+" is not displayed. Please check your application");
			}
		}
		// Click the WebElement
		public void clickElement(WebElement obj, String objName ){
			if(obj.isDisplayed()) {
				if(obj.isEnabled()) {
					obj.click();
					System.out.println(objName+" is cicked");
				}
			}
			else {
				System.out.println(objName+" is disabled");
			}
		}
		// Read locators from Excel
		public void readLocators(String path){
			
			locator=readExcel(path); 
		}
		
		// Read Data from Excel
		public  void readTestData(String path) {
			data = readExcel(path);
		}
		
		// Read Excel File from the mentioned directory
	public Object[][] readExcel(String path) {
		try{
		File file = new File(path);
		FileInputStream xf = new FileInputStream(file);		
		HSSFWorkbook xwb = new HSSFWorkbook(xf);
		HSSFSheet sheet1 = xwb.getSheet("Sheet1");
		int RowCount = sheet1.getLastRowNum()+1;
		int ColCount = sheet1.getRow(0).getLastCellNum();
		matrix = new String[RowCount][ColCount];
		for (int i = 0; i < RowCount; i++){
			for (int j = 0; j < ColCount; j++){
				HSSFCell cell = sheet1.getRow(i).getCell(j);
				if(cell!=null)
				{
					String value= cell.getStringCellValue();
					matrix[i][j]=value;

				}
			
			}			
		}
		}catch(Exception e){
		
		}
		return matrix;
	}
	
	// Navigate from Home Page to Contacts Page
	public  void HomeToContacts(WebDriver wd){
		setValue(8);
		By byHome = getBy(locatorType,value);
		WebElement clickHome = wd.findElement(byHome);
		clickElement(clickHome,obj_Name);
		
		setValue(18);
		By byContacts = getBy(locatorType,value);
		WebElement clickContacts = wd.findElement(byContacts);
		clickElement(clickContacts,obj_Name);
		
		setValue(23);
		By byCont_DropDwn = getBy(locatorType,value);
		WebElement clickCont_DropDwn = wd.findElement(byCont_DropDwn);
		Actions action = new Actions(wd);
		action.moveToElement(clickCont_DropDwn);
		Select select = new Select(clickCont_DropDwn);
		select.selectByIndex(0);
		
		setValue(24);
		By byGo = getBy(locatorType,value);
		WebElement clickGo = wd.findElement(byGo);
		clickElement(clickGo,obj_Name);		
		
	}
	
	// Navigate from Home page to Courses page
	public void HomeToCourses(WebDriver wd){

		setValue(8);
		By byHome = getBy(locatorType,value);
		WebElement clickHome = wd.findElement(byHome);
		clickElement(clickHome,obj_Name);
			
		setValue(33);
		By byCourse = getBy(locatorType,value);
		WebElement clickCourse = wd.findElement(byCourse);
		clickElement(clickCourse,obj_Name);
		
		setValue(34);
		By byCourse_DropDwn = getBy(locatorType,value);
		WebElement eleCourse_DropDwn = wd.findElement(byCourse_DropDwn);
		Actions action = new Actions(wd);
		action.moveToElement(eleCourse_DropDwn);
		Select select = new Select(eleCourse_DropDwn);
		select.selectByIndex(1);
		
		setValue(24);
		By byGo = getBy(locatorType,value);
		WebElement clickGo = wd.findElement(byGo);
		clickElement(clickGo,obj_Name);
			
	}
	
	// Find the Total number of Contacts and return it.
	public int find_no_of_contacts(WebDriver wd){

		setValue(30);
		By byTotal = getBy(locatorType,value);
		WebElement Elem_TotalContacts = wd.findElement(byTotal);
		String TotalContacts = Elem_TotalContacts.getText(); 
		int no_of_contacts = Integer.parseInt(TotalContacts.substring(TotalContacts.length()-1));
		
		return no_of_contacts;
	}
	
	// Switch to Pop Window and return the main Window String
	public String switchToPopupWindow(WebDriver wd)
	{
	   String handle = wd.getWindowHandle();
	   ArrayList<String> tabs = new ArrayList(wd.getWindowHandles());
	   wd.switchTo().window(tabs.get(1));
	   return handle;
	}
	// Generate Randowm Number between 1 and 1000
	public int getRandomNumber(){
		Random rand = new Random(); 
		int rand_int = rand.nextInt(1000);
		return rand_int;
	}
	
	// Scroll Down the page vertically
	public void scrollDown(WebDriver wd){
		
		((JavascriptExecutor) wd).executeScript("scroll(0,250);");
	}
	
	public String selectIndex(WebDriver wd,By byethnity, int index){
		WebElement clickEthnity = wd.findElement(byethnity);
		Actions action = new Actions(wd);
		action.moveToElement(clickEthnity);
		Select select = new Select(clickEthnity);
		select.selectByIndex(index);
		String text = select.getFirstSelectedOption().getText();
		return text;
	}
	
	//Close the browser
	public void closeBrowser(WebDriver wd){
		wd.close();
	}
}
