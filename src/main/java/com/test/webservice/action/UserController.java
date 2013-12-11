package com.test.webservice.action;

import com.generic.entity.User;
import com.generic.service.UserService;
import com.google.gson.Gson;
import com.test.webservice.util.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: proshad
 * Date: 12/1/13
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public String getAllUsers() {
        List allUsers = new ArrayList();
        List<User> users = userService.listOfUsers();
        for (User user : users) {
            Map userMap = new HashMap();
            user = HibernateUtil.unproxy(user);
            userMap.put("email", user.getEmailID());
            userMap.put("password", user.getPassword().trim());
            userMap.put("firstName", user.getFirstName().trim());
            userMap.put("lastName", user.getLastName().trim());
            userMap.put("profileImageUrl", user.getImageUrl());
            allUsers.add(userMap);
        }
        String json = new Gson().toJson(allUsers);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/getDetails/{id}", method = RequestMethod.GET)
    public String getUserDetails(@PathVariable("emailId") String emailId) {

        User user = userService.detailsOfUser(emailId);
        Map userMap = new HashMap();
        user = HibernateUtil.unproxy(user);
        userMap.put("email", user.getEmailID());
        userMap.put("password", user.getPassword().trim());
        userMap.put("firstName", user.getFirstName().trim());
        userMap.put("lastName", user.getLastName().trim());
        userMap.put("profileImageUrl", user.getImageUrl());

        String json = new Gson().toJson(userMap);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    public String saveOrUpdate(@ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            userService.saveOrUpdate(user);
            responseObj.put("status", "success");
            responseObj.put("message", "User save or update successfully");

        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }

    @ResponseBody
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("emailId") String emailId) {
        Map<String, String> responseObj = new HashMap<String, String>();
        try {
            userService.removeUser(emailId);

            responseObj.put("status", "success");
            responseObj.put("message", "User remove successfully");
        } catch (Exception ex) {
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }

    @ResponseBody
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> responseObj = new HashMap<String, String>();
        HttpSession session = request.getSession();
        try {
            String userName = request.getParameter("emailID");
            String password = request.getParameter("password");

            User user = userService.detailsOfUser(userName);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    session.setAttribute("USER", userName);
                    responseObj.put("status", "success");
                    responseObj.put("message", "Login success");

                } else {
                    session.removeAttribute("USER");
                    responseObj.put("status", "fail");
                    responseObj.put("message", "Login incorrect, wrong password");
                }
            } else {
                session.removeAttribute("USER");
                responseObj.put("status", "fail");
                responseObj.put("message", "Login incorrect, wrong emailID");
            }

        } catch (Exception ex) {
            session.removeAttribute("USER");
            responseObj.put("status", "fail");
            responseObj.put("message", ex.getMessage());
        }
        String json = new Gson().toJson(responseObj);
        return json;
    }

    @ResponseBody
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> responseObj = new HashMap<String, String>();
        HttpSession session = request.getSession();
        session.removeAttribute("USER");
        responseObj.put("status", "success");
        responseObj.put("message", "Logout successful");

        String json = new Gson().toJson(responseObj);
        return json;
    }

}
