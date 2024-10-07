package com.w2a.rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {

	public static void main(String[] args) throws IOException {
		
		System.getProperty("user.dir"); // get the path so when we move to another location the path will the same
		Properties  config = new Properties();
		Properties  OR = new Properties();
		FileInputStream fis =  new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
	    config.load(fis); // load the file
	    
	    fis =  new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
	    OR.load(fis);
	    
	    config.getProperty("browser"); // if print this the result is Chrome which is defined in Config.properties
	    System.out.print("test *******");
	    System.out.print(OR.getProperty("bmlBtn_CSS" ));
	}
}
