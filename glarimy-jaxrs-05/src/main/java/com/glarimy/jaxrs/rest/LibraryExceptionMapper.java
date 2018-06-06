package com.glarimy.jaxrs.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.glarimy.jaxrs.api.exceptions.BookNotFoundException;
import com.glarimy.jaxrs.api.exceptions.DuplicateBookException;
import com.glarimy.jaxrs.api.exceptions.InvalidBookException;

@Provider
public class LibraryExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable t) {
		t.printStackTrace();
		if (t instanceof BookNotFoundException)
			return Response.status(404).build();
		if (t instanceof InvalidBookException)
			return Response.status(400).build();
		if (t instanceof DuplicateBookException)
			return Response.status(405).build();
		return Response.status(500).build();

	}

}
