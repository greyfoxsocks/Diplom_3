package stellar.tests;

import stellar.pages.HomePage;
import stellar.pages.LoginPage;
import stellar.pages.RegistrationPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Stellar Burgers")
@Feature("Регистрация")
public class RegistrationTest extends BaseTest {
    private final String browser = System.getProperty("browser", "chrome");

    @Test
    @DisplayName("Успешная регистрация")
    public void testSuccessfulRegistration() {
        runTest(browser, () -> {
            HomePage homePage = new HomePage(driver);
            homePage.clickLoginButton();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickRegisterLink();

            RegistrationPage registrationPage = new RegistrationPage(driver);
            registrationPage.setName("Тестовый Пользователь");
            registrationPage.setEmail("test" + System.currentTimeMillis() + "@example.com");
            registrationPage.setPassword("validPassword123");
            registrationPage.clickRegisterButton();

            assertTrue(new LoginPage(driver).isEmailFieldVisible());
        });
    }

    @Test
    @DisplayName("Ошибка при некорректном пароле")
    public void testInvalidPasswordRegistration() {
        runTest(browser, () -> {
            HomePage homePage = new HomePage(driver);
            homePage.clickLoginButton();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickRegisterLink();

            RegistrationPage registrationPage = new RegistrationPage(driver);
            registrationPage.setName("Тестовый Пользователь");
            registrationPage.setEmail("test@example.com");
            registrationPage.setPassword("123");
            registrationPage.clickRegisterButton();

            assertTrue(registrationPage.isPasswordErrorVisible());
        });
    }
}