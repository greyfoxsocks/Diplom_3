package stellar.tests;

import stellar.api.UserAPI;
import stellar.pages.ForgotPasswordPage;
import stellar.pages.HomePage;
import stellar.pages.LoginPage;
import stellar.pages.RegistrationPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;

@Epic("Stellar Burgers")
@Feature("Авторизация")
public class LoginTest extends BaseTest {
    private static final String PASSWORD = "validPassword123";
    private static final String NAME = "Тестовый Пользователь";
    private static String email;
    private static final Faker faker = new Faker();

    @BeforeEach
    public void registerUser() {
        email = faker.internet().emailAddress();
        UserAPI.registerUser(email, PASSWORD, NAME);
    }

    @AfterEach
    public void deleteUser() {
        String token = UserAPI.loginUser(email, PASSWORD);
        if (token != null) {
            UserAPI.deleteUser(token);
        }
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт'")
    @Description("Проверка входа через главную кнопку авторизации")
    public void testLoginViaMainButton() {
        runTest(System.getProperty("browser", "chrome"), () -> {
            HomePage homePage = new HomePage(driver);
            homePage.open();
            homePage.clickLoginButton();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(email, PASSWORD);
            assertTrue(homePage.isPlaceOrderButtonVisible());
        });
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка входа через кнопку личного кабинета")
    public void testLoginViaPersonalAccount() {
        runTest(System.getProperty("browser", "chrome"), () -> {
            HomePage homePage = new HomePage(driver);
            homePage.open();
            homePage.clickPersonalAccountButton();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(email, PASSWORD);
            assertTrue(homePage.isPlaceOrderButtonVisible());
        });
    }

    @Test
    @DisplayName("Вход через форму регистрации")
    @Description("Проверка входа через ссылку в форме регистрации")
    public void testLoginViaRegistrationForm() {
        runTest(System.getProperty("browser", "chrome"), () -> {
            HomePage homePage = new HomePage(driver);
            homePage.open();
            homePage.clickLoginButton();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickRegisterLink();

            RegistrationPage registrationPage = new RegistrationPage(driver);
            registrationPage.clickLoginLink();

            loginPage.login(email, PASSWORD);
            assertTrue(homePage.isPlaceOrderButtonVisible());
        });
    }

    @Test
    @DisplayName("Вход через форму восстановления пароля")
    @Description("Проверка входа через ссылку в форме восстановления пароля")
    public void testLoginViaPasswordRestore() {
        runTest(System.getProperty("browser", "chrome"), () -> {
            HomePage homePage = new HomePage(driver);
            homePage.open();
            homePage.clickLoginButton();

            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickRestorePasswordLink();

            ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
            forgotPasswordPage.clickLoginLink();

            loginPage.login(email, PASSWORD);
            assertTrue(homePage.isPlaceOrderButtonVisible());
        });
    }
}