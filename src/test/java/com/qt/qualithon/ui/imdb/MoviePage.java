package com.qt.qualithon.ui.imdb;

import com.qt.qualithon.TestSession;
import com.qt.qualithon.ui.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;
import java.util.ArrayList;

/**
 * page object class represents elements and actions on the IMDb Movie Page
 **/
public class MoviePage extends Page{

    public MoviePage(TestSession testSession){
        super(testSession);

        // adjust page for tablet formfactor
        WebElement showMoreLink = this.testSession.driverWait().until(
            ExpectedConditions.presenceOfElementLocated(
              By.cssSelector("a[data-testid='title-pc-expand-more-button']")));
       
        if(showMoreLink.isDisplayed()){
            showMoreLink.click();
        }

    }

    /**
     * get movie title
     *
     * @return    movie title
     **/
    public String title(){
        return this.testSession.driverWait().until(
            ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h1[data-testid='hero-title-block__title']")
            ) 
        ).getText();
    }

    /**
     * get movie director name
     *
     * @return    movie director name
     **/
    public String director(){
        List<WebElement> credits = this.testSession.driverWait().until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
              By.cssSelector("li.ipc-metadata-list__item")));

        // traverse credits sections to find the section with Directors
        for(WebElement credit:credits){
            try{
                if(credit.findElement(By.cssSelector("span")).getText().equalsIgnoreCase("Director")){
                    // find director name from child element of section
                    return credit.findElement(By.cssSelector("a")).getText();
                }
            }catch(NoSuchElementException e){}
        }
        throw new NoSuchElementException("Failed to lookup Director on page");
    }

    /**
     * get list of movie genres
     *
     * @return    list of movie genres
     **/
    public List<String> genres(){
        List<String> genres = new ArrayList<>();
        List<WebElement> credits = this.testSession.driverWait().until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
              By.cssSelector("li.ipc-metadata-list__item")));

        // traverse credits sections to find the section with Writers
        for(WebElement credit:credits){
            try{
                if(credit.findElement(By.cssSelector("span")).getText().equalsIgnoreCase("Genres")){
                    // traverse list of writers on page to add to writers list
                    List<WebElement> genresElements = credit.findElements(By.cssSelector("a"));
                    for(int i = 0; i < genresElements.size(); i++){
                        genres.add(genresElements.get(i).getText());
                    }
                    break;
                }
            }catch(NoSuchElementException e){}
        }
        // if genres list is empty throw exception
        if(genres.isEmpty()){
            throw new NoSuchElementException("Could not lookup genres on Movie page");
        }
        return genres;
    }
    
    /**
     * get movie release year
     *
     * @return    movie release year
     **/
    public String releaseYear(){
        return this.testSession.driverWait().until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[@id='__next']/main/div/section[1]/section/div[3]/section/section/div[2]/div[1]/div/ul/li[1]/span"))).getText();
                // By.cssSelector("#__next > main > div > section.ipc-page-background.ipc-page-background--base.sc-c7f03a63-0.kUbSjY > section > div:nth-child(4) > section > section > div.sc-94726ce4-0.cMYixt > div.sc-94726ce4-2.khmuXj > div > ul > li:nth-child(1) > span"))).getText();
    }

    /**
     * get list of movie writers
     *
     * @return    list of movie writer names
     **/
    public List<String> writers(){
        List<String> writers = new ArrayList<>();
        List<WebElement> credits = this.testSession.driverWait().until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
              By.cssSelector("li.ipc-metadata-list__item")));

        // traverse credits sections to find the section with Writers
        for(WebElement credit:credits){
            try{
                if(credit.findElement(By.cssSelector("span")).getText().equalsIgnoreCase("Writers")){
                    // traverse list of writers on page to add to writers list
                    List<WebElement> writersElements = credit.findElements(By.cssSelector("a"));
                    for(int i = 0; i < writersElements.size(); i++){
                        writers.add(writersElements.get(i).getText());
                    }
                    break;
                }
            }catch(NoSuchElementException e){}
        }

        // if writers list is empty throw exception
        if(writers.isEmpty()){
            throw new NoSuchElementException("Could not lookup Writers on movie page");
        }
        return writers;
    }

}
