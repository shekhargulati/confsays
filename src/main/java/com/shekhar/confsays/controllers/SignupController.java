package com.shekhar.confsays.controllers;

import com.shekhar.confsays.domain.User;
import com.shekhar.confsays.service.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class SignupController
 */
@WebServlet("/signup")
public class SignupController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    UserService userService;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(firstname, lastname, email, password);
        User existingUser = userService.findByEmail(email);
        if (existingUser != null) {
            request.setAttribute("errorMsg", "User already exists with given email");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        Long userId = userService.create(user);

        if (userId != null) {
            request.setAttribute("successMsg", "Congratulations, you have successfully created the account. Please sign in");
            response.sendRedirect("/" + request.getContextPath() + "app/conferences/new");
            return;
        }
        response.sendRedirect("/");
    }


}
