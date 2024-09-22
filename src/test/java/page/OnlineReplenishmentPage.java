package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

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
    private WebElement popupSum;
    private WebElement popupPhoneNumber;


    // Конструктор класса
    public OnlineReplenishmentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
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
        WebElement phoneNumberInput = driver.findElement(By.id("connection-phone"));
        phoneNumberInput.sendKeys(phoneNumber);
        WebElement sum = driver.findElement(By.id("connection-sum"));
        sum.sendKeys(amount);
        WebElement email = driver.findElement(By.id("connection-email"));
        email.sendKeys(connectionEmail);
        WebElement continueButton = driver.findElement(By.xpath("//*[@id='pay-connection']/button"));
        continueButton.click();
    }

    public void fillPaymentForm(String phone, String sum, String email) {
        phoneField.sendKeys(phone);
        sumField.sendKeys(sum);
        emailField.sendKeys(email);
    }

    // Задание Л13 пункт 4.2 + Задание Л14 пункт 2
    // Метод для проверки работы кнопки "Продолжить", а также для проверки:
    // а) корректности отображения суммы ,
    // б) номера телефона,
    public String[] initPopupValues() {
        // Ожидаем появления элементов на странице
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='bepaid-iframe']")));
        popupPhoneNumber = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'Номер:')]")));
        popupSum = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(.,'BYN')]")));

        // Возвращаем текст элементов
        String[] popupValues = new String[2];
        popupValues[0] = popupPhoneNumber.getText();
        popupValues[1] = popupSum.getText();
        return popupValues;
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
