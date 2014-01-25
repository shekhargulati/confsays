package com.shekhar.confsays.dao;


import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.shekhar.confsays.domain.Conference;
import com.shekhar.confsays.domain.Tweet;
import com.shekhar.confsays.domain.UserCountVo;
import org.bson.types.ObjectId;

import java.util.*;

public abstract class Converters {

    public static List<Tweet> toTweets(DBCursor dbCursor) {
        List<Tweet> tweets = new ArrayList<Tweet>();
        while (dbCursor.hasNext()) {
            BasicDBObject dbObject = (BasicDBObject) dbCursor.next();
            String text = dbObject.getString("text");
            String tweetedBy = dbObject.getString("tweetedBy");
            long tweetId = dbObject.getLong("tweetId");
            ObjectId objectId = dbObject.getObjectId("_id");
            String id = objectId.toString();
            Tweet tweet = new Tweet(id, tweetId, text, tweetedBy);
            long retweetId = dbObject.getLong("retweetId", 0L);
            long retweetCount = dbObject.getLong("retweetCount", 0L);
            tweet.setRetweetCount(retweetCount);
            tweet.setRetweetId(retweetId);
            tweets.add(tweet);
        }
        return tweets;
    }

    public static List<UserCountVo> toUsers(Iterable<DBObject> results) {
        List<UserCountVo> users = new ArrayList<UserCountVo>();
        for (DBObject dbObject : results) {
            BasicDBObject basicDBObject = (BasicDBObject) dbObject;
            String twitterHandle = basicDBObject.getString("_id");
            long count = basicDBObject.getLong("count", 0);
            users.add(new UserCountVo(count, twitterHandle));
        }
        return users;
    }

    public static List<Conference> toConferences(DBCursor dbCursor) {
        List<Conference> conferences = new ArrayList<Conference>();
        while (dbCursor.hasNext()) {
            conferences.add(toConference(dbCursor.next()));
        }
        return conferences;
    }

    public static Conference toConference(DBObject dbObject) {
        BasicDBObject basicDBObject = (BasicDBObject) dbObject;
        String id = basicDBObject.getObjectId("_id").toString();
        String title = basicDBObject.getString("title");
        String description = basicDBObject.getString("description");
        String bannerImageUrl = basicDBObject.getString("bannerImageUrl");
        String conferenceUrl = basicDBObject.getString("conferenceUrl");
        String agendaUrl = basicDBObject.getString("agendaUrl");
        boolean track = basicDBObject.getBoolean("track");
        Date startDate = basicDBObject.getDate("startDate");
        Date endDate = basicDBObject.getDate("endDate");
        String urlFragment = basicDBObject.getString("urlFragment");
        Conference conference = new Conference(title, description);
        conference.setId(id);
        conference.setAgendaUrl(agendaUrl);
        conference.setBannerImgUrl(bannerImageUrl);
        conference.setConferenceUrl(conferenceUrl);
        conference.setEndDate(endDate);
        conference.setStartDate(startDate);
        conference.setTrack(track);
        conference.setUrlFragment(urlFragment);
        BasicDBList hashtagsList = (BasicDBList) basicDBObject.get("hashtags");
        if (hashtagsList != null) {
            List<String> hashtags = new ArrayList<String>();
            Iterator<Object> iterator = hashtagsList.iterator();
            while (iterator.hasNext()) {
                String hashTag = (String) iterator.next();
                hashtags.add(hashTag);
            }
            conference.setHashtags(hashtags);
        }
        return conference;
    }

    public static BasicDBObject toDbObject(Conference conference) {
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.append("title", conference.getTitle());
        basicDBObject.append("description", conference.getDescription());
        basicDBObject.append("bannerImageUrl", conference.getBannerImgUrl());
        basicDBObject.append("hashtags", conference.getHashtags());
        basicDBObject.append("startDate", conference.getStartDate());
        basicDBObject.append("endDate", conference.getEndDate());
        basicDBObject.append("conferenceUrl", conference.getConferenceUrl());
        basicDBObject.append("agendaUrl", conference.getAgendaUrl());
        basicDBObject.append("track", conference.isTrack());
        basicDBObject.append("urlFragment", conference.getUrlFragment());
        basicDBObject.append("speakers", conference.getSpeakers());
        return basicDBObject;
    }
}


