package stellar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

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

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
    }

    public void clickBunsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsSection)).click();
    }

    public void clickSaucesSection() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesSection)).click();
    }

    public void clickFillingsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsSection)).click();
    }

    public String getActiveSectionText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeSection)).getText();
    }

    public boolean isPlaceOrderButtonVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton)).isDisplayed();
    }
}