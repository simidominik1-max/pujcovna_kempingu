package cz.uhk.pro2kf2026.service;

import cz.uhk.pro2kf2026.model.Category;
import cz.uhk.pro2kf2026.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService {
    Item getItem(long id);
    void saveItem(Item item);
    void deleteItem(long id);
    List<Item> getAllItems();
    List<Item> getItemsByCategory(Category category);
}
