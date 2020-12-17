package com.example.converters;

import com.example.commands.CategoryCommand;
import com.example.model.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {
    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if (categoryCommand == null) {
            return null;
        }
        final Category category = new Category();
        category.setCategoryId(categoryCommand.getCategoryId());
        category.setDescription(category.getDescription());
        return category;
    }
}
