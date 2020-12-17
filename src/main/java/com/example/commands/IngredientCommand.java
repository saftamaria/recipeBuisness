package com.example.commands;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngredientCommand {
    public Long ingredientId;
    public String description;
    public double amount;
    public UnitOfMeasurementCommand uom;
    public Long recipeId;

}
