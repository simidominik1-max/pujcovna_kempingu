package cz.uhk.pro2kf2026.service;


import cz.uhk.pro2kf2026.model.Category;
import cz.uhk.pro2kf2026.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getCategory(long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.findById(id).ifPresent(category -> categoryRepository.deleteById(id));

    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
