package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JobApplicationPage {
    WebDriver driver;
    WebDriverWait wait;
    
    // Selectors
  
    By fullName = By.xpath("//input[@id='name']");
    By dateOfBirth = By.id("dob");
    By email = By.id("email");
    By cnic = By.id("cnic");
    By phone = By.id("phone");
    By address = By.id("address");
    By city = By.id("city");
    By qualificationDropdown = By.id("qualification");
    By yearOfCompletionDropdown = By.id("yr-of-comp");
    By university = By.id("university");
    By cgpa = By.id("cgpa_cd");
    By currentlyWorkingNo = By.cssSelector("input[name='cur-working'][value='No']");
    By currentSalary = By.id("curent-salry");
    By expectedSalary = By.id("salry-expt");
    By expectedDateOfJoining = By.id("doj");
    By hearAboutUs = By.id("hear-abt-us");
    By experienceDropdown = By.id("experiance");
    By resume = By.id("resume");
    By submitButton = By.id("submit-btn");
    
    public JobApplicationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void fillJobApplicationForm(String name, String dob, String email, String cnic, String phone, String address, String city, 
            String qualification, String yearOfCompletion, String university, String cgpa,
            String expectedSalary, String doj, String hearAboutUs, String experience, String resumePath) {
        
        try {
            //wait.until(ExpectedConditions.elementToBeClickable(selectForm)).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(fullName)).sendKeys(name);
            driver.findElement(dateOfBirth).sendKeys(dob);
            driver.findElement(this.email).sendKeys(email);
            driver.findElement(this.cnic).sendKeys(cnic);
            driver.findElement(this.phone).sendKeys(phone);
            driver.findElement(this.address).sendKeys(address);
            driver.findElement(this.city).sendKeys(city);
            new Select(driver.findElement(qualificationDropdown)).selectByVisibleText(qualification);
            new Select(driver.findElement(yearOfCompletionDropdown)).selectByVisibleText(yearOfCompletion);
            driver.findElement(this.university).sendKeys(university);
            driver.findElement(this.cgpa).sendKeys(cgpa);

            // Radio button
            driver.findElement(currentlyWorkingNo).click();

            //driver.findElement(this.currentSalary).sendKeys(currentSalary);
            driver.findElement(this.expectedSalary).sendKeys(expectedSalary);
            driver.findElement(this.expectedDateOfJoining).sendKeys(doj);
            driver.findElement(this.hearAboutUs).sendKeys(hearAboutUs);
            new Select(driver.findElement(experienceDropdown)).selectByVisibleText(experience);
            driver.findElement(this.resume).sendKeys(resumePath);
        } catch (Exception e) {
            System.out.println("Error filling job application form: " + e.getMessage());
        }
    }

    public void submitForm() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
        } catch (Exception e) {
            System.out.println("Error submitting the form: " + e.getMessage());
        }
    }
}
