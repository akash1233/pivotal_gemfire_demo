package com_pivotal;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.gemfire.mapping.annotation.Region;

import lombok.Getter;

@Region(value = "People")
public class PersonEntity implements Serializable {

    @Id
    @Getter
    private final String name;

    @Getter
    private final int healthrecord;

    @PersistenceConstructor
    public PersonEntity(String name, int healthrecord) {
        this.name = name;
        this.healthrecord = healthrecord;
    }


	public String getName() {
		return name;
	}


	public int getHealthrecord() {
		return healthrecord;
	}


	@Override
    public String toString() {
        return String.format("%s is %d Health Record Number", getName(), getHealthrecord());
    }
}