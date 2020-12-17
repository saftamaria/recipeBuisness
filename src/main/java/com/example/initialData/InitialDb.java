package com.example.initialData;

import com.example.model.*;
import com.example.repositories.CategoryRepository;
import com.example.repositories.RecipeRepository;
import com.example.repositories.UnitOfMeasurementRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

@Component
public class InitialDb implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasurementRepository uomRepository;

    public InitialDb(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasurementRepository uomRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.uomRepository = uomRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    public List<Recipe> getRecipes() {
        saveCategories();
        saveUom();
        List<Recipe> recipes = new ArrayList<>(Arrays.asList(getRecipe1(), getRecipe2()));
        return recipes;
    }

    private Recipe getRecipe1() {
        Recipe recipe1 = new Recipe();
        recipe1.setDescription("sarmale");
        recipe1.setPrepTime(30);
        recipe1.setCookingTime(60);
        recipe1.setServings(20);
        recipe1.setSource("Jamilla");
        recipe1.setUrl("http://jamilla.com");
        recipe1.setDirections("long string long string long string long string long string long string");
        Notes notes1 = new Notes();
        notes1.setRecipeNotes("pune mai putina sare fata de reteta originala");
        recipe1.setCustomNotes(notes1);
        UnitOfMeasurement uom1 = uomRepository.findByDescription("kilogram").orElseThrow(() -> new RuntimeException("uom not found"));
        Ingredient ingredient1 = new Ingredient("varza",20, uom1);
        recipe1.setCustomIngredients(ingredient1);
        recipe1.setDifficulty(Difficulty.ADVANCED);
        Category category1 = categoryRepository.findByDescription("romaneasca").orElseThrow(() -> new RuntimeException("category not found"));
        Set<Category> categorySet = new HashSet<>();
        categorySet.add(category1);
        recipe1.setCategories(categorySet);
        return recipe1;
    }

    private Recipe getRecipe2() {
        Recipe recipe2 = new Recipe();
        recipe2.setDescription("paste carbonara");
        recipe2.setPrepTime(30);
        recipe2.setCookingTime(60);
        recipe2.setServings(20);
        recipe2.setSource("Jamilla");
        recipe2.setUrl("http://jamilla.com");
        recipe2.setDirections("long string long string long string long string long string long string");
        Notes notes1 = new Notes();
        notes1.setRecipeNotes("pune mai putina sare fata de reteta originala");
        recipe2.setCustomNotes(notes1);
        UnitOfMeasurement uom1 = uomRepository.findByDescription("litru").orElseThrow(() -> new RuntimeException("uom not found"));
        Ingredient ingredient1 = new Ingredient("smantana",20, uom1);
        recipe2.setCustomIngredients(ingredient1);
        recipe2.setDifficulty(Difficulty.ADVANCED);
        Category category1 = categoryRepository.findByDescription("italiana").orElseThrow(() -> new RuntimeException("category not found"));
        Set<Category> categorySet = new HashSet<>();
        categorySet.add(category1);
        recipe2.setCategories(categorySet);
        return recipe2;
    }

    private void saveUom() {
        UnitOfMeasurement uom1 = new UnitOfMeasurement();
        uom1.setDescription("kilogram");

        UnitOfMeasurement uom2 = new UnitOfMeasurement();
        uom2.setDescription("litru");

        UnitOfMeasurement uom3 = new UnitOfMeasurement();
        uom3.setDescription("gram");

        List<UnitOfMeasurement> unitOfMeasurements = new ArrayList<>(Arrays.asList(uom1, uom2, uom3));
        uomRepository.saveAll(unitOfMeasurements);
    }

    private void saveCategories() {
        Category category1 = new Category();
        category1.setDescription("romaneasca");

        Category category2 = new Category();
        category2.setDescription("italiana");

        List<Category> categories = new ArrayList<>(Arrays.asList(category1, category2));
        categoryRepository.saveAll(categories);
    }

}
