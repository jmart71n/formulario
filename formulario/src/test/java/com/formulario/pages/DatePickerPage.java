package com.formulario.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DatePickerPage {
    private WebDriver driver;
    @FindBy(how = How.CSS, using = ".date-picker-calendar")
    private WebElement datepickerCalendar;
    @FindBy(how = How.CSS, using = ".input-date-picker-month")
    private WebElement monthSelect;
    @FindBy(how = How.CSS, using = ".input-date-picker-month select")
    private WebElement monthSelectSelect;
    @FindBy(how = How.CSS, using = ".input-date-picker-month option")
    private List<WebElement> monthOptions;
    @FindBy(how = How.CSS, using = ".input-date-picker-year")
    private WebElement yearSelect;
    @FindBy(how = How.CSS, using = ".input-date-picker-year select")
    private WebElement yearSelectSelect;
    @FindBy(how = How.CSS, using = ".input-date-picker-year option")
    private List<WebElement> yearOptions;
    @FindBy(how = How.CSS, using = ".date-picker-nav-controls button:nth-child(1)")
    private WebElement prevMonthButton;
    @FindBy(how = How.CSS, using = ".date-picker-nav-controls button:nth-child(3)")
    private WebElement nextMonthButton;
    @FindBy(how = How.CSS, using = ".date-picker-nav-controls button:nth-child(2)")
    private WebElement todayButton;
    @FindBy(how = How.XPATH, using = "//button[text()='1'][not(contains(@class,'disabled'))]")
    private WebElement day1Button;
    @FindBy(how = How.XPATH, using = "//button[text()='15'][not(contains(@class,'disabled'))]")
    private WebElement day15Button;

    public DatePickerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isCalendarVisible() {
        return datepickerCalendar.isDisplayed();
    }

    public boolean isMonthSelectVisible() {
        return monthSelect.isDisplayed();
    }

    public int countMonthOptions() {
        return monthOptions.size();
    }

    public int getMonthSelected() {
        return Integer.parseInt(monthSelectSelect.getAttribute("value"));
    }

    public boolean isYearSelectVisible() {
        return yearSelect.isDisplayed();
    }

    public int countYearOptions() {
        return yearOptions.size();
    }

    public int getYearSelected() {
        return Integer.parseInt(yearSelectSelect.getAttribute("value"));
    }

    public boolean isPrevMonthButtonVisible() {
        return prevMonthButton.isDisplayed();
    }

    public void clickPrevMonthButton() {
        prevMonthButton.click();
    }

    public boolean isNextMonthButtonVisible() {
        return nextMonthButton.isDisplayed();
    }

    public void clickNextMonthButton() {
        nextMonthButton.click();
    }

    public boolean isTodayButtonVisible() {
        return todayButton.isDisplayed();
    }

    public void clickTodayButton() {
        todayButton.click();
    }

    public void clickDay1Button() {
        day1Button.click();
    }

    public void clickDay15Button() {
        day15Button.click();
    }
}
