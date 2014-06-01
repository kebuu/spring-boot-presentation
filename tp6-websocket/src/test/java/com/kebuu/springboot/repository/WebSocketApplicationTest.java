package com.kebuu.springboot.repository;

import com.kebuu.springboot.WebSocketApplication;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=WebSocketApplication.class)
@WebAppConfiguration
@IntegrationTest
public class WebSocketApplicationTest {

    private WebDriver driver;

    @Before
    public void setupStart(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @After
    public void setupEnd(){
        driver.quit();
    }

    @Test
    public void test() {
        driver.get("http://localhost:8050/");
        driver.findElement(By.id("connect")).click();
        driver.findElement(By.id("name")).sendKeys("zenika");
        driver.findElement(By.id("sendName")).click();
        Assertions.assertThat(driver.findElement(By.xpath("//p[@id='response']/p")).getText()).isEqualTo("Hello, zenika!");
    }
}
