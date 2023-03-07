package com.gardeners.web.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MainControllerTests {

    @Autowired
    MainController mainController;

    @Test
    public void testHashing() {
        String hashedPw1 = mainController.hashPassword("abc");
        String hashedPw2 = mainController.hashPassword("abc");
        assert hashedPw2 != null;
        assert hashedPw1 != null;
        assert !hashedPw2.equals(hashedPw1);
    }

}
