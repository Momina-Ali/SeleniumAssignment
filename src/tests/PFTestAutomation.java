package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.HomePage;
import pages.JobApplicationPage;

import java.io.IOException;

public class PFTestAutomation {
    public static void main(String[] args) {
        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Great1\\Desktop\\SeleniumDrivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Initialize ChromeDriver
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        try {
            HomePage homePage = new HomePage(driver);
            JobApplicationPage jobApplicationPage = new JobApplicationPage(driver);

            // Navigate to the site
            homePage.navigateToHomePage("https://pf.com.pk/");

            // Scroll from top to bottom
            homePage.scrollToBottom();

            // Assert the logo is present
            if (homePage.isLogoDisplayed()) {
                System.out.println("Logo is displayed.");
            } else {
                System.out.println("Logo is not displayed.");
            }

            // Click on each header and open in a new tab, then take a screenshot
            String originalWindow = driver.getWindowHandle();
            homePage.clickHeadersAndTakeScreenshots(originalWindow);

            // Click on Apply Now button
            homePage.clickApplyNow();
            homePage.scrollToBottom();

            // Fill the form for one position in available jobs
            jobApplicationPage.fillJobApplicationForm(
                "Momina",
                "1994-01-01",
                "momina@example.com",
                "1234567890123",
                "1234567890",
                "DHA Lahore 123 street",
                "Lahore",
                "BS Software Engineering",
                "2016",
                "Punjab University",
                "3.5",
                "50000",
                "70000",
                "2024-07-15",
                "LinkedIn",
                "7 Years",
                "C:\\Users\\Great1\\Desktop\\SeleniumDrivers\\Resume_Momina.pdf"
            );

            // Submit the form
            jobApplicationPage.submitForm();
            
            // Display success message
            System.out.println("Job applied successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}
