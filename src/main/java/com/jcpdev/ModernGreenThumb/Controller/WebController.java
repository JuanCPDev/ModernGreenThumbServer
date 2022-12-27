package com.jcpdev.ModernGreenThumb.Controller;

import com.jcpdev.ModernGreenThumb.Model.User;
import com.jcpdev.ModernGreenThumb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class WebController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserDetails")
    public User getUser(@RequestParam String name) throws InterruptedException, ExecutionException {
        return userService.getUserDetails(name);
    }

    @PostMapping("/createUser")
    public String createUser(@RequestBody User user) throws InterruptedException, ExecutionException {
        return userService.saveUserDetails(user);
    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody User user) throws InterruptedException, ExecutionException {
        return userService.updateUserDetails(user);
    }

    @PutMapping("/addtracker")
    public String addTrackerToUser(@RequestBody Map<String,String> json) throws InterruptedException, ExecutionException {
        return userService.addTracker(json);
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam String name) {
        return userService.deleteUser(name);
    }


}
