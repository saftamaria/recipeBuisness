package com.example.converters;

import com.example.commands.IngredientCommand;
import com.example.model.Ingredient;
import com.example.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasurementCommandToUnitOfMeasurement unitOfMeasurementCommandToUnitOfMeasurement;

    public IngredientCommandToIngredient(UnitOfMeasurementCommandToUnitOfMeasurement unitOfMeasurementCommandToUnitOfMeasurement) {
        this.unitOfMeasurementCommandToUnitOfMeasurement = unitOfMeasurementCommandToUnitOfMeasurement;
    }

    @Synchronized
    @Nullable
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        }
        final Ingredient ingredient = new Ingredient();
        ingredient.setIngredientId(source.getIngredientId());
        ingredient.setDescription(source.getDescription());
        ingredient.setAmount(source.getAmount());
        ingredient.setUom(unitOfMeasurementCommandToUnitOfMeasurement.convert(source.getUom()));
        if (source.getRecipeId() != null){
            Recipe recipe = new Recipe();
            recipe.setRecipeId(source.getRecipeId());
            ingredient.setRecipe(recipe);
            recipe.setCustomIngredients(ingredient);
        }
        return ingredient;
    }
}
