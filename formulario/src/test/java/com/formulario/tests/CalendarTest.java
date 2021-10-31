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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.formulario.pages.DatePickerPage;

public class CalendarTest {

    private String baseURL = "https://forms.liferay.com/web/forms/shared/-/form/122548";
    WebDriver driver;

    public SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    public Date date = new Date();
    public int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    public int currentMonth = Calendar.getInstance().get(Calendar.MONTH);


    public String getMonthString (int numberMonth){
        String[] monthName = {"January", "February",
        "March", "April", "May", "June", "July",
        "August", "September", "October", "November",
        "December"};
        return monthName[numberMonth];

    }

    @BeforeTest
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
    }

    @BeforeMethod
    public void goTo() {
        FormPage formPage = new FormPage(driver);
        driver.get(baseURL);
        formPage.waitDateInputIsClickable();
        formPage.clickCalendarButton();
    }

    @Test
    public void testCalendarElements() {
        DatePickerPage datePickerPage = new DatePickerPage(driver);

        Assert.assertTrue(datePickerPage.isCalendarVisible());
        Reporter.log("Calendar is visible", true);

        Assert.assertTrue(datePickerPage.isMonthSelectVisible());
        Reporter.log("Month selector is visible", true);
        Assert.assertEquals(datePickerPage.countMonthOptions(), 12);
        Reporter.log("There are 12 options of Month", true);
        Assert.assertEquals(datePickerPage.getMonthSelected(), currentMonth);
        Reporter.log("Selected month is current month");

        
        Assert.assertTrue(datePickerPage.isYearSelectVisible());
        Reporter.log("Year is visible", true);
        Assert.assertEquals(datePickerPage.countYearOptions(), 11);
        Reporter.log("There are 11 options of Year", true);
        Assert.assertEquals(datePickerPage.getYearSelected(), currentYear);
        Reporter.log("Selected year is current year");


        Assert.assertTrue(datePickerPage.isPrevMonthButtonVisible());
        Reporter.log("Previous month button is visible", true);

        Assert.assertTrue(datePickerPage.isNextMonthButtonVisible());
        Reporter.log("Next month button is visible", true);

        Assert.assertTrue(datePickerPage.isTodayButtonVisible());
        Reporter.log("Today button is visible", true);
    }

    @Test
    public void selectDay1() {
        FormPage formPage = new FormPage(driver);
        DatePickerPage datePickerPage = new DatePickerPage(driver);
        datePickerPage.clickDay1Button();
        Assert.assertTrue(formPage.isDateInputValueOK("01/"));
        Reporter.log("Today button puts day 1 in input", true);
    }
  
    @Test
    public void selectDay15() {
        FormPage formPage = new FormPage(driver);
        DatePickerPage datePickerPage = new DatePickerPage(driver);
        datePickerPage.clickDay15Button();
        Assert.assertTrue(formPage.isDateInputValueOK("15/"));
        Reporter.log("Today button puts day 15 in input", true);
    }

    @Test
    public void prevMonthButton() {
        DatePickerPage datePickerPage = new DatePickerPage(driver);
        datePickerPage.clickPrevMonthButton();
        Assert.assertEquals(datePickerPage.getMonthSelected(), currentMonth - 1);
        Reporter.log("Selected month is previous month", true);
    }

    @Test
    public void nextMonthButton() {
        DatePickerPage datePickerPage = new DatePickerPage(driver);
        datePickerPage.clickNextMonthButton();
        Assert.assertEquals(datePickerPage.getMonthSelected(), currentMonth + 1);
        Reporter.log("Selected month is following month", true);
    }


    @Test
    public void todayButton() {
        FormPage formPage = new FormPage(driver);
        DatePickerPage datePickerPage = new DatePickerPage(driver);
        datePickerPage.clickTodayButton();
        String todayDate = formatter.format(date);
        Assert.assertTrue(formPage.isDateInputValueOK(todayDate));
        Reporter.log("Today button puts today date in input", true);
    }

    @AfterTest
    public void close() {
        driver.close();
        driver.quit();
    }
}