package com.jcpdev.ModernGreenThumb.Controller;

import com.jcpdev.ModernGreenThumb.Model.User;
import com.jcpdev.ModernGreenThumb.Service.MoistureTrackerService;
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
    @Autowired
    private MoistureTrackerService moistureTrackerService;

    @CrossOrigin
    @GetMapping("/getplanters")
    public JSONObject getUser(@RequestParam String userId) throws InterruptedException, ExecutionException {
        return moistureTrackerService.getPlanters(userId);
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
    @CrossOrigin
    @PutMapping("/addtracker")
    public String addTrackerToUser(@RequestBody Map<String,String> json, @RequestParam String userId) throws InterruptedException, ExecutionException {
        return moistureTrackerService.addTracker(json,userId);
    }
    @CrossOrigin
    @PutMapping("/updatetracker")
    public String updateTracker(@RequestBody Map<String,String> json, @RequestParam String userId) throws InterruptedException, ExecutionException {
        return moistureTrackerService.updateTrackerValues(json,userId);
    }
    @CrossOrigin
    @PostMapping("/updatetrackerimage")
    public String updateTrackerImage(@RequestBody Map<String,String> json, @RequestParam String userId) throws InterruptedException, ExecutionException {
        return moistureTrackerService.updateTrackerImage(json,userId);
    }

    @CrossOrigin
    @DeleteMapping("/deleteuser")
    public String deleteUser(@RequestParam String name) {
        return userService.deleteUser(name);
    }

}
