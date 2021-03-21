package com.person.dao;

import com.person.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {


    public Person addPerson(Person person) throws SQLException{
        String SQL = "insert into person (request_date_time, name, birth_year, gender) values(now(),?,?,?)";
        try(Connection conn = new DbConnection().connect();
            PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setString(1, person.getName());
            stmt.setInt(2, person.getBirthYear());
           // stmt.setBoolean(3, person.isGender());
            stmt.setString(3, person.getGender());
            stmt.executeUpdate();
            return person;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Person> getAllPerson(){
        List<Person> personList = new ArrayList<>();
        String SQL = "select * from person";
        try(Connection conn = new DbConnection().connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(SQL)){
        while (rs.next()){
            Person person = new Person(
                    rs.getLong("id"),
                    rs.getDate("request_date_time"),
                    rs.getString("name"),
                    rs.getInt("birth_year"),
                    //rs.getBoolean("gender")
                    rs.getString("gender")
            );
            personList.add(person);
        }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return personList;
    }
        public Person getPersonById(Long id){
        String SQL = "select * from person where id = ?";
        Person person = null;
        try(Connection conn = new DbConnection().connect();
        PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setLong(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next())
                    person = new Person(
                            rs.getLong("id"),
                            rs.getDate("request_date_time"),
                            rs.getString("name"),
                            rs.getInt("birth_year"),
                            //rs.getBoolean("gender")
                            rs.getString("gender")
                            );
            }
        }
        catch (SQLException e){
            System.out.println("Dear " + person.getName() + ", your year of birth is " + person.getBirthYear());
        }
        return person;
        }
        public boolean deletePerson(Long id){
        String SQL = "delete from person where id = ?";
        try(Connection conn = new DbConnection().connect();
        PreparedStatement stmt = conn.prepareStatement(SQL)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
            return true;
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return false;
        }
        public Person updatePerson(Long id, Person person) {
            String SQL = "update person set name = ?, birthYear = ?, gender = ? where id = ?";
            try (Connection conn = new DbConnection().connect();
                 PreparedStatement stmt = conn.prepareStatement(SQL)) {
                stmt.setString(1, person.getName());
                stmt.setInt(2, person.getBirthYear());
                // stmt.setBoolean(3, person.isGender());
                stmt.setString(3, person.getGender());
                stmt.setLong(4, id);
                stmt.executeUpdate();
                return person;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            return null;
        }


}
