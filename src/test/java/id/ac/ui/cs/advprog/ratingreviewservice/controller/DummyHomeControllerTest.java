package id.ac.ui.cs.advprog.ratingreviewservice.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DummyHomeControllerTest {

    @Test
    void testDummyHomePage() {
        DummyHomeController dummyHomeController = new DummyHomeController();
        String viewName = dummyHomeController.homePage();
        assertEquals("DummyHomepage", viewName);
    }
}