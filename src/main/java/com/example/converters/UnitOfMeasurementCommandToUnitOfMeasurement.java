package com.example.converters;

import com.example.model.UnitOfMeasurement;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasurementCommandToUnitOfMeasurement implements Converter<com.example.commands.UnitOfMeasurementCommand, UnitOfMeasurement> {
    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasurement convert(com.example.commands.UnitOfMeasurementCommand source) {
        if (source == null) {
            return null;
        }
         final UnitOfMeasurement unitOfMeasurementCommand = new UnitOfMeasurement();
        unitOfMeasurementCommand.setUomId(source.getUomId());
        unitOfMeasurementCommand.setDescription(source.getDescription());
        return unitOfMeasurementCommand;
    }
}
