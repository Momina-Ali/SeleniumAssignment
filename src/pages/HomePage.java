package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    By logo = By.xpath("//img[@class='home-logo lazyloaded']");
    By headerLinks = By.cssSelector("a[href='/about-us']");
    By applyNowButton = By.cssSelector("li[id='menu-item-16'] a");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void navigateToHomePage(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void scrollToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public boolean isLogoDisplayed() {
        try {
            WebElement logoElement = wait.until(ExpectedConditions.visibilityOfElementLocated(logo));
            return logoElement.isDisplayed();
        } catch (Exception e) {
            System.out.println("Logo not found: " + e.getMessage());
            return false;
        }
    }

    public void clickHeadersAndTakeScreenshots(String originalWindow) throws IOException {
        List<WebElement> headers = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(headerLinks));

        for (WebElement header : headers) {
            String headerText = header.getText();
            String openInNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
            header.sendKeys(openInNewTab);

            // Open new tab
            Set<String> allWindows = driver.getWindowHandles();
            for (String window : allWindows) {
                if (!window.equals(originalWindow)) {
                    driver.switchTo().window(window);
                    break;
                }
            }

            // Wait for the new page to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

            // Take a screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "C:\\Users\\Great1\\Desktop\\SeleniumDrivers\\" + headerText.replaceAll("[^a-zA-Z0-9]", "_") + ".png";
            FileUtils.copyFile(screenshot, new File(screenshotPath));
            System.out.println("Screenshot saved to: " + screenshotPath);

            // Close the new tab and switch back to the original window
            driver.close();
            driver.switchTo().window(originalWindow);
        }
    }

    public void clickApplyNow() {
        try {
            WebElement applyNowBtn = wait.until(ExpectedConditions.elementToBeClickable(applyNowButton));
            applyNowBtn.click();
        } catch (Exception e) {
            System.out.println("Apply Now button not found: " + e.getMessage());
        }
    }
}
