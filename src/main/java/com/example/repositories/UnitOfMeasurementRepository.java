package com.example.repositories;

import com.example.model.UnitOfMeasurement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitOfMeasurementRepository extends CrudRepository<UnitOfMeasurement, Long> {
    Optional<UnitOfMeasurement> findByDescription(String uom);
}
