package com.shekhar.confsays.resources;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.shekhar.confsays.domain.Conference;
import com.shekhar.confsays.services.ConferenceService;
import com.shekhar.confsays.services.GooseExtractorClient;
import com.shekhar.confsays.services.Job;

@Path("/conferences")
public class ConferenceResource {

    @Inject
    private ConferenceService conferenceService;

    @Inject
    private GooseExtractorClient gooseExtractorClient;

    @Resource
    private ManagedExecutorService mes;

    @Inject
    private Event<Job> event;

    @Inject
    private Logger logger;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createNewConference(@Suspended final AsyncResponse asyncResponse, @Valid final Conference conference) {
        mes.submit(new Runnable() {
            @Override
            public void run() {
                Map<String, String> map = gooseExtractorClient.fetchImageAndDescription(conference.getConferenceUrl());
                if (conference.getBannerImgUrl() == null) {
                    conference.setBannerImgUrl(map.get("bannerImgUrl"));
                }
                conference.setDescription(map.get("description"));
                logger.info("Saving conference .. " + conference);
                Conference persitedConference = conferenceService.save(conference);
                logger.info("Saved conference ..");
                if(conference.isTrack()){
                    logger.info("firing off Job event...");
                    event.fire(new Job(persitedConference.getId(), persitedConference.getName(), persitedConference.getHashtags()));
                }
                asyncResponse.resume(Response.status(Status.CREATED).entity(persitedConference).build());
            }
        });

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Conference get(@NotNull @PathParam("id") Long id) {
        return conferenceService.read(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Conference> list(@QueryParam("start") int start, @QueryParam("max") int max) {
        max = max == 0 || max > 10 ? 10 : max;
        return conferenceService.findAll(start, max);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateConference(@PathParam("id") Long id, @Valid Conference conference) {
        Conference updatedConference = conferenceService.update(id, conference);
        return Response.status(Status.OK).entity(updatedConference).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteConference(@PathParam("id") Long id) {
        conferenceService.delete(id);
        return Response.noContent().build();

    }
}
