package stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    protected final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    private final By loginButton = By.xpath("//button[contains(text(),'Войти в аккаунт')]");
    private final By personalAccountButton = By.xpath("//a[@href='/account']");
    private final By bunsSection = By.xpath("//div[./span[text()='Булки']]");
    private final By saucesSection = By.xpath("//div[./span[text()='Соусы']]");
    private final By fillingsSection = By.xpath("//div[./span[text()='Начинки']]");
    private final By activeSection = By.xpath("//div[contains(@class, 'tab_tab_type_current')]");
    private final By placeOrderButton = By.xpath("//button[contains(text(),'Оформить заказ')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Step("Открыть главную страницу")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Кликнуть по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Кликнуть по кнопке 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
    }

    @Step("Кликнуть по разделу 'Булки'")
    public void clickBunsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsSection)).click();
    }

    @Step("Кликнуть по разделу 'Соусы'")
    public void clickSaucesSection() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesSection)).click();
    }

    @Step("Кликнуть по разделу 'Начинки'")
    public void clickFillingsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsSection)).click();
    }

    @Step("Получить текст активного раздела")
    public String getActiveSectionText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeSection)).getText();
    }

    @Step("Проверить видимость кнопки 'Оформить заказ'")
    public boolean isPlaceOrderButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton)).isDisplayed();
    }
}