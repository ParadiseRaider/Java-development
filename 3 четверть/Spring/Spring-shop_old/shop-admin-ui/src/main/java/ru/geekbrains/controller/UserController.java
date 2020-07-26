package ru.geekbrains.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.model.Role;
import ru.geekbrains.model.User;
import ru.geekbrains.repo.RoleRepository;
import ru.geekbrains.rest.NotFoundException;
import ru.geekbrains.service.UserService;

import javax.validation.Valid;
import java.util.Optional;


@RequestMapping("/user")
@Controller
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String userList(Model model,
                           @RequestParam(name = "minAge", required = false) Integer minAge,
                           @RequestParam(name = "maxAge", required = false) Integer maxAge,
                           @RequestParam(value = "username", required = false) String username,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size) {

        Page<User> userPage = userService.filterByAge(minAge, maxAge, username,
                PageRequest.of(page.orElse(1) - 1, size.orElse(5)));
        model.addAttribute("usersPage", userPage);
        model.addAttribute("prevPageNumber", userPage.hasPrevious() ? userPage.previousPageable().getPageNumber() + 1 : -1);
        model.addAttribute("nextPageNumber", userPage.hasNext() ? userPage.nextPageable().getPageNumber() + 1 : -1);
        model.addAttribute("roles", roleRepository.findAll());
        return "users";
    }

    @GetMapping("new")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "user";
    }

    @GetMapping("edit")
    public String editUser(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findById(id)
                .orElseThrow(() -> new NotFoundException()));
        model.addAttribute("roles", roleRepository.findAll());
        return "user";
    }


    @PostMapping
    public String saveUser(@Valid User user, BindingResult bindingResult) {
        userService.save(user);
        return "redirect:/user";
    }

    @DeleteMapping
    public String deleteUser(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/user";
    }
}
