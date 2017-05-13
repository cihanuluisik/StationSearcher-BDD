package com.hsbc.search.repository;

import com.hsbc.search.domain.Station;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SearchRepository extends CrudRepository<Station, String> {

    /**
     * Finds station by using the name as a search criteria.
     * @param name
     * @return  A list of stations whose name contains given station name.
     *          returns empty list, if none found
     */
    @Query("SELECT s FROM Station s WHERE s.name LIKE :name%")
    List<Station> findByName(@Param("name") String name);

}
