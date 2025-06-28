package stellar.tests;

import stellar.pages.HomePage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Stellar Burgers")
@Feature("Конструктор")
public class ConstructorNavigationTest extends BaseTest {
    private final String browser = System.getProperty("browser", "chrome");

    @Test
    @DisplayName("Навигация по разделу Булки")
    @Description("Проверка переключения на раздел 'Булки'")
    public void testBunsNavigation() {
        runTest(browser, () -> {
            HomePage homePage = new HomePage(driver);
            homePage.clickSaucesSection();
            homePage.clickBunsSection();
            assertEquals("Булки", homePage.getActiveSectionText());
        });
    }

    @Test
    @DisplayName("Навигация по разделу Соусы")
    @Description("Проверка переключения на раздел 'Соусы'")
    public void testSaucesNavigation() {
        runTest(browser, () -> {
            HomePage homePage = new HomePage(driver);
            homePage.clickSaucesSection();
            assertEquals("Соусы", homePage.getActiveSectionText());
        });
    }

    @Test
    @DisplayName("Навигация по разделу Начинки")
    @Description("Проверка переключения на раздел 'Начинки'")
    public void testFillingsNavigation() {
        runTest(browser, () -> {
            HomePage homePage = new HomePage(driver);
            homePage.clickFillingsSection();
            assertEquals("Начинки", homePage.getActiveSectionText());
        });
    }
}