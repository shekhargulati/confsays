package com.shekhar.confsays.exceptions.mapping;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.shekhar.confsays.exceptions.ConferenceNotFoundException;

@Provider
public class ConferenceNotFoundMapper implements ExceptionMapper<ConferenceNotFoundException> {

    @Override
    public Response toResponse(ConferenceNotFoundException exception) {
        return Response.status(Status.NOT_FOUND).build();
    }

}
