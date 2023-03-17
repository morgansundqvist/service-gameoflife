package se.ms.gameoflife;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class GameOfLifeApplicationMainTests {

    @Test
    public void mainMethodStartsApplication() {
        // The ApplicationContextRunner allows you to test the main method without
        // starting the full application
        ApplicationContextRunner contextRunner = new ApplicationContextRunner()
                .withUserConfiguration(GameoflifeApplication.class);

        // Assert that the main method does not throw any exceptions
        assertDoesNotThrow(() -> contextRunner.run(context -> {
            // Call the main method and pass an empty array of strings as the argument
            GameoflifeApplication.main(new String[] {});
        }));
    }

}