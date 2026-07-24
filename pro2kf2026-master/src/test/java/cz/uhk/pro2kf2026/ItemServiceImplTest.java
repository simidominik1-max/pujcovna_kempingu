package cz.uhk.pro2kf2026;

import cz.uhk.pro2kf2026.model.Category;
import cz.uhk.pro2kf2026.model.Item;
import cz.uhk.pro2kf2026.repository.ItemRepository;
import cz.uhk.pro2kf2026.service.ItemServiceImpl;
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
class ItemServiceImplTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemServiceImpl itemService;

    @Test
    void getItem_ShouldReturnItem_WhenExists() {
        Item mockItem = mock(Item.class);
        when(itemRepository.findById(1L)).thenReturn(Optional.of(mockItem));

        Item result = itemService.getItem(1L);

        assertNotNull(result);
        assertEquals(mockItem, result);
    }

    @Test
    void saveItem_ShouldCallRepositorySave() {
        Item mockItem = mock(Item.class);

        itemService.saveItem(mockItem);

        verify(itemRepository).save(mockItem);
    }

    @Test
    void deleteItem_ShouldCallDelete_WhenExists() {
        Item mockItem = mock(Item.class);
        when(itemRepository.findById(1L)).thenReturn(Optional.of(mockItem));

        itemService.deleteItem(1L);

        verify(itemRepository).deleteById(1L);
    }

    @Test
    void getAllItems_ShouldReturnList() {
        List<Item> mockList = Arrays.asList(mock(Item.class), mock(Item.class));
        when(itemRepository.findAll()).thenReturn(mockList);

        List<Item> result = itemService.getAllItems();

        assertEquals(2, result.size());
    }

    @Test
    void getItemsByCategory_ShouldReturnItems() {
        Category mockCategory = mock(Category.class);
        List<Item> mockList = Arrays.asList(mock(Item.class));
        when(itemRepository.findByCategory(mockCategory)).thenReturn(mockList);

        List<Item> result = itemService.getItemsByCategory(mockCategory);

        assertEquals(1, result.size());
        verify(itemRepository).findByCategory(mockCategory);
    }

    @Test
    void getItem_ShouldReturnNull_WhenNotExists() {
        when(itemRepository.findById(99L)).thenReturn(Optional.empty());

        Item result = itemService.getItem(99L);

        assertNull(result);
    }

    @Test
    void deleteItem_ShouldNotCallDelete_WhenItemDoesNotExist() {
        when(itemRepository.findById(99L)).thenReturn(Optional.empty());

        itemService.deleteItem(99L);

        verify(itemRepository, never()).deleteById(anyLong());
    }
}