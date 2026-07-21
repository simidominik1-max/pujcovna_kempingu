package cz.uhk.pro2kf2026;

import cz.uhk.pro2kf2026.model.Item;
import cz.uhk.pro2kf2026.repository.ItemRepository;
import cz.uhk.pro2kf2026.service.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
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

    private Item testItem;

    @BeforeEach
    void setUp() {
        // Příprava testovacího pejska před každým testem
        testItem = new Item();
        // Pokud máš v modelu setId(), můžeš odkomentovat:
        // testItem.setId(1L);
    }

    @Test
    void getItem_ShouldReturnItemIfExists() {
        when(itemRepository.findById(1L)).thenReturn(Optional.of(testItem));

        Item result = itemService.getItem(1L);

        assertNotNull(result);
        assertEquals(testItem, result);
    }

    @Test
    void getItem_ShouldReturnNullIfNotFound() {
        when(itemRepository.findById(99L)).thenReturn(Optional.empty());

        Item result = itemService.getItem(99L);

        assertNull(result);
    }

    @Test
    void saveItem_ShouldCallRepositorySave() {
        itemService.saveItem(testItem);

        verify(itemRepository, times(1)).save(testItem);
    }

    @Test
    void deleteItem_ShouldDeleteIfExists() {
        when(itemRepository.findById(1L)).thenReturn(Optional.of(testItem));

        itemService.deleteItem(1L);

        verify(itemRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteItem_ShouldNotDeleteIfNotExists() {
        when(itemRepository.findById(99L)).thenReturn(Optional.empty());

        itemService.deleteItem(99L);

        // Ověříme, že se metoda deleteById nikdy nezavolala, protože pes neexistoval
        verify(itemRepository, never()).deleteById(anyLong());
    }

    @Test
    void getAllItems_ShouldReturnListOfItems() {
        List<Item> items = Arrays.asList(new Item(), new Item());
        when(itemRepository.findAll()).thenReturn(items);

        List<Item> result = itemService.getAllItems();

        assertEquals(2, result.size());
        verify(itemRepository, times(1)).findAll();
    }
}