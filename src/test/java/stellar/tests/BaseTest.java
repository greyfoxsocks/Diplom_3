package stellar.tests;

import io.qameta.allure.Allure;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import stellar.utils.WebDriverFactory;

import java.io.ByteArrayInputStream;
import java.time.Duration;

@ExtendWith(AllureJunit5.class)
public class BaseTest implements TestWatcher {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String BASE_URL = "https://stellarburgers.nomoreparties.site/";

    protected void initDriver(String browser) {
        driver = WebDriverFactory.createDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(BASE_URL);
    }

    protected void runTest(String browser, Runnable testLogic) {
        initDriver(browser);
        try {
            testLogic.run();
        } finally {
            tearDown();
        }
    }

    protected void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        if (driver != null) {
            Allure.addAttachment("Screenshot on failure",
                    new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    }
}