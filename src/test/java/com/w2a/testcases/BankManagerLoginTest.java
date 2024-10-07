package com.w2a.testcases;


import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class BankManagerLoginTest extends TestBase{
	
	@Test
public void bankManagerLoginTest() throws InterruptedException, IOException {
		
		verifyEquals("abc", "xyz");
		Thread.sleep(500);
		// screenshot is generated under link and user can click on to open image instead of showing tag "<a href="c:\\...">Screenshot</a>"
		//System.setProperty("org.uncommons.reportng.escape-output", "false"); 
		log.debug("Inside Login Test !!1");
		click("bmlBtn_CSS");
	Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))),"Login not successful");
	log.debug("Login successfully executed !!!");
	
	//Reporter.log("Login successfully executed !!!");
	//target =  blank: open new tab whening clicking screenshot link
	// Reporter.log("<a target=\"_blank\" href=\"D:\\screenshot\\Screenshot.jpg\">Screenshot</a>");
	// Reporter.log("<br>"); 
	// Reporter.log("<a target=\"_blank\" href=\"D:\\screenshot\\Screenshot.jpg\"><img src=\"D:\\screenshot\\Screenshot.jpg\"  height=200 width=200></a>");
}
}
