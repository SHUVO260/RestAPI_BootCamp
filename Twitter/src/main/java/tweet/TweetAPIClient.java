package tweet;


import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class TweetAPIClient extends RestAPI {

    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    private final String DELETE_TWEET_ENDPOINT = "/statuses/destroy.json";
    private final String GET_USER_TWEET_ENDPOINT = "/statuses/user_timeline.json";

    //ReTweet
    private final String RETWEET_USER_TWEET_ENDPOINT="/statuses/retweet.json";

    private final String UNRETWEET_POST_USER_ENDPOINT = "/statuses/unretweet.json";

    private final String SHOW_GET_USER_ENDPOINT = "/statuses/show.json?id=13760868246";

    private final String FAVORITES_POST_USER_ENDPOINT = "/favorites/create.json";

    private final String FAVORITES_DESTROY_POST_USER_ENDPOINT = "/favorites/destroy.json";

    private final String FAVORITES_LIST_USER_ENDPOINT="/favorites/list.json";

    private final String STATUS_LOOKUP_GET_USER_ENDPOINT = "/statuses/lookup.json";

    private final String GET_RETWEETS_USER_ENDPOINT = "/statuses/retweets.json";

    private final String GET_HOME_TIMELINE_USER_ENDPOINT="/statuses/home_timeline.json";


    /**
     * UserTime Twwet
     * @return
     */
    public ValidatableResponse getUserTimeTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT)
                .then();
    }

    /**
     * Create Tweet
     * @param tweet
     * @return
     */

    public ValidatableResponse createTweet(String tweet)  {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .when().post(this.baseUrl+this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    /**
     * Delete Tweet
     * @param tweetId
     * @return
     */
    public ValidatableResponse deleteTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.DELETE_TWEET_ENDPOINT)
                .then();
    }

    /**
     * Create ReTweet
     * @param tweetId
     * @return
     */
    public ValidatableResponse createRetweet(Long tweetId){
        return given().auth().oauth(this.apiKey,this.apiSecretKey,this.accessToken,this.accessTokenSecret)
                .param("id", tweetId)
                .when().post(this.baseUrl+this.RETWEET_USER_TWEET_ENDPOINT)
                .then();
    }

    /**
     * UnReTweet
     * @param tweetId
     * @return
     */
    public ValidatableResponse unReTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.UNRETWEET_POST_USER_ENDPOINT)
                .then();
    }
    /**
     * Show TweetId
     * @param tweetId
     * @return
     */

    public ValidatableResponse showTweetID(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().get(this.baseUrl + this.SHOW_GET_USER_ENDPOINT)
                .then();
    }

    /**
     * Favourite Tweet
     * @param tweetId
     * @return
     */

    public ValidatableResponse favoritesTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().post(this.baseUrl + this.FAVORITES_POST_USER_ENDPOINT)
                .then();
    }
    /**
     * Negative Tweet
     * @param tweetId
     * @return
     */

    public ValidatableResponse createTweetWithWrongFavoritesInvalidEndPoint(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().post(this.baseUrl + this.FAVORITES_POST_USER_ENDPOINT + "1234")
                .then();
    }
    /**
     * Unlike favourite Tweet
     * @param tweetId
     * @return
     */
    public ValidatableResponse unlikeFavoritesTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.FAVORITES_DESTROY_POST_USER_ENDPOINT)
                .then();
    }

    /**
     *Favorite List
     * @param count
     * @return
     */
    public ValidatableResponse favoriteListTweet(int count, String favoriteList) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", count)
                .params("screen_name", favoriteList)
                .when().get(this.baseUrl + this.FAVORITES_LIST_USER_ENDPOINT)
                .then();
    }

    /**
     * create Status LokUp
     * @param tweetId
     * @return
     */
    public ValidatableResponse getStatusLookUp(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.STATUS_LOOKUP_GET_USER_ENDPOINT)
                .then();
    }

    /**
     * Get Status Retweets
     * @param tweetId
     * @return
     */
    public ValidatableResponse getRetweets(long tweetId){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.GET_RETWEETS_USER_ENDPOINT )
                .then();
    }

    /**
     * Get TimeLine
     * @return
     */
    public ValidatableResponse getTimeLineTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_HOME_TIMELINE_USER_ENDPOINT)
                .then();
    }





}
