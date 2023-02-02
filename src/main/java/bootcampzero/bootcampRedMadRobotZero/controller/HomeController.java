package bootcampzero.bootcampRedMadRobotZero.controller;

import bootcampzero.bootcampRedMadRobotZero.dto.ItemDto;
import bootcampzero.bootcampRedMadRobotZero.dto.UserDto;
import bootcampzero.bootcampRedMadRobotZero.models.Item;
import bootcampzero.bootcampRedMadRobotZero.models.User;
import bootcampzero.bootcampRedMadRobotZero.service.ItemFileUploadService;
import bootcampzero.bootcampRedMadRobotZero.service.ItemService;
import bootcampzero.bootcampRedMadRobotZero.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemFileUploadService itemFileUploadService;

    @GetMapping(value = "/")
    public String homePage(Model model) {
        model.addAttribute("item", itemService.getItems());
        return "home";
    }

    @GetMapping(value = "/enter")
    public String enterPage() {
        return "enter";
    }

    @GetMapping(value = "/403")
    public String deniedPage() {
        return "403";
    }

    @GetMapping(value = "/signup")
    public String signUpPage() {
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signUp(UserDto userDto) {
        if (userService.registerUser(userDto)) {
            return "redirect:/signup?success";
        }
        return "redirect:/signup?error";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage() {
        return "profile";
    }

    @PostMapping(value = "/viewpic/{picToken}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public @ResponseBody byte[] viewPic(@PathVariable(name = "picToken") String token) throws IOException {
        return itemFileUploadService.getItemAvatar(token);
    }

    @PostMapping(value = "/addItem")
    public String addItem(@RequestParam(name = "image_pic")MultipartFile multipartFile, Item item) {
        itemFileUploadService.uploadItemAvatar(multipartFile, item);
        itemService.addItem(item);
        return "redirect:/";
    }





}
