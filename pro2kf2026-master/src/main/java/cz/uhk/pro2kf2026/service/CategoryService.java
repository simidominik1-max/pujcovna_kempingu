package cz.uhk.pro2kf2026.service;


import cz.uhk.pro2kf2026.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    Category getCategory(long id);
    void saveCategory(Category category);
    void deleteCategory(long id);
    List<Category> getAllCategories();
}
