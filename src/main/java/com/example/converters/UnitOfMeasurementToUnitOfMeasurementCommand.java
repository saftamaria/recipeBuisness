package com.example.converters;

import com.example.commands.UnitOfMeasurementCommand;
import com.example.model.UnitOfMeasurement;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasurementToUnitOfMeasurementCommand implements Converter<UnitOfMeasurement, UnitOfMeasurementCommand> {
    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasurementCommand convert(UnitOfMeasurement source) {
        if (source == null) {
            return null;
        }
        final UnitOfMeasurementCommand unitOfMeasurementCommand = new UnitOfMeasurementCommand();
        unitOfMeasurementCommand.setUomId(source.getUomId());
        unitOfMeasurementCommand.setDescription(source.getDescription());
        return unitOfMeasurementCommand;
    }
}
