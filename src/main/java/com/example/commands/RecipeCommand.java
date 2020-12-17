package com.example.commands;

import com.example.model.Difficulty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RecipeCommand {
    public Long recipeId;
    //@Min(20)
    //@Max(1000)
    public String description;
    public int prepTime;
    public int cookingTime;
    public int servings;
    public String source;
    //@URL
    public String url;
    //@Size(min = 10, max = 500)
    public String directions;
    public Set<IngredientCommand> ingredients = new HashSet<>();
    public Byte[] image;
    public Difficulty difficulty;
    public NotesCommand notes;
    public Set<CategoryCommand> categories = new HashSet<>();


}
