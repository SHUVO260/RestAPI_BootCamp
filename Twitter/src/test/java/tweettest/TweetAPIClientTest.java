package tweettest;


import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.UUID;

public class TweetAPIClientTest {

    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI(){
        this.tweetAPIClient=new TweetAPIClient();
    }

    /**
     * Tweet Test
     */
    @Test(enabled = false)
    public void testUserCanTweetSuccessfully(){
        String tweet="RestAPI Automation First Test"+ UUID.randomUUID().toString();
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    /**
     * TweetTwice
     */
    @Test(enabled = false)
    public void testUserCanNotTweetTheSameTweetTwiceInARow(){
        String tweet="RestAPI Automation and Tweet Test checking";
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        response.statusCode(200);
        System.out.println(response.extract().body().asString());
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
      response= this.tweetAPIClient.createTweet(tweet);
       response.statusCode(403);
        String expectedMessage = "Status is a duplicate.";
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage, expectedMessage);
        Assert.assertNotSame("200", 403);
    }

    /**
     * Delete Tweet
     */
    @Test(enabled = false)
    public void testDelete(){
        String tweet="We are learning RestAPI Automation729c86df-b3d7-4d22-b712-a56d33a13d6d";
        ValidatableResponse response=this.tweetAPIClient.deleteTweet(1308641504542552065l);
        // Verify that the tweet was successfully deleted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    /**
     * ReTweet
     */
    @Test(enabled = false)
    public void testReTweet(){
        String tweet="Your vote has never mattered more than it does right now.\n" +
                "\n" +
                "Today is National Voter Registration Day. Go to http://iwillvote.com to register and make a plan to vote. And make sure everybody you know does, too.";
        ValidatableResponse response=this.tweetAPIClient.createRetweet(1308390673939410944l);
        // Verify that the tweet was successfully Retweeted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    /**
     * Un ReTweet
     */
    @Test(enabled = false)
    public void testUnReTweet(){
        String tweet="Your vote has never mattered more than it does right now.\n" +
                "\n" +
                "Today is National Voter Registration Day. Go to http://iwillvote.com to register and make a plan to vote. And make sure everybody you know does, too.";
        ValidatableResponse response=this.tweetAPIClient.unReTweet(1308390673939410944l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    /**
     * Show Tweet Id
     */
    @Test(enabled = false)
    public void testShowTweetID(){
        String tweet="hello";
        ValidatableResponse response=this.tweetAPIClient.showTweetID(1308642289288388608l);
        System.out.println(response.extract().body().asString());
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    /**
     * Favorite Tweet
     */
    @Test(enabled = false)
    public void FavoritesTweetID(){
        String tweet="RestAPI Automation First Testd24c071b-e274-4a09-9384-9bb4fb712a1d";
        ValidatableResponse response=this.tweetAPIClient.favoritesTweet(1308658828800557057l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    /**
     * Wrong Favorite
     */
    @Test(enabled = false)
    public void TestCreateTweetWithWrongFavoritesEndPoint(){
        String tweet="RRestAPI Automation First Testd24c071b-e274-4a09-9384-9bb4fb712a1d";
        ValidatableResponse response=this.tweetAPIClient.createTweetWithWrongFavoritesInvalidEndPoint(1308658828800557057l);
        int actualCode = response.extract().statusCode();
        // String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(404, actualCode);
    }

    /**
     * Unlike Favorite Tweet
     */
    @Test(enabled = false)
    public void unLikeFavoritesTweet(){
        String tweet="RestAPI Automation First Testd24c071b-e274-4a09-9384-9bb4fb712a1d";
        ValidatableResponse response=this.tweetAPIClient.unlikeFavoritesTweet(1308658828800557057l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    @Test(enabled = false)
    public void testFavoriteListTweet(){
        String tweet="favoriteList";
        ValidatableResponse response=this.tweetAPIClient.favoriteListTweet(10,"favoriteList");
        System.out.println(response.extract().body().asString());
        response.statusCode(200);
//        String actualTweet=response.extract().body().path("text");
//        Assert.assertEquals(tweet,actualTweet);
    }

    /**
     * GetStatus LookUp
     */
    @Test(enabled = false)
    public void testGetStatusLookUp(){
        String expectedTweet="RestAPI Automation First Testd24c071b-e274-4a09-9384-9bb4fb712a1d";
        ValidatableResponse response=this.tweetAPIClient.getStatusLookUp(1308658828800557057l);
        response.statusCode(200);
        System.out.println(response.extract().body().asString());
//        String actualTweet=response.extract().body().path("text");
//        Assert.assertEquals(expectedTweet,actualTweet);
    }

    /**
     * Re Tweet
     */
    @Test(enabled = false)
    public void testGetRetweets(){
        String tweet="We are learning RestAPI Automation021103f2-0a97-4540-b583-46fc2ded22d7";
        ValidatableResponse response=this.tweetAPIClient.getRetweets(1305375464630878208l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    /**
     * Get Time Line Tweet
     */
    @Test(enabled = true)
    public void testGetTimeLineTweet(){
        String tweet="Get Time Line";
        ValidatableResponse response= this.tweetAPIClient.getTimeLineTweet();
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }



}

