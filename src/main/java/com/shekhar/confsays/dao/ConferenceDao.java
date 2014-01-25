package com.shekhar.confsays.dao;

import com.shekhar.confsays.domain.Conference;

import java.util.List;

public interface ConferenceDao {
    void save(Conference conference);

    List<Conference> findAll();

    Conference find(String id);

    void update(String id, Conference conference);

    void delete(String id);

    Conference findByTitle(String name);

    List<String> findSpeakers(String conferenceId);
}
