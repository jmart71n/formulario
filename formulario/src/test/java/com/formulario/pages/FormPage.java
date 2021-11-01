package com.formulario.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormPage {
    private WebDriver driver;
    @FindBy(how = How.CSS, using = "h1")
    private WebElement titlePage;
    @FindBy(how = How.CSS, using = "[data-field-name='WhatIsYourName'] .loading-animation")
    private WebElement nameInputLoading;
    @FindBy(how = How.CSS, using = "[data-field-name='WhatIsTheDateOfYourBirth'] .loading-animation")
    private WebElement dateInputLoading;
    @FindBy(how = How.CSS, using = "[data-field-name='WhyDidYouJoinTheTestingArea'] .loading-animation")
    private WebElement textareaLoading;
    @FindBy(how = How.CSS, using = "span[aria-atomic='true']")
    private WebElement invalidFormMessage;
    @FindBy(how = How.CSS, using = "[data-field-name='WhatIsYourName'] input")
    private WebElement nameInput;
    @FindBy(how = How.CSS, using = "[data-field-name='WhatIsYourName'] label")
    private WebElement nameInputLabel;
    @FindBy(how = How.CSS, using = "[data-field-name='WhatIsYourName'] .form-feedback-item")
    private WebElement nameInputMessage;
    @FindBy(how = How.CSS, using = "[data-field-name='WhatIsTheDateOfYourBirth'] .input-group-inset")
    private WebElement dateInput;
    @FindBy(how = How.CSS, using = "[data-field-name='WhatIsTheDateOfYourBirth'] button")
    private WebElement calendarButton;
    @FindBy(how = How.CSS, using = "[data-field-name='WhatIsTheDateOfYourBirth'] label")
    private WebElement dateInputLabel;
    @FindBy(how = How.CSS, using = "[data-field-name='WhatIsTheDateOfYourBirth'] .form-feedback-item")
    private WebElement dateInputMessage;
    @FindBy(how = How.CSS, using = "[data-field-name='WhyDidYouJoinTheTestingArea'] textarea")
    private WebElement textarea;
    @FindBy(how = How.CSS, using = "[data-field-name='WhyDidYouJoinTheTestingArea'] label")
    private WebElement textareaLabel;
    @FindBy(how = How.CSS, using = "[data-field-name='WhyDidYouJoinTheTestingArea'] .form-feedback-item")
    private WebElement textareaMessage;
    @FindBy(how = How.ID, using = "ddm-form-submit")
    private WebElement submitButton;

    public FormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isElementVisible(WebElement element) {
        return element.isDisplayed();
    }

    public void clickOnElement(WebElement element) {
        element.click();
    }

    public void sendText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void waitLoadingElements() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.invisibilityOf(nameInputLoading));
        wait.until(ExpectedConditions.invisibilityOf(dateInputLoading));
        wait.until(ExpectedConditions.invisibilityOf(textareaLoading));
    }

    public void waitInvalidFormMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(invalidFormMessage));
    }

    public void waitNameInputIsClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(nameInput));
    }

    public void waitDateInputIsClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(dateInput));
    }

    public void waitTextareaIsClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(textarea));
    }
    // -Title and subtitles
    public boolean isTitleOk(String title) {
        return titlePage.getText().contains(title);
    }

    public void clickTitle() {
        clickOnElement(submitButton);
    }

    public boolean isInvalidFormMessageOk(String text) {
        return invalidFormMessage.getText().contains(text);
    }
    // -Submit Button
    public boolean isSubmitButtonVisible() {
        return submitButton.isDisplayed();
    }

    public void clickSubmitButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));
        clickOnElement(submitButton);
    }
    // -NameInput
    public boolean isNameInputVisible() {
        return nameInput.isDisplayed();
    }

    public boolean isNameInputValueOK(String text) {
        return nameInput.getAttribute("value").contains(text);
    }

    public boolean isNameInputStyleOK(String text) {
        return nameInput.getCssValue("background-color").contains(text);
    }

    public boolean isNameLabelVisible() {
        return nameInputLabel.isDisplayed();
    }

    public boolean isNameLabelOk(String text) {
        return nameInputLabel.getText().contains(text);
    }

    public boolean isNameMessagePresent() {
        try {
            return nameInputMessage.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isNameMessageOk(String text) {
        return nameInputMessage.getText().contains(text);
    }

    public void setName(String name) {
        sendText(nameInput, name);
    }
    // -DateInput default
    public boolean isCalendarButtonVisible() {
        return calendarButton.isDisplayed();
    }

    public void clickCalendarButton() {
        clickOnElement(calendarButton);
    }

    public boolean isDateInputVisible() {
        return dateInput.isDisplayed();
    }

    public boolean isDateInputValueOK(String text) {
        return dateInput.getAttribute("value").contains(text);
    }

    public boolean isDateInputStyleOK(String text) {
        return dateInput.getCssValue("background-color").contains(text);
    }

    public boolean isDateLabelVisible() {
        return dateInputLabel.isDisplayed();
    }

    public boolean isDateLabelOk(String text) {
        return dateInputLabel.getText().contains(text);
    }

    public boolean isDateMessagePresent() {
        try {
            return dateInputMessage.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isDateMessageOk(String text) {
        return dateInputMessage.getText().contains(text);
    }

    public void setDate(String date) {
        sendText(dateInput, date);
    }
    // -Textarea default
    public boolean isTextareaVisible() {
        return textarea.isDisplayed();
    }

    public boolean isTextareaValueOK(String text) {
        return textarea.getAttribute("value").contains(text);
    }

    public boolean isTextareaStyleOK(String text) {
        return textarea.getCssValue("background-color").contains(text);
    }

    public boolean isTextareaLabelVisible() {
        return textareaLabel.isDisplayed();
    }

    public boolean isTextareaLabelOk(String text) {
        return textareaLabel.getText().contains(text);
    }

    public boolean isTextareaMessagePresent() {
        try {
            return textareaMessage.isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isTextareaMessageOk(String text) {
        return textareaMessage.getText().contains(text);
    }

    public void setComment(String comments) {
        sendText(textarea, comments);
    }

    public void clickEveryField() {
        clickOnElement(nameInput);
        clickOnElement(dateInput);
        clickOnElement(textarea);
    }

    public void fillFormAndSubmit(String fullName, String date, String comment) {
        sendText(nameInput, fullName);
        sendText(dateInput, date);
        sendText(textarea, comment);
        clickOnElement(submitButton);
    }
}