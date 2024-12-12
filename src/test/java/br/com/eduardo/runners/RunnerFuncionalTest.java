package br.com.eduardo.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/",
                glue = "br.com.eduardo.steps",
                tags = "@funcionais",
                dryRun = false,
                plugin = {"pretty", "html:target/report-html/report.html", "json:target/report-json/report.json"})
public class RunnerFuncionalTest {

    @BeforeClass
    public static void reset(){
        System.setProperty("webdriver", "D:\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me/");
        driver.findElement(By.id("email")).sendKeys("rodrigo@garro");
        driver.findElement(By.id("senha")).sendKeys("garro10");
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.linkText("reset")).click();
        driver.quit();
    }

}