package TestNG;
import static org.testng.Assert.assertEquals;

import java.io.*;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Listeners(TestNG.ExtentReportListener.class)
public class Homepagetesting {
	
    WebDriver driver;
    Actions link;
    WebDriverWait wait;
    String homepage="https://westfloridaahec.org/";
    String pwin;
    String opt_name;
    File source;
    TakesScreenshot ts;
   
    public void takess(String name)
    {
    	ts = (TakesScreenshot) driver;
		source = ts.getScreenshotAs(OutputType.FILE);
		try {
		FileUtils.copyFile(source, new File("E:\\Java\\Eclipse\\Capstone_Project\\screenshots\\" + name+ ".png"));
		} catch (Exception e) {
		e.printStackTrace();
		}
    }
    
	@Parameters("browser")
	@BeforeClass
	public void setup(String browser) {
		if (browser.equalsIgnoreCase("chrome"))
		{
			 driver = new ChromeDriver();
//			 File chromeLogFile = new File("chrome_log.txt");
//	         PrintStream chromeLogStream = new PrintStream(new FileWriter(chromeLogFile, true));
//	         System.setOut(chromeLogStream); 
		}
		//else if (browser.equalsIgnoreCase("firefox"))
		//{
		   // driver = new FirefoxDriver();
//		    File firefoxLogFile = new File("firefox_log.txt");
//            PrintStream firefoxLogStream = new PrintStream(new FileWriter(firefoxLogFile, true));
//            System.setOut(firefoxLogStream); 
		//}
		
		
		driver.get(homepage);
	    driver.manage().window().maximize();
	    pwin=driver.getWindowHandle().toString();
	    link=new Actions(driver);
		
	}
	
	@AfterClass
	public void tearDown() 
	{
	        driver.quit();
	}

	
	@Test(priority = 1)
	
