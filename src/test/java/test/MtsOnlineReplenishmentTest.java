package test;

import io.github.bonigarcia.wdm.WebDriverManager;
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
    public void testBlockTitle() {

        // Задание Л13 пункт 1. Проверка названия блока "Онлайн пополнение без комиссии"
        onlineReplenishmentPage.verifyBlockTitle();
    }

    @Test
    public void testPaymentLogos() {
        // Задание Л13 пункт 2. Проверка наличия логотипов платёжных систем
        onlineReplenishmentPage.verifyPaymentLogos();
    }

    @Test
    public void testMoreInfoLink() {

        // Задание Л13 пункт 3. Проверка работы ссылки «Подробнее о сервисе»
        onlineReplenishmentPage.checkMoreInfoLink();
    }

    @Test
    public void testEmptyFieldLabels() {

        // Задание Л14 пункт 1. Проверка надписей в незаполненных полях для всех вариантов оплаты
        onlineReplenishmentPage.checkEmptyFieldLabelsForPaymentOptions();
    }
    // Задание Л13 пункт 4.2 + Задание Л14 пункт 2
    @Test
    public void testContinueButtonFunctionality() {

        onlineReplenishmentPage.fillInTelecomServices(phone, sum, email);
        String[] webElements = onlineReplenishmentPage.initPopupValues();
        String actualSum = webElements[1].split(" ")[0].split("\\.")[0];
        String actualButtonValue = webElements[2].substring(9, 13);
        String actualPhone = webElements[0].substring(30);

        //  Преобразование строки sum в нужный формат для проверки отображения на кнопке
        double sumDouble = Double.parseDouble(sum);
        //  Форматирование с двумя знаками после запятой
        DecimalFormat formattedSumDouble = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.ENGLISH));
        String formattedNumber = formattedSumDouble.format(sumDouble); // Использует точку как разделитель
        // Теперь formattedNumber будет в формате"0.00"
        // Возврат в строку
        String SumOnTheButton = formattedNumber; // Это значение уже в строковом формате

        Assertions.assertEquals(actualSum, sum, "Сумма неверна");
        Assertions.assertEquals(actualPhone, phone, "Телефон неверен");
        Assertions.assertEquals(actualButtonValue, SumOnTheButton, "Сумма на кнопке неверна");
        Assertions.assertTrue(onlineReplenishmentPage.areCardsDisplayedPopup(), "Иконки карт в попапе не отображаются");

        //Проверка плейсхолдеров полей карты
        String[] placeholders = onlineReplenishmentPage.getPopupFieldsPlaceholders();
        Assertions.assertEquals(placeholders[0], "Номер карты", "Неверный плейсхолдер номера карты");
        Assertions.assertEquals(placeholders[1], "Срок действия", "Неверный лейсхолдер срока карты");
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