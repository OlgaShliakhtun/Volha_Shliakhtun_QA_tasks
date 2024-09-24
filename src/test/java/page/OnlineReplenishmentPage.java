package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OnlineReplenishmentPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    @FindBy(id = "connection-phone")
    private WebElement connectionPhoneField;

    @FindBy(id = "connection-sum")
    private WebElement connectionSumField;

    @FindBy(id = "connection-email")
    private WebElement connectionEmailField;

    @FindBy(id = "internet-phone")
    private WebElement internetPhoneField;

    @FindBy(id = "internet-sum")
    private WebElement internetSumField;

    @FindBy(id = "internet-email")
    private WebElement internetEmailField;

    @FindBy(id = "score-instalment")
    private WebElement scoreInstalmentField;

    @FindBy(id = "instalment-sum")
    private WebElement instalmentSumField;

    @FindBy(id = "score-arrears")
    private WebElement scoreArrearsField;

    @FindBy(id = "arrears-sum")
    private WebElement arrearsSumField;

    @FindBy(id = "arrears-email")
    private WebElement arrearsEmailField;

    @FindBy(className = "select__arrow")
    private WebElement selectHeader;

    @FindBy(xpath = "//input[@id='connection-phone']")
    private WebElement phoneField;

    @FindBy(xpath = "//input[@id='connection-sum']")
    private WebElement sumField;

    @FindBy(xpath = "//input[@id='connection-email']")
    private WebElement emailField;
    @FindBy(xpath = "//div[@id='pay-section']")
    private WebElement paySection;
    @FindBy(xpath = "//form[@id='pay-connection']//button")
    private WebElement continueButton;

    private WebElement popupSum;

    private WebElement popupPhoneNumber;

    private WebElement buttonValue;

    // Конструктор класса
    public OnlineReplenishmentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    private void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    private void openSelect() {
        scrollToElement(paySection);
        wait.until(ExpectedConditions.elementToBeClickable(selectHeader)).click();
    }

    //Задание Л13 пункт 1
    // Метод для проверки названия блока "Онлайн пополнение без комиссии"
    public void verifyBlockTitle() {
        WebElement blockTitle = driver.findElement(By.xpath("//div[@id='pay-section']//h2[contains(text(), 'Онлайн пополнение')]"));
        js.executeScript("arguments[0].scrollIntoView(true);", blockTitle);
        String actualTitle = blockTitle.getText().replaceAll("\n", " ").replaceAll("\\s+", " ").trim();
        assertEquals("Онлайн пополнение без комиссии", actualTitle);
    }

    //Задание Л13 пункт 2
    // Метод для проверки наличия логотипов платёжных систем
    public void verifyPaymentLogos() {
        assertTrue("Логотип платёжной системы Visa не найден", driver.findElements(By.xpath("//img[@alt='Visa']")).size() > 0);
        assertTrue("Логотип платёжной системы Verified By Visa не найден", driver.findElements(By.xpath("//img[@alt='Verified By Visa']")).size() > 0);
        assertTrue("Логотип платёжной системы MasterCard не найден", driver.findElements(By.xpath("//img[@alt='MasterCard']")).size() > 0);
        assertTrue("Логотип платёжной системы MasterCard Secure Code не найден", driver.findElements(By.xpath("//img[@alt='MasterCard Secure Code']")).size() > 0);
        assertTrue("Логотип платёжной системы Белкарт не найден", driver.findElements(By.xpath("//img[@alt='Белкарт']")).size() > 0);
    }

    //Задание Л13 пункт 3
    // Метод для проверки ссылки "Подробнее о севисе"
    public void checkMoreInfoLink() {
        WebElement moreInfoLink = driver.findElement(By.linkText("Подробнее о сервисе"));
        moreInfoLink.click();
        String pageTitle = driver.getTitle();
        assertEquals("Порядок оплаты и безопасность интернет платежей", pageTitle);
        driver.navigate().back();
    }

    // Задание Л14 пункт 1: проверка надписей в незаполненных полях для всех вариантов оплаты
    public void checkEmptyFieldLabelsForPaymentOptions() {
        openSelect();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Проверка полей для услуг связи
        checkPlaceholderForService(connectionPhoneField, "Номер телефона");
        checkPlaceholderForService(connectionSumField, "Сумма");
        checkPlaceholderForService(connectionEmailField, "E-mail для отправки чека");

        // Проверка для "Домашний интернет"
        selectPaymentOption("Домашний интернет");
        checkPlaceholderForService(internetPhoneField, "Номер абонента");
        checkPlaceholderForService(internetSumField, "Сумма");
        checkPlaceholderForService(internetEmailField, "E-mail для отправки чека");

        // Проверка для "Рассрочка"
        selectPaymentOption("Рассрочка");
        checkPlaceholderForService(scoreInstalmentField, "Номер счета на 44");
        checkPlaceholderForService(instalmentSumField, "Сумма");

        // Проверка для "Задолженность"
        selectPaymentOption("Задолженность");
        checkPlaceholderForService(scoreArrearsField, "Номер счета на 2073");
        checkPlaceholderForService(arrearsSumField, "Сумма");
        checkPlaceholderForService(arrearsEmailField, "E-mail для отправки чека");

        // Проверка для "Услуги связи"
        selectPaymentOption("Услуги связи");
    }

    private void checkPlaceholderForService(WebElement inputField, String expectedPlaceholder) {
        String placeholder = inputField.getAttribute("placeholder");
        System.out.println("Placeholder поля " + expectedPlaceholder + ": " + placeholder);
        assertEquals(expectedPlaceholder, placeholder);
    }

    private void selectPaymentOption(String optionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(selectHeader)).click();

        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()='" + optionText + "']")));
        option.click();
    }

    //Задание Л13 пункт 4.1
    // Метод для заполнения полей для «Услуги связи»
    public void fillInTelecomServices(String phoneNumber, String amount, String connectionEmail) {
        phoneField.sendKeys(phoneNumber);
        sumField.sendKeys(amount);
        emailField.sendKeys(connectionEmail);
        continueButton.click();

    }

    // Задание Л13 пункт 4.2 + Задание Л14 пункт 2
    public String[] initPopupValues() {

        // Ожидаем появления iframe
        WebDriverWait waitForFrame = new WebDriverWait(driver, Duration.ofSeconds(10));
        waitForFrame.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@class='bepaid-iframe']")));
        // Теперь ожидаем появления элементов внутри iframe
        popupPhoneNumber = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Номер:')]")));
        popupSum = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'BYN')]")));
        buttonValue = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Оплатить')]")));

        String[] asd = new String[3];
        asd[0] = popupPhoneNumber.getText();
        asd[1] = popupSum.getText();
        asd[2] = buttonValue.getText();
        return asd;
    }

    public boolean areCardsDisplayedPopup() {
        WebElement cards = driver.findElement(By.xpath("//div[@class='cards-brands cards-brands__container ng-tns-c61-0 ng-trigger ng-trigger-brandsState ng-star-inserted']"));
        return cards.isDisplayed();
    }

    public String[] getPopupFieldsPlaceholders() {
        WebElement cardNumber = driver.findElement(By.xpath("//div[@class='content ng-tns-c46-1']/label"));
        WebElement cardDate = driver.findElement(By.xpath("//div[@class='content ng-tns-c46-4']/label"));
        WebElement cardCvc = driver.findElement(By.xpath("//div[@class='content ng-tns-c46-5']/label"));
        WebElement cardHolderName = driver.findElement(By.xpath("//div[@class='content ng-tns-c46-3']/label"));

        String[] placeholders = new String[4];
        placeholders[0] = cardNumber.getText();
        placeholders[1] = cardDate.getText();
        placeholders[2] = cardCvc.getText();
        placeholders[3] = cardHolderName.getText();

        return placeholders;
    }

    public void handleCookieConsent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Найти элемент согласия с куками
            WebElement agreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cookie-agree")));

            // Прокрутка для видимости элемента
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", agreeButton);

            // Подождите, пока элемент станет кликабельным
            wait.until(ExpectedConditions.elementToBeClickable(agreeButton));

            // Кликнуть на кнопку
            agreeButton.click();
            System.out.println("Согласие с куки-файлами подтверждено");

        } catch (NoSuchElementException e) {
            System.out.println("Уведомление о куках не появилось, продолжаем тестирование");
        } catch (TimeoutException e) {
            System.out.println("Уведомление о куках не появилось в установленное время, продолжаем тестирование");
        } catch (ElementClickInterceptedException e) {
            System.out.println("Кнопка согласия с куками перекрыта другим элементом, продолжаем тестирование");
        }
    }
}
