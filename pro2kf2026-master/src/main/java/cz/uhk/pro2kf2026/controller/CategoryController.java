package cz.uhk.pro2kf2026.controller;



import cz.uhk.pro2kf2026.model.Category;
import cz.uhk.pro2kf2026.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String all(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories_list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") long id, Model model) {
        model.addAttribute("category", categoryService.getCategory(id));
        return "categories_detail";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return "categories_edit";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("category", categoryService.getCategory(id));
        return "categories_edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Category category) {
        categoryService.saveCategory(category);
        return "redirect:/categories/";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories/";
    }



}
