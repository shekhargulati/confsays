package com.shekhar.confsays.rest;

import com.mongodb.DB;
import com.shekhar.confsays.dao.ConferenceDao;
import com.shekhar.confsays.domain.Conference;
import com.shekhar.confsays.service.ArchiverClient;
import org.apache.shiro.util.StringUtils;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Path("/api/conferences")
public class ConferenceResource {

    @Inject
    private ConferenceDao conferenceDao;
    @Inject
    private ArchiverClient archiverClient;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewConference(Conference conference) {
        String title = conference.getTitle();
        String urlFragment = toUrlFragment(title);
        conference.setUrlFragment(urlFragment);
        conferenceDao.save(conference);
        conference = conferenceDao.findByTitle(urlFragment);
        archiverClient.submitNewJob(conference);
        return Response.status(Response.Status.CREATED).entity(conference).build();
    }

    private static String toUrlFragment(String title) {
        title = title.toLowerCase();
        String[] parts = title.split("\\s");
        return StringUtils.join(Arrays.asList(parts).listIterator(), "-");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Conference> findAll() {
        return conferenceDao.findAll();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Conference viewConference(@PathParam("id") String id) {
        return conferenceDao.find(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response updateConference(@PathParam("id") String id, Conference conference) {
        Conference persistedConference = conferenceDao.find(id);
        if (persistedConference == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        conferenceDao.update(id, conference);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteConference(@PathParam("id") String id) {
        Conference persistedConference = conferenceDao.find(id);
        if (persistedConference == null) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
        conferenceDao.delete(id);
        return Response.noContent().build();
    }


}
