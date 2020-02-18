package com.smoothstack.borrower.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import com.smoothstack.borrower.services.BorrowerServices;

@Component
@Path("/member")
public class BorrowerController {

	private BorrowerServices borrowerService;

	public BorrowerController(BorrowerServices borrowerService) {
		this.borrowerService = borrowerService;

	}

	@POST
	@Path("/checkout/{branchId}/{bookId}/{cardNo}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })

	public Response checkOutBook(@PathParam("branchId") int branchId, @PathParam("bookId") int bookId,
			@PathParam("cardNo") int cardNo) {

		String details;

		try {

			details = borrowerService.checkOutBook(branchId, bookId, cardNo);

			return Response.ok(details).build();

		} catch (Exception e) {
			String resp = "Failed to check out a book; " + e.getMessage();
			return Response.status(Response.Status.BAD_REQUEST).entity(resp).build();
		}

	}

	@POST
	@Path("/checkin/{bookId}/{cardNo}")
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response checkInBook(@PathParam("bookId") int bookId, @PathParam("cardNo") int cardNo) {

		String resp = "";
		try {
			boolean status = borrowerService.checkInBook(bookId, cardNo);
			if (status) {
				resp = "Book has been checked in successfully.";
				return Response.ok(resp).build();
			} else {
				resp = "Failed to check in, please try again.";
				return Response.status(Response.Status.NOT_MODIFIED).entity(resp).build();
			}

		} catch (Exception e) {
			resp = "Failed to check in the book.";
			return Response.status(Response.Status.BAD_REQUEST).entity(resp).build();
		}

	}
}
