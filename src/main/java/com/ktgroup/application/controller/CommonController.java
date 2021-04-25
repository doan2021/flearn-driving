package com.ktgroup.application.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ktgroup.application.common.GooglePojo;
import com.ktgroup.application.common.GoogleUtils;
import com.ktgroup.application.dto.AppUserForm;
import com.ktgroup.application.services.AppUserServices;
import com.ktgroup.application.services.RoleServices;
import com.ktgroup.application.utils.WebUtils;

@Controller
public class CommonController {
    
    @Autowired
    AppUserServices appUserServices;

    @Autowired
    RoleServices roleServices;
    
    @Autowired
    private GoogleUtils googleUtils;
    
    @GetMapping(value = { "/", "/login"})
    public String welcomePage(Model model) {
        return "login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403Page";
    }
    
    @GetMapping(value = {"/register"})
    public String registerPage(Model model) {
        model.addAttribute("appUserForm", new AppUserForm());
        return "Register";
    }
    
    @RequestMapping("/login-google")
    public String loginGoogle(HttpServletRequest request) throws ClientProtocolException, IOException {
      String code = request.getParameter("code");
      
      if (code == null || code.isEmpty()) {
        return "redirect:/login?google=error";
      }
      String accessToken = googleUtils.getToken(code);
      
      GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
      UserDetails userDetail = googleUtils.buildUser(googlePojo);
      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
          userDetail.getAuthorities());
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      return "redirect:/user";
    }
}
