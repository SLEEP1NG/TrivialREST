package com.example;

import customerstuff.CustomerInfo;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("customers")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        List<CustomerInfo> all = CustomerInfo.getAllCustomer();
        StringBuilder rv = new StringBuilder("All Customers:\n");
        for (CustomerInfo ci : all) {
            rv.append(ci.toString()).append("\n");
        }
        return rv.toString();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerInfo> getStructured() {
        return CustomerInfo.getAllCustomer();
    }
    
    @Path("/{id: \\d+}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerInfo getByPk(@PathParam("id") int pk) {
        return CustomerInfo.getByPK(pk);
    }
   
    @Path("/{id: \\d+}/name")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getNameByPk(@PathParam("id") int pk) {
        return CustomerInfo.getByPK(pk).getName();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOne(CustomerInfo newOne) {
        int idx = CustomerInfo.insertNew(newOne);
        ResponseBuilder rb = Response.ok();
        rb.status(Response.Status.CREATED);
        rb.header("location", "" + idx);
        rb.entity("It worked, created entry at index " + idx);
        return rb.build();
    }
}
