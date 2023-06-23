package com.automation.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtility {
	
	private FileInputStream istream = null ; 
	private Properties propfile = null ; 
	
	public Properties loadFile () {
		// Constants.PROPERTIES_FILE ---> propertyFilePath ---> FileInputStream ---> Properties propfile.load()  
		this.propfile = new Properties();
		String propertyFilePath = Constants.PROPERTIES_FILE; 
		//System.out.println("Properties file path retrieved = " + propertyFilePath);
		try {
			istream = new FileInputStream(propertyFilePath) ;
			propfile.load(istream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return propfile ; 
		
	}
	
	public String getPropertyValue(String key) {
		loadFile() ; 
		String value = propfile.getProperty(key) ; 
		return value ; 
	}
	/*
	 public void writeDataToPropertyFile(File path, String key, String value) {

		Properties propFile = new Properties();
		propFile.setProperty(key, value);
		try {
			propFile.store(new FileOutputStream(path), "updating data");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	*/

}
