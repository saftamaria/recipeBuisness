package com.example.converters;

import com.example.commands.CategoryCommand;
import com.example.model.Category;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {
    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if (source == null) {
            return null;
        }
        final CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setCategoryId(source.getCategoryId());
        categoryCommand.setDescription(source.getDescription());
        return categoryCommand;
    }
}
