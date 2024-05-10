package demo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.bouncycastle.jcajce.provider.asymmetric.dsa.DSASigner.detDSASha3_224;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.Location;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

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

    public static String IST(){
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

    public  String DATE(){

        LocalDate currentDate = LocalDate.now();
        LocalDate locateAfter7Days = currentDate.plusDays(7);
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


        System.out.println("Start Test case: testCase01");
        // Create a JavascriptExecutor instance
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");

        WebElement name = WrapperMethods.wrap_findElement(driver, By.xpath("//div//div[@id='i1']/parent::div/../..//input"));
        WrapperMethods.wrap_sendKeys(name, "Darpan");
        
        String IndianTime = IST();
        WebElement questionsEle = WrapperMethods.wrap_findElement(driver, By.xpath("//div//div[@id='i5']/parent::div/../..//div/textarea"));
        WrapperMethods.wrap_sendKeys(questionsEle, "I want to be the best QA Engineer!"+IndianTime);

        
        WebElement radioSelect = WrapperMethods.wrap_findElement(driver,By.xpath("//div/label[@for='i13']//div[contains(@class,'nQOrEb')]"));
        js.executeScript("arguments[0].scrollIntoView(true);", radioSelect);
        WrapperMethods.wrap_click(radioSelect);

        WebElement javaSelect = WrapperMethods.wrap_findElement(driver, By.xpath("//div/label[@for='i30']"));
        WrapperMethods.wrap_click(javaSelect);

        WebElement seleniumSelect = WrapperMethods.wrap_findElement(driver,By.xpath("//div/label[@for='i33']"));
        WrapperMethods.wrap_click(seleniumSelect);

        WebElement testngSelect = WrapperMethods.wrap_findElement(driver, By.xpath("//div/label[@for='i39']"));
        WrapperMethods.wrap_click(testngSelect);

        WebElement addressBy = WrapperMethods.wrap_findElement(driver, By.xpath("//div[@aria-labelledby='i42']"));
        WrapperMethods.wrap_click(addressBy);

        WebElement ratherNotSay = WrapperMethods.wrap_findElement(driver, By.xpath("(//div[contains(@data-value,'Rather')])[2]"));
        js.executeScript("arguments[0].scrollIntoView(true);", ratherNotSay);
        Thread.sleep(1000);
        WrapperMethods.wrap_click(ratherNotSay);
        //div[@id='i46']/ancestor::div[@class='geS5n']//input
         
        WebElement dateSelect = WrapperMethods.wrap_findElement(driver,By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[6]/div/div/div[2]/div/div/div[2]/div[1]/div/div[1]/input"));
        //WebElement dateSelect = WrapperMethods.wrap_findElement(driver, By.xpath("//div[@id='i46']/ancestor::div[@class='geS5n']//input[@data-initial-value='2024-05-16']"));
        js.executeScript("arguments[0].scrollIntoView(true);", dateSelect);
        //WrapperMethods.wrap_click(dateSelect);
        String dateFormat = DATE();
        //dateSelect.sendKeys(dateFormat);
        WrapperMethods.wrap_sendKeys(dateSelect,dateFormat);
        //Thread.sleep(1000);

        String timeFormat = Time();

        String[] timeNew  = timeFormat.split(" ");

        String[] hh_mm = timeNew[0].split(":");
        WebElement hour = WrapperMethods.wrap_findElement(driver, By.xpath("//*[@id='mG61Hd']/div[2]/div/div[2]/div[7]/div/div/div[2]/div/div[1]/div[2]/div[1]/div/div[1]/input"));
        WrapperMethods.wrap_sendKeys(hour, hh_mm[0]);

        WebElement minute = WrapperMethods.wrap_findElement(driver,By.xpath("//*[@id='mG61Hd']/div[2]/div/div[2]/div[7]/div/div/div[2]/div/div[3]/div/div[1]/div/div[1]/input"));
        WrapperMethods.wrap_sendKeys(minute, hh_mm[1]);
        
       WebElement submit = WrapperMethods.wrap_findElement(driver, By.xpath("//span[text()='Submit']"));
       WrapperMethods.wrap_click(submit);

        System.out.println("end Test case: testCase01");
    }


}
