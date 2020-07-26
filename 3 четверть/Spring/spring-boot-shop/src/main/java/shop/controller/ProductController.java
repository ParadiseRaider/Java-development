package shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.persist.entity.Product;
import shop.service.ProductService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@RequestMapping("/product")
@Controller
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String productList(Model model,
                              @RequestParam(value = "minCost", required = false) BigDecimal minCost,
                              @RequestParam(value = "maxCost", required = false) BigDecimal maxCost,
                              @RequestParam(value = "names", required = false) String names,
                              @RequestParam(value = "page") Optional<Integer> page) {
        logger.info("Product list. With minCost = {} and maxCost = {}", minCost, maxCost);
        model.addAttribute("products",
                productService.filterByCost(minCost, maxCost, names, PageRequest.of(page.orElse(1)-1,5)));
        model.addAttribute("page",page.orElse(1));
        return "products";
    }

    @GetMapping("new")
    public String createProduct(Model model) {
        if (!model.containsAttribute("product")) {
            model.addAttribute("product", new Product());
        }
        return "product";
    }

    @GetMapping("edit/{id}")
    public String updateProduct(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("product", productService.findById(id));
        return "product";
    }

    @PostMapping
    public String saveProduct(@Valid Product product, BindingResult bindingResult) {
        if (product.getCost()!=null) {
            if (product.getCost().signum()==-1) {
                bindingResult.rejectValue("cost", "error.cost", "Цена не может быть отрицательна!");
                return "product";
            }
        }
        if (bindingResult.hasErrors()) {
            return "product";
        }
        productService.save(product);
        return "redirect:/product";
    }

    @DeleteMapping
    public String deleteProduct(@RequestParam("id") Long id) {
        logger.info("Delete Product {}", id);
        productService.delete(id);
        return "redirect:/product";
    }
}
