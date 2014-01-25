package com.shekhar.confsays.rest;

import com.shekhar.confsays.dao.ConferenceDao;
import com.shekhar.confsays.domain.Conference;
import com.shekhar.confsays.domain.User;
import com.shekhar.confsays.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@Path("/conferences")
public class ConferenceViewResource {

    @Inject
    private UserService userService;

    @Inject
    private ConferenceDao conferenceDao;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public void viewAllConferences(@Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        List<Conference> conferences = conferenceDao.findAll();
        request.setAttribute("conferences", conferences);
        request.getRequestDispatcher("../listConferences.jsp").forward(request, response);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/new")
    public void showConferenceForm(@Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        Subject subject = SecurityUtils.getSubject();
        String email = subject.getPrincipal().toString();
        User loggedInUser = userService.findByEmail(email);
        request.setAttribute("fullname", loggedInUser.getFirstname() + " " + loggedInUser.getLastname());
        request.getRequestDispatcher("../../conference.jsp").forward(request, response);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/{conference}")
    public void viewConference(@PathParam("conference") String name, @Context HttpServletRequest request, @Context HttpServletResponse response) throws ServletException, IOException {
        Conference conference = conferenceDao.findByTitle(name);
        request.setAttribute("conference", conference);
        request.setAttribute("conferenceId", conference.getId());
        request.getRequestDispatcher("../../viewConference.jsp").forward(request, response);
    }

}
