package org.example.qa.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public class UiTestBase extends TestBase {

    final static AllureSelenide allureSelenide =
            new AllureSelenide()
                    .screenshots(true)
                    .savePageSource(true);

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", allureSelenide);
    }

}
