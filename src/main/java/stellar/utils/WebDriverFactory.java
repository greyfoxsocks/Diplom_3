package stellar.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class WebDriverFactory {
    public static WebDriver createDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "yandex":
                return setupYandexDriver();
            case "chrome":
            default:
                return setupChromeDriver();
        }
    }

        private static WebDriver setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    private static WebDriver setupYandexDriver() {
        String driverPath = new File("src/main/resources/chromedriver.exe").getAbsolutePath();
        System.setProperty("webdriver.chrome.driver", driverPath);

        ChromeOptions options = new ChromeOptions();
        options.setBinary(getYandexBrowserPath());

        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        return new ChromeDriver(options);
    }

    private static String getYandexBrowserPath() {
        String customPath = System.getenv("YANDEX_BROWSER_PATH");
        if (customPath != null && !customPath.isEmpty()) {
            return customPath;
        }

        String os = System.getProperty("os.name").toLowerCase();
        String userHome = System.getProperty("user.home");

        if (os.contains("win")) {
            return "C:\\Users\\test\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";
        } else if (os.contains("mac")) {
            return "/Applications/Yandex.app/Contents/MacOS/Yandex";
        } else if (os.contains("linux")) {
            return "/usr/bin/yandex-browser";
        } else {
            throw new UnsupportedOperationException("Unsupported OS: " + os);
        }
    }
}