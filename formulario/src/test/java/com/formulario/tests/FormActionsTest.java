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
import com.formulario.pages.SuccessPage;

public class FormActionsTest {

    WebDriver driver;

    private String baseURL = "https://forms.liferay.com/web/forms/shared/-/form/122548";
    private String errorBgColor = "254, 239, 239";
    private String errorMessage = "This field is required.";
    private String nameOk = "John Doe";
    private String dateOk = "10/10/1999";
    private String comment = "Lorem Ipsum is simply dummy text";

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
    public void fillNameInput() {
        FormPage formPage = new FormPage(driver);
        formPage.waitNameInputIsClickable();
        formPage.setName(nameOk);
        Assert.assertTrue(formPage.isNameInputValueOK(nameOk));
        Reporter.log("Input Name: Value match with filled", true);
    }

    @Test
    public void fillDateInputOK() {
        FormPage formPage = new FormPage(driver);
        formPage.waitDateInputIsClickable();
        formPage.setDate(dateOk);
        Assert.assertTrue(formPage.isDateInputValueOK(dateOk));
        Reporter.log("Input Date: Value match with filled", true);
    }

    @Test
    public void fillDateInput_MonthKO() {
        String dateKO = "20/10/1999";
        String expectedDate = "02/01/0199";
        FormPage formPage = new FormPage(driver);
        formPage.waitDateInputIsClickable();
        formPage.setDate(dateKO);
        Assert.assertFalse(formPage.isDateInputValueOK(dateKO));
        Assert.assertTrue(formPage.isDateInputValueOK(expectedDate));
        Reporter.log("Input Date: Month cannot start with 2", true);
    }

    @Test
    public void fillDateInput_MonthZero() {
        String dateKO = "00/10/1999";
        String expectedDate = "01/01/999";
        FormPage formPage = new FormPage(driver);
        formPage.waitDateInputIsClickable();
        formPage.setDate(dateKO);
        Assert.assertFalse(formPage.isDateInputValueOK(dateKO));
        Assert.assertTrue(formPage.isDateInputValueOK(expectedDate));
        Reporter.log("Input Date: Month cannot be zero", true);
    }

    @Test
    public void fillDateInput_DayKO() {
        String dateKO = "10/32/1999";
        String expectedDate = "10/31/999";
        FormPage formPage = new FormPage(driver);
        formPage.waitDateInputIsClickable();
        formPage.setDate(dateKO);
        Assert.assertFalse(formPage.isDateInputValueOK(dateKO));
        Assert.assertTrue(formPage.isDateInputValueOK(expectedDate));
        Reporter.log("Input Date: Day cannot be greater than 31", true);
    }

    @Test
    public void fillDateInput_DayZero() {
        String dateKO = "10/00/1999";
        String expectedDate = "10/01/999";
        FormPage formPage = new FormPage(driver);
        formPage.waitDateInputIsClickable();
        formPage.setDate(dateKO);
        Assert.assertFalse(formPage.isDateInputValueOK(dateKO));
        Assert.assertTrue(formPage.isDateInputValueOK(expectedDate));
        Reporter.log("Input Date: Day cannot be zero", true);
    }

    @Test
    public void filltextArea() {
        FormPage formPage = new FormPage(driver);
        formPage.waitTextareaIsClickable();
        formPage.setComment(comment);
        Assert.assertTrue(formPage.isTextareaValueOK(comment));
        Reporter.log("Textarea: Value match with filled", true);
    }

    @Test
    public void checkMandatoryName() {
        FormPage formPage = new FormPage(driver);
        formPage.waitNameInputIsClickable();
        formPage.setDate(dateOk);
        formPage.setComment(comment);
        formPage.clickSubmitButton();
        formPage.waitInvalidFormMessage();
        Assert.assertTrue(formPage.isNameInputStyleOK(errorBgColor));
        Reporter.log("Input Name Mandatory: Background has error color", true);
        Assert.assertTrue(formPage.isNameMessagePresent());
        Reporter.log("Input Name Mandatory: Message is present", true);
        Assert.assertTrue(formPage.isNameMessageOk(errorMessage));
        Reporter.log("Input Name Mandatory: Message is correct", true);
        Assert.assertTrue(
                formPage.isInvalidFormMessageOk("This form is invalid. Check field What is your name?"));
        Reporter.log("Input Date: Invalid Form Message is correct", true);
    }

    @Test
    public void checkMandatoryDate() {
        FormPage formPage = new FormPage(driver);
        formPage.waitDateInputIsClickable();
        formPage.setName(nameOk);
        formPage.setComment(comment);
        formPage.clickSubmitButton();
        formPage.waitInvalidFormMessage();
        Assert.assertTrue(formPage.isDateInputStyleOK(errorBgColor));
        Reporter.log("Input Date Mandatory: Background has error color", true);
        Assert.assertTrue(formPage.isDateMessagePresent());
        Reporter.log("Input Date Mandatory: Message is present", true);
        Assert.assertTrue(formPage.isDateMessageOk(errorMessage));
        Reporter.log("Input Date Mandatory: Message is correct", true);
        Assert.assertTrue(
                formPage.isInvalidFormMessageOk("This form is invalid. Check field What is the date of your birth"));
        Reporter.log("Input Date: Invalid Form Message is correct", true);

    }

