package demo.test;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class localCapabalities_modernOptions_demo {

    private WebDriver driver;

    @Test
    public void showLocalCapabilities() {
        
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");      
        options.setAcceptInsecureCerts(true);           


        driver = new ChromeDriver(options);

        try {
            driver.get("https://the-internet.herokuapp.com/");

            Capabilities caps = ((HasCapabilities) driver).getCapabilities();

            String browserName = caps.getBrowserName();       
            String browserVersion = caps.getBrowserVersion(); 
            
            Platform platform = null;
            try {
                platform = caps.getPlatformName();            
            } catch (NoSuchMethodError | Exception ignored) { }

            System.out.println("Browser: " + browserName);
            System.out.println("Browser Version: " + browserVersion);
            System.out.println("Platform: " + (platform != null ? platform.name() : "unknown"));

            
            System.out.println("All Capabilities: " + caps.asMap());

        } finally {
            
            if (driver != null) {
                driver.quit();
            }
        }
    }

    
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}