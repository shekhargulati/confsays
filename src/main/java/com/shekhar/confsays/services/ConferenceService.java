package com.shekhar.confsays.services;

import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import com.shekhar.confsays.domain.Conference;
import com.shekhar.confsays.exceptions.ConferenceNotFoundException;

@ApplicationScoped
@Transactional
public class ConferenceService {

    @Inject
    private EntityManager entityManager;

    public Conference save(Conference conference) {
        entityManager.persist(conference);
        return conference;
    }

    public Conference read(@NotNull Long id) {
        return entityManager.find(Conference.class, id);
    }

    public List<Conference> findAll(int start, int max) {
        TypedQuery<Conference> query = entityManager.createQuery(
                "SELECT c from Conference c ORDER BY c.createdOn DESC", Conference.class);
        query.setFirstResult(start);
        query.setMaxResults(max);
        return query.getResultList();
    }

    public Conference update(Long id, Conference conference) {
        Conference existingConference = this.read(id);
        if (existingConference == null) {
            throw new ConferenceNotFoundException("No conference found for id: " + id);
        }
        existingConference = copy(conference, existingConference);
        entityManager.persist(existingConference);
        return conference;
    }

    private Conference copy(Conference conference, Conference existingConference) {
        existingConference.setName(conference.getName());
        existingConference.setConferenceUrl(conference.getConferenceUrl());
        existingConference.setBannerImgUrl(conference.getBannerImgUrl());
        existingConference.setDescription(conference.getDescription());
        existingConference.setEndDate(conference.getEndDate());
        existingConference.setHashtags(conference.getHashtags());
        existingConference.setSpeakers(conference.getSpeakers());
        existingConference.setStartDate(conference.getStartDate());
        existingConference.setTrack(conference.isTrack());
        return existingConference;
    }

    public void delete(Long id) {
        Conference conference = this.read(id);
        if (conference == null) {
            throw new ConferenceNotFoundException("No conference found for id: " + id);
        }
        entityManager.remove(conference);
    }

    public List<Conference> findAllTrackableConferences() {
        TypedQuery<Conference> query = entityManager.createQuery("SELECT c from Conference c WHERE c.track =:track",
                Conference.class);
        query.setParameter("track", true);
        return query.getResultList();
    }

    public Set<String> findSpeakers(Long conferenceId) {
        return entityManager.find(Conference.class, conferenceId).getSpeakers();
    }
}
