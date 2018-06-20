package Testing;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


import com.gargoylesoftware.htmlunit.javascript.host.Iterator;



public class Tatoc {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vibhushrivastav\\eclipse-workspace\\training\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://10.0.1.86/tatoc");

		
		driver.findElement(By.linkText("Basic Course")).click();


		driver.findElement(By.className("greenbox")).click();
	
		driver.switchTo().frame(driver.findElement(By.id("main")));
		String Box1 = driver.findElement(By.id("answer")).getAttribute("class");
		String Box2 = null;
		do {
			driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#child")));
			Box2 = driver.findElement(By.id("answer")).getAttribute("class");
			driver.switchTo().parentFrame();
			if(Box1.equals(Box2)){
				driver.findElement(By.linkText("Proceed")).click();
				break;
			}
			else driver.findElement(By.linkText("Repaint Box 2")).click();
		}
		while(!Box1.equals(Box2));


		WebElement Drag = driver.findElement(By.id("dragbox"));

		WebElement Drop = driver.findElement(By.id("dropbox"));

		Actions builder = new Actions(driver);

		builder.dragAndDrop(Drag, Drop).build().perform();

		driver.findElement(By.linkText("Proceed")).click();

		driver.findElement(By.linkText("Launch Popup Window")).click();

		String parentWindow = driver.getWindowHandle(); 
		String childWindow = null;

		java.util.Set<String> windows = driver.getWindowHandles();

		driver.findElement(By.linkText("Launch Popup Window")).click();
		String MainWindow = driver.getWindowHandle();	
		for(String popWindow : driver.getWindowHandles()) {
			driver.switchTo().window(popWindow);
		}
		WebElement text = driver.findElement(By.id("name"));
		text.sendKeys("Vibhu");
		driver.findElement(By.id("submit")).click();
		driver.switchTo().window(MainWindow);
		driver.findElement(By.linkText("Proceed")).click();	
		
		
		
		driver.findElement(By.linkText("Generate Token")).click();
		String token1 = driver.findElement(By.id("token")).getText();
		String Token2 = token1.substring(token1.indexOf(":")+2);
		Cookie cookie = new Cookie("Token",Token2);
		driver.manage().addCookie(cookie);
		driver.findElement(By.linkText("Proceed")).click();

	}
}