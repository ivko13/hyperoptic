package com.hyperoptic;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class HyperopticFilloutForm {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://www.katalon.com/";
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void shouldSubmitFormConfirmNumbersChange() throws Exception {

        driver.get("https://www.ultimateqa.com/filling-out-forms/");
        System.out.println("01.Successfully open home page");

        // filling out form //

        driver.findElement(By.id("et_pb_contact_name_1")).click();
        driver.findElement(By.id("et_pb_contact_name_1")).clear();
        driver.findElement(By.id("et_pb_contact_name_1")).sendKeys("Ivana");
        System.out.println("02.Successfully entered name");

        driver.findElement(By.id("et_pb_contact_message_1")).click();
        driver.findElement(By.id("et_pb_contact_message_1")).clear();
        driver.findElement(By.id("et_pb_contact_message_1")).sendKeys("Testing...");
        System.out.println("03.Successfully entered message");

        //checking numbers before submit//
        String numbersBeforeSubmit = driver.findElement(By.className("et_pb_contact_captcha_question")).getText();
        System.out.println("Numbers Before Submit: " + numbersBeforeSubmit);

        driver.findElement(By.name("et_pb_contact_captcha_1")).click();
        driver.findElement(By.name("et_pb_contact_captcha_1")).clear();
        driver.findElement(By.name("et_pb_contact_captcha_1")).sendKeys("-1");
        System.out.println("04.Successfully entered result");

        //checking numbers after submit//
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Message'])[2]/following::button[1]")).click();
        String numbersAfterSubmit = driver.findElement(By.className("et_pb_contact_captcha_question")).getText();

        System.out.println("Numbers After Submit: " + numbersAfterSubmit);

        assertFalse(numbersBeforeSubmit.equalsIgnoreCase(numbersAfterSubmit));

    }

    @Test
    public void shouldSubmitFormConfirmSucess() throws Exception {

        driver.get("https://www.ultimateqa.com/filling-out-forms/");
        System.out.println("01.Successfully open home page");

        // filling out form //
        driver.findElement(By.id("et_pb_contact_name_1")).click();
        driver.findElement(By.id("et_pb_contact_name_1")).clear();
        driver.findElement(By.id("et_pb_contact_name_1")).sendKeys("Ivanka");

        driver.findElement(By.id("et_pb_contact_message_1")).click();
        driver.findElement(By.id("et_pb_contact_message_1")).clear();
        driver.findElement(By.id("et_pb_contact_message_1")).sendKeys("testing again");

        // adding the displayed numbers to enter the correct result //
        String numbersBeforeSubmit = driver.findElement(By.className("et_pb_contact_captcha_question")).getText();
        System.out.println("Numbers Before Submit: " + numbersBeforeSubmit);
        String[] onlyNumbers = numbersBeforeSubmit.split("\\+");
        Integer firstNumber = Integer.parseInt(onlyNumbers[0].trim());
        Integer secondNumber = Integer.parseInt(onlyNumbers[1].trim());
        Integer result = firstNumber + secondNumber;
        System.out.println("Result: " + result);

        // sending calculated result//
        driver.findElement(By.name("et_pb_contact_captcha_1")).click();
        driver.findElement(By.name("et_pb_contact_captcha_1")).clear();
        driver.findElement(By.name("et_pb_contact_captcha_1")).sendKeys(result.toString());
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Message'])[2]/following::button[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Blog'])[2]/following::div[9]")).click();

        WebElement successElement = driver.findElement(By.xpath("//*[@id=\"et_pb_contact_form_1\"]/div/p"));

        assertTrue(successElement.getText().equals("Success"));
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
