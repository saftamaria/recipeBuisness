package com.example.controllers;

import com.example.commands.RecipeCommand;
import com.example.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Slf4j
@Controller
//@RequestMapping("/api")
public class RecipeController {

    private static final String RECIPE_RECIPEFORM_URL = "recipeform";

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes")
    public ResponseEntity<Set<RecipeCommand>> getAll() {
        return ResponseEntity.ok(recipeService.findAllRecipes());
    }

    @GetMapping("/recipe/{id}/showRecipe")
    public String getRecipeById(@PathVariable Long id, Model model) {
        //return ResponseEntity.ok(recipeService.findRecipeById(id));
        model.addAttribute("recipeById", recipeService.findRecipeById(id));
        return "showRecipe";
    }

    @PostMapping("/recipe/save")
    public ResponseEntity<RecipeCommand> saveRecipe(@RequestBody RecipeCommand recipeCommand) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(recipeService.createRecipe(recipeCommand));
    }

    @DeleteMapping("/recipe/delete/{id}")
    public ResponseEntity<Void> removeRecipeById (@PathVariable Long id){
        recipeService.deleteRecipeById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return RECIPE_RECIPEFORM_URL;
    }

    @GetMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findRecipeById(Long.valueOf(id)));
        return RECIPE_RECIPEFORM_URL;
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(objectError -> {
                log.debug(objectError.toString());
            });
            return RECIPE_RECIPEFORM_URL;
        }
        RecipeCommand savedCommand = recipeService.createRecipe(command);
        model.addAttribute("recipeById", savedCommand);
        return "showRecipe";
    }

    @GetMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable String id){
        log.debug("Deleting id: " + id);
        recipeService.deleteRecipeById(Long.valueOf(id));
        return "redirect:/";
    }
}
