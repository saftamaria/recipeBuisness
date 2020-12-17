package com.example.converters;

import com.example.commands.RecipeCommand;
import com.example.model.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final CategoryCommandToCategory categoryCommandToCategory;
    private final NotesCommandToNotes notesCommandToNotes;

    public RecipeCommandToRecipe(IngredientCommandToIngredient ingredientCommandToIngredient, CategoryCommandToCategory categoryCommandToCategory, NotesCommandToNotes notesCommandToNotes) {
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.categoryCommandToCategory = categoryCommandToCategory;
        this.notesCommandToNotes = notesCommandToNotes;
    }


    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if (source == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setRecipeId(source.getRecipeId());
        recipe.setDescription(source.getDescription());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setCookingTime(source.getCookingTime());
        recipe.setServings(source.getServings());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());
        recipe.setDirections(source.getDirections());
        if (source.getIngredients().size() > 0 && source.getIngredients() != null) {
            source.getIngredients().forEach(i -> recipe.getIngredients().add(ingredientCommandToIngredient.convert(i)));
        }
        recipe.setImage(source.getImage());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setNotes(notesCommandToNotes.convert(source.getNotes()));
        if (source.getCategories().size() > 0 && source.getCategories() != null) {
            source.getCategories().forEach(c -> recipe.getCategories().add(categoryCommandToCategory.convert(c)));
        }
        return recipe;
    }
}
