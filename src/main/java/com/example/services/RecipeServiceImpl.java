package com.example.services;

import com.example.commands.RecipeCommand;
import com.example.converters.RecipeCommandToRecipe;
import com.example.converters.RecipeToRecipeCommand;
import com.example.exceptions.NotFoundException;
import com.example.model.Recipe;
import com.example.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand, RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    @Override
    public Set<RecipeCommand> findAllRecipes() {
        Set<RecipeCommand> recipeCommands = new HashSet<>();
        recipeRepository.findAll().forEach(r -> recipeCommands.add(recipeToRecipeCommand.convert(r)));
        return recipeCommands;
    }

    @Override
    public RecipeCommand findRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new NotFoundException("recipe with id " + id.toString() + " was not found"));
        return recipeToRecipeCommand.convert(recipe);
    }

    @Transactional
    @Override
    public RecipeCommand createRecipe(RecipeCommand recipeCommand) {
        Recipe saveRecipe = recipeRepository.save(recipeCommandToRecipe.convert(recipeCommand));
        return recipeToRecipeCommand.convert(saveRecipe);
    }

    @Override
    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }
}
