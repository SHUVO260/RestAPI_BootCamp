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
     * GET All Tweet Information with valid data
     */
    @Test
    public void testGetUserTimeTweetWithValidData(){
        ValidatableResponse response = this.tweetAPIClient.getUserTimeTweetWithValidData(10,"Shuvo7762133");
        int actualCode = response.extract().statusCode();
        System.out.println(actualCode);
        Assert.assertEquals(200,actualCode);
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
     * Create reTweet with invalid data
     */
    @Test(enabled = true)
    public void testCreateRetweetWithInvalidData(){
        ValidatableResponse response=this.tweetAPIClient.createReTweetWithInvalidData(1309188858433724422l);
        int actualReTweet=response.extract().statusCode();
        System.out.println(actualReTweet);
        Assert.assertEquals(404,actualReTweet);
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
     *  Un reTweet with invalid data
     */

    @Test(enabled = true)
    public void testUnReTweetInvalidId(){
        ValidatableResponse response=this.tweetAPIClient.unReTweetInvalidID(324236500424335363l);
        int actualUnRetweet=response.extract().statusCode();
        System.out.println(actualUnRetweet);
        Assert.assertEquals(404,actualUnRetweet);
    }

    /**
     * Show Tweet Id
     */
    @Test(enabled = true)
    public void testShowTweetID(){
        String tweet="hello";
        ValidatableResponse response=this.tweetAPIClient.showTweetID(1308642289288388608l);
        System.out.println(response.extract().body().asString());
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    /**
     * Show tweet with invalid data
     */
    @Test(enabled = true)
    public void testShowTweetIDWithInvalidData(){
        String tweet="Today is cloudy.";
        ValidatableResponse response=this.tweetAPIClient.showTweetIDWithInvalidData(1309196682865840000l);
        System.out.println(response.extract().body().asString());
        int actualCode = response.extract().statusCode();
        Assert.assertEquals(404, actualCode);
    }

    /**
     * Favorite Tweet
     */
    @Test(enabled = false)
    public void testFavoritesTweetID(){
        String tweet="RestAPI Automation First Testd24c071b-e274-4a09-9384-9bb4fb712a1d";
        ValidatableResponse response=this.tweetAPIClient.favoritesTweet(1308658828800557057l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    /**
     * Favorites with invalid data
     */
    @Test
    public void testCreateTweetWithWrongFavoritesEndPoint(){
        String tweet = "Check user ID042a5d91-b156-4b9d-9dfa-ca94b5638801";
        ValidatableResponse response = tweetAPIClient.favoritesTweetWithWrongFavoritesEndPoint(1308874571995664386L);
        int actualCode = response.extract().statusCode();
        Assert.assertEquals(404, actualCode);
    }


    /**
     * Wrong Favorite
     */
    @Test(enabled = false)
    public void testCreateTweetWrongFavoritesEndPoint(){
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
    public void testUnLikeFavoritesTweet(){
        String tweet="RestAPI Automation First Testd24c071b-e274-4a09-9384-9bb4fb712a1d";
        ValidatableResponse response=this.tweetAPIClient.unlikeFavoritesTweet(1308658828800557057l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    /**
     *Favorite List
     * @param
     * @return
     */
    @Test(enabled = true)
    public void testFavoriteListTweet(){
        ValidatableResponse response=this.tweetAPIClient.favoriteListTweet("Shuvo77621336");
        int actualCode=response.extract().statusCode();
        System.out.println(actualCode);
        Assert.assertEquals(200, actualCode);
    }


    /**
     * Favorite List Invalid Data
     */
    @Test(enabled = true)
    public void testFavoriteListTweetWithInvalidEndpoint(){
        ValidatableResponse response=this.tweetAPIClient.favoriteListTweetWithInvalidEndPoint("Shuvo77621336");
        int actualCode=response.extract().statusCode();
        System.out.println(actualCode);
        Assert.assertEquals(200, actualCode);
    }

    /**
     * create Status LookUp with valid data
     */
    @Test(enabled = true)
    public void testGetStatusLookUp(){
        ValidatableResponse response=this.tweetAPIClient.getStatusLookUp(20,"Shuvo77621336");
        int actualResult=response.extract().statusCode();
        System.out.println(actualResult);
        System.out.println(response.extract().body().asString());
        Assert.assertEquals(200,actualResult);
    }
    /**
     * create Status LookUp with invalid data
     */
    @Test(enabled = true)
    public void testGetStatusLookUpWithInvalidData(){
        ValidatableResponse response=this.tweetAPIClient.getStatusLookUpWithInvalidData(20,"Shuvo77621336");
        int actualResult=response.extract().statusCode();
        Assert.assertEquals(404,actualResult);
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
    public void testGetHomeTimeLineTweets() {
        ValidatableResponse response = this.tweetAPIClient.getTimeLineTweet();
        int actualCode = response.extract().statusCode();
        System.out.println(actualCode);
        Assert.assertEquals(200, actualCode);

        }
    @Test(enabled = true)
    public void testGetTimeLineTweetWithInvalidEndPoint () {
        ValidatableResponse response = this.tweetAPIClient.getTimeLineTweetWithInvalidEndPoint();
        int actualCode = response.extract().statusCode();
        Assert.assertEquals(404, actualCode);
    }
        /**
         * Create Read Tweet
         * @param
         * @return
         */

        @Test(enabled = true)
        public void testReadTweetID () {
            String expectedTweet = "Boot camp knocking";
            ValidatableResponse response = this.tweetAPIClient.readTweet(1309361604740427779l);
            // Verify that the tweet was successfully deleted
            System.out.println(response.extract().body().asString());
            response.statusCode(200);
            String actualTweet = response.extract().body().path("text");
            Assert.assertEquals(expectedTweet, actualTweet);
        }

    @Test(enabled = true)
    public void testCreateRetweet(){
        String retweet="RT @Shuvo77621336: Boot camp knocking";
        ValidatableResponse response=this.tweetAPIClient.createReTweet(1309361604740427779l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(retweet,actualTweet);
    }


}



