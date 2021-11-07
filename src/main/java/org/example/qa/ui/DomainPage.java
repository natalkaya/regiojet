package org.example.qa.ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.example.qa.util.ConfigReader;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class DomainPage {

    static void tryOpen() throws Exception {
        String baseUrl = ConfigReader.domainPageUrl;

        // checking current url because the website redirects to the new version randomly
        int tryCount = 0;
        final int maxTryCount = 5;
        String currentDriverUrl;
        do {
            tryCount++;
            Selenide.open(baseUrl);
            currentDriverUrl = Selenide.webdriver().driver().getWebDriver().getCurrentUrl();

            if (tryCount > maxTryCount) throw new Exception("Max try attempt recieved");

        } while (!currentDriverUrl.contains(baseUrl));
    }

    static class ApplyCookieAlert {
        public static SelenideElement ButtonApplyCookie = $(By.xpath("//button[contains(text(), 'PRIJAŤ VŠETKY')]"));
    }

    static class FindTicketsBar {
        public static SelenideElement InputRouteFrom = $(By.id("react-select-4-input"));
        public static SelenideElement InputRouteTo = $(By.id("react-select-5-input"));
        public static SelenideElement ButtonSearch = $(By.xpath("//button[@data-id='search-btn']"));

        public static SelenideElement InputDepartureDate = $(By.xpath("//*[@data-id='departure-date']"));

        //Calendar
        public static SelenideElement FirstAvailableMondayDay =
                $(By.xpath("//*[contains(@class, 'CalendarDay__firstDayOfWeek') and @aria-disabled='false']"));
    }

    static class CitiesMenu {
        public static SelenideElement Ostrava = $(By.id("react-select-4-option-52"));
        public static SelenideElement Brno = $(By.id("react-select-5-option-2"));
    }

    static class FoundRoutesLayout {
        public static List<SelenideElement> routesAll = $$(By.xpath("//ul/li[.//h2]"));
        public static List<SelenideElement> routesDirect = $$(By.xpath("//ul/li[.//span[contains(text(), 'Priame spojenie')]]"));
    }

}
