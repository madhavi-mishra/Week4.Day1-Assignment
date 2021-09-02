package week4.day1.assignment;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafgroundFrame {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");      
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//1.Take the the screenshot of the click me button of first frame
		WebElement frame1 = driver.findElement(By.xpath("(//div[@id='wrapframe'])[1]/iframe"));
		driver.switchTo().frame(frame1);
		WebElement clickMe1 = driver.findElement(By.xpath("//button[@id='Click']"));
		File src1 = driver.getScreenshotAs(OutputType.FILE);
		File dst = new File("./snaps/clickMe.png");
		FileUtils.copyFile(src1, dst);
		driver.switchTo().defaultContent();
		
		//2.Find the number of frames- find the Elements by tagname - iframe
		List<WebElement> NumOfFrames = driver.findElements(By.tagName("iframe"));
		System.out.println("Total number of frames on main page : "+NumOfFrames.size());
			
		
		
		
	}

}
/*Assignment 4:
------------
http://leafground.com/pages/frame.html
1.Take the the screenshot of the click me button of first frame
2.Find the number of frames
  - find the Elements by tagname - iframe
  - Store it in a list
  - Get the size of the list. (This gives the count of the frames visible to the main page)
*/