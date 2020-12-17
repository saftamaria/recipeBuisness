package com.example.services;

import com.example.commands.UnitOfMeasurementCommand;
import com.example.model.UnitOfMeasurement;

import java.util.Set;

public interface UnitOfMeasService {

    Set<UnitOfMeasurementCommand> listOfUom();
}
