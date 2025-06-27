package stellar.tests;

import stellar.pages.HomePage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Stellar Burgers")
@Feature("Конструктор")
public class ConstructorNavigationTest extends BaseTest {

    @ParameterizedTest(name = "Навигация по разделу Булки в браузере {0}")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testBunsNavigation(String browser) {
        runTest(browser, () -> {
            HomePage homePage = new HomePage(driver);
            homePage.clickSaucesSection();
            homePage.clickBunsSection();
            assertEquals("Булки", homePage.getActiveSectionText());
        });
    }

    @ParameterizedTest(name = "Навигация по разделу Соусы в браузере {0}")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testSaucesNavigation(String browser) {
        runTest(browser, () -> {
            HomePage homePage = new HomePage(driver);
            homePage.clickSaucesSection();
            assertEquals("Соусы", homePage.getActiveSectionText());
        });
    }

    @ParameterizedTest(name = "Навигация по разделу Начинки в браузере {0}")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testFillingsNavigation(String browser) {
        runTest(browser, () -> {
            HomePage homePage = new HomePage(driver);
            homePage.clickFillingsSection();
            assertEquals("Начинки", homePage.getActiveSectionText());
        });
    }
}