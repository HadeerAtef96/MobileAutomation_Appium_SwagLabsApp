package utils.elementActions;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static utils.LogHelper.logErrorStep;
import static utils.LogHelper.logInfoStep;


public class NativeIOSActions {

    //Define Global Variables
    AppiumDriver driver;
    Wait<AppiumDriver> wait;

    //Constructor
    public NativeIOSActions(AppiumDriver driver) {
        //Assign driver from page class to Action class
        this.driver = driver;
        //define the wait type and wait configuration
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    //Actions Methods
    @Step
    public void tap(By locator) {
        try {
            //wait until the element is visible on page on GUI
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            //wait until the element is enabled or clickable on page
            wait.until(ExpectedConditions.elementToBeClickable(locator));

            //perform the tap action
            Map<String, Object> param = new HashMap<>();
            param.put("elementId", ((RemoteWebElement) driver.findElement(locator)).getId());
            Dimension elementSize = driver.findElement(locator).getSize();
            param.put("x", elementSize.getWidth() / 2);
            param.put("y", elementSize.getHeight() / 2);
            driver.executeScript("mobile: tap", param);

            logInfoStep("Tapping on Element [%s]".formatted(locator));
        } catch (Exception e) {
            logErrorStep("Failed to Tap on Element [%s]".formatted(locator), e);
        }

    }

    public void tap(By locator, String direction) {
        //Scroll into screen until the element is visible into viewPort
        scrollUntilElementDisplayed(locator, direction);

        tap(locator);
    }

    @Step
    public void doubleTap(By locator) {
        try {
            //wait until the element is visible on page on GUI
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            //wait until the element is enabled or clickable on page
            wait.until(ExpectedConditions.elementToBeClickable(locator));

            //perform the double tap action
            Dimension elementSize = driver.findElement(locator).getSize();

            Map<String, Object> param = new HashMap<>();
            param.put("elementId", ((RemoteWebElement) driver.findElement(locator)).getId());
            param.put("x", elementSize.getWidth() / 2);
            param.put("y", elementSize.getHeight() / 2);
            driver.executeScript("mobile: doubleTap", param);

            logInfoStep("Double Tapping on Element [%s]".formatted(locator));
        } catch (Exception e) {
            logErrorStep("Failed to Double Tap on Element [%s]".formatted(locator), e);
        }

    }

    public void doubleTap(By locator, String direction) {
        //Scroll into screen until the element is visible into viewPort
        scrollUntilElementDisplayed(locator, direction);

        doubleTap(locator);
    }

    @Step
    public void tapAndHold(By locator) {
        try {
            //wait until the element is visible on page on GUI
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            //wait until the element is enabled or clickable on page
            wait.until(ExpectedConditions.elementToBeClickable(locator));

            //perform the tapAndHold action
            Dimension elementSize = driver.findElement(locator).getSize();

            Map<String, Object> param = new HashMap<>();
            param.put("elementId", ((RemoteWebElement) driver.findElement(locator)).getId());
            param.put("duration", 2);
            param.put("x", elementSize.getWidth() / 2);
            param.put("y", elementSize.getHeight() / 2);
            driver.executeScript("mobile: touchAndHold", param);

            logInfoStep("Tapping and Hold on Element [%s]".formatted(locator));
        } catch (Exception e) {
            logErrorStep("Failed to Tap and Hop on Element [%s]".formatted(locator), e);
        }

    }

    public void tapAndHold(By locator, String direction) {
        //Scroll into screen until the element is visible into viewPort
        scrollUntilElementDisplayed(locator, direction);

        tapAndHold(locator);
    }

    @Step
    public void dragAndDrop(By startLocator, By destinationLocator) {
        try {
            //wait until the element is visible on page on GUI
            wait.until(ExpectedConditions.visibilityOfElementLocated(startLocator));
            //wait until the element is enabled or clickable on page
            wait.until(ExpectedConditions.elementToBeClickable(startLocator));

            //perform the drag and drop action
            Point startPoint = getElementCenter( driver.findElement(startLocator));
            Point endPoint = getElementCenter(driver.findElement(destinationLocator));

            Map<String, Object> param = new HashMap<>();
            param.put("duration", 1);
            param.put("fromX", startPoint.getX());
            param.put("fromY", startPoint.getY());
            param.put("toX", endPoint.getX());
            param.put("toY", endPoint.getY());
            driver.executeScript("mobile: dragFromToForDuration", param);

            logInfoStep("Drag the Element [%s] into Element [%s]".formatted(startLocator, destinationLocator));
        } catch (Exception e) {
            logErrorStep("Failed to Drag the Element [%s] into Element [%s]".formatted(startLocator, destinationLocator), e);
        }
    }

    public void dragAndDrop(By startLocator, By destinationLocator, String direction) {
        //Scroll into screen until the element is visible into viewPort
        scrollUntilElementDisplayed(startLocator, direction);

        dragAndDrop(startLocator, destinationLocator);
    }

    @Step
    public void zoomIn(By locator, double zoomingPercentage) {
        try {
            //wait until the element is visible on page on GUI
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            //wait until the element is enabled or clickable on page
            wait.until(ExpectedConditions.elementToBeClickable(locator));

            //perform the zoom in action
            Map<String, Object> param = new HashMap<>();
            param.put("elementId", ((RemoteWebElement) driver.findElement(locator)).getId());
            param.put("scale", 1+zoomingPercentage);
            param.put("velocity", 2.2);
            driver.executeScript("mobile: pinch", param);

            logInfoStep("Zooming In into the Element [%s]".formatted(locator));
        } catch (Exception e) {
            logErrorStep("Failed to Zoom In into the Element [%s]".formatted(locator), e);
        }
    }

    public void zoomIn(By locator, double zoomIngDistance, String direction) {
        //Scroll into screen until the element is visible into viewPort
        scrollUntilElementDisplayed(locator, direction);

        zoomIn(locator, zoomIngDistance);
    }

    @Step
    public void zoomOut(By locator, double zoomingDistance) {
        try {
            //wait until the element is visible on page on GUI
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            //wait until the element is enabled or clickable on page
            wait.until(ExpectedConditions.elementToBeClickable(locator));

            //perform the zoom out action
            Map<String, Object> param = new HashMap<>();
            param.put("elementId", ((RemoteWebElement) driver.findElement(locator)).getId());
            param.put("scale", zoomingDistance);
            param.put("velocity", -2.2);
            driver.executeScript("mobile: pinch", param);

            logInfoStep("Zooming Out into the Element [%s]".formatted(locator));
        } catch (Exception e) {
            logErrorStep("Failed to Zoom Out into the Element [%s]".formatted(locator), e);
        }
    }

    public void zoomOut(By locator, double zoomIngDistance, String direction) {
        //Scroll into screen until the element is visible into viewPort
        scrollUntilElementDisplayed(locator, direction);

        zoomOut(locator, zoomIngDistance);
    }

    @Step
    public void type(By locator, String value) {
        try {
            //wait until the element is visible on page on GUI
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            //wait until the element is enabled or clickable on page
            wait.until(ExpectedConditions.elementToBeClickable(locator));

            //take the Type action inside wait with lambda function
            wait.until(d -> {
                driver.findElement(locator).clear();
                return true;
            });

            wait.until(d -> {
                driver.findElement(locator).sendKeys(value);
                return true;
            });

            logInfoStep("Typing text [%s] on Element [%s]".formatted(value, locator));
        } catch (Exception e) {
            logErrorStep("Failed to Type text [%s] on Element [%s]".formatted(value, locator), e);
        }

    }

    public void type(By locator, String value, String direction) {
        //Scroll into screen until the element is visible into viewPort
        scrollUntilElementDisplayed(locator, direction);

        type(locator, value);
    }

    @Step
    public String readTextFromElement(By locator) {

        try {
            //wait until the element is visible on page on GUI
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            //wait until the element is enabled or clickable on page
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            //take the Type action inside wait with lambda function
            String value = wait.until(d -> driver.findElement(locator).getText());

            logInfoStep("Reading text [%s] from Element [%s]".formatted(value, locator));
            return value;
        } catch (Exception e) {
            logErrorStep("Failed to Read text from Element [%s]".formatted(locator), e);
            return null;
        }
    }

    public String readTextFromElement(By locator, String direction) {
        //Scroll into screen until the element is visible into viewPort
        scrollUntilElementDisplayed(locator, direction);

        return readTextFromElement(locator);
    }

    @Step
    public boolean isElementDisplayed(By locator) {
        //take the isDisplayed action inside wait with lambda function
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            logInfoStep("The Element [%s] is Displayed".formatted(locator));
            return true;
        } catch (Exception e) {
            logErrorStep("The Element [%s] is Not Displayed".formatted(locator));
            return false;
        }
    }

