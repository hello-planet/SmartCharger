package com.func.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.func.service.CommonManager;

/**
 * For managing, reading, writing and parsing etc. on .json, .xml etc.
 * 
 * @author helloplanet
 * @create 2018.4.15
 *
 */
public class FileManager extends CommonManager{
	
	/**
	 * To read xml files.<br>
	 * Suitable with small xml files.
	 * @param filepath
	 * @return
	 */
	public static Document xmlParser(String filePath){
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance(); 
		Document document = null; 
	     try {  
	        DocumentBuilder builder = builderFactory.newDocumentBuilder(); 
	        document = builder.parse(new File(filePath)); 
	     } catch (ParserConfigurationException e) { 
	        e.printStackTrace();  
	     } catch (SAXException e) { 
	        e.printStackTrace(); 
	     } catch (IOException e) { 
	        e.printStackTrace(); 
	     } 
	     return document;
	}
	
	public static void jsonParser(String filePath){
		
	}
}
