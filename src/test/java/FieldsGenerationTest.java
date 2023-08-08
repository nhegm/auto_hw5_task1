import org.junit.jupiter.api.Test;

public class FieldsGenerationTest {

    @Test
    void shouldGenerateData() {

        DataGenerator newData = new DataGenerator();
        System.out.println(newData.getCity());
        System.out.println(newData.getMeetingDate());
        System.out.println(newData.getName());
        System.out.println(newData.getPhone());

    }

}
