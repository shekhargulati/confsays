package com.shekhar.confsays.dao;

import com.mongodb.*;
import com.shekhar.confsays.domain.Tweet;
import com.shekhar.confsays.domain.UserCountVo;

import javax.inject.Inject;
import java.util.List;

import static com.shekhar.confsays.dao.Converters.toTweets;
import static com.shekhar.confsays.dao.Converters.toUsers;

public class TweetDaoImpl implements TweetDao {

    private DBCollection dbCollection;

    @Inject
    public TweetDaoImpl(DB db) {
        this.dbCollection = db.getCollection("tweets");
    }

    @Override
    public List<Tweet> findAll(String jobId, int start, int end) {
        BasicDBObject query = new BasicDBObject();
        query.append("jobId", jobId);
        BasicDBObject fields = new BasicDBObject("tweetId", 1).append("text", 1).append("tweetedBy", 1);

        DBCursor dbCursor = dbCollection.find(query, fields).sort(new BasicDBObject("createdAt", -1)).skip(start).limit(end);
        return toTweets(dbCursor);
    }

    @Override
    public long count(String jobId) {
        return dbCollection.count(new BasicDBObject("jobId", jobId));
    }

    @Override
    public List<Tweet> findByMostRetweet(String jobId, int max) {
        BasicDBObject query = new BasicDBObject();
        query.append("jobId", jobId);
        BasicDBObject fields = new BasicDBObject("tweetId", 1).append("text", 1).append("tweetedBy", 1).append("retweetCount", 1).append("retweetId", 1);

        DBCursor dbCursor = dbCollection.find(query, fields).sort(new BasicDBObject("retweetCount", -1)).limit(max);
        return toTweets(dbCursor);
    }

    @Override
    public List<UserCountVo> findActiveUsers(String jobId, int max) {
        //db.tweets.aggregate({$group:{_id:"$tweetedBy",count:{$sum :1}}},{$sort:{count:-1}},{$limit:5})

        BasicDBObject match = new BasicDBObject();
        match.append("$match", new BasicDBObject("jobId", jobId));

        BasicDBObject group = new BasicDBObject();
        group.append("$group", new BasicDBObject("_id", "$tweetedBy").append("count", new BasicDBObject("$sum", 1)));

        BasicDBObject sort = new BasicDBObject();
        sort.append("$sort", new BasicDBObject("count", -1));

        BasicDBObject limit = new BasicDBObject();
        limit.append("$limit", max);

        AggregationOutput aggregationOutput = dbCollection.aggregate(match, group, sort, limit);
        Iterable<DBObject> results = aggregationOutput.results();
        return toUsers(results);
    }

    @Override
    public List<UserCountVo> findUserMentions(String jobId, List<String> users) {
        //db.tweets.aggregate({$unwind:"$mentions"},{$match:{mentions:{$in:["ArvindKejriwal","timesnow"]}}},{$group:{_id : "$mentions",count : {$sum:1}}},{$sort:{count:-1}},{$limit:5})

        BasicDBObject unwind = new BasicDBObject();
        unwind.append("$unwind", "$mentions");

        BasicDBObject match = new BasicDBObject();
        match.append("$match", new BasicDBObject("mentions", new BasicDBObject("$in", users)).append("jobId", jobId));

        BasicDBObject group = new BasicDBObject();
        group.append("$group", new BasicDBObject("_id", "$mentions").append("count", new BasicDBObject("$sum", 1)));

        BasicDBObject sort = new BasicDBObject();
        sort.append("$sort", new BasicDBObject("count", -1));

        AggregationOutput aggregateOutput = dbCollection.aggregate(unwind, match, group, sort);
        Iterable<DBObject> results = aggregateOutput.results();
        return toUsers(results);

    }

    @Override
    public List<UserCountVo> findByMostMentioned(String jobId, int max) {
        //db.tweets.aggregate({$unwind:"$mentions"},{$group:{_id : "$mentions",count : {$sum:1}}},{$sort:{count:-1}},{$limit:5})
        BasicDBObject unwind = new BasicDBObject();
        unwind.append("$unwind", "$mentions");


        BasicDBObject match = new BasicDBObject();
        match.append("$match", new BasicDBObject().append("jobId", jobId));


        BasicDBObject group = new BasicDBObject();
        group.append("$group", new BasicDBObject("_id", "$mentions").append("count", new BasicDBObject("$sum", 1)));

        BasicDBObject sort = new BasicDBObject();
        sort.append("$sort", new BasicDBObject("count", -1));

        BasicDBObject limit = new BasicDBObject();
        limit.append("$limit", max);

        AggregationOutput aggregationOutput = dbCollection.aggregate(unwind, match, group, sort, limit);
        Iterable<DBObject> results = aggregationOutput.results();

        return toUsers(results);

    }
}
