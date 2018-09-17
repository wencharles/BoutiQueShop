package com.sopia.boutiqueshop.entity.services;

import com.sopia.boutiqueshop.entities.Category;

import java.util.List;

public interface CategoriesService {

    public List<Category> getCategories();

    public void saveCategory(Category category);

    public Category getCategory(int CategoryId);

    public void deleteCategory(int CategoryId);

}
