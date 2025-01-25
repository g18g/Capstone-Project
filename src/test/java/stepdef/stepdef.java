package stepdef;


import java.io.File;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;


public class stepdef {
	WebDriver driver;
	 WebDriverWait wait; 
	 String homepage="https://westfloridaahec.org";
	 Actions link;
	 String opt_name;
	 File source;
	 TakesScreenshot ts;
	 public void takess(String name)
	    {
	    	ts = (TakesScreenshot) driver;
			source = ts.getScreenshotAs(OutputType.FILE);
			try {
			FileUtils.copyFile(source, new File("E:\\Java\\Eclipse\\Capstone_Project\\screenshots\\BDD\\" + name+ ".png"));
			} catch (Exception e) {
			e.printStackTrace();
			}
	    }
	 
	   @Before
		public void setup() {
		    driver = new ChromeDriver();
		    driver.get(homepage);
			//String pwin=driver.getWindowHandle().toString();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
			driver.manage().window().maximize();
			link=new Actions(driver);
	   }
	   
	   //navigation
	   @Given("click on {string} in the main menu")
	   public void clickporgram(String fieldname)
	   {
		   WebElement prog_menu=driver.findElement(By.partialLinkText(fieldname));
		   link.moveToElement(prog_menu).perform();
		  
	   }
	   
	   @When("select {string} from the dropdown and click on {string}")
	   public void clicktobacco(String optname,String prog_name)
	   {
		   List<WebElement> options=driver.findElements(By.xpath("//li[@id='menu-item-264']//ul[@class='sub-menu']//a"));
		   for(WebElement opt:options)
			{
				link.moveToElement(opt).click().perform();
				
				wait.until(ExpectedConditions.elementToBeClickable(By.linkText(prog_name)));
				if(prog_name.contains("QUIT TOBACCO"))
				{
			    driver.findElement(By.xpath("//*[@id=\"post-340\"]/div/div/div/div/div/div[1]/div[1]/div/a")).click();
			    break;
				}
				else if(prog_name.contains("TRAINING"))
				{
					 driver.findElement(By.xpath("//*[@id=\"post-340\"]/div/div/div/div/div/div[1]/div[3]/div/a")).click();
					 break;
				}
			
			}
	   }
	   
	   @Then("should be redirected to the {string} page")
	   public void redirectedto(String title)
	   {
		   //System.out.println(driver.getTitle());
		    Assert.assertTrue(driver.getTitle().contains(title));//  title is displayed
			takess(title);
			//System.out.println("\nNavigated Successfully to "+title+"page\n\n");
			driver.navigate().back();
	   }
	   
	   @Given("To {string} page")
	   public void i_am_on_the_page(String pageName) {
	        System.out.println("Navigating to " + pageName + " page");
	        WebElement prog_menu=driver.findElement(By.partialLinkText("PROGRAMS"));
			   link.moveToElement(prog_menu).perform();
			   List<WebElement> options=driver.findElements(By.xpath("//li[@id='menu-item-264']//ul[@class='sub-menu']//a"));
			   for(WebElement opt:options)
				{
				   if(opt.getText().contains(pageName))
				   {
					   link.moveToElement(opt).click().perform();
					   break;
				   }
				   else if(opt.getText().contains(pageName))
				   {
					   link.moveToElement(opt).click().perform();
					   break; 
				   }
				}
	    }
	   
