package com.example.services;

import com.example.commands.UnitOfMeasurementCommand;
import com.example.converters.UnitOfMeasurementToUnitOfMeasurementCommand;
import com.example.repositories.UnitOfMeasurementRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasServiceImpl implements UnitOfMeasService {

    private final UnitOfMeasurementRepository unitOfMeasurementRepository;
    private final UnitOfMeasurementToUnitOfMeasurementCommand unitOfMeasurementToUnitOfMeasurementCommand;

    public UnitOfMeasServiceImpl(UnitOfMeasurementRepository unitOfMeasurementRepository, UnitOfMeasurementToUnitOfMeasurementCommand unitOfMeasurementToUnitOfMeasurementCommand) {
        this.unitOfMeasurementRepository = unitOfMeasurementRepository;
        this.unitOfMeasurementToUnitOfMeasurementCommand = unitOfMeasurementToUnitOfMeasurementCommand;
    }
    
    @Override
    public Set<UnitOfMeasurementCommand> listOfUom() {
         return StreamSupport.stream(unitOfMeasurementRepository.findAll().spliterator(), false)
                .map(unitOfMeasurementToUnitOfMeasurementCommand::convert)
                .collect(Collectors.toSet());// folosim StreamSupport pt cand am f multe date; atunci va fi true
    }
}
