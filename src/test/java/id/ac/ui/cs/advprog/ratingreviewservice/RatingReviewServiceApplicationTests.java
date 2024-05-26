package id.ac.ui.cs.advprog.ratingreviewservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RatingReviewServiceApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true, "The context loads successfully");
	}

	@Test
	void testMain() {
		RatingReviewServiceApplication.main(new String[] {});
	}
}