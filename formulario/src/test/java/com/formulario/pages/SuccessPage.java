package com.formulario.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SuccessPage {
    private WebDriver driver;

    @FindBy(how = How.CSS, using = "h1")
    WebElement titlePage;
    @FindBy(how = How.CSS, using = "h2")
    WebElement subtitlePage;

    @FindBy(how = How.ID, using = "ToastAlertContainer")
    WebElement alertToast;
    @FindBy(how = How.CSS, using = "button.close")
    WebElement closeToast;
    @FindBy(how = How.CSS, using = "[data-field-name='WhyDidYouJoinTheTestingArea'] .loading-animation")
    WebElement textareaLoading;


    public SuccessPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // -Title
    public boolean isTitleOk(String title) {
        return titlePage.getText().contains(title);
    }

    // -Toast
    public boolean isToastVisible() {
        return alertToast.isDisplayed();
    }
    public boolean isCloseToastVisible() {
        return closeToast.isDisplayed();
    }
    public boolean isToastTextOk(String text) {
        return alertToast.getText().contains(text);
    }
    public void waitForToast() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(alertToast));
    }
}