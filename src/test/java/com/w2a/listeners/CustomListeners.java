package com.w2a.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;

import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;
import com.w2a.base.TestBase;
import com.w2a.utilities.MonitoringMail;
import com.w2a.utilities.TestConfig;
import com.w2a.utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener, ISuiteListener{

	public String messageBody;
	@Override
	public void onTestStart(ITestResult result) {
		test = rep.startTest(result.getName().toUpperCase());
		//runmode - y
		if(!TestUtil.isTestRunnable(result.getName(), excel)) {
			throw new SkipException("Skipping the test "+result.getName().toUpperCase()+ " as the run mode is No");
		}
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, result.getName().toUpperCase() + " PASS");
		rep.endTest(test);
		rep.flush();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		System.setProperty("org.uncommons.reportng.escape-output", "false"); 
		try {
			TestUtil.captureScreenshot(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.log(LogStatus.FAIL, result.getName().toUpperCase() + "Fail with exception : "+result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		Reporter.log("Capturing screenshot !!!");
		//target =  blank: open new tab whening clicking screenshot link
		// Reporter.log("<a target=\"_blank\" href=\"D:\\screenshot\\Screenshot.jpg\">Screenshot</a>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
		 Reporter.log("<br>"); 
		 Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+"  height=200 width=200></a>");
		 rep.endTest(test);
		rep.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, result.getName().toUpperCase() + " Skipped the test as the run mode is NO");
		rep.endTest(test);
		rep.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		MonitoringMail mail = new MonitoringMail();
		
		try {
			 messageBody = "http://" + InetAddress.getLocalHost().getHostAddress() + ":8080/job/DataDrivenLiveProject/Extent_20Report/" ;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			mail.sendMail(TestConfig.server, TestConfig.from,TestConfig.to, TestConfig.subject, messageBody);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
