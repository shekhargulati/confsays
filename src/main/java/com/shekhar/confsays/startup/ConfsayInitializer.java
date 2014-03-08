package com.shekhar.confsays.startup;

import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.shekhar.confsays.domain.Conference;
import com.shekhar.confsays.services.ConferenceService;
import com.shekhar.confsays.services.Job;

@Startup
@Singleton
public class ConfsayInitializer {

    @Inject
    private ConferenceService conferenceService;
    @Inject
    private Event<Job> event;
    
    @Inject
    private Logger logger;

    @PostConstruct
    public void onStartup() {
        logger.info("Startup ......");
        List<Conference> conferences = conferenceService.findAllTrackableConferences();
        for (Conference conference : conferences) {
            event.fire(new Job(conference.getId(), conference.getName(), conference.getHashtags()));
        }
    }
}
