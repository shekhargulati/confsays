package com.shekhar.confsays.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import twitter4j.Status;

import com.shekhar.confsays.domain.Tweet;
import com.shekhar.confsays.resources.UserCountVo;

@Stateless
@Transactional
public class TweetService {

    @Inject
    private EntityManager entityManager;

    @Asynchronous
    public void updateQueryWithRetweetCount(Status retweetedStatus) {
        long retweetedStatusId = retweetedStatus.getId();
        long retweetCount = retweetedStatus.getRetweetCount();

        Query updateTweetQuery = entityManager
                .createQuery("UPDATE Tweet t SET t.retweetCount =:retweetCount WHERE t.tweetId =:tweetId");

        updateTweetQuery.setParameter("retweetCount", retweetCount);
        updateTweetQuery.setParameter("tweetId", retweetedStatusId);
        updateTweetQuery.executeUpdate();
    }

    @Asynchronous
    public void save(Tweet tweet) {
        entityManager.persist(tweet);
    }

    public List findOne(long retweetedStatusId) {
        Query query = entityManager.createQuery("SELECT t.id from Tweet t where t.tweetId =:tweetId");
        query.setParameter("tweetId", retweetedStatusId);
        return query.getResultList();
    }

    public List<Tweet> findAll(Long conferenceId, int start, int end) {
        TypedQuery<Tweet> query = entityManager.createQuery(
                "select t from Tweet t where t.conferenceId =:conferenceId ORDER BY t.createdAt DESC", Tweet.class);
        query.setParameter("conferenceId", conferenceId);
        query.setFirstResult(start);
        query.setMaxResults(end);
        return query.getResultList();
    }

    public List<Tweet> findByMostRetweet(Long conferenceId, int max) {
        TypedQuery<Tweet> query = entityManager.createQuery(
                "SELECT t FROM Tweet t WHERE t.conferenceId =:conferenceId ORDER BY t.retweetCount DESC", Tweet.class);
        query.setParameter("conferenceId", conferenceId);
        query.setMaxResults(max);
        return query.getResultList();
    }

    public List<UserCountVo> findActiveUsers(Long conferenceId, int max) {
        TypedQuery<UserCountVo> query = entityManager
                .createQuery(
                        "SELECT NEW com.shekhar.confsays.resources.UserCountVo(t.tweetedBy, COUNT(t)) FROM Tweet t WHERE t.conferenceId =:conferenceId GROUP BY t.tweetedBy ORDER BY COUNT(t) DESC",
                        UserCountVo.class);
        query.setParameter("conferenceId", conferenceId);
        query.setMaxResults(max);
        return query.getResultList();
    }

    public List<UserCountVo> findByMostMentioned(Long conferenceId, int max, Set<String> speakers) {
        Query query = entityManager
                .createQuery("select m, count(1) from Tweet t inner join t.mentions m WHERE t.conferenceId =:conferenceId group by m ORDER BY COUNT(1) DESC");
        query.setParameter("conferenceId", conferenceId);
        query.setMaxResults(max);
        Iterator itr = query.getResultList().iterator();
        List<UserCountVo> userCountVos = new ArrayList<>();
        while (itr.hasNext()) {
            Object[] mentionCount = (Object[]) itr.next();
            String mention = (String) mentionCount[0];
            Long count = (Long) mentionCount[1];
            if(!speakers.contains(mention)){
                userCountVos.add(new UserCountVo(mention, count));
            }
        }
        return userCountVos;
    }

    public List<UserCountVo> findUserMentions(Long conferenceId, Set<String> speakers) {
        Query query = entityManager
                .createQuery("select m, count(1) from Tweet t inner join t.mentions m WHERE t.conferenceId =:conferenceId group by m ORDER BY COUNT(1) DESC");
        query.setParameter("conferenceId", conferenceId);
        Iterator itr = query.getResultList().iterator();
        List<UserCountVo> userCountVos = new ArrayList<>();
        while (itr.hasNext()) {
            Object[] mentionCount = (Object[]) itr.next();
            String mention = (String) mentionCount[0];
            Long count = (Long) mentionCount[1];
            if(speakers.contains(mention)){
                userCountVos.add(new UserCountVo(mention, count));
            }
        }
        return userCountVos;
    }
}
