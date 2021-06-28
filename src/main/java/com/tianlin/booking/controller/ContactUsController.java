package com.tianlin.booking.controller;

import com.tianlin.booking.entity.ContactUs;
import com.tianlin.booking.repository.ContactUsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 *
 */
@Controller
public class ContactUsController {

    @Autowired
    private ContactUsRepository contactUsRepository;

    @GetMapping("/contactus")
    public String contactUsForm(Model model) {
        model.addAttribute("contactUs", new ContactUs());
        return "contactUs";
    }

    @PostMapping("/contactus")
    public String createContactUs(@Valid ContactUs contactUs, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "contactUs";
        }
        contactUsRepository.save(contactUs);
        model.addAttribute("contactUs", contactUs);
        return "result1";
    }


}
