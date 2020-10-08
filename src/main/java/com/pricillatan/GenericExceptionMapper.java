package com.pricillatan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {
    private static final Logger LOGGER = LoggerFactory.getLogger(GenericExceptionMapper.class);
    @Context
    private HttpServletRequest httpRequest;
    @Context
    private HttpHeaders headers;

    private MediaType getAcceptType() {
        List<MediaType> accepts = headers.getAcceptableMediaTypes();
        if (accepts != null && !accepts.isEmpty()) {
            for (MediaType accept : accepts) {
                if (MediaType.APPLICATION_JSON_TYPE.equals(accept) || MediaType.APPLICATION_XML_TYPE.equals(accept) || MediaType.TEXT_PLAIN_TYPE.equals(accept)) {
                    return accept;
                }
            }
        }
        return MediaType.APPLICATION_JSON_TYPE;
    }

    @Override
    public Response toResponse(Exception exception) {

        MediaType acceptType = getAcceptType();
        LOGGER.error("{} {}{}, error: {}", httpRequest.getMethod(), httpRequest.getServletPath(), httpRequest.getPathInfo(), exception.getMessage(), exception);
        Map<String, Object> err = new HashMap<>(3);
        if (exception instanceof WebApplicationException) {
            Response r = ((WebApplicationException) exception).getResponse();
            Response.StatusType type = r.getStatusInfo();
            err.put("statusCode", type.getStatusCode());
            err.put("code", type.getReasonPhrase());
            err.put("msg", exception.getMessage());
            return Response.status(type.getStatusCode()).entity(err).type(acceptType).build();
        } else {
            err.put("statusCode", Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            err.put("code", "SystemError");
            err.put("msg", exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(err).type(acceptType).build();
        }
    }


}
