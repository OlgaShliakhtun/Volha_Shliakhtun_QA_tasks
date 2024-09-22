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

import java.time.Duration;

public class MtsOnlineReplenishmentTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private OnlineReplenishmentPage onlineReplenishmentPage;

    @Before
    public void setUp() {
        // Настройка WebDriverManager для автоматической загрузки драйвера
        WebDriverManager.chromedriver().setup();
        // Создание экземпляра веб-драйвера для Chrome
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        onlineReplenishmentPage = new OnlineReplenishmentPage(driver);
    }

    @Test
    public void testOnlineReplenishment() throws InterruptedException {
        // Переход на сайт mts.by
        driver.get("https://mts.by");
        Thread.sleep(500);
        // Согласие с куки-файлами
        //driver.findElement(By.id("cookie-agree")).click();
        onlineReplenishmentPage.handleCookieConsent();

        // Задание Л13 пункт 1. Проверка названия блока "Онлайн пополнение без комиссии"
        onlineReplenishmentPage.verifyBlockTitle();

        // Согласие с куки-файлами
        onlineReplenishmentPage.handleCookieConsent();


        // Задание Л13 пункт 2. Проверка наличия логотипов платёжных систем
        onlineReplenishmentPage.verifyPaymentLogos();

        // Согласие с куки-файлами
        onlineReplenishmentPage.handleCookieConsent();

        // Задание Л13 пункт 3. Проверка работы ссылки «Подробнее о сервисе»
        onlineReplenishmentPage.checkMoreInfoLink();

        // Согласие с куки-файлами
        onlineReplenishmentPage.handleCookieConsent();

        // Задание Л14 пункт 1. Метод для проверки надписей в незаполненных полях для всех вариантов оплаты
        onlineReplenishmentPage.checkEmptyFieldLabelsForPaymentOptions();

        // Согласие с куки-файлами
        onlineReplenishmentPage.handleCookieConsent();

        // Задание Л13 пункт 4.1. Заполняем поля «Услуги связи»
        onlineReplenishmentPage.fillInTelecomServices("297777777", "5", "olga@gmail.com");

        // Согласие с куки-файлами
        onlineReplenishmentPage.handleCookieConsent();

        // Задание Л13 пункт 4.2 + Задание Л14 пункт 2
        // Проверяем работу кнопки "Продолжить", а также :
        // а) корректности отображения суммы,
        // б) номера телефона

        String[] webElements = onlineReplenishmentPage.initPopupValues();
        String actualSum = webElements[1].split(" ")[0].split("\\.")[0];
        String actualPhone = webElements[0].substring(30);
        Assertions.assertEquals(actualSum, "5", "Сумма неверна");
        Assertions.assertEquals(actualPhone, "297777777", "Телефон неверен");
    }

    @After
    public void tearDown() {
        // Закрываем драйвер
        if (driver != null) {
            driver.quit();
        }
    }
}

