package app.web;

import app.user.model.dto.UserLoginRequest;
import app.user.model.dto.UserRegisterResponse;
import app.user.service.UserService;
import app.user.model.dto.UserRegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView getUsersRegister(ModelAndView modelAndView, UserRegisterRequest user) {
        modelAndView.setViewName("register");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView doUsersRegister(@Valid @ModelAttribute("user") UserRegisterRequest user,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView,
                                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute("user", user);
            modelAndView.setViewName("register");
            return modelAndView;
        }

        userService.register(user);

        modelAndView.setViewName("redirect:/users/login");

        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView getUserLogin(ModelAndView modelAndView, @ModelAttribute("user") UserLoginRequest user) {
        modelAndView.setViewName("login");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView doUserLogin(@Valid @ModelAttribute("user") UserLoginRequest user,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView,
                                        RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute("user", user);
            modelAndView.setViewName("login");
            return modelAndView;
        }


        //todo handle exceptions
        userService.login(user);

        modelAndView.setViewName("redirect:/home");

        return modelAndView;
    }

}
