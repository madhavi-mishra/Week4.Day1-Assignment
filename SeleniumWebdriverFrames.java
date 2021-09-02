package week4.day1.assignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumWebdriverFrames {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get(" https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		//Frame 1
		WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='frame1']"));
		driver.switchTo().frame(frame1);
		driver.findElement(By.xpath("//b[@id='topic']/following::input")).sendKeys("Frames & Nested frames");
		
		//Nested Frame
		WebElement frame3 = driver.findElement(By.id("frame3"));
		driver.switchTo().frame(frame3);
		driver.findElement(By.xpath("//input[@id='a']")).click();
        driver.switchTo().defaultContent();
        
        //frame2
        WebElement frame2 = driver.findElement(By.id("frame2"));
        driver.switchTo().frame(frame2);
        WebElement options = driver.findElement(By.id("animals"));
        Select animals = new Select(options);
        animals.selectByIndex(2);
	}

}
