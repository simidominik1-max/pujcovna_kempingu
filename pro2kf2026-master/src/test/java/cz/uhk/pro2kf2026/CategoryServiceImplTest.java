package cz.uhk.pro2kf2026;

import cz.uhk.pro2kf2026.model.Category;
import cz.uhk.pro2kf2026.repository.CategoryRepository;
import cz.uhk.pro2kf2026.service.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void getCategory_ShouldReturnCategory_WhenExists() {
        Category mockCategory = mock(Category.class);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(mockCategory));

        Category result = categoryService.getCategory(1L);

        assertNotNull(result);
        assertEquals(mockCategory, result);
        verify(categoryRepository).findById(1L);
    }

    @Test
    void getCategory_ShouldReturnNull_WhenNotExists() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        Category result = categoryService.getCategory(1L);

        assertNull(result);
    }

    @Test
    void saveCategory_ShouldCallRepositorySave() {
        Category mockCategory = mock(Category.class);

        categoryService.saveCategory(mockCategory);

        verify(categoryRepository).save(mockCategory);
    }

    @Test
    void deleteCategory_ShouldCallDelete_WhenCategoryExists() {
        Category mockCategory = mock(Category.class);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(mockCategory));

        categoryService.deleteCategory(1L);

        verify(categoryRepository).deleteById(1L);
    }

    @Test
    void deleteCategory_ShouldNotCallDelete_WhenCategoryDoesNotExist() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        categoryService.deleteCategory(1L);

        verify(categoryRepository, never()).deleteById(anyLong());
    }

    @Test
    void getAllCategories_ShouldReturnListOfCategories() {
        List<Category> mockList = Arrays.asList(mock(Category.class), mock(Category.class));
        when(categoryRepository.findAll()).thenReturn(mockList);

        List<Category> result = categoryService.getAllCategories();

        assertEquals(2, result.size());
        verify(categoryRepository).findAll();
    }
}