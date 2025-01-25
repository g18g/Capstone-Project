package TestNG;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportListener  implements ITestListener {
	
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter sparkReporter;
    ExtentTest test;
    public void onTestStart(ITestContext context) {
        // Set up Extent Reports
        sparkReporter = new ExtentSparkReporter("E:\\Java\\Eclipse\\Capstone_Project\\reports\\CapstoneReport.html");
        sparkReporter.config().setDocumentTitle("Automation Report"); // Title of the report
        sparkReporter.config().setReportName("Capstone Report"); // Name of the report
        sparkReporter.config().setTheme(Theme.DARK); // Theme of the report
      
        extent.attachReporter(sparkReporter);

        // Adding System Information
        extent.setSystemInfo("Computer Name", "localhost");
        extent.setSystemInfo("Tester", "Gagan");
        extent.setSystemInfo("OS", "Windows");
    }
	    
@Override
	 public void onTestSuccess(ITestResult result) {
	       
		 test = extent.createTest(result.getMethod().getMethodName());
	        test.log(Status.PASS, "Test case PASSED is: " + result.getMethod().getMethodName());  // Log success status
	    }
@Override 
	 public void onTestFailure(ITestResult result) {
	        // Log failure status and cause
	        test.log(Status.FAIL, "Test case FAILED is: " + result.getMethod().getMethodName());
	        test.log(Status.FAIL, "Test case FAILED cause: " + result.getThrowable());
	 }
@Override
	 public void onTestSkipped(ITestResult result) {
	        // Log skipped status
	        test.log(Status.SKIP, "Test case SKIPPED is: " + result.getMethod().getMethodName());
	 }
@Override
	 public void onFinish(ITestContext context) {
	        // Write all the logs into the report
	        extent.flush();
	 }
	 
}
