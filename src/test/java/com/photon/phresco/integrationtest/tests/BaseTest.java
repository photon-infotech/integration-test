package com.photon.phresco.integrationtest.tests;


import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.photon.phresco.integrationtest.commons.ConfigurationReader;
import com.photon.phresco.integrationtest.commons.Constants;

public class BaseTest implements Constants {

	private final static Logger log = Logger.getLogger("BaseTest");
	protected static String environment = "";
	private static ConfigurationReader configReader;
	protected static Map<String, String> testMap = new HashMap<String, String>();
	
	@Parameters({"environment"})
	@BeforeSuite (alwaysRun = true) 
	public void beforeSuite(String aEnvironment) throws Exception{
		environment = aEnvironment;
		log.info("Environment : " + environment);
		
		if (configReader == null) {
			configReader = new ConfigurationReader(this.getClass().getResourceAsStream("/env-config.xml"));
			if (environment.isEmpty()) {
				environment = configReader.getDefaultEnvName();
			}
		}
		
		testMap.put("key1", "value1");
		testMap.put("key2", "value2");
	}
	
	
	@AfterSuite (alwaysRun = true) 
	public void cleanUpSuite() throws Exception{
		log.info(" After suite = clean up started");
		configReader = null;
	}
}
