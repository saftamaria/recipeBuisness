package com.example.model;

import javax.persistence.*;

@Entity(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ingredient_id")
    private Long ingredientId;

    private String description;
    private double amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasurement uom;

    @ManyToOne
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String description, double amount, UnitOfMeasurement uom) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
    }

    public Ingredient(String description, double amount, UnitOfMeasurement uom, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.uom = uom;
        this.recipe = recipe;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public UnitOfMeasurement getUom() {
        return uom;
    }

    public void setUom(UnitOfMeasurement uom) {
        this.uom = uom;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ingredientId=" + ingredientId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", uom=" + uom +
                ", recipe=" + recipe +
                '}';
    }
}
