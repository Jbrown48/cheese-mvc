package org.launchcode.controllers;

import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static jdk.nashorn.internal.objects.ArrayBufferView.length;

/**
 * Created by jarrett on 6/5/2017.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add Register");
        model.addAttribute("user", new User());
        return "user/add";
    }

    @RequestMapping(value= "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute User user, String verify){

        model.addAttribute(user);
        model.addAttribute("title", "Add Register");
        boolean passwordsMatch = true;
        boolean usernameEmailValid = true;
        String x = user.getUsername();
        String y = user.getEmail();
        String p = user.getPassword();

        if (x.length() < 5 || x.length() > 15 || y.length() == 0){
            model.addAttribute("errorUsernameEmail", "Username/Email is not valid.");
            usernameEmailValid = false;

        }

        if (p.length() == 0 || verify == null
                || !user.getPassword().equals(verify)){
            passwordsMatch = false;
            model.addAttribute("errorPassword", "Passwords do not match.");
        }

        if (passwordsMatch && usernameEmailValid){
            return "user/index";
        }

        return "user/add";
    }

    @RequestMapping(value="index", method = RequestMethod.POST)
    public String index(Model model, @PathVariable String username){
        model.addAttribute("username", username);

        return "user/index";
    }


}

