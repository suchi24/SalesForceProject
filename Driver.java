package K12Arch;


public class Driver {
	
	public static void main(String[] args) {
	
		AutomationScripts a = new AutomationScripts();
		a.SFDCLogin();
		//a.sfContact_Create();
		//a.sfContact_Update();
		//a.sfContactsDelete();
		//a.sfCourseCreate();
		a.sfLogout();
		//a.close();
	}

}
