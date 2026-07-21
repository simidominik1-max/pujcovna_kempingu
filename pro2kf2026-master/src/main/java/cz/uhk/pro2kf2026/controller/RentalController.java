package cz.uhk.pro2kf2026.controller;

import cz.uhk.pro2kf2026.model.Rental;
import org.springframework.ui.Model;
import cz.uhk.pro2kf2026.service.ItemService;
import cz.uhk.pro2kf2026.service.RentalService;
import cz.uhk.pro2kf2026.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rentals")
public class RentalController  {

    private final RentalService rentalService;
    private final UserService userService;
    private final ItemService itemService;

    @Autowired
    public RentalController(RentalService rentalService, UserService userService, ItemService itemService) {
        this.rentalService = rentalService;
        this.userService = userService;
        this.itemService = itemService;
    }

    @GetMapping("/")
    public String all(Model model) {
        model.addAttribute("rentals", rentalService.getAllRentals());
        return "rentals_list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") long id, Model model) {
        model.addAttribute("rental", rentalService.getRental(id));
        return "rentals_detail";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("rental", new Rental());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("items", itemService.getAllItems());
        return "rentals_edit";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("rental", rentalService.getRental(id));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("items", itemService.getAllItems());
        return "rentals_edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Rental rental) {
        rentalService.saveRental(rental);
        return "redirect:/rentals/";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        rentalService.deleteRental(id);
        return "redirect:/rentals/";
    }




}
