package com.sopia.boutiqueshop.entity.services.impl;

import com.sopia.boutiqueshop.entities.Category;
import com.sopia.boutiqueshop.entity.services.CategoriesService;
import com.sopia.boutiqueshop.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoriesServiceImpl implements CategoriesService {


    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public List<Category> getCategories() {
        return categoriesRepository.findAll();
    }

    @Override
    public void saveCategory(Category category) {
        categoriesRepository.save(category);
    }

    @Override
    public Category getCategory(int CategoryId) {
        return categoriesRepository.findOne(CategoryId);
    }

    @Override
    public void deleteCategory(int CategoryId) {
    categoriesRepository.delete(CategoryId);
    }
}
