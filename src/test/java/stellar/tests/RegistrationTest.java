package stellar.tests;

import stellar.pages.HomePage;
import stellar.pages.LoginPage;
import stellar.pages.RegistrationPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Stellar Burgers")
@Feature("Регистрация")
public class RegistrationTest extends BaseTest {

    @ParameterizedTest(name = "Успешная регистрация в браузере {0}")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testSuccessfulRegistration(String browser) {
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

    @ParameterizedTest(name = "Ошибка при некорректном пароле в браузере {0}")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testInvalidPasswordRegistration(String browser) {
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