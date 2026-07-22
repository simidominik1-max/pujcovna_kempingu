package cz.uhk.pro2kf2026.service;

import cz.uhk.pro2kf2026.model.Category;
import cz.uhk.pro2kf2026.model.Item;
import cz.uhk.pro2kf2026.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item getItem(long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void deleteItem(long id) {
        itemRepository.findById(id).ifPresent(item -> itemRepository.deleteById(id));
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> getItemsByCategory(Category category) {
        return itemRepository.findByCategory(category);
    }
}
