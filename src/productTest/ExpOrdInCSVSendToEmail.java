package productTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.openqa.selenium.Alert;
 
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;


public class ExpOrdInCSVSendToEmail {
	public static WebDriver driver;

  @Test//(priority=0 ,enabled=false)
  public void exportInCSV() throws InterruptedException, AWTException {
	  
	  // 1.driver
	  	 driver.get(Util.BASE_URL2);
	  	 
	  //2. Login
	   	 driver.findElement(By.id("username")).clear();
	  	 driver.findElement(By.id("username")).sendKeys("user01");
	  	 driver.findElement(By.id("login")).clear();
	  	 driver.findElement(By.id("login")).sendKeys("guru99com");
	  	 driver.findElement(By.xpath("*//*[@id='loginForm']//*[@class='form-button']")).submit();
	     Thread.sleep(3000);
	     
	  //close Popup window
	      driver.findElement(By.xpath("//*[@id='message-popup-window']//a/span")).click();
	      
	  //3. Sales-> Order Menu""
		  Thread.sleep(3000);	 
	      Actions action = new Actions(driver);
	   	//	  Thread.sleep(1000);
	      WebElement element = driver.findElement(By.linkText("Sales"));
	   //   System.out.println("SALES Web element identified " ); 

		  Thread.sleep(3000);
	     

          action.moveToElement(element).build().perform();
	  //    System.out.println("SALES Web element CLICKED " ); 

          Thread.sleep(3000);
          WebElement element1 = driver.findElement(By.xpath("//span[contains(text(),'Orders')]"));
	      System.out.println("ORDERS Web element identified " ); 

		  Thread.sleep(3000);

	      action.moveToElement(element1).click().build().perform();
	      System.out.println("ORDERS Web element CLICKED " ); 

		  Thread.sleep(2000);
		  
	  //4.Sort by name.
		  Select drpSortBy = new Select(driver.findElement(By.id("sales_order_grid_export"))); 
		  Thread.sleep(1000);

		  drpSortBy.selectByVisibleText("CSV");
	//	  driver.findElement(By.cssSelector("button[title=\'Export\']")).click();
		  
		  
		  
		  // explicit wait - to wait for the compose button to be click-able
		           WebDriverWait wait = new WebDriverWait(driver,30);
		           wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[title=\'Export\']")));
		           // click on the compose button as soon as the "compose" button is visible
		           driver.findElement(By.cssSelector("button[title=\'Export\']")).click();		  

		  
		  
		  
		  
		  Robot rb = new Robot();
		// Press arrow down key to select save radio button.
		 			Thread.sleep(9000);	
					rb.keyPress(KeyEvent.VK_DOWN);
				//    System.out.println("VK_DOWN Web element PRESSED " ); 

					rb.keyRelease(KeyEvent.VK_DOWN);
			//	    System.out.println("VK_DOWN Web element RELEASED " ); 

					Thread.sleep(2000);	

	 				// Press tab key three time to navigate to Save button. 
					for(int i=0;i<1;i++)
					{
						Thread.sleep(2000);	
						rb.keyPress(KeyEvent.VK_TAB);
					//    System.out.println("VK_TAB Web element RELEASED " );
					} 	
					

	         rb.keyPress(KeyEvent.VK_ENTER);
			    System.out.println("VK_ENTER Web element PRESSED " ); 

	         rb.keyRelease(KeyEvent.VK_ENTER);
			    System.out.println("VK_ENTER Web element PRESSED " ); 

		  Thread.sleep(3000);

 

  }

  
  @Test//(priority=1 ,enabled=false)
  public void verifyInvoicePrinted() throws InterruptedException, ParseException, FileNotFoundException{
   
  // 1.driver
  	 driver.get(Util.BASE_URL2);
  	 
  //2. Login
   	 driver.findElement(By.id("username")).clear();
  	 driver.findElement(By.id("username")).sendKeys("user01");
  	 driver.findElement(By.id("login")).clear();
  	 driver.findElement(By.id("login")).sendKeys("guru99com");
  	 driver.findElement(By.xpath("*//*[@id='loginForm']//*[@class='form-button']")).submit();
     Thread.sleep(3000);
     
  //close Popup window
      driver.findElement(By.xpath("//*[@id='message-popup-window']//a/span")).click();
      
  //3. Sales-> Order Menu""
	  Thread.sleep(3000);	 
      Actions action = new Actions(driver);
   	//	  Thread.sleep(1000);
      WebElement element = driver.findElement(By.linkText("Sales"));
      System.out.println("SALES Web element identified " ); 

	  Thread.sleep(3000);
	  action.moveToElement(element).build().perform();
      System.out.println("SALES Web element CLICKED " ); 

      Thread.sleep(3000);
      WebElement element1 = driver.findElement(By.xpath("//span[contains(text(),'Orders')]"));
      System.out.println("ORDERS Web element identified " ); 

	  Thread.sleep(3000);

      action.moveToElement(element1).click().build().perform();
      System.out.println("ORDERS Web element CLICKED " ); 

	  Thread.sleep(3000);
	  
	  Select drpSalesOrder = new Select(driver.findElement(By.id("sales_order_grid_filter_status")));
	  drpSalesOrder.selectByVisibleText("Canceled");
	
	  Thread.sleep(5000);

	 // driver.findElement(By.cssSelector("td.a-center input.massaction-checkboxinput")).click();
//	driver.findElement(By.xpath("//*[@id='sales_order_grid_table']//td[@class='a-center']/input[@class='massaction-checkboxinput']")).click();
	driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div[3]/div/div[2]/div/table/tbody/tr[1]/td/input")).click();
 
	driver.findElement(By.cssSelector("#sales_order_grid button[title=\'Search\']")).click();
	// 6.In Actions, select ""Print Invoices"". Click Submit
	Select drpSalesOrderPr = new Select(driver.findElement(By.id("sales_order_grid_massaction-select")));
	  drpSalesOrderPr.selectByVisibleText("Print Invoices");
		driver.findElement(By.cssSelector("#sales_order_grid button[title=\'Submit\']")).click();
		String errMsgAct ="There are no printable documents related to selected orders.";
		String errMsgExp =driver.findElement(By.cssSelector("#messages ul.messages span")).getText();
		try {
	    	 AssertJUnit.assertEquals(errMsgAct, errMsgExp );
       }catch(Exception e) {
  	      e.printStackTrace();
       }

		  Thread.sleep(3000);

  	  // 8.In the status field select ""Complete"". Click Search
		    drpSalesOrder = new Select(driver.findElement(By.id("sales_order_grid_filter_status")));

	  drpSalesOrder.selectByVisibleText("Complete");
	  Thread.sleep(3000);	
	  driver.findElement(By.cssSelector("#sales_order_grid button[title=\'Search\']")).click();


	  // 9.Select the checkbox next to first order
		driver.findElement(By.xpath("/html/body/div[1]/div[4]/div/div[3]/div/div[2]/div/table/tbody/tr[1]/td/input")).click();
		
		  
		  

	  // 10.In Actions, select ""Print Invoices"". Click Submit
	    drpSalesOrderPr = new Select(driver.findElement(By.id("sales_order_grid_massaction-select")));

		drpSalesOrderPr.selectByVisibleText("Print Invoices");
		driver.findElement(By.cssSelector("#sales_order_grid button[title=\'Submit\']")).click();
	//	String pdfCreated ="D:\\JES\\SELENIUM\\invoice"+Util.dateprint();
		//11. Verify invoice is downloaded" 
		Util.fileCreated();

  }
  
  
  @Test// (priority=1 ,enabled=false) 
 public void  verifyReviewMsg() throws InterruptedException    {
	  // 1.Goto http://live.guru99.com
		 driver.get(Util.BASE_URL);
		 
		 // 2. Go To Link - http://live.guru99.com/index.php/review/product/list/id/1/
	 	 driver.get(Util.BASE_URL3);

		// 3. Fill the required review and submit it
	 	 String reviewAct="NOKIA Good Performance";
	 	// String summaryAct="GOOD ONE";
		 driver.findElement(By.id("review_field")).sendKeys("NOKIA Good Performance");
		 driver.findElement(By.id("summary_field")).sendKeys("GOOD ONE");
		 driver.findElement(By.id("nickname_field")).sendKeys("RICK");
		 driver.findElement(By.cssSelector("button[title=\'Submit Review\']")).submit();
		 
		// 4.Go to http://live.guru99.com/index.php/backendlogin

		 driver.get(Util.BASE_URL2);

	 	//5. Login
	   	 driver.findElement(By.id("username")).clear();
	  	 driver.findElement(By.id("username")).sendKeys("user01");
	  	 driver.findElement(By.id("login")).clear();
	  	 driver.findElement(By.id("login")).sendKeys("guru99com");
	  	 driver.findElement(By.xpath("*//*[@id='loginForm']//*[@class='form-button']")).submit();
	     Thread.sleep(3000);
	     
	   //close Popup window
	      driver.findElement(By.xpath("//*[@id='message-popup-window']//a[@title='close']/span")).click();
		  Thread.sleep(3000);

		 
		 //  6. Go to Catalogue -> Reviews and Ratings -> Customer Reviews ->Pending Reviews Menu
	   
	       Actions action = new Actions(driver); 
	       WebElement element = driver.findElement(By.linkText("Catalog"));
	       Thread.sleep(1000);
	       action.moveToElement(element).build().perform();

	       element =  driver.findElement(By.linkText("Reviews and Ratings"));
		   action.moveToElement(element).build().perform();
		   Thread.sleep(1000);

		   element =  driver.findElement(By.linkText("Customer Reviews")) ;
		   action.moveToElement(element).build().perform();
			Thread.sleep(1000);

	       element =driver.findElement(By.linkText("Pending Reviews"));
	        Thread.sleep(3000);

	       action.moveToElement(element).click(element).build().perform();
	       	 	   Thread.sleep(3000);

			      
		 // 7.Sort table by Id and select comment then click on Edit link
	 	//  driver.findElement(By.linkText("ID")).click();
	 	 //  Thread.sleep(2000);
	 	   
	 	   action = new Actions(driver); 
	       element = 	 	  driver.findElement(By.xpath("//*[@id='html-body']//*[@id='reviwGrid_table']//td[6]")) ;
 
	 	   action.moveToElement(element).build().perform();

	       Thread.sleep(1000);
	 //	  driver.findElement(By.linkText("Review")).click();
	  //	  driver.findElement(By.xpath("//*[@id='html-body']//*[@id='reviwGrid_table']//td[6]")).click();
	 	   Thread.sleep(1000);
	 	  driver.findElement(By.linkText("Edit")).click();
	 	   Thread.sleep(2000);
	 	  
	 	   // 8.Change status to 'Approved' and click ""Save Review""

	 	  
	 	 Select drpStatusBy = new Select(driver.findElement(By.id("status_id"))); 
		  Thread.sleep(1000);

		  drpStatusBy.selectByVisibleText("Approved");
		  Thread.sleep(2000);

		  driver.findElement(By.id("save_button")).click();
	
		  // 9.Go to http://live.guru99.com/. Click Mobile Menu

			 driver.get(Util.BASE_URL);
			 driver.findElement(By.linkText("Mobile")).click();
			 
			 // 10. Click on Sony Xperia image.

	         driver.findElement(By.xpath("/*[@id='top']//li[@class='item last'][./a[@class='product-image' and @title= 'Xperia']/img[@alt='Xperia']]")).click(); 
			  Thread.sleep(2000);

			//  11. In detail page click on Review tab at bottom of page
				 Util.scrollDown();
				  Thread.sleep(3000);

	    
	    driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div[2]/div[2]/ul/li[2]/span")).click();
     Thread.sleep(1000);
	
	 
 //12. Verify the review comment is shown" 
     
     String reviewExp=driver.findElement(By.xpath("//*[@id='customer-reviews']/dl/dd")).getText();
     System.out.println("ORDERS Web element CLICKED "+reviewExp ); 

    try {
    	 AssertJUnit.assertEquals(reviewAct, reviewExp.substring(0,reviewAct.length()));

	//  Assert.assertTrue(reviewAct.contains(reviewExp),"Yes it is FOUND");
      }catch (Exception e){
 	      e.printStackTrace();
    
     } 

  }
  
  
 @Test//(priority=1 ,enabled=false)
 void demo() throws InterruptedException{
	 driver.get("http://live.guru99.com/index.php/mobile/sony-xperia.html");
	 
	    Util.scrollDown();
	     Thread.sleep(2000);
	   //  driver.findElement(By.linkText("Reviews")).click();
 		// Actions action = new Actions(driver); 
		//  WebElement element =driver.findElement(By.cssSelector("ul.toggle-tabs li.last span.Reviews"));
 		 //   action.moveToElement(element).click().build().perform();
	//	  driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div[2]/div[2]/ul/li[2]/contains(text(),'Reviews')")).click();
 		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div[2]/div[2]/ul/li[2]/span")).click();

	    //   element = driver.findElement(By.linkText("Reviews"));
		//    action.moveToElement(element).click().build().perform();
				 	 //   action.moveToElement(element).build().perform();

	 
 		
 }
 
  
  
  @Test//(priority=1 ,enabled=false)
 public void verifySorting() throws InterruptedException {
	 
	  
	 //  * "1.Go to http://live.guru99.com/index.php/backendlogin

	 /*	
	 5.Verify the sort is working as expected"*/
	  // 1.driver
  	 driver.get(Util.BASE_URL2);
  	 
  //2.  Login with credentials provided
   	 driver.findElement(By.id("username")).clear();
  	 driver.findElement(By.id("username")).sendKeys("user01");
  	 driver.findElement(By.id("login")).clear();
  	 driver.findElement(By.id("login")).sendKeys("guru99com");
  	 driver.findElement(By.xpath("*//*[@id='loginForm']//*[@class='form-button']")).submit();
     Thread.sleep(3000);
     
  //close Popup window
      driver.findElement(By.xpath("//*[@id='message-popup-window']//a/span")).click();
      
  //3. Sales-> Invoices Menu""
	  Thread.sleep(3000);	 
      Actions action = new Actions(driver);
   	  Thread.sleep(1000);
      WebElement element = driver.findElement(By.linkText("Sales"));

	  Thread.sleep(2000);
	  action.moveToElement(element).build().perform();

      Thread.sleep(2000);
      WebElement element1 = driver.findElement(By.xpath("//span[contains(text(),'Invoices')]"));

	  Thread.sleep(1000);

      action.moveToElement(element1).click().build().perform();
   //   System.out.println("Invoices Web element CLICKED " ); 
      
 	// 4.Sort Invoice Date Column in ascending and descending order
	   Thread.sleep(3000);
	   driver.findElement(By.linkText("Invoice Date")).click();
	  
	 	WebElement table = driver.findElement(By.xpath("//*[@id='sales_invoice_grid_table']/tbody")) ;
	   
	//	WebElement table = driver.findElement(By.xpath("//*[@id='sales_invoice_grid_table']/tr[@class='odd' or @class='even']")) ;

	   ArrayList<String> obtainedList = new ArrayList<>(); 
		List<WebElement> elementList= table.findElements(By.xpath("tr"));
		for(WebElement we:elementList){
		   obtainedList.add(we.getText());
		}
		ArrayList<String> sortedList = new ArrayList<>();   
		for(String s:obtainedList){
		System.out.println("SALES Web element INVOICE "+s ); 

		sortedList.add(s);
		}
		for(String s:sortedList){
			System.out.println("SORTED ASCENDING   Web element INVOICE "+s ); 

			}
		
		Collections.sort(sortedList);
		AssertJUnit.assertTrue(sortedList.equals(obtainedList));
		
	 	// 4.Sort DESCENDING Invoice Date Column in ascending and descending order
		   Thread.sleep(3000);
		   driver.findElement(By.linkText("Invoice Date")).click();
		   
		   

		  table = driver.findElement(By.xpath("//*[@id='sales_invoice_grid_table']/tbody")) ;
		   
		//	WebElement table = driver.findElement(By.xpath("//*[@id='sales_invoice_grid_table']/tr[@class='odd' or @class='even']")) ;

		   obtainedList = new ArrayList<>(); 
		    elementList= table.findElements(By.xpath("tr"));
			for(WebElement we:elementList){
			   obtainedList.add(we.getText());
			}
		    sortedList = new ArrayList<>();   
			for(String s:obtainedList){
			System.out.println("SALES Web element INVOICE "+s ); 

			sortedList.add(s);
			}


	 	Collections.sort(sortedList, Collections.reverseOrder());
	 	
		for(String s:sortedList){
			System.out.println("SORTED DESCENDING   Web element INVOICE "+s ); 
			}

		AssertJUnit.assertTrue(sortedList.equals(obtainedList));
 }
  
  
  
  
  
  
 @Test//(priority=1 ,enabled=false)
  public void prodPriceOnConsole() throws InterruptedException {
	  


  //1. Go to http://live.guru99.com/index.php/
    driver.get(Util.BASE_URL4);
   
  
 //2. Click on Advance Search
 driver.findElement(By.linkText("Advanced Search")).click();
 
 Thread.sleep(2000);


 //3. In Price field enter range 0-150. Click Search
 driver.findElement(By.id("price")).clear();
 driver.findElement(By.id("price")).sendKeys("0");
 
 driver.findElement(By.id("price_to")).clear();
 driver.findElement(By.id("price_to")).sendKeys("150");
 
 Thread.sleep(2000);
// Util.scrollDown();


 //driver.findElement(By.linkText("Search")).click();
 driver.findElement(By.xpath("//*[@id='form-validate']//button[@class='button']")).click();

 
//  * 4. Note the Price and Product Name in the result. Print on console
 
  
 WebElement element 		  =	driver.findElement(By.xpath("/*[@id='top']//div[@class='category-products']/ul"));
 List<WebElement> elementList = element.findElements(By.xpath("li[@class='item last']"));
 System.out.println("MOBILES NUMB  "+elementList.size() ); 
 
 for(WebElement we:elementList){

	 String mobileName = we.findElement(By.xpath("div/h2/a")).getText();
	    System.out.println("MOBILES FOUND  "+mobileName ); 

	//    Thread.sleep(2000);

	String  mobCost     = we.findElement(By.xpath("div/div//span[starts-with(@id,'product-price')]")).getText();
    System.out.println("MOBILES FOUND  "+mobileName+mobCost ); 
    
 }
 
 
 // 5. Again, In Price field enter range 151-1000. Click Search
 driver.findElement(By.linkText("Advanced Search")).click();
 
 Thread.sleep(2000);
 
    driver.findElement(By.id("price")).clear();
    driver.findElement(By.id("price")).sendKeys("151");
    
    driver.findElement(By.id("price_to")).clear();
    driver.findElement(By.id("price_to")).sendKeys("1000");
    
    Thread.sleep(2000);
    //driver.findElement(By.linkText("Search")).click();
    driver.findElement(By.xpath("//*[@id='form-validate']//button[@class='button']")).click();

    //6. Note the Price and Product Name in the result. Print on console" */

   element 		  =	driver.findElement(By.xpath("/*[@id='top']//div[@class='category-products']/ul"));
   elementList = element.findElements(By.xpath("li[@class='item last']"));
    System.out.println("MOBILES NUMB  "+elementList.size() ); 
    
    for(WebElement we:elementList){

   	 String mobileName = we.findElement(By.xpath("div/h2/a")).getText();

   	String  mobCost     = we.findElement(By.xpath("div/div//span[starts-with(@id,'product-price')]")).getText();
       System.out.println("MOBILES FOUND  "+mobileName+"   "+mobCost ); 
       
    }

   

 
 }
 
 
 @Test//(priority=1 ,enabled=true)

 public void verifyDisAbleAndBlankField() throws InterruptedException {
	 
	 //  * "1.Go to http://live.guru99.com/index.php/backendlogin

	 driver.get(Util.BASE_URL2);
  	 
  //2.  Login with credentials provided
   	 driver.findElement(By.id("username")).clear();
  	 driver.findElement(By.id("username")).sendKeys("user01");
  	 driver.findElement(By.id("login")).clear();
  	 driver.findElement(By.id("login")).sendKeys("guru99com");
  	 driver.findElement(By.xpath("*//*[@id='loginForm']//*[@class='form-button']")).submit();
     Thread.sleep(3000);
     
  //close Popup window
      driver.findElement(By.xpath("//*[@id='message-popup-window']//a/span")).click();
      
  //3.Go to Customers-> Manage Customers menu

	  Thread.sleep(3000);	 
      Actions action = new Actions(driver);
   	  Thread.sleep(1000);
      WebElement element = driver.findElement(By.linkText("Customers"));

	  Thread.sleep(2000);
	  action.moveToElement(element).build().perform();

      Thread.sleep(2000);
      WebElement element1 = driver.findElement(By.xpath("//span[contains(text(),'Manage Customers')]"));

	  Thread.sleep(1000);
	  action.moveToElement(element1).click().build().perform();
	  
//	  
	  //4.Open any customer's detail by clicking on view link in the grid
	  driver.findElement(By.linkText("Edit")).click();
	 
	  //5.Click on 'Account Information' tab for the customer's detail page
	   driver.findElement(By.linkText("Account Information")).click();
	   
	   //6. Verify disabled fields
	
	   WebElement table = driver.findElement(By.xpath("//*[@id='customer_info_tabs_account_content']/*[@class='entry-edit']") );
	   List<WebElement> elementList= table.findElements(By.xpath(".//input|.//select"));

	   //List<WebElement> elementList= table.findElements(By.xpath("//descendant::input"));
	   //List<WebElement> elementList= table.findElements(By.tagName("input"));
	   // List<WebElement> elementList= table.findElements(By.cssSelector("input,select"));
	   // List<WebElement> elementList= table.findElements(By.xpath("div[@id='_accountpassword_fieldset' or @id='_accountbase_fieldset']//table[@class='form-list']//input|//select"));
	   // List<WebElement>elementList = table.findElements(By.cssSelector("#_accountpassword_fieldset , #_accountbase_fieldset .form-list tbody tr input,select"));

		  Thread.sleep(1000);
	       System.out.println("FIELD size  "+elementList.size()  ); 

		for(WebElement we:elementList){
		 	String  inVal = null ;
	 	   inVal = we.getAttribute("value") ;
	 	   System.out.println("FIELD LENGTH  "+inVal);		 	
	 		//7. Verify Blank fields" */ 
		 	if(we.isEnabled())
		 		
			{
		 		/*			  
		 		if (inVal.length()==0)
			 		System.out.println("   FIELD EMPTY  ");*/
			}else
			       System.out.println("   FIELD NOT ENABLED   "  ); 
			 //	String  inVal = we.findElement(By.xpath("//input")).getAttribute("value") ;
			
			    
		 	}
		}


 
 
  @BeforeTest
  void launchBrowser() {
  	 driver =Util.setgecodriver();
  	// driver = new FirefoxDriver();
   	 driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
  }
  @AfterTest
  void closeBrowser(){
   //	    driver.close();
  	}
    
}
