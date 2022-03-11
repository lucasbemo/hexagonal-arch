package com.lz.hexagonal.arch;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class PersonEntity {

    @Id @GeneratedValue
    private long id;
    private String name;
}
