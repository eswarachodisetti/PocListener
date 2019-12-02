package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtility {

	 Properties props=new Properties();
	public  String readProperty(String str) 
	{
		String s=null;
		try {	
			
			//props.load(this.getClass().getResourceAsStream("//tmp//application.properties"));
			File f=new File("//tmp//application.properties");
			//File f=new File("C://docker//application.properties");
			
			FileReader fs=new FileReader(f);
			props.load(fs);
					
			s=props.getProperty(str);
			System.out.println("The read property is:"+s);
		} catch (FileNotFoundException e) {
		
			System.out.println("File Not Exception:"+e);
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			
			System.out.println("IOException:"+e);
			throw new RuntimeException(e.getMessage());
		}catch (Exception e) {
			
			System.out.println("Exception:"+e);
			throw new RuntimeException(e.getMessage());
		}
		return s;
	}
}
