import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.common.io.Files;

public class Browzbot {

    private static final Set<String> KNOWN_0_ARG_COMMANDS = Set.of("STOP", "BACK", "FORWARD");
    private static final Set<String> KNOWN_1_ARG_COMMANDS = Set.of("OPEN", "SHOOT", "WAIT");
    private static final Set<String> KNOWN_2_ARG_COMMANDS = Set.of("CLICK", "FILL", "SELECT");

    WebDriver driver;

    /**
     * Used for automated testing only. Don't try and do anything with this sucker,
     * 'cause it'll blow up - HARD.
     * 
     * For God's sake - think of the children.
     */
    public Browzbot() {
        driver = new NullWebDriver();
    }

    public Browzbot(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            this.driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            this.driver = new FirefoxDriver();
        } else {
            driver = new NullWebDriver();
        }
    }

    public Result run(Instruction instruction) {

        if (instruction.command().isEmpty()) {
            return new Result(1, "empty instruction");
        }

        if (!known(instruction)) {
            return new Result(1, "unknown command " + instruction.command());
        }

        if (!hasExpectedNumberOfArguments(instruction)) {
            return new Result(2, "wrong number of arguments for command " + instruction.command());
        }

        handle(instruction);

        return new Result(0, "OK");

    }

    private void handle(Instruction instruction) {
        if (this.driver instanceof NullWebDriver) {
            return;
        }

        String command = instruction.command();

        switch (command) {
            case "STOP":
                handleQuit();
                break;
            case "BACK":
                handleBack();
                break;
            case "FORWARD":
                handleForward();
                break;
            case "OPEN":
                handleOpen(instruction);
                break;
            case "SHOOT":
                handleShoot(instruction);
                break;
            case "WAIT":
                handleWait(instruction);
                break;
            case "CLICK":
                handleClick(instruction);
                break;
            case "FILL":
                handleFill(instruction);
                break;
            case "SELECT":
                handleSelect(instruction);
                break;
            default:
                // ignore any other command
                break;
        }

    }

    private void handleQuit() {
        quit();
    }

    private void handleBack() {
        navigateBack();
    }

    private void handleForward() {
        navigateForward();
    }

    private void handleOpen(Instruction instruction) {
        String domain = instruction.primaryArgument();
        open("http:\\" + domain);
    }

    private void handleShoot(Instruction instruction) {
        String screenshotFileName = instruction.primaryArgument();
        takeScreenshot(screenshotFileName);
    }

    private void handleWait(Instruction instruction) {
        int secondsToWait = Integer.parseInt(instruction.primaryArgument());
        wait(1000 * secondsToWait);
    }

    private void handleClick(Instruction instruction) {
        String thingToClick = instruction.primaryArgument().toUpperCase();
        String details = instruction.secondaryArguments();

        switch (thingToClick) {
            case "ID":
                handleIdClick(details);
                break;
            case "CLASS":
                handleClassClick(details);
                break;
            case "LINK":
                handleLinkClick(details);
                break;
            default:
                // ignore other click types
                break;
        }

    }

    private void handleIdClick(String id) {
        clickById(id);

    }

    private void handleClassClick(String details) {
        String classDesc = details.replaceAll("\\s+", ".");
        clickByClass(classDesc);
    }

    private void handleLinkClick(String details) {
        clickLink(details);
    }

    private void handleFill(Instruction instruction) {
        String fieldIdToFill = instruction.primaryArgument();
        String textToFill = instruction.secondaryArguments();

        fillFormField(fieldIdToFill, textToFill);
    }

    private void handleSelect(Instruction instruction) {
        String selectId = instruction.primaryArgument();
        String valueToChoose = instruction.secondaryArguments();

        selectFromDropdown(selectId, valueToChoose);
    }

    //
    // These following helper methods are all complete, so don't change 'em!
    // You'll need them to create your methods, so you'll need to understand
    // **what** then do - don't necessarily worry about **how** they do it.
    //

    // fieldId is case sensitive, yo
    private void fillFormField(String fieldId, String fieldValue) {
        try {
            WebElement formField = driver.findElement(By.id(fieldId));
            forceClick(formField);
            formField.sendKeys(fieldValue);
        } catch (NoSuchElementException e) {
            // fail silently...so unhelpful
        }

    }

    private void forceClick(WebElement elem) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", elem);
    }

    private void clickById(String id) {
        try {
            WebElement thingToClick = driver.findElement(By.id(id));
            forceClick(thingToClick);
        } catch (NoSuchElementException e) {
            // fail silently...sob
        }
    }

    private void clickByClass(String htmlClass) {
        try {
            WebElement thingToClick = driver.findElement(By.cssSelector(htmlClass));
            forceClick(thingToClick);
        } catch (NoSuchElementException e) {
            // fail silently...fail-ridden
        }
    }

    private void takeScreenshot(String screenshotName) {
        TakesScreenshot scrShot = (TakesScreenshot) driver;
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);

        try {
            Files.copy(srcFile, new File(screenshotName));
        } catch (IOException e) {
            // fail silently...so shameful
        }

    }

    private void selectFromDropdown(String dropdownId, String selectedItem) {
        try {
            Select select = new Select(driver.findElement(By.id(dropdownId)));
            select.selectByVisibleText(selectedItem);
        } catch (NoSuchElementException e) {
            // fail silently...wracked by guilt
        }

    }

    private void clickLink(String linkText) {
        try {
            WebElement link = driver.findElement(By.partialLinkText(linkText));
            forceClick(link);
        } catch (NoSuchElementException e) {
            // fail silently...I feel dirty
        }

    }

    private void navigateBack() {
        driver.navigate().back();
    }

    private void navigateForward() {
        driver.navigate().forward();
    }

    private void wait(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            // fail silently...don't do this on the job, folks
        }
    }

    private void open(String url) {
        try {
            driver.get(url);
        } catch (WebDriverException e) {
            // fail silently...to protect the innocent
        }
    }

    private void quit() {
        driver.quit();
    }

    private boolean hasExpectedNumberOfArguments(Instruction instruction) {
        String command = instruction.command();
        int numArgs = numArguments(instruction);

        if (numArgs == 0) {
            return KNOWN_0_ARG_COMMANDS.contains(command);
        } else if (numArgs == 1) {
            return KNOWN_1_ARG_COMMANDS.contains(command);
        } else {
            return KNOWN_2_ARG_COMMANDS.contains(command);
        }

    }

    private int numArguments(Instruction instruction) {
        boolean primaryArgEmpty = instruction.primaryArgument().isEmpty();
        boolean secondaryArgEmpty = instruction.secondaryArguments().isEmpty();

        if (primaryArgEmpty && secondaryArgEmpty) {
            return 0;
        } else if (!primaryArgEmpty && !secondaryArgEmpty) {
            return 2;
        } else {
            return 1;
        }
    }

    private boolean known(Instruction instruction) {
        String command = instruction.command();
        return KNOWN_0_ARG_COMMANDS.contains(command) || KNOWN_1_ARG_COMMANDS.contains(command)
                || KNOWN_2_ARG_COMMANDS.contains(command);
    }
}
