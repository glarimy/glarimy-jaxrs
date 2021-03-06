package com.glarimy.jaxrs;

import static java.lang.Math.min;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/library")
public class BookRootResource {
	private static LibraryService service = new LibraryService();

	@GET
	@Path("/book/{isbn}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response find(@PathParam("isbn") int isbn) {
		Book book = service.find(isbn);
		if (book == null)
			return Response.status(404).build();
		else
			return Response.ok().entity(book).build();
	}

	@POST
	@Path("/book")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response create(Book book) {
		try {
			service.add(book);
			return Response.created(new URI(book.getIsbn() + "")).entity(book).build();
		} catch (Exception e) {
			return Response.status(500).entity(book).build();
		}
	}

	@PUT
	@Path("/book")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response update(Book book) {
		service.replace(book);
		return Response.ok().entity(book).build();
	}

	@DELETE
	@Path("/book/{isbn}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response remove(@PathParam("isbn") int isbn) {
		Book book = service.find(isbn);
		service.remove(isbn);
		return Response.ok().entity(book).build();
	}

	@GET
	@Path("/book")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response find(@QueryParam("title") String title, @HeaderParam("limit") @DefaultValue("10") int limit) {
		BookList list = new BookList();
		if (title != null) {
			List<Book> books = service.listByTitle(title);
			list.setBooks(books.subList(0, min(books.size(), limit)));
		} else {
			List<Book> books = service.list();
			list.setBooks(books.subList(0, min(books.size(), limit)));
		}
		return Response.ok().entity(list).build();
	}
}
