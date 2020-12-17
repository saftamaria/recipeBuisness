package com.example.converters;

import com.example.commands.RecipeCommand;
import com.example.model.Recipe;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final CategoryToCategoryCommand categoryToCategoryCommand;
    private final NotesToNotesCommand notesToNotesCommand;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientToIngredientCommand, CategoryToCategoryCommand categoryToCategoryCommand, NotesToNotesCommand notesToNotesCommand) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
        this.notesToNotesCommand = notesToNotesCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null) {
            return null;
        }

        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setRecipeId(source.getRecipeId());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookingTime(source.getCookingTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setDirections(source.getDirections());
        if (source.getIngredients().size() > 0 && source.getIngredients() != null) {
            source.getIngredients().forEach(i -> recipeCommand.getIngredients().add(ingredientToIngredientCommand.convert(i)));
        }
        recipeCommand.setImage(source.getImage());
        recipeCommand.setDifficulty(source.getDifficulty());
        if (source.getCategories().size() > 0 && source.getCategories() != null) {
           source.getCategories().forEach(c -> recipeCommand.getCategories().add(categoryToCategoryCommand.convert(c)));
        }
        recipeCommand.setNotes(notesToNotesCommand.convert(source.getNotes()));
        return recipeCommand;
    }
}

