package com.shekhar.confsays.view;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shekhar.confsays.domain.Conference;
import com.shekhar.confsays.services.ConferenceService;

@WebServlet("/conference")
public class ConferenceView extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private ConferenceService conferenceService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long conferenceId = Long.valueOf(request.getParameter("id"));
        Conference conference = conferenceService.read(conferenceId);
        request.setAttribute("conference", conference);
        request.setAttribute("conferenceId", conferenceId);
        request.getRequestDispatcher("conference.jsp").forward(request, response);
    }

}
