package com.example.java_web.repository;

import com.example.java_web.entity.InPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InPlaceRepository extends JpaRepository<InPlace, Integer> {
    @Query(value = "insert into in_place (id_person, id_place) values (:id_person, :id_place) " +
            "ON CONFLICT(id_person, id_place) DO UPDATE SET id_person = excluded.id_person, id_place = excluded.id_place  " +
            "RETURNING id;",
            nativeQuery = true)
    Integer insertOnConflict(@Param("id_person") Integer idPerson,
                                @Param("id_place") Integer idPlace);
}