	public void navigationmenu() 
	{
		 wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		 List<WebElement> options;
		 System.out.println("\n----------NAVIGATION MENU ------------- ");
		//home
		    driver.findElement(By.partialLinkText("HOME")).click();
		   // System.out.println(driver.getCurrentUrl());
		    Assert.assertTrue(driver.getCurrentUrl().contains(homepage),"Home page Filed");
		    
		    takess("Home");
		    System.out.println("\nHOME page is working ");
			System.out.println();
		    
		//about
		    WebElement menu=driver.findElement(By.partialLinkText("ABOUT"));
		    link.moveToElement(menu).perform();
			options=driver.findElements(By.xpath("//li[@id='menu-item-616']//ul[@class='sub-menu']//a"));
			for(WebElement opt:options)
			{
				opt_name=opt.getText();
				link.moveToElement(opt).click().perform();
				wait.until(ExpectedConditions.titleContains(opt_name));
				System.out.print("\n"+driver.getTitle());
				Assert.assertTrue(driver.getTitle().contains(opt_name),opt_name+"Failed");
				takess(opt_name);
				System.out.print("  PASSED");
				driver.navigate().back();
				menu = driver.findElement(By.partialLinkText("ABOUT"));
				link.moveToElement(menu).perform();
				options=driver.findElements(By.xpath("//li[@id='menu-item-616']//ul[@class='sub-menu']//a"));
			}
			System.out.println("\nABOUT page is working ");
			System.out.println();
			
		  //programs
			 WebElement prog_menu=driver.findElement(By.partialLinkText("PROGRAMS"));
				
				    link.moveToElement(prog_menu).perform();
					options=driver.findElements(By.xpath("//li[@id='menu-item-264']//ul[@class='sub-menu']//a"));
					for(WebElement opt:options)
					{
						//String covering_florida="Educating Towards a Healthier Community";
						opt_name=opt.getText();
						link.moveToElement(opt).click().perform();
						System.out.print("\n"+driver.getTitle());
						if(opt_name.equals("Covering Florida"))
						{
							Assert.assertTrue(driver.getTitle().contains("Navigators"),"Covering Florida page Failed");
							takess("Covering Florida");
							driver.navigate().back();
							break;
						} 
						else
						{
						Assert.assertTrue(driver.getTitle().contains(opt_name),opt_name+" page Failed");
						takess(opt_name);
						System.out.print("  PASSED");
						driver.navigate().back();
						prog_menu = driver.findElement(By.partialLinkText("PROGRAMS"));
						link.moveToElement(prog_menu).perform();
						options=driver.findElements(By.xpath("//li[@id='menu-item-264']//ul[@class='sub-menu']//a"));
						}
		
					}
					System.out.println("\nPROGRAMS page is working ");
					System.out.println();
			
			//Services
			 menu=driver.findElement(By.partialLinkText("SERVICES"));
			 link.moveToElement(menu).perform();
				options=driver.findElements(By.xpath("//li[@id='menu-item-331']//ul[@class='sub-menu']//a"));
				for(WebElement opt:options)
				{
					opt_name=opt.getText();
					link.moveToElement(opt).click().perform();
					//wait.until(ExpectedConditions.titleContains(opt_name));
					System.out.print("\n"+driver.getTitle());
					Assert.assertTrue(driver.getTitle().contains(opt_name),opt_name+" page Failed");
					takess(opt_name);
					System.out.print("  PASSED");
					driver.navigate().back();
					
					menu = driver.findElement(By.partialLinkText("SERVICES"));
					link.moveToElement(menu).perform();
					options=driver.findElements(By.xpath("//li[@id='menu-item-331']//ul[@class='sub-menu']//a"));
				}
				System.out.println("\nSERVICES page is working ");
				System.out.println("\n");
				
				//cpr
				 menu=driver.findElement(By.partialLinkText("CPR"));
				 link.moveToElement(menu).perform();
					options=driver.findElements(By.xpath("//li[@id='menu-item-467']//ul[@class='sub-menu']//a"));
					for(WebElement opt:options)
					{
						opt_name=opt.getText();
						link.moveToElement(opt).click().perform();
						wait.until(ExpectedConditions.titleContains(opt_name));
						System.out.print("\n"+driver.getTitle());
						Assert.assertTrue(driver.getTitle().contains(opt_name),opt_name+" page Failed");
						takess(opt_name);
						System.out.print("  PASSED");
						driver.navigate().back();
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
						
						menu = driver.findElement(By.partialLinkText("CPR"));
						link.moveToElement(menu).perform();
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='menu-item-467']//ul[@class='sub-menu']")));
						options=driver.findElements(By.xpath("//li[@id='menu-item-467']//ul[@class='sub-menu']//a"));
					}
					System.out.println("\nCPR page is working");
					System.out.println();
					
					//contact us
					driver.findElement(By.partialLinkText("CONTACT US")).click();
					Assert.assertTrue(driver.getTitle().contains("CONTACT US"),"Contact us page failed");
					takess("CONTACT US");
					System.out.println("\nCONTACT US page is working ");
					System.out.println();
					driver.get(homepage);
					
					//news
					driver.findElement(By.partialLinkText("NEWS")).click();
					Assert.assertTrue(driver.getTitle().contains("NEWS"),"News page failed");
					takess("NEWS");
					System.out.println("\nNEWS page is working");
					System.out.println();
					driver.get(homepage);
					
					//my account
					driver.findElement(By.partialLinkText("MY ACCOUNT")).click();
					Assert.assertTrue(driver.getTitle().contains("My account"),"My Account page failed");
					takess("My account");
					System.out.println("\nMY ACCOUNT page is working ");
					System.out.println();
					driver.get(homepage);
					
