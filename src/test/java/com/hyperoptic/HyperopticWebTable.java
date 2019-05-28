package com.hyperoptic;

import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class HyperopticWebTable {
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
    public void shouldAddAndVerifyEmailIsPresent() throws Exception {
        driver.get("http://www.way2automation.com/angularjs-protractor/webtables/");

        // filling out form //
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Add User'])[1]/i[1]")).click();
        driver.findElement(By.name("FirstName")).click();
        driver.findElement(By.name("FirstName")).clear();
        driver.findElement(By.name("FirstName")).sendKeys("Ivana");

        driver.findElement(By.name("LastName")).click();
        driver.findElement(By.name("LastName")).clear();
        driver.findElement(By.name("LastName")).sendKeys("Tosic");

        driver.findElement(By.name("UserName")).click();
        driver.findElement(By.name("UserName")).clear();
        driver.findElement(By.name("UserName")).sendKeys("Admin");

        driver.findElement(By.name("Password")).click();
        driver.findElement(By.name("Password")).clear();
        driver.findElement(By.name("Password")).sendKeys("password8");

        driver.findElement(By.name("optionsRadios")).click();
        driver.findElement(By.name("RoleId")).click();
        driver.findElement(By.name("Mobilephone")).click();
        driver.findElement(By.name("Email")).click();
        driver.findElement(By.name("Email")).clear();
        driver.findElement(By.name("Email")).sendKeys("ivana@hyperqueen.com");

        driver.findElement(By.name("Mobilephone")).click();
        driver.findElement(By.name("Mobilephone")).clear();
        driver.findElement(By.name("Mobilephone")).sendKeys("6545457");

        driver.findElement(By.name("RoleId")).click();
        new Select(driver.findElement(By.name("RoleId"))).selectByVisibleText("Customer");
        driver.findElement(By.name("RoleId")).click();

        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Close'])[1]/following::button[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Role'])[1]/following::span[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Role'])[1]/following::span[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Role'])[1]/following::span[1]")).click();

        List<WebElement> tableRows = driver.findElements(By.xpath("/html/body/table/tbody/tr"));
        int numberOfRows = tableRows.size();

        Boolean isEmailPresent = false;
        for (int rowNumber = 1; rowNumber <= numberOfRows; rowNumber++) {

            WebElement emailCell = driver.findElement(By.xpath("/html/body/table/tbody/tr[" + rowNumber + "]/td[7]"));
            String email = emailCell.getText();

            if (email.equalsIgnoreCase("ivana@hyperqueen.com")) {
                isEmailPresent = true;
                System.out.println("email is found in row " + rowNumber);
            }
        }

        assertTrue(isEmailPresent);
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

