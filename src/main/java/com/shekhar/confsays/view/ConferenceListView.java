package com.shekhar.confsays.view;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shekhar.confsays.domain.Conference;
import com.shekhar.confsays.services.ConferenceService;

@WebServlet("/conferences")
public class ConferenceListView extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private ConferenceService conferenceService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Conference> conferences = conferenceService.findAll(0, 10);
        request.setAttribute("conferences", conferences);
        request.getRequestDispatcher("conferences.jsp").forward(request, response);
    }

}