    @Step
    public boolean isElementDisplayed(By locator, String direction) {
        try {
            wait.until(d -> {
                singleSwipeIntoScreen(direction);
                return driver.findElement(locator).isDisplayed();
            });

            logInfoStep("The Element [%s] is Displayed".formatted(locator));
            return true;

        } catch (TimeoutException e) {
            logErrorStep("The Element [%s] is Not Displayed".formatted(locator));
            return false;
        }
    }

    @Step
    public boolean isElementNotDisplayed(By locator, String direction) {

        try {
            wait.until(d -> {
                singleSwipeIntoScreen(direction);
                return driver.findElement(locator).isDisplayed();
            });
            logInfoStep("The Element [%s] is Not Displayed".formatted(locator));
            return false;

        } catch (TimeoutException e) {
            logErrorStep("The Element [%s] is Displayed".formatted(locator));
            return true;
        }

    }

    public void singleSwipeIntoScreen(String direction) {
        int screenHeight = driver.manage().window().getSize().getHeight();
        int screenWidth = driver.manage().window().getSize().getWidth();

        //Execute the Single Swipe Action
        Map<String, Object> param = new HashMap<>();
        param.put("velocity",500);

        switch (direction) {
            case "Down" -> param.put("direction", "up");
            case "Up" -> param.put("direction", "down");
            case "Left" -> param.put("direction", "right");
            case "Right" -> param.put("direction", "left");
            case null -> {}
            default -> logErrorStep("Direction value is not correct, it must be Up or Down or Left or Right or null", new Exception());
        }

        driver.executeScript("mobile: swipe", param);

    }

