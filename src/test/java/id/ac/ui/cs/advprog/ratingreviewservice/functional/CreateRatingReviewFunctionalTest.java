package id.ac.ui.cs.advprog.ratingreviewservice.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateRatingReviewFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     * The base URL for testing. Default value to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = String.format("%s:%d/rating-review/list", testBaseUrl, serverPort);
    }

    @Test
    void pageTitle_isCorrect(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(testBaseUrl + ":" + serverPort + "/rating-review/add");
        String pageTitle = driver.getTitle();

        // Verify
        assertEquals("Add Rating & Review", pageTitle);
    }

    @Test
    void listMessage_createRatingReviewPage_isCorrect(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(testBaseUrl + ":" + serverPort + "/rating-review/add");
        String welcomeMessage = driver.findElement(By.tagName("h3")).getText();

        // Verify
        assertEquals("Add a new rating and review", welcomeMessage);
    }

    @Test
    void addRatingReview_isCorrect(ChromeDriver driver) throws Exception {
        // Exercise
        driver.get(testBaseUrl + ":" + serverPort + "/product/create");

        driver.findElement(By.id("nameInput")).sendKeys("New Rating Review");
        driver.findElement(By.id("ratingInput")).sendKeys("5");
        driver.findElement(By.id("reviewInput")).sendKeys("Ini cuma testing");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        driver.get(baseUrl);

        // Verify
        WebElement ratingReviewList = driver.findElement(By.tagName("body"));
        assertTrue(ratingReviewList.getText().contains("New Rating Review"));
        assertTrue(ratingReviewList.getText().contains("5"));
        assertTrue(ratingReviewList.getText().contains("Ini cuma testing"));
    }
}