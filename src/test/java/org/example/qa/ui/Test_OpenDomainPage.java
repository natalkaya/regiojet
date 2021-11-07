package org.example.qa.ui;

import org.example.qa.test.UiTestBase;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static org.example.qa.model.Location.BRNO;
import static org.example.qa.model.Location.OSTRAVA;

public class Test_OpenDomainPage extends UiTestBase {

    @Test
    void openDomainPage() throws Exception {
        final String CityRouteFrom = OSTRAVA.name;
        final String CityRouteTo = BRNO.name;

        DomainPage.tryOpen();
        DomainPage.ApplyCookieAlert.ButtonApplyCookie.click();

        DomainPage.FindTicketsBar.InputRouteFrom.setValue(CityRouteFrom);
        DomainPage.CitiesMenu.Ostrava.click();

        DomainPage.FindTicketsBar.InputRouteTo.setValue(CityRouteTo);
        DomainPage.CitiesMenu.Brno.click();

        DomainPage.FindTicketsBar.InputDepartureDate.click();
        DomainPage.FindTicketsBar.FirstAvailableMondayDay.click();

        DomainPage.FindTicketsBar.ButtonSearch.click();

        DomainPage.FoundRoutesLayout.routesAll.forEach(el -> el.shouldBe(visible));

        log.info("Direct routes count: " + (long) DomainPage.FoundRoutesLayout.routesDirect.size());
    }
}
