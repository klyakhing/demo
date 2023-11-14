package com.example.service;

import com.example.model.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getCategories();
    public void save(Category user);
    public Category findById(Integer id);
    public void delete(Category user);
}
