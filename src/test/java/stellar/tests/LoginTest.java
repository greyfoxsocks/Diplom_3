package stellar.tests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stellar.pages.ForgotPasswordPage;
import stellar.pages.HomePage;
import stellar.pages.LoginPage;
import stellar.pages.RegistrationPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Stellar Burgers")
@Feature("Авторизация")
public class LoginTest extends BaseTest {
    private final String PASSWORD = "validPassword123";
    private final String NAME = "Тестовый Пользователь";

    private String registerNewUser() {
        HomePage homePage = new HomePage(driver);
        homePage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        String email = "test" + System.currentTimeMillis() + "@example.com";
        registrationPage.setName(NAME);
        registrationPage.setEmail(email);
        registrationPage.setPassword(PASSWORD);
        registrationPage.clickRegisterButton();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("/login"));
        return email;
    }

    @ParameterizedTest(name = "Вход через кнопку 'Войти в аккаунт' в браузере {0}")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testLoginViaMainButton(String browser) {
        runTest(browser, () -> {
            String email = registerNewUser();
            driver.get(BASE_URL);

            HomePage homePage = new HomePage(driver);
            homePage.clickLoginButton();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(PASSWORD);
            loginPage.clickLoginButton();

            assertTrue(new HomePage(driver).isPlaceOrderButtonVisible());
        });
    }

    @ParameterizedTest(name = "Вход через кнопку 'Личный кабинет' в браузере {0}")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testLoginViaPersonalAccount(String browser) {
        runTest(browser, () -> {
            String email = registerNewUser();
            driver.get(BASE_URL);

            HomePage homePage = new HomePage(driver);
            homePage.clickPersonalAccountButton();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.setEmail(email);
            loginPage.setPassword(PASSWORD);
            loginPage.clickLoginButton();

            assertTrue(new HomePage(driver).isPlaceOrderButtonVisible());
        });
    }

    @ParameterizedTest(name = "Вход через кнопку в форме регистрации в браузере {0}")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testLoginViaRegistrationForm(String browser) {
        runTest(browser, () -> {
            String email = registerNewUser();
            driver.get(BASE_URL);

            HomePage homePage = new HomePage(driver);
            homePage.clickLoginButton();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickRegisterLink();

            RegistrationPage registrationPage = new RegistrationPage(driver);
            registrationPage.clickLoginLink();

            loginPage.setEmail(email);
            loginPage.setPassword(PASSWORD);
            loginPage.clickLoginButton();

            assertTrue(new HomePage(driver).isPlaceOrderButtonVisible());
        });
    }

    @ParameterizedTest(name = "Вход через кнопку в форме восстановления пароля в браузере {0}")
    @ValueSource(strings = {"chrome", "yandex"})
    public void testLoginViaPasswordRestore(String browser) {
        runTest(browser, () -> {
            String email = registerNewUser();
            driver.get(BASE_URL);

            HomePage homePage = new HomePage(driver);
            homePage.clickLoginButton();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickRestorePasswordLink();

            ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
            forgotPasswordPage.clickLoginLink();

            loginPage.setEmail(email);
            loginPage.setPassword(PASSWORD);
            loginPage.clickLoginButton();

            assertTrue(new HomePage(driver).isPlaceOrderButtonVisible());
        });
    }
}