package com.shekhar.confsays.rest;

import com.shekhar.confsays.dao.ConferenceDao;
import com.shekhar.confsays.domain.CountResult;
import com.shekhar.confsays.domain.Tweet;
import com.shekhar.confsays.dao.TweetDao;
import com.shekhar.confsays.domain.UserCountVo;
import com.shekhar.confsays.service.ArchiverClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/conferences/{conferenceId}")
public class TweetResource {

    @Inject
    TweetDao tweetDao;

    @Inject
    ConferenceDao conferenceDao;

    @Inject
    ArchiverClient archiverClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/tweets")
    public List<Tweet> timeline(@PathParam("conferenceId") String conferenceId, @QueryParam("start") int start, @QueryParam("max") int max) {
        max = max == 0 || max > 50 ? 50 : max;
        int end = start + max;
        return tweetDao.findAll(conferenceId, start, end);
    }

    @GET
    @Path("/tweets/count")
    @Produces(MediaType.APPLICATION_JSON)
    public CountResult count(@PathParam("conferenceId") String conferenceId) {
        long count = tweetDao.count(conferenceId);
        return new CountResult(count);
    }


    @GET
    @Path("/retweets")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tweet> showMostRetweeted(@PathParam("conferenceId") String conferenceId, @QueryParam("max") int max) {
        max = max == 0 || max > 50 ? 5 : max;
        List<Tweet> tweets = tweetDao.findByMostRetweet(conferenceId, max);
        return tweets;
    }


    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserCountVo> showActiveUsers(@PathParam("conferenceId") String conferenceId, @QueryParam("max") int max) {
        max = max == 0 || max > 50 ? 5 : max;
        return tweetDao.findActiveUsers(conferenceId, max);
    }

    @GET
    @Path("/mentions/speakers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserCountVo> topSpeakers(@PathParam("conferenceId") String conferenceId, @QueryParam("max") int max) {
        max = max == 0 || max > 10 ? 5 : max;
        List<String> speakers = conferenceDao.findSpeakers(conferenceId);
        return tweetDao.findUserMentions(conferenceId, speakers);
    }


    @GET
    @Path("/mentions/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserCountVo> showUserMentions(@PathParam("conferenceId") String conferenceId, @QueryParam("max") int max) {
        max = max == 0 || max > 10 ? 10 : max;
        return tweetDao.findByMostMentioned(conferenceId, max);
    }

    @Path("/stop")
    @DELETE
    public Response stopTracking(@PathParam("conferenceId") String conferenceId) {
        archiverClient.stopJob(conferenceId);
        return Response.noContent().build();

    }


}
