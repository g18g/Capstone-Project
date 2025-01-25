package TestNG;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportListener2  implements ITestListener {
	
	 public static ExtentReports extent = new ExtentReports();
	    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	    public ExtentSparkReporter sparkReporter;
	    
	 @Override
	    public void onStart(ITestContext context)
	    {
	        // Set up Extent Reports
	        sparkReporter = new ExtentSparkReporter("E:\\Java\\Eclipse\\Capstone_Project\\reports\\CapstoneReport.html");
	        sparkReporter.config().setDocumentTitle("Automation Report"); // Title of the report
	        sparkReporter.config().setReportName("Capstone Report"); // Name of the report
	        sparkReporter.config().setTheme(Theme.DARK);
	        extent.attachReporter(sparkReporter);
	        
	          // Adding System Information
	        extent.setSystemInfo("Computer Name", "localhost");
	        extent.setSystemInfo("Tester", "Gagan");
	        extent.setSystemInfo("OS", "Windows");
	    }
	 @Override
	    public void onTestStart(ITestResult result) {
	        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
	         test.set(extentTest);
	 }
	  @Override
	    public void onTestSuccess(ITestResult result) {
	       test.get().log(Status.PASS, "Test case PASSED is: " + result.getMethod().getMethodName());
	    }
	  @Override
	    public void onTestFailure(ITestResult result) {
	        test.get().log(Status.FAIL, "Test case FAILED is: " + result.getMethod().getMethodName());
	         test.get().log(Status.FAIL, result.getThrowable());
	        Object currentClass = result.getInstance();
	  }
	  @Override
	    public void onTestSkipped(ITestResult result) {
	        test.get().log(Status.SKIP, "Test case SKIPPED is: " + result.getMethod().getMethodName());
	    }
	 
@Override
	 public void onFinish(ITestContext context) {
	        // Write all the logs into the report
	        extent.flush();
	 }
	 
}
