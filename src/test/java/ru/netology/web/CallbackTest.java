package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class CallbackTest {

//успешная отправка
    @Test
    void shouldSuccess() {
        open("http://localhost:7777");
        $("[type=text]").setValue("Василий Васильев");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".paragraph").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
   //ФИО по-английски
    @Test
    void shouldHaveEnglishSymbols() {
        open("http://localhost:7777");
        $("[type=text]").setValue("Vasily");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text > .input__inner > .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    //ФИО с цифрами
    @Test
    void shouldHaveNumbersFIO() {
        open("http://localhost:7777");
        $("[type=text]").setValue("В1силий");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text > .input__inner > .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    //ФИО со спецсимволами
    @Test
    void shouldHaveSpecialCharacters() {
        open("http://localhost:7777");
        $("[type=text]").setValue("В@силий");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_text > .input__inner > .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    //номер телефона без + в начале
    @Test
    void shouldHaveNotPlus() {
        open("http://localhost:7777");
        $("[type=text]").setValue("Василий Васильев");
        $("[data-test-id=phone] input").setValue("79270000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel > .input__inner > .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    //номер телефона имеет меньше 11 цифр
    @Test
    void shouldHaveLessThanElevenNumbers() {
        open("http://localhost:7777");
        $("[type=text]").setValue("Василий Васильев");
        $("[data-test-id=phone] input").setValue("+7927000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel > .input__inner > .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    //номер телефона имеет больше 11 цифр
    @Test
    void shouldHaveMoreThanElevenNumbers() {
        open("http://localhost:7777");
        $("[type=text]").setValue("Василий Васильев");
        $("[data-test-id=phone] input").setValue("+792700000000");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel > .input__inner > .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    //номер телефона имеет буквы
    @Test
    void shouldNumberHaveCharacters() {
        open("http://localhost:7777");
        $("[type=text]").setValue("Василий Васильев");
        $("[data-test-id=phone] input").setValue("+7927000000f");
        $("[data-test-id=agreement]").click();
        $("[type=button]").click();
        $(".input_type_tel > .input__inner > .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    //нет согласия по персональным данным
    @Test
    void shouldAgreementFalse() {
        open("http://localhost:7777");
        $("[type=text]").setValue("Василий Васильев");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[type=button]").click();
        $(".input_invalid").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}

