package com.example.jetpackcompose.presentation.ui.recipe_list

import com.example.jetpackcompose.presentation.ui.recipe_list.FoodCategory.*

enum class FoodCategory(val value: String) {
    ALL("ALL"),
    CHICKEN("Chicken"),
    BEEF("Beef"),
    SOUP("Soup"),
    DESSERT("Dessert"),
    VEGETARIAN("Vegetarian"),
    VEGAN("Vegan"),
    MILK("Milk"),
    PIZZA("Pizza"),
    DONUT("Donut"),
    BURGER("Burger")
}

fun getAllFoodCategories(): List<FoodCategory> {
    return listOf(ALL, CHICKEN, BEEF, SOUP, DESSERT, VEGETARIAN, VEGAN, MILK, PIZZA, DONUT, BURGER)
}

fun getFoodCategory(value: String): FoodCategory? {
    val map = FoodCategory.values().associateBy(FoodCategory::value)
    return map[value]
}

fun getFoodCategoryList(): List<String> {
    return listOf("","CHICKEN", "BEEF", "SOUP", "DESSERT", "VEGETARIAN", "VEGAN", "MILK", "PIZZA", "DONUT", "BURGER")
}