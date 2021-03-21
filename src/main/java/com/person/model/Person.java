package com.person.model;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "person")
public class Person {
    private Long id;
    private Date requestDateTime;
    private String name;
    private int birthYear;
    //private boolean gender;
    private String gender;
}
