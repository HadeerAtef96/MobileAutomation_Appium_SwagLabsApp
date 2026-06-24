package utils.elementActions;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

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
                .ignoring(ElementNotInteractableException.class);
    }

    //Actions Methods
    public void tap(By locator) {
        //wait until the element is visible on page on GUI
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        //wait until the element is enabled or clickable on page
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        //Execute Tap Action
        Map<String, Object> param = new HashMap<>();
        param.put("elementId", ((RemoteWebElement) driver.findElement(locator)).getId());
        Dimension elementSize = driver.manage().window().getSize();
        param.put("x", elementSize.getWidth() / 2);
        param.put("y", elementSize.getHeight() / 2);
        driver.executeScript("mobile: tap", param);
    }

    public void tap(By locator, String direction) {
        //Scroll into screen until the element is visible into viewPort
        scrollUntilElementDisplayed(locator, direction);

        tap(locator);
    }

    public void doubleTap(By locator) {
        //wait until the element is visible on page on GUI
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        //wait until the element is enabled or clickable on page
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        //Find the element by locator
        WebElement element = driver.findElement(locator);

        //Find the Coordinates of Center of Element
        Point elementCenterCoordinates = getElementCenter(element);

        //Declare the finger object and sequence object
        PointerInput finger = new PointerInput(TOUCH, "finger 1");
        Sequence sequence = new Sequence(finger, 0);

        //List all moves taken by the finger to make the double tap action
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), viewport(), elementCenterCoordinates.getX(), elementCenterCoordinates.getY()));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(new Pause(finger, Duration.ofMillis(100)));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(sequence));
    }

    public void doubleTap(By locator, String direction) {
        //Scroll into screen until the element is visible into viewPort
        scrollUntilElementDisplayed(locator, direction);

        doubleTap(locator);
    }

    public void longTap(By locator) {
        //wait until the element is visible on page on GUI
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        //wait until the element is enabled or clickable on page
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        //Find the element by locator
        WebElement element = driver.findElement(locator);

        //Find the Coordinates of Center of Element
        Point elementCenterCoordinates = getElementCenter(element);

        //Declare the finger object and sequence object
        PointerInput finger = new PointerInput(TOUCH, "finger 1");
        Sequence sequence = new Sequence(finger, 0);

        //List all moves taken by the finger to make the long tap action
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), viewport(), elementCenterCoordinates.getX(), elementCenterCoordinates.getY()));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(new Pause(finger, Duration.ofMillis(3000)));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(List.of(sequence));
    }

    public void longTap(By locator, String direction) {
        //Scroll into screen until the element is visible into viewPort
        scrollUntilElementDisplayed(locator, direction);

        longTap(locator);
    }

    public void dragAndDrop(By startLocator, By destinationLocator) {
        //wait until the element is visible on page on GUI
        wait.until(ExpectedConditions.visibilityOfElementLocated(startLocator));
        //wait until the element is enabled or clickable on page
        wait.until(ExpectedConditions.elementToBeClickable(startLocator));

        //Find the element by locator
        WebElement startElement = driver.findElement(startLocator);
        WebElement destinationElement = driver.findElement(destinationLocator);

        //Find the Coordinates of Center of Element
        Point startElement_CenterCoordinates = getElementCenter(startElement);
        Point destinationElement_CenterCoordinates = getElementCenter(destinationElement);

        //Declare the finger object and sequence object
        PointerInput finger = new PointerInput(TOUCH, "finger 1");
        Sequence sequence = new Sequence(finger, 0);

        //List all moves taken by the finger to make the drag&drop action
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), viewport(), startElement_CenterCoordinates.getX(), startElement_CenterCoordinates.getY()));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(new Pause(finger, Duration.ofMillis(250)));
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), viewport(), destinationElement_CenterCoordinates.getX(), destinationElement_CenterCoordinates.getY()));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //Run the sequence of the finger moves
        driver.perform(List.of(sequence));

    }

    public void dragAndDrop(By startLocator, By destinationLocator, String direction) {
        //Scroll into screen until the element is visible into viewPort
        scrollUntilElementDisplayed(startLocator, direction);

        dragAndDrop(startLocator, destinationLocator);
    }

    public void zoomIn(By locator, int zoomingDistance) {
        //wait until the element is visible on page on GUI
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        //wait until the element is enabled or clickable on page
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        //Find the element by locator
        WebElement element = driver.findElement(locator);

        //Find the start point of each finger
        Point elementCenterCoordinates = getElementCenter(element);
        Point start1 = new Point(elementCenterCoordinates.getX(), elementCenterCoordinates.getY() - 50);
        Point start2 = new Point(elementCenterCoordinates.getX(), elementCenterCoordinates.getY() + 50);

        //Find the end point of each finger
        int x = start1.getX();
        int y = start1.getY() - zoomingDistance;
        Point end1 = new Point(x, y);

        int a = start2.getX();
        int b = start2.getY() + zoomingDistance;
        Point end2 = new Point(a, b);

        //Declare the finger object and sequence object for Finger 1
        PointerInput finger1 = new PointerInput(TOUCH, "finger 1");
        Sequence sequence1 = new Sequence(finger1, 0);

        //List all moves taken by the Finger 1 to make the zoom in action
        sequence1.addAction(finger1.createPointerMove(Duration.ofMillis(0), viewport(), start1.getX(), start1.getY()));
        sequence1.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence1.addAction(new Pause(finger1, Duration.ofMillis(250)));
        sequence1.addAction(finger1.createPointerMove(Duration.ofMillis(0), viewport(), end1.getX(), end1.getY()));
        sequence1.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //Declare the finger object and sequence object for Finger 2
        PointerInput finger2 = new PointerInput(TOUCH, "finger 2");
        Sequence sequence2 = new Sequence(finger1, 0);

        //List all moves taken by the Finger 2 to make the zoom in action
        sequence2.addAction(finger2.createPointerMove(Duration.ofMillis(0), viewport(), start2.getX(), start2.getY()));
        sequence2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence2.addAction(new Pause(finger2, Duration.ofMillis(250)));
        sequence2.addAction(finger2.createPointerMove(Duration.ofMillis(0), viewport(), end2.getX(), end2.getY()));
        sequence2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //Execute the 2 Sequences
        driver.perform(List.of(sequence1, sequence2));
    }

    public void zoomIn(By locator, int zoomIngDistance, String direction) {
        //Scroll into screen until the element is visible into viewPort
        scrollUntilElementDisplayed(locator, direction);

        zoomIn(locator, zoomIngDistance);
    }

    public void zoomOut(By locator, int zoomingDistance) {
        //wait until the element is visible on page on GUI
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        //wait until the element is enabled or clickable on page
        wait.until(ExpectedConditions.elementToBeClickable(locator));

        //Find the element by locator
        WebElement element = driver.findElement(locator);

        //Find the start point of each finger
        Point elementCenterCoordinates = getElementCenter(element);
        Point start1 = new Point(elementCenterCoordinates.getX(), elementCenterCoordinates.getY() - 50);
        Point start2 = new Point(elementCenterCoordinates.getX(), elementCenterCoordinates.getY() + 50);

        //Find the end point of each finger
        int x = start1.getX();
        int y = start1.getY() - zoomingDistance;
        Point end1 = new Point(x, y);

        int a = start2.getX();
        int b = start2.getY() + zoomingDistance;
        Point end2 = new Point(a, b);

        //Declare the finger object and sequence object for Finger 1
        PointerInput finger1 = new PointerInput(TOUCH, "finger 1");
        Sequence sequence1 = new Sequence(finger1, 0);

        //List all moves taken by the Finger 1 to make the zoom in action
        sequence1.addAction(finger1.createPointerMove(Duration.ofMillis(0), viewport(), end1.getX(), end1.getY()));
        sequence1.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence1.addAction(new Pause(finger1, Duration.ofMillis(250)));
        sequence1.addAction(finger1.createPointerMove(Duration.ofMillis(0), viewport(), start1.getX(), start1.getY()));
        sequence1.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //Declare the finger object and sequence object for Finger 2
        PointerInput finger2 = new PointerInput(TOUCH, "finger 2");
        Sequence sequence2 = new Sequence(finger1, 0);

        //List all moves taken by the Finger 2 to make the zoom in action
        sequence2.addAction(finger2.createPointerMove(Duration.ofMillis(0), viewport(), end2.getX(), end2.getY()));
        sequence2.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence2.addAction(new Pause(finger2, Duration.ofMillis(250)));
        sequence2.addAction(finger2.createPointerMove(Duration.ofMillis(0), viewport(), start2.getX(), start2.getY()));
        sequence2.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //Execute the Zoom Out
        driver.perform(List.of(sequence1, sequence2));
    }

    public void zoomOut(By locator, int zoomIngDistance, String direction) {
        //Scroll into screen until the element is visible into viewPort
        scrollUntilElementDisplayed(locator, direction);

        zoomOut(locator, zoomIngDistance);
    }

    public void type(By locator, String value) {
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
    }

    public void type(By locator, String value, String direction) {
        //Scroll into screen until the element is visible into viewPort
        scrollUntilElementDisplayed(locator, direction);

        type(locator, value);
    }

    public String readTextFromElement(By locator) {
        //wait until the element is visible on page on GUI
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        //wait until the element is enabled or clickable on page
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        //take the Type action inside wait with lambda function
        return wait.until(d -> driver.findElement(locator).getText());
    }

    public String readTextFromElement(By locator, String direction) {
        //Scroll into screen until the element is visible into viewPort
        scrollUntilElementDisplayed(locator, direction);

        return readTextFromElement(locator);
    }

    public boolean isElementDisplayed(By locator) {
        //take the isDisplayed action inside wait with lambda function
        try {
            return wait.until(d -> driver.findElement(locator).isDisplayed());
        } catch (TimeoutException e) {
            return false;

        }
    }

    public boolean isElementDisplayed(By locator, String direction) {
        try {
            wait.until(d -> {
                singleSwipeIntoScreen(direction);
                return driver.findElement(locator).isDisplayed();
            });

            return true;

        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isElementNotDisplayed(By locator, String direction) {

        try {
            wait.until(d -> {
                singleSwipeIntoScreen(direction);
                return driver.findElement(locator).isDisplayed();
            });

            return false;

        } catch (TimeoutException e) {
            return true;
        }

    }

    public void singleSwipeIntoScreen(String direction) {
        //Start Point in the Center of Screen
        Point startPoint;
        Dimension screenSize = driver.manage().window().getSize();
        int x = screenSize.getWidth() / 2;
        int y = screenSize.getHeight() / 2;
        startPoint = new Point(x, y);

        //End Point based on the Scrolling Direction
        Point endPoint;
        int a = 0;
        int b = 0;

        switch (direction) {
            case "Up":
                a = x;
                b = y +  screenSize.getHeight() /3;
                break;

            case "Down":
                a = x;
                b = y - screenSize.getHeight() /3;;
                break;

            case "Left":
                a = x + screenSize.getWidth() /3;
                b = y;
                break;

            case "Right":
                a = x - screenSize.getWidth() /3;
                b = y;
                break;
        }

        endPoint = new Point(a, b);

        //Declare the finger object and sequence object
        PointerInput finger = new PointerInput(TOUCH, "finger 1");
        Sequence sequence = new Sequence(finger, 0);

        //List all moves taken by the finger to make the swipe action
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), viewport(), startPoint.getX(), startPoint.getY()));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(new Pause(finger, Duration.ofMillis(250)));
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), viewport(), endPoint.getX(), endPoint.getY()));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //Run the sequence of the finger moves
        driver.perform(List.of(sequence));
    }

    public void singleSwipeIntoElement(String direction, By scrollableElement){
        //Start Point is the Center of Element
        Point startPoint;
        startPoint = getElementCenter(driver.findElement(scrollableElement));
        int x = startPoint.getX();
        int y = startPoint.getY();

        //End Point based on the Scrolling Direction
        Dimension elementSize = driver.findElement(scrollableElement).getSize();
        int elementHeight = elementSize.getHeight();
        int elementWidth = elementSize.getWidth();

        Point endPoint;
        int a = 0;
        int b = 0;

        switch (direction) {
            case "Up":
                a = x;
                b = y + elementHeight/3;
                break;

            case "Down":
                a = x;
                b = y - elementHeight/3;
                break;

            case "Left":
                a = x + elementWidth/3;
                b = y;
                break;

            case "Right":
                a = x - elementWidth/3;
                b = y;
                break;
        }

        endPoint = new Point(a,b);

        //Declare the finger object and sequence object
        PointerInput finger = new PointerInput(TOUCH, "finger 1");
        Sequence sequence = new Sequence(finger, 0);

        //List all moves taken by the finger to make the swipe action
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), viewport(), startPoint.getX(), startPoint.getY()));
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        sequence.addAction(new Pause(finger, Duration.ofMillis(250)));
        sequence.addAction(finger.createPointerMove(Duration.ofMillis(0), viewport(), endPoint.getX(), endPoint.getY()));
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        //Run the sequence of the finger moves
        driver.perform(List.of(sequence));

    }

    public void scrollUntilElementDisplayed(By targetLocator, String direction) {
        wait.until(d -> {
            singleSwipeIntoScreen(direction);
            return driver.findElement(targetLocator).isDisplayed();
        });
    }

    public void scrollUntilElementDisplayed(By targetLocator, String direction, By scrollableElement) {
        wait.until(d -> {
            singleSwipeIntoElement(direction,scrollableElement);
            return driver.findElement(targetLocator).isDisplayed();
        });
    }

    public static Point getElementCenter(WebElement element) {
        Point location = element.getLocation();
        Dimension size = element.getSize();
        int x = (size.getWidth() / 2) + location.getX();
        int y = (size.getHeight() / 2) + location.getY();
        return new Point(x, y);
    }

}
