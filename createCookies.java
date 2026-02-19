package demo.test;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
// If you use WebDriverManager, uncomment the next line and the setup call in setUp()
// import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class createCookies {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // If you use WebDriverManager to handle driver binaries, uncomment these lines:
        // WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void addAndDeleteCookie() {
        // Navigate to a page on the target domain (important before adding cookies)
        driver.get("https://the-internet.herokuapp.com/");

        // Create and add a cookie
        Cookie myCookie = new Cookie("trainerCookie", "Diya123");
        driver.manage().addCookie(myCookie);

        // Fetch the cookie and validate
        Cookie fetched = driver.manage().getCookieNamed("trainerCookie");
        Assert.assertNotNull(fetched, "Cookie was not added");
        Assert.assertEquals(fetched.getValue(), "Diya123", "Cookie value mismatch");

        System.out.println("Added Cookie: " + fetched);
        
        String path = "https://the-internet.herokuapp.com/";
        
        System.out.println("Expiry-> "+myCookie.getExpiry());
        System.out.println("Path-> "+myCookie.getPath());
        System.out.println("isSecure-> "+myCookie.isSecure());
        System.out.println("Hash Code-> "+myCookie.hashCode());
        System.out.println("toString-> "+myCookie.toString());
        
        System.out.println("isHttpOnly-> "+myCookie.isHttpOnly());
        

        // Print all cookies
        Set<Cookie> all = driver.manage().getCookies();
        System.out.println("Total cookies now: " + all.size());
        for (Cookie c : all) {
            System.out.println("Cookie -> " + c.getName() + " = " + c.getValue());
        }

        // Delete the cookie by name
        driver.manage().deleteCookieNamed("trainerCookie");

        // Verify deletion (should be null)
        Cookie afterDelete = driver.manage().getCookieNamed("trainerCookie");
        Assert.assertNull(afterDelete, "Cookie was not deleted");

    }
}