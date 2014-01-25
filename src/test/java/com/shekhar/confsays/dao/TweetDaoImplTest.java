package com.shekhar.confsays.dao;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.shekhar.confsays.domain.Tweet;
import com.shekhar.confsays.domain.UserCountVo;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class TweetDaoImplTest {

    private static DB db;
    private TweetDao tweetDao;

    private String conferenceId = "52e2e591ef86a6a36ca1565e";

    @BeforeClass
    public static void beforeClass() throws Exception {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDB("testdb");
    }

    @Before
    public void setup() {
        tweetDao = new TweetDaoImpl(db);
    }

    @Test
    public void testFindAll() throws Exception {
        List<Tweet> tweets = tweetDao.findAll(conferenceId, 0, 25);
        assertEquals(25, tweets.size());
    }

    @Test
    public void testCount() throws Exception {
        long count = tweetDao.count(conferenceId);
        assertEquals(618, count);
    }

    @Test
    public void testFindByMostRetweet() throws Exception {
        List<Tweet> tweets = tweetDao.findByMostRetweet(conferenceId, 2);
        assertEquals(2, tweets.size());
        assertEquals(429, tweets.get(0).getRetweetCount());
        assertEquals(423, tweets.get(1).getRetweetCount());
    }

    @Test
    public void testFindActiveUsers() throws Exception {
        List<UserCountVo> activeUsers = tweetDao.findActiveUsers(conferenceId, 2);
        assertEquals(2, activeUsers.size());
        assertEquals(13, activeUsers.get(0).getCount());
        assertEquals(6, activeUsers.get(1).getCount());

    }

    @Test
    public void testFindUserMentions() throws Exception {
        List<UserCountVo> userMentions = tweetDao.findUserMentions(conferenceId, Arrays.asList("ArvindKejriwal", "timesnow"));
        assertEquals(2, userMentions.size());
        assertEquals(24, userMentions.get(0).getCount());
        assertEquals(13, userMentions.get(1).getCount());
    }

    @Test
    public void testFindByMostMentioned() throws Exception {
        List<UserCountVo> userMentions = tweetDao.findByMostMentioned(conferenceId, 2);
        assertEquals(2, userMentions.size());
        assertEquals(24, userMentions.get(0).getCount());
        assertEquals(22, userMentions.get(1).getCount());
    }
}
