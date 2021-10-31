package com.formulario.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.formulario.pages.FormPage;

public class FormElementsTest {

    private String baseURL = "https://forms.liferay.com/web/forms/shared/-/form/122548";
    WebDriver driver;

    @BeforeTest
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
    }

    @BeforeMethod
    public void goTo() {
        driver.get(baseURL);
    }

    @Test
    public void testElementsByDefault() {
        FormPage formPage = new FormPage(driver);
        formPage.waitLoadingElements();

        Assert.assertTrue(formPage.isTitleOk("This is a Liferay Forms"));
        Reporter.log("Title: Text is ok", true);

        Assert.assertTrue(formPage.isNameInputVisible());
        Reporter.log("Input Name: Input is displayed", true);
        Assert.assertTrue(formPage.isNameLabelVisible());
        Reporter.log("Input Name: Label is displayed", true);
        Assert.assertTrue(formPage.isNameLabelOk("What is your name?"));
        Reporter.log("Input Name: Label match with text", true);
        Assert.assertFalse(formPage.isNameMessagePresent());
        Reporter.log("Input Name: Error message is not displayed", true);

        Assert.assertTrue(formPage.isCalendarButtonVisible());
        Reporter.log("Input Date: Calendar is displayed", true);
        Assert.assertTrue(formPage.isDateInputVisible());
        Reporter.log("Input Date: Input is displayed", true);
        Assert.assertTrue(formPage.isDateInputValueOK("__/__/____"));
        Reporter.log("Input Date: Value match with pattern", true);
        Assert.assertTrue(formPage.isDateLabelVisible());
        Reporter.log("Input Date: Label is displayed", true);
        Assert.assertTrue(formPage.isDateLabelOk("What is the date of your birth?"));
        Reporter.log("Input Date: Label match with text", true);
        Assert.assertFalse(formPage.isDateMessagePresent());
        Reporter.log("Input Date: Error message is not displayed", true);

        Assert.assertTrue(formPage.isTextareaVisible());
        Reporter.log("Textarea: Input is displayed", true);
        Assert.assertTrue(formPage.isTextareaLabelVisible());
        Reporter.log("Textarea: Label is displayed", true);
        Assert.assertTrue(formPage.isTextareaLabelOk("Why did you join the testing area?"));
        Reporter.log("Textarea: Label match with text", true);
        Assert.assertFalse(formPage.isTextareaMessagePresent());
        Reporter.log("Textarea: Error message is not displayed", true);

        Assert.assertTrue(formPage.isSubmitButtonVisible());
        Reporter.log("Submit button: Button is displayed", true);

    }

    @AfterTest
    public void close() {
        driver.close();
        driver.quit();
    }
}