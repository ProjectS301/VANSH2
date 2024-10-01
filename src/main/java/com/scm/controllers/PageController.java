package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.forms.UserForm;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
public class PageController {

  @Autowired
  private UserService userService;

  @RequestMapping("/home")
  public String home(Model model) {
    System.out.println("Home page handler");
    model.addAttribute("name", "substring Technologies");
    model.addAttribute("Youtubechannel", "learn code");

    return "home";
  }

  @RequestMapping("/services")
  public String servicesPage() {
    System.out.println("services page loading");
    return "services";
  }

  // contact page

  @GetMapping("/contact")
  public String contact() {
    return new String("contact");
  }

  // this is showing login page
  @GetMapping("/login")
  public String login() {
    return new String("login");
  }

  // registration page
  @GetMapping("/register")
  public String register() {
    UserForm userForm = new UserForm();
    // default data bhi daal sakte hai
    // userForm.setName("Durgesh");
    // userForm.setAbout("This is about : Write something about yourself");
    model.addAttribute("userForm", userForm);

    return "register";
  }

  // processing register

  @RequestMapping(value = "/do-register", method = RequestMethod.POST)
  public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,HttpSession session) {
    System.out.println("Processing registration");
    // fetch form data
    // UserForm
    System.out.println(userForm);

    // validate form data
    if (rBindingResult.hasErrors()) {
      return "register";
    }

    // TODO::Validate userForm[Next Video]

    // save to database

    // userservice

    // UserForm--> User
    // User user = User.builder()
    // .name(userForm.getName())
    // .email(userForm.getEmail())
    // .password(userForm.getPassword())
    // .about(userForm.getAbout())
    // .phoneNumber(userForm.getPhoneNumber())
    // .profilePic(
    // "https://www.learncodewithdurgesh.com/_next/image?url=%2F_next%2Fstatic%2Fmedia%2Fdurgesh_sir.35c6cb78.webp&w=1920&q=75")
    // .build();

    User user = new User();
    user.setName(userForm.getName());
    user.setEmail(userForm.getEmail());
    user.setPassword(userForm.getPassword());
    user.setAbout(userForm.getAbout());
    user.setPhoneNumber(userForm.getPhoneNumber());
    user.setEnabled(false);
    user.setProfilePic(
        "data:image/png;base64,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");

    User savedUser = userService.saveUser(user);

    System.out.println("user saved :");

    // message = "Registration Successful"

    // add the message:

    Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

    session.setAttribute("message", message);

    // redirectto login page
    return "redirect:/register";
  }

}
