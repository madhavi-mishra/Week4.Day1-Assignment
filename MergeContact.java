package week4.day1.assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		// 1. Launch URL "http://leaftaps.com/opentaps/control/login"
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// 2. Enter UserName and Password Using Id Locator
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		// 3. Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();
		// 4. Click on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();
		// 5. Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();
		// 6. Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//div[@class='frameSectionBody']/ul/li[4]/a")).click();
		// 7. Click on Widget of From Contact
		driver.findElement(By.xpath("//table[@id='widget_ComboBox_partyIdFrom']//following-sibling::a")).click();
		
		// 8. Click on First Resulting Contact
		Set<String> wHandlesSet = driver.getWindowHandles();
		List<String> wHandlesList = new ArrayList<String>(wHandlesSet);
		driver.switchTo().window(wHandlesList.get(1));
		driver.findElement(By.xpath("//div[@class='x-grid3-body']/div[1]/table/tbody/tr[1]/td[1]/div/a[1]")).click();
		driver.switchTo().window(wHandlesList.get(0));
		
		// 9. Click on Widget of To Contact
		driver.findElement(By.xpath("//table[@id='widget_ComboBox_partyIdTo']//following-sibling::a")).click();

		
		Set<String> wHandlesSet1 = driver.getWindowHandles();
		List<String> wHandlesList1 = new ArrayList<String>(wHandlesSet1);
		driver.switchTo().window(wHandlesList1.get(1));
		
		Thread.sleep(5000);
		//10. Click on Second Resulting Contact
		driver.findElement(By.xpath("//div[@class='x-grid3-body']/div[2]/table/tbody/tr[1]/td[1]/div/a[1]")).click();
		driver.switchTo().window(wHandlesList1.get(0));
		//11. Click on Merge button using Xpath Locator
		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
		// 12. Accept the Alert
		Alert alert = driver.switchTo().alert();
		alert.accept();	
		// 13. Verify the title of the page
		String expectedTitle = "View Contact | opentaps CRM";
		String actualTitle = driver.getTitle();
		System.out.println("Actual Title of Page: "+actualTitle);
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Expected Title is same as actual Title");
		}
		else
		{
			System.out.println("Title of resulting page is not matching");
		}
	}
}

/*
 * //Pseudo Code
 * 
 * 1. Launch URL "http://leaftaps.com/opentaps/control/login"
 * 
 * 2. Enter UserName and Password Using Id Locator
 * 
 * 3. Click on Login Button using Class Locator
 * 
 * 4. Click on CRM/SFA Link
 * 
 * 5. Click on contacts Button
 * 
 * 6. Click on Merge Contacts using Xpath Locator
 * 
 * 7. Click on Widget of From Contact
 * 
 * 8. Click on First Resulting Contact
 * 
 * 9. Click on Widget of To Contact
 * 
 * 10. Click on Second Resulting Contact
 * 
 * 11. Click on Merge button using Xpath Locator
 * 
 * 12. Accept the Alert
 * 
 * 13. Verify the title of the page
 */
