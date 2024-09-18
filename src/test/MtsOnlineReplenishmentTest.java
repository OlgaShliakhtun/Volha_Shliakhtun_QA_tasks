package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;


public class MtsOnlineReplenishmentTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        // Настройка WebDriverManager для автоматической загрузки драйвера
        WebDriverManager.chromedriver().setup();
        // Создание экземпляра веб-драйвера для Chrome
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void testOnlineReplenishment() throws InterruptedException {
        // Переход на сайт mts.by
        driver.get("https://mts.by");
        Thread.sleep(5000);
        WebElement cookieAgree = driver.findElement(By.id("cookie-agree"));
        cookieAgree.click();

        //1.1
        // Проверяем название блока - "Онлайн пополнение без комиссии"
        WebElement blockTitle = driver.findElement(By.xpath("//div[@id='pay-section']//h2[contains(text(), 'Онлайн пополнение')]"));
        // Прокручиваем страницу до элемента
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", blockTitle);
        // Получаем текст элемента и очищаем его от лишних пробелов и переносов
        String actualTitle = blockTitle.getText().replaceAll("\n", " ").replaceAll("\\s+", " ").trim(); // Убираем переносы и лишние пробелы
        // Проверяем, что текст соответствует ожидаемому значению
        assertEquals("Онлайн пополнение без комиссии", actualTitle);

        // 2. Проверяем наличие логотипов платёжных систем
        List<WebElement> paymentLogoVisa = driver.findElements(By.xpath("//img[@alt=\"Visa\"]"));
        assertTrue("Логотип платёжны системы Visa не найден", paymentLogoVisa.size() > 0);
        List<WebElement> paymentLogoVerifiedByVisa = driver.findElements(By.xpath("//img[@alt=\"Verified By Visa\"]"));
        assertTrue("Логотип платёжны системы Verified By Visa не найден", paymentLogoVerifiedByVisa.size() > 0);
        List<WebElement> paymentLogoMasterCard = driver.findElements(By.xpath("//img[@alt=\"MasterCard\"]"));
        assertTrue("Логотип платёжны системы MasterCard не найден", paymentLogoMasterCard.size() > 0);
        List<WebElement> paymentLogoMasterCardSecureCode = driver.findElements(By.xpath("//img[@alt=\"MasterCard Secure Code\"]"));
        assertTrue("Логотип платёжны системы MasterCard Secure Code не найден", paymentLogoMasterCardSecureCode.size() > 0);
        List<WebElement> paymentLogoBelkart = driver.findElements(By.xpath("//img[@alt=\"Белкарт\"]"));
        assertTrue("Логотип платёжны системы Белкарт не найден", paymentLogoBelkart.size() > 0);

        // 3. Проверяем работу ссылки «Подробнее о сервисе»
        WebElement moreInfoLink = driver.findElement(By.linkText("Подробнее о сервисе"));
        moreInfoLink.click();
        // Получим заголовок страницы
        String pageTitle = driver.getTitle();
        // Ожидаемое значение заголовка страницы
        String expectedTitle = "Порядок оплаты и безопасность интернет платежей";
        // Проверка, что заголовок страницы соответствует ожидаемому
        assertEquals(pageTitle, expectedTitle, "Заголовок страницы не соответствует ожидаемому.");
        // Возвращаемся на предыдущую страницу
        driver.navigate().back();

        // 4. Заполняем поля и проверяем работу кнопки «Продолжить»
        WebElement phoneNumberInput = driver.findElement(By.id("connection-phone"));
        phoneNumberInput.click();
        phoneNumberInput.sendKeys("297777777");
        WebElement sum = driver.findElement(By.id("connection-sum"));
        sum.click();
        sum.sendKeys("5");
        WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"pay-connection\"]/button"));
        continueButton.click();
        List<WebElement> containers = driver.findElements(By.xpath("//div[contains(@class, 'app-wrapper')]"));
        for (WebElement container : containers) {
            if (container.isDisplayed()) {
                WebElement paymentContainer = container;
                break; // Останавливаем цикл после нахождения первого видимого элемента
            }
        }

    }

    @After
    public void tearDown() {
        // Закрываем драйвер
        if (driver != null) {
            driver.quit();
        }
    }
}
