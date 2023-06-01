package com.example.java_web.repository;

import com.example.java_web.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {

    Place findByName(String name);

    void deleteByName(String name);

    @Query(value = "insert into places (name, location_x, location_y) values (:name, :location_x, :location_y) " +
            "ON CONFLICT(location_x, location_y) DO UPDATE SET location_x = excluded.location_x, location_y = excluded.location_y  " +
            "RETURNING id;",
            nativeQuery = true)
    Integer insertOnConflict(@Param("name") String name,
                                @Param("location_x") Double locationX,
                                @Param("location_y") Double locationY);
}
