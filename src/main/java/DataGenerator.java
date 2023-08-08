import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private char[] rusLettersVowels = {'а', 'е', 'и', 'о'};
    private char[] rusLettersConsonants = {'б', 'в', 'г', 'к', 'л', 'м', 'н', 'п', 'р', 'с', 'т'};

    Faker faker = new Faker(new Locale("ru"));
    String city = new String(rusLettersConsonants[new Random().nextInt(rusLettersConsonants.length)] + "" + rusLettersVowels[new Random().nextInt(rusLettersVowels.length)]);
    LocalDate meetingDate = LocalDate.now().plusDays(new Random().nextInt(30) + 3);
    String name = faker.name().fullName();
    String phone = faker.bothify("+7 ### ### ## ##");

    public String getCity() {
        return city;
    }

    public LocalDate getMeetingDate() {
        return meetingDate;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

}
