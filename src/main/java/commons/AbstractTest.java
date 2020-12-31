package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public abstract class AbstractTest {
    private WebDriver driver;
    protected Log log;

    public AbstractTest() {
        log = LogFactory.getLog(getClass());
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String url) {
        if (driver != null) return driver;
        switch (browserName.toLowerCase()) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
            }
            case "hfirefox" -> {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setHeadless(true);
                driver = new FirefoxDriver(firefoxOptions);
            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            }
            case "hchrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setHeadless(true);
//                chromeOptions.addArguments("--start-maximized");
//                chromeOptions.addArguments("--incognito");
//                chromeOptions.setCapability("unhandledPromptBehavior", "accept");
                driver = new ChromeDriver(chromeOptions);
            }
            default -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
            }
        }
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
        return driver;
    }

    protected boolean verifyTrue(boolean condition) {
        boolean status = true;
        try {
            if (condition) {
                log.info(" -------------------------- VERIFY PASSED -------------------------- ");
            } else {
                log.info(" -------------------------- VERIFY FAILED -------------------------- ");
            }
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean status = true;
        try {
            if (!condition) {
                log.info(" -------------------------- VERIFY PASSED -------------------------- ");
            } else {
                log.info(" -------------------------- VERIFY FAILED -------------------------- ");
            }
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean status = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info(" -------------------------- VERIFY PASSED -------------------------- ");
        } catch (Throwable e) {
            status = false;
            log.info(" -------------------------- VERIFY FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status;
    }

    protected void closeBrowserAndDriver() {
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            log.info("OS name = " + osName);

            String cmd = "";
            if (driver != null) {
                driver.quit();
            } else {
                return;
            }

            if (driver.toString().toLowerCase().contains("chrome")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill chromedriver";
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                }
            } else if (driver.toString().toLowerCase().contains("internetexplorer")) {
                if (osName.toLowerCase().contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
                }
            } else if (driver.toString().toLowerCase().contains("firefox")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill geckodriver";
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
                }
            } else if (driver.toString().toLowerCase().contains("edge")) {
                if (osName.toLowerCase().contains("mac")) {
                    cmd = "pkill msedgedriver";
                } else if (osName.toLowerCase().contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
                }
            }

            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();

            log.info("---------- QUIT BROWSER SUCCESS ----------");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    protected String getCurrentDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        int date = localDateTime.getDayOfMonth();
        return date + "";
    }

    protected String getCurrentMonthName() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Month month = localDateTime.getMonth();
        return month.getDisplayName(TextStyle.FULL, Locale.US);
    }

    protected String getCurrentMonthNumber() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Month month = localDateTime.getMonth();
        int monthNumber = month.getValue();
        if (monthNumber < 10) {
            return "0" + monthNumber;
        }
        return monthNumber + "";
    }

    protected String getCurrentTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss a");  //yyyy/MMMM/dd
        return dtf.format(localDateTime);
    }

    protected String getCurrentYear() {
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.getYear() + "";
    }

    protected String dateTimeCustomFormat() {
        return getCurrentMonthName() + " " + getCurrentDate() + ", " + getCurrentYear();
    }

    protected void sleepInSecond(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