    public void singleSwipeIntoElement(String direction, By scrollableElement) {

        Map<String, Object> param = new HashMap<>();
        param.put("velocity",500);
        param.put("elementId",((RemoteWebElement)driver.findElement(scrollableElement)).getId());

        switch (direction) {
            case "Down" -> param.put("direction", "up");
            case "Up" -> param.put("direction", "down");
            case "Left" -> param.put("direction", "right");
            case "Right" -> param.put("direction", "left");
            case null -> {}
            default -> logErrorStep("Direction value is not correct, it must be Up or Down or Left or Right or null", new Exception());
        }

        driver.executeScript("mobile: swipe", param);
    }

    public void scrollUntilElementDisplayed(By targetLocator, String direction) {
        try {
            if (!driver.findElement(targetLocator).isDisplayed())
                throw new NotFoundException();

        } catch (Exception e) {
            try {
                wait.until(d -> {
                    singleSwipeIntoScreen(direction);
                    return driver.findElement(targetLocator).isDisplayed();
                });

                logInfoStep("Scrolling into Screen in direction [%s] until Element [%s] is Displayed".formatted(direction, targetLocator));
            } catch (Exception f) {
                logErrorStep("Failed to Scroll into Screen in direction [%s] until Element [%s] is Displayed".formatted(direction, targetLocator), f);
            }
        }
    }

    public void scrollUntilElementDisplayed(By targetLocator, String direction, By scrollableElement) {
        try {
            wait.until(d -> {
                singleSwipeIntoElement(direction, scrollableElement);
                return driver.findElement(targetLocator).isDisplayed();
            });

            logInfoStep("Scrolling into Scrollable Element [%s] in direction [%s] until Element [%s] is Displayed".formatted(scrollableElement, direction, targetLocator));
        } catch (Exception e) {
            logErrorStep("Failed to Scroll into Scrollable Element [%s] in direction [%s] until Element [%s] is Displayed".formatted(scrollableElement, direction, targetLocator), e);
        }

    }

    public static Point getElementCenter(WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();
        int x = (size.getWidth() / 2) + location.getX();
        int y = (size.getHeight() / 2) + location.getY();
        return new Point(x, y);
    }

}
