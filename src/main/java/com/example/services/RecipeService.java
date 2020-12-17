package com.example.services;

import com.example.commands.RecipeCommand;
import com.example.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<RecipeCommand> findAllRecipes();
    RecipeCommand findRecipeById(Long id);
    RecipeCommand createRecipe(RecipeCommand recipeCommand);
    void deleteRecipeById(Long id);

}
