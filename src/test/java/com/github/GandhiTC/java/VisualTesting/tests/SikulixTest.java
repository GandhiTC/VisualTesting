package com.github.GandhiTC.java.VisualTesting.tests;



import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.sikuli.basics.Debug;
import org.sikuli.basics.Settings;
//import java.net.URISyntaxException;
//import java.net.URL;



public class SikulixTest
{
	
	private Screen	screen;
	private String	basePath;


//	public SikulixTest() throws URISyntaxException
	public SikulixTest()
	{
			screen 				= new Screen();
//		URL resourceFolderURL 	= this.getClass().getClassLoader().getResource("sikulix_images");
//			basePath 			= resourceFolderURL.toURI().getPath() + "/";
			basePath 			= System.getProperty("user.dir") + "\\src\\test\\resources\\sikulix_images\\";
			
	}
	
	
//	public static void main(String... args) throws FindFailed, URISyntaxException
	public static void main(String... args) throws FindFailed
	{
		SikulixTest sikulixTest = new SikulixTest();
		sikulixTest.startTest();
	}


	private void startTest() throws FindFailed
	{
		setupLogger();
		clickWindowsStartAndOpenMSWord();
		typeTextInWordAndSave();
		System.exit(0);
	}
	
	
	private void setupLogger()
	{
		//	https://sikulix-2014.readthedocs.io/en/latest/scripting.html#debuglog
		
		
	    Settings.ActionLogs = true;			// (message prefix: [log])
	    Settings.InfoLogs 	= true;			// (message prefix: [info])
	    Settings.DebugLogs 	= false;		// (message prefix: [debug])
	    Settings.LogTime 	= true;
//	    Debug.setLogFile("absolute-path-to-file");	// to redirect the Sikuli messages to a file, no default

	    Settings.UserLogs 		= true;		// (False: user log calls are ignored)
	    Settings.UserLogPrefix 	= "user";	// (message prefix)
	    Settings.UserLogTime 	= true;
	    
	    Debug.user("This is an example of a user log.");
	}


	private void clickWindowsStartAndOpenMSWord() throws FindFailed
	{
		screen.click(basePath + "windows-start.png");
		screen.wait(1.0); //need delay to allow animation to bring start menu
		screen.type("word");
		screen.wait(1.0); //wait for 1 second to show results
		screen.type(Key.ENTER);
	}


	private void typeTextInWordAndSave() throws FindFailed
	{
		screen.click(basePath + "blank-document2.png");
		screen.type("This is a test using Sikulix API.");
		screen.type("s", KeyModifier.CTRL);
		screen.click(basePath + "browse-save2.png");
		screen.type("sikuli-test-document");
		screen.click(basePath + "btn-save3.png");
		screen.hover(screen.find(basePath + "app-close.png"));
		screen.wait(new Pattern(basePath + "app-close2.png")).click();
	}
}
