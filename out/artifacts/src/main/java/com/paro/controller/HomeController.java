package com.paro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    //default handler method
    @RequestMapping
    public String welcome(Model model) {
        model.addAttribute("greeting", "Welcome to Web Store!");
        model.addAttribute("tagline", "The one and only amazing web store");
        /*
        forward: the same request
        redirect: the new request
        */
        //return "forward:/welcome/greeting";
        //return "redirect:welcome/greeting"; //solves the multiple submission, also flash attributes can be used if the data between old and new request wanted to be kept
        /*
        @RequestMapping
        public String welcome(Model model, RedirectAttributes redirectAttributes) {
            model.addAttribute("greeting", "Welcome to Web Store!");
            model.addAttribute("tagline", "The one and only amazing web store");
            redirectAttributes.addFlashAttribute("greeting", "Welcome to Web Store!");
            redirectAttributes.addFlashAttribute("tagline", "The one and only amazing web store");
            return "redirect:/welcome/greeting";
        }
         */
        return "welcome";
    }

    /*
    redirect: the new request
    forward: the same request
     */
    @RequestMapping("/welcome/greeting")
    public String greeting() {
        return "welcome";
    }
}
