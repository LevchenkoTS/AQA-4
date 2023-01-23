package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.security.Key;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class DeliveryCardTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
    @Test
    public void shouldFormSubmission() {
        String date = generateDate(3);
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.BACK_SPACE));
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue("Левченко Татьяна Сергеевна");
        $("[data-test-id=phone] input").setValue("+79151378527");
        $(".checkbox__box").click();
        $(".button__content").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + date));
    }

    @Test
    public void shouldFormSubmission2() {
        String date = generateDate(0);
        System.out.println(date);
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        $("[data-test-id=city] input").click();
        $("[data-test-id=city] input").sendKeys("м","о");
        $("[data-test-id=city] input").sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.BACK_SPACE));
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=date] .input__control[placeholder='Дата встречи']").
                sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        $("[data-test-id=name] input").setValue("Левченко Татьяна Сергеевна");
        $("[data-test-id=phone] input").setValue("+79151378527");
        $(".checkbox__box").click();
        $(".button__content").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на "));
    }
}
