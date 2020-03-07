package com.github.GandhiTC.java.VisualTesting.tests;



import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;



public class AShotTest
{
	String			savedImage	= System.getProperty("user.dir") + "\\src\\test\\resources\\ashot_images\\ElementScreenshot.png";
	WebDriver 		driver;
	WebElement 		logoImage;
	Screenshot		screenshot;
	BufferedImage	expectedImage;
	BufferedImage	actualImage;
	ImageDiffer		imgDiff;
	ImageDiff		diff;
	
	
	@BeforeClass
	public void getReferenceImage() throws InterruptedException, IOException
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/Drivers/ChromeDriver.exe");
		driver = new ChromeDriver();
		
		driver.get("http://demo.automationtesting.in/Register.html");
		driver.manage().window().setPosition(new Point(0,0));
		driver.manage().window().setSize(new Dimension(1024,650));
		Thread.sleep(2000);
		
		logoImage 		= driver.findElement(By.cssSelector("#imagetrgt"));
		screenshot		= new AShot().takeScreenshot(driver, logoImage);
		
		ImageIO.write(screenshot.getImage(), "PNG", new File(savedImage));
		Thread.sleep(2000);
		
		expectedImage	= ImageIO.read(new File(savedImage));
		actualImage		= screenshot.getImage();
	}
	
	
	@Test
	public void positiveTest() throws IOException
	{
		imgDiff			= new ImageDiffer();
		diff			= imgDiff.makeDiff(actualImage, expectedImage);
		
		Assert.assertFalse(diff.hasDiff(), "Images are not the same!");
	}
	
	
	@Test
	public void negativeTest() throws IOException
	{
		imgDiff			= new ImageDiffer();
		diff			= imgDiff.makeDiff(actualImage, expectedImage);
		
		Assert.assertTrue(diff.hasDiff(), "Images are the same, they were expected to be different!");
	}
	
	
	@AfterClass
	public void cleanUp()
	{
		driver.quit();
	}
}
