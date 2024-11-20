package org.acme;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @GET
    public List<Person> getAllPeople() {
        return Person.listAll(); // ดึงข้อมูลทั้งหมด
    }

    @POST
    @Transactional
    public Response addPerson(Person person) {
        person.persist(); // เพิ่มข้อมูลใหม่
        return Response.status(Response.Status.CREATED).entity(person).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deletePerson(@PathParam("id") Long id) {
        boolean deleted = Person.deleteById(id); // ลบข้อมูลตาม ID
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
