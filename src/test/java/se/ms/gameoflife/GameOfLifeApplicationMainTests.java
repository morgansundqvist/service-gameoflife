package se.ms.gameoflife;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class GameOfLifeApplicationMainTests {

    @Test
    public void mainMethodStartsApplication() {

        ApplicationContextRunner contextRunner = new ApplicationContextRunner()
                .withUserConfiguration(GameoflifeApplication.class);

        assertDoesNotThrow(() -> contextRunner.run(context -> {

            GameoflifeApplication.main(new String[] {});
        }));
    }

}