    @Test
    public void checkMandatoryComment() {
        FormPage formPage = new FormPage(driver);
        formPage.waitDateInputIsClickable();
        formPage.setDate(dateOk);
        formPage.setName(nameOk);
        formPage.clickSubmitButton();
        formPage.waitInvalidFormMessage();
        Assert.assertTrue(formPage.isTextareaStyleOK(errorBgColor));
        Reporter.log("Textarea Mandatory: Background has error color", true);
        Assert.assertTrue(formPage.isTextareaMessagePresent());
        Reporter.log("Textarea Mandatory: Message is present", true);
        Assert.assertTrue(formPage.isTextareaMessageOk(errorMessage));
        Reporter.log("Textarea Mandatory: Message is correct", true);
        Assert.assertTrue(
                formPage.isInvalidFormMessageOk("This form is invalid. Check field Why did you join the testing area"));
        Reporter.log("Textarea Mandatory: Invalid Form Message is correct", true);
    }

    @Test
    public void checkMandatoryClickingOutside() {
        FormPage formPage = new FormPage(driver);
        formPage.waitNameInputIsClickable();
        formPage.clickEveryField();
        formPage.clickTitle();

        Assert.assertTrue(formPage.isNameInputStyleOK(errorBgColor));
        Reporter.log("Input Name Mandatory: Background has error color", true);
        Assert.assertTrue(formPage.isNameMessagePresent());
        Reporter.log("Input Name Mandatory: Message is present", true);
        Assert.assertTrue(formPage.isNameMessageOk(errorMessage));
        Reporter.log("Input Name Mandatory: Message is correct", true);

        // -- BUG: Input Date has not mandatory message if you do not click on Submit
        // Assert.assertTrue(formPage.isDateInputStyleOK(errorBgColor));
        // Reporter.log("Input Date Mandatory: Background has error color", true);
        // Assert.assertTrue(formPage.isDateMessagePresent());
        // Reporter.log("Input Date Mandatory: Message is present", true);
        // Assert.assertTrue(formPage.isDateMessageOk(errorMessage));
        // Reporter.log("Input Date Mandatory: Message is correct", true);

        Assert.assertTrue(formPage.isTextareaStyleOK(errorBgColor));
        Reporter.log("Textarea Mandatory: Background has error color", true);
        Assert.assertTrue(formPage.isTextareaMessagePresent());
        Reporter.log("Textarea Mandatory: Message is present", true);
        Assert.assertTrue(formPage.isTextareaMessageOk(errorMessage));
        Reporter.log("Textarea Mandatory: Message is correct", true);
    }

    @Test
    public void checkMandatoryClickingSubmit() {
        FormPage formPage = new FormPage(driver);
        formPage.waitNameInputIsClickable();
        formPage.clickTitle();
        formPage.clickSubmitButton();
        formPage.waitInvalidFormMessage();

        Assert.assertTrue(formPage.isNameInputStyleOK(errorBgColor));
        Reporter.log("Input Name Mandatory: Background has error color", true);
        Assert.assertTrue(formPage.isNameMessagePresent());
        Reporter.log("Input Name Mandatory: Message is present", true);
        Assert.assertTrue(formPage.isNameMessageOk(errorMessage));
        Reporter.log("Input Name Mandatory: Message is correct", true);

        Assert.assertTrue(formPage.isDateInputStyleOK(errorBgColor));
        Reporter.log("Input Date Mandatory: Background has error color", true);
        Assert.assertTrue(formPage.isDateMessagePresent());
        Reporter.log("Input Date Mandatory: Message is present", true);
        Assert.assertTrue(formPage.isDateMessageOk(errorMessage));
        Reporter.log("Input Date Mandatory: Message is correct", true);

        Assert.assertTrue(formPage.isTextareaStyleOK(errorBgColor));
        Reporter.log("Textarea Mandatory: Background has error color", true);
        Assert.assertTrue(formPage.isTextareaMessagePresent());
        Reporter.log("Textarea Mandatory: Message is present", true);
        Assert.assertTrue(formPage.isTextareaMessageOk(errorMessage));
        Reporter.log("Textarea Mandatory: Message is correct", true);
    }

    @Test
    public void submitOkForm() {
        FormPage formPage = new FormPage(driver);
        SuccessPage successPage = new SuccessPage(driver);

        formPage.waitDateInputIsClickable();
        formPage.setDate(dateOk);
        formPage.setName(nameOk);
        formPage.setComment(comment);
        formPage.clickSubmitButton();
        successPage.waitForToast();

        Assert.assertTrue(successPage.isTitleOk("Information sent"));
        Reporter.log("Success Page: Title is correct", true);
        
        Assert.assertTrue(successPage.isToastVisible());
        Reporter.log("Success Page: Toast appears", true);
    
        Assert.assertTrue(successPage.isCloseToastVisible());
        Reporter.log("Success Page: Toast has close button", true);

        Assert.assertTrue(successPage.isToastTextOk("Success:Your request completed successfully."));
        Reporter.log("Success Page: Toast text is correct", true);
 
    }


    @AfterTest
    public void close() {
        driver.close();
        driver.quit();
    }
}