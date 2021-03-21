package com.person.service;

import com.person.dao.PersonDao;
import com.person.model.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;


@Path("/person")
public class PersonService {
    private final PersonDao personDao = new PersonDao();
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Person> getAllPerson(){
      return personDao.getAllPerson();
      }

    @GET
    @Path("/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonById(@PathParam("personId") Long personId){
        return personDao.getPersonById(personId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Person createPerson(Person person) throws SQLException {
        return personDao.addPerson(person);
    }
    @PUT
    @Path("/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person updatePerson(Person person, @PathParam("personId") Long personId){
        return personDao.updatePerson(personId, person);
    }
    @DELETE
    @Path("/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deletePerson(@PathParam("personId") Long personId){
        personDao.deletePerson(personId);
    }
}
