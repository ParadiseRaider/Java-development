package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.model.Product;
import ru.geekbrains.repo.CategoryRepository;
import ru.geekbrains.rest.NotFoundException;
import ru.geekbrains.service.ProductService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@RequestMapping("/product")
@Controller
public class ProductController {
    private ProductService productService;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductService productService, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String productList(Model model,
                              @RequestParam(value = "minCost", required = false) BigDecimal minCost,
                              @RequestParam(value = "maxCost", required = false) BigDecimal maxCost,
                              @RequestParam(value = "names", required = false) String names,
                              @RequestParam(value = "page") Optional<Integer> page) {
        model.addAttribute("products",
                productService.filterByCost(minCost, maxCost, names, PageRequest.of(page.orElse(1)-1,5)));
        model.addAttribute("page",page.orElse(1));
        model.addAttribute("categories", categoryRepository.findAll());
        return "products";
    }

    @GetMapping("new")
    public String createProduct(Model model) {
        if (!model.containsAttribute("product")) {
            model.addAttribute("product", new Product());
            model.addAttribute("categories", categoryRepository.findAll());
        }
        return "product";
    }

    @GetMapping("edit/{id}")
    public String updateProduct(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("product", productService.findById(id)
                .orElseThrow(() -> new NotFoundException()));
        model.addAttribute("categories", categoryRepository.findAll());
        return "product";
    }

    @PostMapping
    public String saveProduct(@Valid Product product, BindingResult bindingResult) {
        productService.save(product);
        return "redirect:/product";
    }

    @DeleteMapping
    public String deleteProduct(@RequestParam("id") Long id) {
        productService.delete(id);
        return "redirect:/product";
    }
}
