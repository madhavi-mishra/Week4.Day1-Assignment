package week4.day1.assignment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.messages.internal.com.google.protobuf.Value;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNowFrame {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		// Step1: Load ServiceNow application URL given above
		driver.get("https://dev113545.service-now.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame1);
		//Step2: Enter username as admin 
		driver.findElement(By.id("user_name")).sendKeys("admin");
		//Step3: Enter password as w6hnF2FRhwLC
		driver.findElement(By.id("user_password")).sendKeys("w6hnF2FRhwLC");
		//Step4: Click Login 
		driver.findElement(By.id("sysverb_login")).click();
		// Step5: Search “incident “ Filter Navigator
		driver.findElement(By.id("filter")).sendKeys("incident");	
		driver.findElement(By.xpath("//div[contains(text(),'Incidents')]")).click();
		//Step6: Click “All” 
		WebElement frame2 = driver.findElement(By.xpath("//main[@class='navpage-main']//iframe"));
        driver.switchTo().frame(frame2);
        driver.findElement(By.xpath("//b[contains(text(),'All')]")).click();
        
        //Step7: Click New button
        driver.findElement(By.xpath("//button[contains(text(),'New')]")).click();

        //Step8: Select a value for Caller and Enter value for short_description 
        driver.findElement(By.id("lookup.incident.caller_id")).click();
        Set<String> wHandleset = driver.getWindowHandles();
		List<String> wHandlesList = new ArrayList<String>(wHandleset);
		driver.switchTo().window(wHandlesList.get(1));
		Thread.sleep(5000);
		driver.findElement(By.linkText("Abel Tuter")).click();
		driver.switchTo().window(wHandlesList.get(0));
		Thread.sleep(5000);
		driver.switchTo().frame(frame2);
		driver.findElement(By.xpath("//input[@id='incident.short_description']")).sendKeys("Short Description of Abel Tuter");
		
		String incidentNumber = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("Value");
		System.out.println("Incident Number="+incidentNumber);
		driver.findElement(By.xpath("//button[@id='sysverb_insert']")).click();
		driver.findElement(By.xpath("//span[@class='sr-only']//following-sibling::input")).sendKeys(incidentNumber);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@class='sr-only']//following-sibling::input")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		String actualIncidentNumber = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		System.out.println("actual Incident Number="+actualIncidentNumber);
		if(incidentNumber.equals(actualIncidentNumber))
		{
			System.out.println("Incident has been created successfully.");
			File src1 = driver.getScreenshotAs(OutputType.FILE);
			File dst = new File("./snaps/IncidentReport.png");
			FileUtils.copyFile(src1, dst);
		}
		else
			System.out.println("Incident is not created successfully.");
			
		
		
	}

}
/*
 * Assignment 2: ServiceNow - Frames ----------------------------------
 * 
 * ServiceNow- refer the attached document below for clarity
 * 
 * Instance URL: https://dev113545.service-now.com/ 
 * Username: admin 
 * Password:
 * w6hnF2FRhwLC
 * 
 * Step1: Load ServiceNow application URL given above
 *  Step2: Enter username as admin 
 *  Step3: Enter password as w6hnF2FRhwLC 
 *  Step4: Click Login 
 *  Step5: Search “incident “ Filter Navigator 
 *  Step6: Click “All” 
 *  Step7: Click New button
 * Step8: Select a value for Caller and Enter value for short_description 
 * Step9:Read the incident number and save it a variable 
 * Step10: Click on Submit button
 *  Step 11: Search the same incident number in the next search screen as below 
 *  Step12: Verify the incident is created successful and take snapshot of
 * the created incident.
 */