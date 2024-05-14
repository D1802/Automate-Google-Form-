package demo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap; 

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    private static String IST(){
        long epochTime = 171057201L; // Exam Time
        // Convert epoch time to LocalDateTime in GMT
        LocalDateTime gmtDateTime = LocalDateTime.ofEpochSecond(epochTime / 1000, 0, ZoneOffset.UTC);

        
        // Convert GMT time to IST
        ZonedDateTime gmtZonedDateTime = ZonedDateTime.of(gmtDateTime, ZoneOffset.UTC);
        ZonedDateTime istZonedDateTime = gmtZonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Kolkata"));
        LocalDateTime istDateTime = istZonedDateTime.toLocalDateTime();

        // Define the date-time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Format the converted time
        String formattedISTDateTime = istDateTime.format(formatter);
        return formattedISTDateTime;
    }

    private String DATE(){

        LocalDate currentDate = LocalDate.now();
        LocalDate locateAfter7Days = currentDate.minusDays(7);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/YYYY");
        String formattedDate = dateFormat.format(locateAfter7Days);
    
        System.out.println("Date After 7 Days:"+formattedDate); 
       
        return formattedDate;
    }
    
    public String Time(){
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm a");
        System.out.println("Time:=" +timeFormat.format(currentTime));
        return timeFormat.format(currentTime);
    }

    public  void testCase01() throws InterruptedException{

        try {

            System.out.println("Start Test case: testCase01");
            // Create a JavascriptExecutor instance
            JavascriptExecutor js = (JavascriptExecutor) driver;
            driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
    
            WebElement name = WrapperMethods.wrap_findElement(driver, By.xpath("//div//div[@id='i1']/parent::div/../..//input"));
            WrapperMethods.wrap_sendKeys(name, "Darpan");
            
            String IndianTime = IST();
            WebElement questionsEle = WrapperMethods.wrap_findElement(driver, By.xpath("//div//div[@id='i5']/parent::div/../..//div/textarea"));
            js.executeScript("arguments[0].scrollIntoView(true);", questionsEle);
            WrapperMethods.wrap_sendKeys(questionsEle, "I want to be the best QA Engineer!"+IndianTime);
    
         
            selectExperianceBtn(driver, "0_to_2");
    
            selectLearning(driver,"java" );
            selectLearning(driver, "selenium");
            selectLearning(driver, "testng");
           
    
            WebElement addressBy = WrapperMethods.wrap_findElement(driver, By.xpath("//div[@aria-labelledby='i42']"));
            WrapperMethods.wrap_click(addressBy);
    
            String addressSelectString = AddresSelect(driver, "None");
            WebElement ClickAddress = WrapperMethods.wrap_findElement(driver, By.xpath("(//div[@data-value='"+addressSelectString+"'])[2]"));
            js.executeScript("arguments[0].scrollIntoView(true);", ClickAddress);
            WrapperMethods.wrap_click(ClickAddress);
            
             
            WebElement dateSelect = WrapperMethods.wrap_findElement(driver,By.xpath("//input[@type='date']"));
            js.executeScript("arguments[0].scrollIntoView(true);", dateSelect);
           
            String dateFormat = DATE();
            
            WrapperMethods.wrap_sendKeys(dateSelect,dateFormat);
           
            String timeFormat = Time();
    
            String[] timeNew  = timeFormat.split(" ");
    
            String[] hh_mm = timeNew[0].split(":");
            WebElement hour = WrapperMethods.wrap_findElement(driver, By.xpath("//input[@aria-label='Hour']"));
            WrapperMethods.wrap_sendKeys(hour, hh_mm[0]);
    
            WebElement minute = WrapperMethods.wrap_findElement(driver,By.xpath("//input[@aria-label='Minute']"));
            WrapperMethods.wrap_sendKeys(minute, hh_mm[1]);
            
           WebElement submit = WrapperMethods.wrap_findElement(driver, By.xpath("//span[text()='Submit']"));
           WrapperMethods.wrap_click(submit);
    
           WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    
           WebElement responce = WrapperMethods.wrap_findElement(driver, By.xpath("//div[contains(text(),'Thanks')]"));
           if(wait.until(ExpectedConditions.visibilityOf(responce)).isDisplayed())
                System.out.println("Forms Submited Succesfully");
           else
                System.out.println("Error: For Submission");
    
            System.out.println("end Test case: testCase01");
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception:"+e.getMessage());
        }

    }



    public static  void selectLearning(ChromeDriver driver,String str){
        
        HashMap<String, String> selectTextBox = new HashMap<String, String>();

        selectTextBox.put("java", "i30");
        selectTextBox.put("selenium", "i33");
        selectTextBox.put("testng", "i39");
        selectTextBox.put("springboot", "i36");

        WebElement selectCheackBox = WrapperMethods.wrap_findElement(driver, By.xpath("//div/label[@for='"+selectTextBox.get(str)+"']"));
        WrapperMethods.wrap_click(selectCheackBox);

    }

    public static void selectExperianceBtn(ChromeDriver driver , String exp){

        HashMap<String, String> selectRadio = new HashMap<String, String>();
        selectRadio.put("0_to_2", "0 - 2");
        selectRadio.put("3_to_5", "3 - 5");
        selectRadio.put("6_to_10", "6 - 10");
        selectRadio.put("10_more", "> 10");

           
        WebElement radioSelect = WrapperMethods.wrap_findElement(driver,By.xpath("//div[@aria-label='"+selectRadio.get(exp)+"']"));
        WrapperMethods.wrap_click(radioSelect);
        

    }

    private static String AddresSelect(ChromeDriver driver, String str){
      
        HashMap<String, String> addresSelect = new HashMap<String, String>();
        addresSelect.put("Mr","Mr");
        addresSelect.put("Ms", "Ms");
        addresSelect.put("Mrs", "Mrs");
        addresSelect.put("None", "Rather not say");
        
      
        return addresSelect.get(str);
    }


}
