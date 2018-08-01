package productTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;

import static org.testng.Assert.assertEquals;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class PurchaseUseEmailChro   {
	public static WebDriver driver;

	
  @Test 
  //(dependsOnMethods = { "creatAccShareWishList" })
  public void purchaeProd() throws InterruptedException  {
	  
	  //2. Click my Account

		 driver.findElement(By.xpath("//*[@id='header']//span[contains(text(),'Account')]")).click(); 
		 driver.findElement(By.xpath("/*[@id='top']//a[contains(text(),'My Account')]")).click();
    
		 //3. Login  
		   Util.getGuruAccount(driver );
	         System.out.println("   Click wish List "); 

		  
	   //4. click My Wish List
		   Thread.sleep(3000);

	         /*
	          * WebDriverWait wait2 = new WebDriverWait(driver, 10);
				wait2.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("xpath_of_element_to_be_invisible")));
				driver.findElement(By.xpath("xpath_element_to_be_clicked")).click();
	          */
	         
	         
	         WebElement ele = driver.findElement(By.xpath("/*[@id='top']//*[contains(text(),'My Wishlist')]"));
	         JavascriptExecutor executor = (JavascriptExecutor)driver;
	         executor.executeScript("arguments[0].click();", ele);  
	         
	         
	         System.out.println("  Shared  wish List "); 

	   //5.  Click Add to Cart 
	         
	         if   ((driver.findElements(By.xpath("/*[@id='top']//form[@id='wishlist-view-form']/div/p")).size()) !=0 )
			 {
	    		 driver.findElement(By.linkText("TV")).click();
	    		 //Add to Wish List
	    		 driver.findElement(By.xpath("/*[@id='top']//li[@class='item last'][./a[contains(@href,'lg-lcd.html') and @title='LG LCD']]//*[contains(text(),'Add to Wishlist')]")).click(); 
	    		//To check login
		         System.out.println("  before Calling ACC "); 
		         Thread.sleep(3000);
		        
		         if ((driver.findElements(By.xpath("/*[@id='top']//*[contains(text(),'My Wishlist')]")).size())==0) {
		        	 
		        
		        	 Util.getGuruAccount(driver);
		         }
			 }
	         	    	 
	        	 ele =driver.findElement(By.xpath("/*[@id='top']//*[@id='wishlist-view-form']//*[contains(text(),'Add to Cart')]")) ;

	        	 executor.executeScript("arguments[0].click();", ele);  
	         

		  //6. Click Proceed to checkout  
  
	      //  ele =		  driver.findElement(By.linkText("Proceed to Checkout"));
	         ele =		  driver.findElement(By.xpath("html/body/div/div/div[2]/div/div/div/div[3]/div/ul/li[1]/button"));
	       // ele =		  driver.findElement(By.xpath("/*[@id='top']//ul/li/button[title='Proceed to Checkout']"));
			  ele.click();
			  //7. Shipping Information
			  driver.findElement(By.xpath("/*[@id='top']//*[@id='opc-billing']//*[contains(text(),'Billing Information')]")).click();
			  Select addBy = new Select(driver.findElement(By.id("billing-address-select")));
			  addBy.selectByVisibleText("New Address");
			  driver.findElement(By.id("billing:firstname")).clear();
			  driver.findElement(By.id("billing:firstname")).sendKeys("Ricky");
			  driver.findElement(By.id("billing:middlename")).clear();
			  driver.findElement(By.id("billing:middlename")).sendKeys("INFANT");
			  driver.findElement(By.id("billing:lastname")).clear();
			  driver.findElement(By.id("billing:lastname")).sendKeys("GNANASUSAIRAJ");
			  driver.findElement(By.id("billing:company")).clear();
			  driver.findElement(By.id("billing:company")).sendKeys("DIVINE");
			  driver.findElement(By.id("billing:street1")).clear();
			  driver.findElement(By.id("billing:street1")).sendKeys("20 OLDE SUREY DR");
			  driver.findElement(By.id("billing:street2")).clear();

			  driver.findElement(By.id("billing:street2")).sendKeys("ACTON");
			  driver.findElement(By.id("billing:city")).clear();
			  driver.findElement(By.id("billing:city")).sendKeys("ACTON");
			  driver.findElement(By.id("billing:region")).clear();	
			  driver.findElement(By.id("billing:region")).sendKeys("MA");			  
			  driver.findElement(By.id("billing:postcode")).clear();
			  driver.findElement(By.id("billing:postcode")).sendKeys("01720");
			  Select addContBy = new Select(driver.findElement(By.id("billing:country_id")));
			  addContBy.selectByVisibleText("United States");

			  driver.findElement(By.id("billing:telephone")).clear();
			  driver.findElement(By.id("billing:telephone")).sendKeys("9784026871");
			  driver.findElement(By.id("billing:fax")).clear();
			  driver.findElement(By.id("billing:fax")).sendKeys("INFANT");

			  driver.findElement(By.cssSelector("div#billing-buttons-container button ")).click();
			  String flatRateFixedAct = driver.findElement(By.cssSelector("form#co-shipping-method-form span.price")).getText();
			  String flatRateFixedExp ="$5.00";
			  
			  try {	 
			    	System.out.println("sFlatRatePrice = "+flatRateFixedAct);
			    	System.out.println("flatRatePrice = "+flatRateFixedExp);
			    	AssertJUnit.assertEquals(flatRateFixedAct, flatRateFixedExp);
				    } catch (Exception e) {
				    	e.printStackTrace();	    	
				    }	
			  
			  
			  
		//	  Assert.assertEquals(flatRateFixedAct, flatRateFixedExp);
			  driver.findElement(By.cssSelector("#shipping-method-buttons-container button")).click();
			  driver.findElement(By.cssSelector(" #p_method_checkmo  ")).click();
			  driver.findElement(By.cssSelector("#payment-buttons-container button")).click();
			  driver.findElement(By.cssSelector("#review-buttons-container button " )).click();
			  Thread.sleep(2000);
			   String MainWindow=driver.getWindowHandle();		
		  		
		          // To handle all new opened window.				
		          Set<String> s1=driver.getWindowHandles();		
		          Iterator<String> i1=s1.iterator();		
		          		
		          while(i1.hasNext())			
		          {		
		              String ChildWindow=i1.next();		
		              // Switching to Child window
                      driver.switchTo().window(ChildWindow);		
		                 	
		          }
		          
			  Thread.sleep(2000);

			  String orderGenerMsg = driver.findElement(By.xpath("/*[@id='top']//div[@class='page-title']/h1")).getText();
			  String orderExpMsg ="YOUR ORDER HAS BEEN RECEIVED.";
			  AssertJUnit.assertEquals(orderGenerMsg, orderExpMsg);
			  
		//	  Thread.sleep(2000);
			  
			  String orderNo = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div/p[1]/a")).getText();
		      System.out.println("ORDER NUMBER "+orderNo); 
		      Util.orderNo = orderNo;	
		      System.out.println(" Util  ORDER NUMBER "+Util.orderNo); 
		      System.out.println(" END OF PRODUCT  ORDER ....... "); 


		      


			  //6.Click Estimate
	 //	  driver.findElement(By.linkText("Estimate")).click();
  }
  @BeforeTest
  void launchBrowser() {
  	 driver =Util.setgecodriver();
 // 	 driver = new FirefoxDriver();
//  	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  	 driver.get(Util.BASE_URL);
  }
  @AfterTest
  void closeBrowser(){
   	    driver.close();
  	}
    
  }