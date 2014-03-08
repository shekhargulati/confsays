package com.shekhar.confsays.resources;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.shekhar.confsays.domain.Tweet;
import com.shekhar.confsays.services.ConferenceService;
import com.shekhar.confsays.services.TweetService;

@Path("/public/conferences/{conferenceId}")
public class PublicConferenceResource {

    @Inject
    private TweetService tweetService;

    @Inject
    private ConferenceService conferenceService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/tweets")
    public List<Tweet> timeline(@PathParam("conferenceId") Long conferenceId, @QueryParam("start") int start,
            @QueryParam("max") int max) {
        max = max == 0 || max > 50 ? 50 : max;
        int end = start + max;
        return tweetService.findAll(conferenceId, start, end);
    }

    @GET
    @Path("/retweets")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Tweet> showMostRetweeted(@PathParam("conferenceId") Long conferenceId, @QueryParam("max") int max) {
        max = max == 0 || max > 50 ? 5 : max;
        List<Tweet> tweets = tweetService.findByMostRetweet(conferenceId, max);
        return tweets;
    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserCountVo> showActiveUsers(@PathParam("conferenceId") Long conferenceId, @QueryParam("max") int max) {
        max = max == 0 || max > 50 ? 5 : max;
        return tweetService.findActiveUsers(conferenceId, max);
    }

    @GET
    @Path("/mentions/speakers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserCountVo> topSpeakers(@PathParam("conferenceId") Long conferenceId, @QueryParam("max") int max) {
        max = max == 0 || max > 10 ? 5 : max;
        Set<String> speakers = conferenceService.findSpeakers(conferenceId);
        return tweetService.findUserMentions(conferenceId, speakers);
    }

    @GET
    @Path("/mentions/users")
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserCountVo> showUserMentions(@PathParam("conferenceId") Long conferenceId, @QueryParam("max") int max) {
        max = max == 0 || max > 10 ? 10 : max;
        Set<String> speakers = conferenceService.findSpeakers(conferenceId);
        return tweetService.findByMostMentioned(conferenceId, max, speakers);
    }

}
