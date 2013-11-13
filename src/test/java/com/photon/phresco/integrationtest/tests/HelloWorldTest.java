package com.photon.phresco.integrationtest.tests;

import java.util.logging.Logger;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

import com.photon.phresco.integrationtest.model.HelloWorldModel.HelloWorldTestData;

public class HelloWorldTest extends BaseTest {
	private final static Logger log = Logger.getLogger("HelloWorldTest");
	 
	@BeforeClass (alwaysRun = true)
	public void setUpBeforeClass() throws Exception{
		
	}
	
	@Test(dataProviderClass=com.photon.phresco.integrationtest.dataproviders.ITDataProvider.class, dataProvider="positivehelloworlddata")
	public void testHelloWorldTest(HelloWorldTestData helloWorldTestData) throws Exception {
		log.info("Executing testHelloWorldTest");
		String key = helloWorldTestData.getKey();
		String expectedValue = helloWorldTestData.getExpectedValue();
		String valueFromTestMap = testMap.get(key);
		assertEquals(valueFromTestMap, expectedValue);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}
