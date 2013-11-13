package com.photon.phresco.integrationtest.dataproviders;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import com.photon.phresco.integrationtest.model.HelloWorldModel;
import com.photon.phresco.integrationtest.model.HelloWorldModel.HelloWorldTestData;

public class ITDataProvider {
	
	@DataProvider(name = "positivehelloworlddata")
	public static Iterator<Object[]> positiveHelloWorldDataProvider (ITestContext context) throws Exception {
		String inputFile = context.getCurrentXmlTest().getParameter("positivehelloworlddataxml");
		HelloWorldModel helloWorldModel = getFileContentList(inputFile, HelloWorldModel.class);
		List<HelloWorldTestData> helloWorldTestData = helloWorldModel.getHelloWorldTestData();
		return getDataObject(helloWorldTestData);
	}
	
	private static <T> Iterator<Object[]> getDataObject(List<T> objList) {
		List<Object[]> dataToBeReturned = new ArrayList<Object[]>();
		for (T userData : objList) {
			dataToBeReturned.add(new Object[] { userData } );
		}
		return dataToBeReturned.iterator();
	}
	
	@SuppressWarnings("unchecked")
	private static <T> T getFileContentList(String filenamePath, Class<T> clazz) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		InputStream resourceAsStream = clazz.getResourceAsStream(filenamePath);
		return (T) jaxbUnmarshaller.unmarshal(resourceAsStream);
	}

}
