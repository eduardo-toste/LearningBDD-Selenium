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
                tags = "@unitários",
                dryRun = false,
                plugin = {"pretty", "html:target/report-html/report.html", "json:target/report-json/report.json"})
public class RunnerTest {
}