package com.jcpdev.ModernGreenThumb.Controller;

import com.jcpdev.ModernGreenThumb.Model.User;
import com.jcpdev.ModernGreenThumb.Service.UserService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class WebController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping("/getplanters")
    public JSONObject getUser(@RequestParam String userId) throws InterruptedException, ExecutionException {
        return userService.getPlanters(userId);
    }

    @CrossOrigin
    @PostMapping("/createuser")
    public String createUser(@RequestBody User user) throws InterruptedException, ExecutionException {
        return userService.saveUserDetails(user);
    }

    @CrossOrigin
    @PutMapping("/updateuser")
    public String updateUser(@RequestBody User user) throws InterruptedException, ExecutionException {
        return userService.updateUserDetails(user);
    }

    @PutMapping("/addtracker")
    public String addTrackerToUser(@RequestBody Map<String,String> json, @RequestParam String userId) throws InterruptedException, ExecutionException {
        return userService.addTracker(json,userId);
    }

    @DeleteMapping("/deleteuser")
    public String deleteUser(@RequestParam String name) {
        return userService.deleteUser(name);
    }

}
