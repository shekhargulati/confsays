package com.shekhar.confsays.dao;

import com.shekhar.confsays.domain.Tweet;
import com.shekhar.confsays.domain.UserCountVo;

import java.util.List;

public interface TweetDao {

    List<Tweet> findAll(String jobId, int start, int end);

    long count(String jobId);

    List<Tweet> findByMostRetweet(String jobId, int max);

    List<UserCountVo> findActiveUsers(String jobId, int max);

    List<UserCountVo> findUserMentions(String jobId, List<String> users);

    List<UserCountVo> findByMostMentioned(String jobId, int max);
}
