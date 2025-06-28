package stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailField = By.xpath("//input[@type='text']");
    private final By passwordField = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private final By restorePasswordLink = By.xpath("//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Ввести email: {email}")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
    }

    @Step("Кликнуть по кнопке 'Войти'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Кликнуть по ссылке 'Зарегистрироваться'")
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    @Step("Кликнуть по ссылке 'Восстановить пароль'")
    public void clickRestorePasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(restorePasswordLink)).click();
    }

    @Step("Проверить видимость поля email")
    public boolean isEmailFieldVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).isDisplayed();
    }

    @Step("Выполнить вход с email={email} и паролем")
    public void login(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }
}