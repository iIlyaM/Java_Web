package com.example.java_web.repository;

import com.example.java_web.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByName(String name);

    void deleteByName(String name);

    @Query(value = "insert into persons (name, age, sex, document) values (:name, :age, :sex, :document) " +
            "ON CONFLICT(document) DO UPDATE SET document = excluded.document RETURNING id;",
            nativeQuery = true)
    Integer insertOnConflict(@Param("name") String name,
                                @Param("age") Integer age,
                                @Param("sex") String sex,
                                @Param("document") String document);
}
