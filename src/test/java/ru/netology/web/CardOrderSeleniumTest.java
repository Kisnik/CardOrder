package ru.netology.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.By.cssSelector;

public class CardOrderSeleniumTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "driver/win/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }


    //успешная отправка
    @Test
    void shouldSuccess() {
        driver.get("http://localhost:7777");
        driver.findElement(cssSelector("[type='text']")).sendKeys("Василий Васильев");
        driver.findElement(cssSelector("[data-test-id='phone'] input")).sendKeys("+79270000000");
        driver.findElement(cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(cssSelector("[type='button']")).click();
        String actual = driver.findElement(cssSelector(".paragraph")).getText();
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        assertEquals(expected, actual);

    }

    //ФИО по-английски
    @Test
    void shouldHaveEnglishSymbols() {
        driver.get("http://localhost:7777");
        driver.findElement(cssSelector("[type='text']")).sendKeys("Vasily");
        driver.findElement(cssSelector("[data-test-id='phone'] input")).sendKeys("+79270000000");
        driver.findElement(cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(cssSelector("[type='button']")).click();
        String actual = driver.findElement(cssSelector(".input_type_text > .input__inner > .input__sub")).getText();
        String expected = "Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.";
        assertEquals(expected, actual);

    }
}