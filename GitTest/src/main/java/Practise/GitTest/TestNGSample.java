package Practise.GitTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class TestNGSample {
	// Initializing driver to null
	static WebDriver driver = null;

	@Test
	public static void method1() {
		// TODO Auto-generated method stub
		driver = initDriver();
		enterUrl("http://automationpractice.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.className("login")).click();

		// Validation page navigated successfully, we will get title and it should be
		// "Login - My Store"
		System.out.println(driver.getTitle());
		login("test9009@gmail.com", "Password1");

		// Creating a wishlist
		createWishList("SampleWishList9009");

		// Add item to wish list
		addItemWishList();

		// Deleting item to wish list
		deleteItem();

		// Deleting the wishList
		deleteWishList();

		// logout
		tearDown();
	}

	public static WebDriver initDriver() {
		// Set Up of driver and initializing driver
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}

	public static void enterUrl(String url) {
		driver.get(url);
	}

	public static void login(String userName, String password) {
		driver.findElement(By.id("email")).sendKeys(userName);
		driver.findElement(By.id("passwd")).sendKeys(password);
		driver.findElement(By.name("SubmitLogin")).click();
	}

	// Creation of Wish List
	public static void createWishList(String wishListname) {
		driver.findElement(By.xpath("//a[@title='View my customer account']")).click();
		driver.findElement(By.xpath("//a[@title='My wishlists']")).click();
		driver.findElement(By.id("name")).sendKeys(wishListname);
		driver.findElement(By.id("submitWishlist")).click();
	}

	// Addition of item into wish List
	public static void addItemWishList() {// Add item to wish list

		// Actions Class
		Actions obj = new Actions(driver);

		// Mouse Hover the mouse over "Women" Tab
		obj.moveToElement(driver.findElement(By.xpath("//a[@title='Women']"))).build().perform();
		// Click on Tshirts Link and T-SHIRTS Page is displayed with available option as
		// "Faded Short Sleeve T-shirts"
		driver.findElement(By.linkText("T-shirts")).click();

		// Mouse Hover the mouse over "Faded Short Sleeve T-shirts"
		obj.moveToElement(driver.findElement(By.xpath("//img[@title='Faded Short Sleeve T-shirts']"))).build()
				.perform();
		// Click on More Button
		driver.findElement(By.xpath("//a[@title='View']")).click();

		// Click on " Add to wishlist" link available on right hand side of the page.
		driver.findElement(By.id("wishlist_button")).click();
		// Click on Close button
		driver.findElement(By.xpath("//a[@title='Close']")).click();

		// Verify item added successfully
		// Click on Customer Account
		driver.findElement(By.xpath("//a[@title='View my customer account']")).click();

		// Click on My Wish list Link
		driver.findElement(By.xpath("//a[@title='My wishlists']")).click();

		// Click on "View" link available under Direct Link.
		driver.findElement(By.xpath("//a[contains(text(),'View')]")).click();

		// Validated ""Faded Short Sleeve T-shirts" text is displayed.
		System.out.println(driver.findElement(By.id("s_title")).getText());
	}

	// Deleting item from wish list
	public static void deleteItem() {
		// Navigate to Home Page by clicking on Customer Account
		driver.findElement(By.xpath("//a[@title='View my customer account']")).click();

		// Click on My Wish list Link
		driver.findElement(By.xpath("//a[@title='My wishlists']")).click();

		// Click on "View" link available under Direct Link.
		driver.findElement(By.xpath("//a[contains(text(),'View')]")).click();

		// Click on Delete (X) link .
		driver.findElement(By.xpath("//a[@class='lnkdel']")).click();

		// Added item would be removed from wish list, however page needs to be
		// refreshed, under wish list QTY would be 0
		driver.navigate().refresh();

	}

	// Deleting wish list
	public static void deleteWishList() {// Navigate to Home Page by clicking on Customer Account
		driver.findElement(By.xpath("//a[@title='View my customer account']")).click();
		// Click on My Wish list Link
		driver.findElement(By.xpath("//a[@title='My wishlists']")).click();
		// Click on Delete (X) link available under Delete
		driver.findElement(By.xpath("//i[@class='icon-remove']")).click();
		// Click on "OK" on the displayed pop up and wish list would be deleted
		Alert alertObj = driver.switchTo().alert();
		alertObj.accept();

	}

	// Log Out and close the web driver
	public static void tearDown() {
		driver.findElement(By.className("logout")).click();
		if (driver.getTitle().equalsIgnoreCase("Login - My Store"))
			System.out.println("LogOut happen Successfully");

		driver.close();
	}
}
