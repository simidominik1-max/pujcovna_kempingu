package cz.uhk.pro2kf2026.controller;

import cz.uhk.pro2kf2026.model.Item;
import cz.uhk.pro2kf2026.service.ItemService;
import cz.uhk.pro2kf2026.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final UserService userService;

    @Autowired
    public ItemController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String all(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        return "items_list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") long id, Model model) {
        model.addAttribute("item", itemService.getItem(id));
        return "items_detail";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("users", userService.getAllUsers());
        return "items_edit";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("item", itemService.getItem(id));
        model.addAttribute("users", userService.getAllUsers());
        return "items_edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Item item) {
        itemService.saveItem(item);
        return "redirect:/items/";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        itemService.deleteItem(id);
        return "redirect:/items/";
    }
}