	   //healthy aging form fill
	   @When("Fill in the form with {string}, {string}, {string}, {string}")
	   public void filldetails(String fname, String lname,String phn ,String eid)
	   {
		   wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"post-500\"]/div/div[1]/div/div[1]/div/form/div/div/header/div/h1")));
		   driver.findElement(By.xpath("//input[@id='cog-input-auto-0']")).sendKeys(fname);
			driver.findElement(By.id("cog-input-auto-1")).sendKeys(lname);
			driver.findElement(By.id("cog-1")).sendKeys(phn);
			driver.findElement(By.id("cog-2")).sendKeys(eid);
	   }
	   
	   @When("Select \"Tai Chi for Arthritis\" Program checkbox")
	   public void programcheckbox() throws InterruptedException
	   {
		   driver.findElement(By.xpath("//*[@id=\"post-500\"]/div/div[1]/div/div[1]/div/form/div/div/div[1]/div/div[4]/fieldset[1]/div[1]/div[1]/div/label[1]/span[1]/span")).click();
		   Thread.sleep(1000);
	   }
	   
	   @When("Select \"Santa Rosa\" from the country radio buttons")
	   public void country() throws InterruptedException
	   {
			driver.findElement(By.xpath("//*[@id=\"post-500\"]/div/div[1]/div/div[1]/div/form/div/div/div[1]/div/div[4]/fieldset[2]/div[1]/div[1]/div/label[2]")).click();
			 Thread.sleep(1000);
	   }
	   
	   @When("Select \"No preference\" radiobox")
	   public void preference()
	   {
		   driver.findElement(By.xpath("//*[@id=\"post-500\"]/div/div[1]/div/div[1]/div/form/div/div/div[1]/div/div[5]/fieldset/div[1]/div[1]/div/label[3]/span[2]")).click();
		   driver.findElement(By.xpath("//*[@id=\"cog-6\"]")).sendKeys("An individual looking to take classes wherever they may be available in my county"); 
	   }
	   @When("Select Email Notification Checkbox and click on submit")
	   public void submit() throws InterruptedException
	   {
		   driver.findElement(By.xpath("//*[@id=\"post-500\"]/div/div[1]/div/div[1]/div/form/div/div/div[1]/div/div[8]/div/div[1]/label/span[2]")).click();
		   driver.findElement(By.id("cog-7")).sendKeys("From my friend");
		   takess("formfill");
		   Thread.sleep(2000);
		  WebElement button = driver.findElement(By.xpath("//*[@id=\"post-500\"]/div/div[1]/div/div[1]/div/form/div/div/div[1]/div/div[10]/button/span"));
	        button.click();		   
	   }
	   @Then("Should see Form Successfully sumbitted message")
	   public void success() throws InterruptedException {
		  Thread.sleep(3000);
		   String message=driver.findElement(By.xpath("//*[@id=\"post-500\"]/div/div[1]/div/div[1]/div/form/div/div/div[1]/div[1]/div/div[1]")).getText();
		   Assert.assertTrue(message.contains("Thank you for filling out the form. Your response has been recorded"),"Form Submission Failed");
		  // System.out.println("\nForm Successfully Submitted");
	   }
	   
	   //covering florida
	    @When("User Enters the details")
	    public void user_enters_details() throws InterruptedException {
	    	 driver.switchTo().frame(1);
	   	  driver.findElement(By.name("zipcode")).sendKeys("32006");
	   	  Select distance=new Select(driver.findElement(By.id("milesAway")));
	   	  distance.selectByIndex(2);
	   	  
	   	  Select language=new Select(driver.findElement(By.id("lang")));
	   	  language.selectByIndex(2);
	   	  
	   	  List<WebElement> checkbox=driver.findElements(By.cssSelector("input[type='checkbox'"));
	   	  for(WebElement x:checkbox) {
	   		  x.click();
	   	  }
	   	  
	   	  driver.findElement(By.name("in_person")).click();
	   	  driver.findElement(By.id("submit_button")).click();
	   	  
	    }
	    @Then("User should see all the places")
	    public void user_gets_all_the_places() {
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("locations")));
	    	Assert.assertNotNull(driver.findElement(By.className("locations")));
	    	System.out.println("\nForm Successfully Submitted\n\n");
		  }
	  

	
	   //login&registration
	   
	    @Given("to {string} page")
	    public void myaccountpage(String pageName)
	    {
	    	driver.findElement(By.partialLinkText("MY ACCOUNT")).click();
	    }
	    
	    //register
	    @When("Details {string}, {string}, {string} into the form")
	    public void enterdetails(String username,String emailid,String password)
	    {
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
	    	driver.findElement(By.id("reg_username")).sendKeys(username);
			driver.findElement(By.id("reg_email")).sendKeys(emailid);
			driver.findElement(By.id("reg_password")).sendKeys(password);
	    }
	    @When("click register")
	    public void register()
	    {
	    	driver.findElement(By.name("register")).click();
	    }
	    @Then("redirected to {string} dashboard")
	    public void registerdashboard(String username)
	    {
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
	    	String message=driver.findElement(By.xpath("//*[@id=\"post-381\"]/div/div/div[1]/div[1]/span[1]")).getText();
				Assert.assertTrue(message.contains("Hello "+username),"Registeration Failed");
				takess("register");
				driver.findElement(By.xpath("//*[@id=\"post-381\"]/div/div/nav/ul/li[7]/a")).click(); //logout
			System.out.println("Registeration Successfull\n\n\n");	
			driver.get(homepage);
	    }
	    
	    //login
	    @When("details {string}, {string} into the form")
	    public void logindetails(String username,String password)
	    {
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
	    	driver.findElement(By.id("username")).sendKeys(username);
			driver.findElement(By.id("password")).sendKeys(password);
	    }
	    @When("click login")
	    public void login()
	    {
	    	driver.findElement(By.name("login")).click();
	    }
	   
	    @Then("redirected to {string} login dashboard")
	    public void logindashboard(String username)
	    {
	    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
	    	String message=driver.findElement(By.xpath("//*[@id=\"post-381\"]/div/div/div[1]/div[1]/span[1]")).getText();
			
				Assert.assertTrue(message.contains("Hello "+username),"Login Failed");
				takess("login");
				System.out.println("\t\tLogin Successfull");
				driver.findElement(By.xpath("//*[@id=\"post-381\"]/div/div/nav/ul/li[7]/a")).click();   //logout
				Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[1]/h2")).isDisplayed());
				System.out.println("\t\tLogged out");
				takess("logout");
	
			driver.get(homepage);
	    }
	    
	    //user already exist
	    
	    @Then("User should get account already exist error")
	    public void user_should_get_account_already_exist_error() throws InterruptedException {
	    	Thread.sleep(1000);
	    	String error=driver.findElement(By.xpath("//*[@id=\"post-381\"]/div/div/div[1]/div/div")).getText();
	    	//boolean contains=error.contains("Error: An account is already registered with jason19@gmail.com. Please log in or use a different email address");
	    	Assert.assertTrue(error.contains("Error: An account is already registered with jason19@gmail.com. Please log in or use a different email address"));
	    	System.out.println("\nUser Already Exist \n\n");
	    	driver.quit();
	    }
	    
	   


	    //forgot password
	    @When("Click on forgot password")
	    public void forgotpass()
	    {
	    	driver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[1]/form/p[4]/a")).click();
	    }
	    @When("Enter the {string} username and click on submit")
	    public void enterid(String usern)
	    {
	    	driver.findElement(By.id("user_login")).sendKeys(usern);
			driver.findElement(By.xpath("//*[@id=\"post-381\"]/div/div/form/p[3]/button")).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
	    }
	    @Then("should see password reset link sent to email message")
	    public void fpsuccess()
	    {
	    	String r=driver.findElement(By.className("wc-block-components-notice-banner__content")).getText();
			Assert.assertTrue(r.contains("Password reset email has been sent"),r);
			System.out.println("FORGOT PASSWORD SUCCESS\n\n\n");
	    }
	    
	    @After
	    public void quit()
	    	{
	    	driver.close();
	    	}
	
}
