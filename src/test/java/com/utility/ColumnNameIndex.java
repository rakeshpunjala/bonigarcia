package com.utility;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;

public class ColumnNameIndex {
	
	static WebDriver driver;
 
public static void main(String[] args) throws InterruptedException {
	
	System.out.println(getindexnumber(""));
	
}
 
public static Object getindexnumber(String coumnName) throws InterruptedException {

  Object indexNumber = "Column Name is Blank";
 
 WebDriverManager.chromedriver().setup();
 
  driver = new ChromeDriver();  
  driver.get("https://support.smartbear.com/testleft/docs/samples/web-orders-basic.html");  
  driver.manage().window().maximize(); 
  driver.findElement(By.xpath("//a[text()='http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx']")).click(); 
  String main = driver.getWindowHandle();  
  Set<String> childWindows = driver.getWindowHandles();  
  for(String child:childWindows) {
  if(child!=main) {
  driver.switchTo().window(child);	 
  }}  
  driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");  
  driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");  
  driver.findElement(By.id("ctl00_MainContent_login_button")).click();
  List<WebElement> columnsElements = driver.findElements(By.xpath("//*[@class='SampleTable']//tr/th"));  
  List<String>columnsNames = columnsElements.stream().map(a->a.getText()).collect(Collectors.toList()); 
  for(String eachColumn : columnsNames) {
  if(coumnName==null || coumnName.isEmpty()){
  return indexNumber;  
  }
  if(eachColumn.equalsIgnoreCase(coumnName)) {
  indexNumber = (columnsNames.indexOf(eachColumn)+1);
  }
  }
  driver.close();
  driver.switchTo().window(main);
  driver.close();

 
  return indexNumber;
 
	
}

}
