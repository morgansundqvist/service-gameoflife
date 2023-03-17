package se.ms.gameoflife.types;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ApiResponseTest {
    @Test
    public void testApiResponse() {
        ApiResponse<String> response = new ApiResponse<>("data", "test message");

        // Test data
        assertEquals("data", response.getData(), "The data should be 'data'");
        response.setData("updated data");
        assertEquals("updated data", response.getData(), "The updated data should be 'updated data'");

        // Test message
        assertEquals("test message", response.getMessage(), "The message should be 'test message'");
        response.setMessage("updated message");
        assertEquals("updated message", response.getMessage(), "The updated message should be 'updated message'");
    }
}
