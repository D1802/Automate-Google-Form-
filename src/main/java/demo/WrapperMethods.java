package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WrapperMethods {

    

    public static WebElement wrap_findElement(WebDriver driver,By b){

       
        return driver.findElement(b);
    }

    public static void wrap_click(WebElement e){

        int x = e.getLocation().getY();
        int y = e.getLocation().getX();

        System.out.println("Element click Location is ("+x+","+y+")");
        if (e.isDisplayed()) {
            e.click();    
        }else
            System.out.println("Element is Not Displayed");
        
        
    }

    public static void wrap_sendKeys(WebElement e,String text) throws InterruptedException{

        //e.clear();
        Thread.sleep(1000);
        e.sendKeys(text);

    }
    
}
