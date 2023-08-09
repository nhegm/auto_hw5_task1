import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PlanningDateTest {

    @Test
    void shouldPassWhenNewDateSetTest() {

        DataGenerator dataGenerator = new DataGenerator();
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("[data-test-id=name] .input__control").setValue(dataGenerator.getName());
        form.$("[data-test-id=phone] .input__control").setValue(dataGenerator.getPhone());
        form.$("[data-test-id=city] .input__control").setValue(dataGenerator.getCity());
        form.$("[data-test-id=city] .input__control").sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        form.$("[data-test-id=date] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        form.$("[data-test-id=date] .input__control")
                .setValue(dataGenerator.getMeetingDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        form.$("[data-test-id=date] .input__control").click();
        form.$(".checkbox").sendKeys(Keys.SPACE);
        form.$(".button").click();
        $("[data-test-id=success-notification]").shouldBe(visible);
        $(".notification__title").shouldHave(exactText("Успешно!"));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + dataGenerator.getMeetingDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))))
                .shouldBe(Condition.visible);
        form.$("[data-test-id=name] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        form.$("[data-test-id=name] .input__control").setValue(dataGenerator.getName());
        form.$("[data-test-id=phone] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        form.$("[data-test-id=phone] .input__control").setValue(dataGenerator.getPhone());
        form.$("[data-test-id=city] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        form.$("[data-test-id=city] .input__control").setValue(dataGenerator.getCity());
        form.$("[data-test-id=city] .input__control").sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        form.$("[data-test-id=date] .input__control").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        form.$("[data-test-id=date] .input__control")
                .setValue(dataGenerator.getMeetingDate().minusDays(2).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        form.$(".button").click();
        $("[data-test-id=replan-notification]").shouldBe(visible);
        $("[data-test-id=replan-notification] .notification__title").shouldHave(exactText("Необходимо подтверждение"));
        $("[data-test-id=replan-notification] .notification__content")
                .shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id=replan-notification] .button__text").shouldHave(exactText("Перепланировать")).click();
        $("[data-test-id=success-notification]").shouldBe(visible);
        $(".notification__title").shouldHave(exactText("Успешно!"));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + dataGenerator.getMeetingDate().minusDays(2).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))))
                .shouldBe(Condition.visible);

    }

}