					//cart
					driver.findElement(By.xpath("//*[@id=\"menu-main-menu\"]/li[9]/a")).click();
					Assert.assertTrue(driver.getTitle().contains("Cart"),"Cart page failed");
					takess("Cart");
					System.out.println("\nCART page is working ");
					System.out.println();
					driver.get(homepage);	
					
					
					
					
					System.out.println();
				
		}
	
	
	@Test(priority=2)
	public void searchbar()
	{
		String searchQuery="health programs";
		driver.findElement(By.name("s")).sendKeys(searchQuery);
		driver.findElement(By.xpath("//*[@id=\"wrapper\"]/header/div[1]/div[3]/div[1]/div/div/div/div/form/div/div[2]/input")).click();
		Assert.assertTrue(driver.getCurrentUrl().contains("?s="));
		System.out.println("\nSearch bar is working");
		driver.get(homepage);
	}
	
	@Test(priority = 3)
	public void programsandservices()
	{
		System.out.println("\n----------PROGRAMS MENU ------------- ");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		WebElement prog_menu=driver.findElement(By.partialLinkText("PROGRAMS"));
		
	    link.moveToElement(prog_menu).perform();
		List<WebElement>options=driver.findElements(By.xpath("//li[@id='menu-item-264']//ul[@class='sub-menu']//a"));
		for(WebElement opt:options)
		{
			//String opt_name=opt.getText();
			link.moveToElement(opt).click().perform();
			
				//Quit Tobacco
			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("QUIT TOBACCO")));
		    driver.findElement(By.xpath("//*[@id=\"post-340\"]/div/div/div/div/div/div[1]/div[1]/div/a")).click();
		
			//System.out.println(driver.getTitle());
			Assert.assertTrue(driver.getTitle().contains("Quit Tobacco"),"Quit Tobacco page Failed");//  title is displayed
			Assert.assertTrue(driver.findElement(By.tagName("a")).isDisplayed()); //phone number is displayed
			takess("Quit Tobacco");
			driver.navigate().back();
			System.out.println("Quit Tobacco page is working");
			
			//system change
				driver.findElement(By.xpath("/html/body/div[8]/div/main/div/section/div/div/div/div/div/div/div[1]/div[2]/div/a")).click();
				Assert.assertTrue(driver.getTitle().contains("Systems Change"),"System Change page failed");
				takess("Systems Change");
				driver.navigate().back();
				System.out.println("Systems Change page is working");
				
	        //Training			
				driver.findElement(By.xpath("//*[@id=\"post-340\"]/div/div/div/div/div/div[1]/div[3]/div/a")).click();
				Assert.assertTrue(driver.getTitle().contains("Training"),"Training page failed");
				takess("Training");
				driver.navigate().back();
				System.out.println("Training page is working");
				
			break;
			
		}
		System.out.println("Contents in Tobacco Page are working ");
		driver.get(homepage);
		
	}

		
	@Test(priority = 4)
	public void validateContactUs() 
	{
		System.out.println("\n----------CONTACT US ------------- ");
		driver.findElement(By.partialLinkText("CONTACT US")).click();
		   
			Assert.assertTrue(driver.getTitle().contains("CONTACT US"),"Contact us page failed");
			takess("CONTACT US");
		    System.out.print("\nContact Us Page is working");
		    System.out.println("\n");
		    driver.get(homepage);
	}
	
	
	 @DataProvider(name = "loginData")
	    public Object[][] getLoginData() 
	 {
	        return new Object[][]{
	        	 {"Mike24", "Mike24123!"},  //valid ones
	        };
	        	
	 }
	@Test(priority = 5,dataProvider = "loginData")
	public void login(String username,String password)
	{
		System.out.println("\n----------LOG IN ------------- ");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10)); // Maximum wait time of 10 seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
		driver.findElement(By.partialLinkText("MY ACCOUNT")).click();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		takess("login form");
		driver.findElement(By.name("login")).click();
		
		String message=driver.findElement(By.xpath("//*[@id=\"post-381\"]/div/div/div[1]/div[1]/span[1]")).getText();
		 Assert.assertTrue(message.contains("Hello"),"Login Failed");
		{
			//Assert.assertTrue(message.contains("Hello "+username),"Registration failed");
			takess("login");
			System.out.println("Sucessfully logged in");
			driver.findElement(By.xpath("//*[@id=\"post-381\"]/div/div/nav/ul/li[7]/a")).click();   //logout
			takess("logout");
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[1]/h2")).isDisplayed(),"Logout failed");
			System.out.println("Sucessfully logged out");
		}
		driver.get(homepage);
	}
	
	//invalid password login
	@Test(priority = 8)
	public void invalidpasslogin()
	{
		System.out.println("\n----------LOG IN WITH INVALID PASSWORD ------------- ");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10)); // Maximum wait time of 10 seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
		driver.findElement(By.partialLinkText("MY ACCOUNT")).click();
		driver.findElement(By.id("username")).sendKeys("Mike24");
		driver.findElement(By.id("password")).sendKeys("invalidpassword");
		takess("login form");
		driver.findElement(By.name("login")).click();
		String error = driver.findElement(By.xpath("//*[@id=\"post-381\"]/div/div/div[1]/div/div")).getText();
		Assert.assertTrue(error.contains("incorrect"),"Invalid password loginfailed");
		driver.get(homepage);
	}
	//invalid username login
	@Test(priority = 9)
	public void invalidusernamelogin()
	{
		System.out.println("\n----------LOG IN WITH INVALID USERNAME ------------- ");
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10)); // Maximum wait time of 10 seconds
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
		driver.findElement(By.partialLinkText("MY ACCOUNT")).click();
		driver.findElement(By.id("username")).sendKeys("Invalidusername");
		driver.findElement(By.id("password")).sendKeys("Mike24123!");
		takess("login form");
		driver.findElement(By.name("login")).click();
		takess("invalid username login");
		String error = driver.findElement(By.xpath("//*[@id=\"post-381\"]/div/div/div[1]/div/div")).getText();
		Assert.assertTrue(error.contains("not registered"),"Invalid username login failed");
		driver.get(homepage);
	}
	
	 @DataProvider(name = "registerData")
	    public Object[][] getregisterData() {
	        return new Object[][]{
	        	 {"Mike26","mike26@example.com","Mike24123!"},  //valid ones
	        	
	        };
	        	
	        }
	@Test(priority = 6, dataProvider="registerData")
	public void register(String username,String emailid,String password)
	{
		System.out.println("\n---------- REGISTRATION ------------- ");
		//register
		driver.findElement(By.partialLinkText("MY ACCOUNT")).click();
		driver.findElement(By.id("reg_username")).sendKeys(username);
		driver.findElement(By.id("reg_email")).sendKeys(emailid);
		driver.findElement(By.id("reg_password")).sendKeys(password);
		takess("register form");
		driver.findElement(By.name("register")).click();
		
		String message=driver.findElement(By.xpath("//*[@id=\"post-381\"]/div/div/div[1]/div[1]/span[1]")).getText();
		Assert.assertTrue(message.contains("Hello"),"Registration failed");
		{
			//Assert.assertTrue(message.contains("Hello "+username),driver.findElement(By.xpath("//*[@id=\"post-381\"]/div/div/div[1]/div/div")).getText());
			takess("register sucess");
			System.out.println("Login Successfull");
			driver.findElement(By.xpath("//*[@id=\"post-381\"]/div/div/nav/ul/li[7]/a")).click(); //logout
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[1]/h2")).isDisplayed(),"Logout failed");
			System.out.println("Logged out");
			
		System.out.println("Registeration Verification Passed");	
		}
		driver.get(homepage);
	}
	@Test(priority = 7)
	public void forgotpassword()
	{
		System.out.println("\n--------Forgot Password---------");
		driver.findElement(By.partialLinkText("MY ACCOUNT")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
		driver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[1]/form/p[4]/a")).click();
		driver.findElement(By.id("user_login")).sendKeys("Mike24");
		driver.findElement(By.xpath("//*[@id=\"post-381\"]/div/div/form/p[3]/button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
		String r=driver.findElement(By.className("wc-block-components-notice-banner__content")).getText();
		Assert.assertTrue(r.contains("Password reset email has been sent"),r);
		System.out.println("Forgot Password option working");
		driver.get(homepage);
	}
}

