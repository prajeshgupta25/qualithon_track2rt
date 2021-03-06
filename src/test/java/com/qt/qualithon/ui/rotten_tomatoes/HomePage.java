package com.qt.qualithon.ui.rotten_tomatoes;

import com.qt.qualithon.TestSession;
import com.qt.qualithon.ui.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
// import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
// import org.openqa.selenium.JavascriptExecutor;

// import java.util.List;

/**
 * represents Rotten Tomatoes Web Home Page elements and ui actions (page object)
 **/
public class HomePage extends Page {

    public HomePage(TestSession testSession){
        super(testSession);
    }

    /**
     * perform a search for movie title and return the resultlist page
     *
     * @param     movieTitle    movie name
     * @return    Rotten Tomatoes Results Page page object
     **/
    public ResultsPage search(String movieTitle){
        WebElement searchInput = this.testSession.driverWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input.search-text")
            )
        );
        searchInput.sendKeys(movieTitle);
        searchInput.sendKeys(Keys.ENTER);
        // searchInput.submit();

        return new ResultsPage(this.testSession);
    }
}
