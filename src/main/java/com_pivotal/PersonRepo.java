package com_pivotal;
import org.springframework.data.gemfire.repository.query.annotation.Trace;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepo extends CrudRepository<PersonEntity, String> {

    @Trace
    PersonEntity findByName(String name);

    @Trace
    Iterable<PersonEntity> findByHealthrecordGreaterThan(int healthrecord);

    @Trace
    Iterable<PersonEntity> findByHealthrecordLessThan(int healthrecord);

//    @Trace
//    Iterable<PersonEntity> findByHealthrecordonRangeBetween(int greaterThanAge, int lessThanAge);

}