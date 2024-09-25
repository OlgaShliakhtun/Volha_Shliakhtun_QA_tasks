package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.OnlineReplenishmentPage;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.Duration;
import java.util.Locale;

public class MtsOnlineReplenishmentTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private OnlineReplenishmentPage onlineReplenishmentPage;
    private String phone = "297777777";
    private String sum = "5";
    private String email = "olga@gmail.com";

    @Before
    public void setUp() {
        // Настройка WebDriverManager для автоматической загрузки драйвера
        WebDriverManager.chromedriver().setup();
        // Создание экземпляра веб-драйвера для Chrome
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        onlineReplenishmentPage = new OnlineReplenishmentPage(driver);
        // Выполнение инициализационной логики
        initializeTestEnvironment();
    }

    private void initializeTestEnvironment() {
        // Переход на сайт mts.by
        driver.get("https://mts.by");

        // Согласие с куки-файлами
        onlineReplenishmentPage.handleCookieConsent();
    }

    @Test
    @Description("Проверка названия блока 'Онлайн пополнение без комиссии'")
    @Step("Выполняем проверку заголовка блока 'Онлайн пополнение без комиссии'")
    public void testBlockTitle() {
        onlineReplenishmentPage.verifyBlockTitle();
    }

    @Test
    @Description("Проверка наличия логотипов платёжных систем")
    @Step("Выполняем проверку логотипов платёжных систем")
    public void testPaymentLogos() {
        onlineReplenishmentPage.verifyPaymentLogos();
    }

    @Test
    @Description("Проверка работы ссылки 'Подробнее о сервисе'")
    @Step("Проверяем работу ссылки'Подробнее о сервисе'")
    public void testMoreInfoLink() {
        onlineReplenishmentPage.checkMoreInfoLink();
    }

    @Test
    @Description("Проверка надписей в незаполненных полях для всех вариантов оплаты")
    @Step("Проверяем надписи в незаполненных полях для всех вариантов оплаты")
    public void testEmptyFieldLabels() {
        onlineReplenishmentPage.checkEmptyFieldLabelsForPaymentOptions();
    }

    @Test
    @Description("Проверка функциональности кнопки 'Продолжить'")
    @Step("Заполняем данные для опции 'Услуги связи'")
    public void testContinueButtonFunctionality() {
        onlineReplenishmentPage.fillInTelecomServices(phone, sum, email);
        String[] webElements = onlineReplenishmentPage.initPopupValues();
        String actualSum = webElements[1].split(" ")[0].split("\\.")[0];
        String actualButtonValue = webElements[2].substring(9, 13);
        String actualPhone = webElements[0].substring(30);

        // Преобразование строки sum в нужный формат для проверки отображения на кнопке
        double sumDouble = Double.parseDouble(sum);
        // Форматирование с двумя знаками после запятой
        DecimalFormat formattedSumDouble = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.ENGLISH));
        String formattedNumber = formattedSumDouble.format(sumDouble);
        String SumOnTheButton = formattedNumber;

        @Step("Проверяем отображение суммы")
        Assertions.assertEquals(actualSum, sum, "Сумма неверна");

        @Step("Проверяем отображение номера телефона")
        Assertions.assertEquals(actualPhone, phone, "Телефон неверен");

        @Step("Проверяем отображение номера телефона на кнопке")
        Assertions.assertEquals(actualButtonValue, SumOnTheButton, "Сумма на кнопке неверна");

        @Step("Проверяем отображение иконок карт")
        Assertions.assertTrue(onlineReplenishmentPage.areCardsDisplayedPopup(), "Иконки карт в попапе не отображаются");

        @Step("Проверяем плейсхолдеры полей карты")

        // Проверка плейсхолдеров полей карты
        String[] placeholders = onlineReplenishmentPage.getPopupFieldsPlaceholders();
        Assertions.assertEquals(placeholders[0], "Номер карты", "Неверный плейсхолдер номера карты");
        Assertions.assertEquals(placeholders[1], "Срок действия", "Неверный плейсхолдер срока карты");
        Assertions.assertEquals(placeholders[2], "CVC", "Неверный плейсхолдер CVC карты");
        Assertions.assertEquals(placeholders[3], "Имя держателя (как на карте)", "Неверный плейсхолдер имени держателя карты");
    }

    @After
    public void tearDown() {
        // Закрываем драйвер
        if (driver != null) {
            driver.quit();
        }
    }
}