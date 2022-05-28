package com.ezen.springfeed.notification;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/notification")
public class NotificationController {

    private final NotificationService ns;

    public NotificationController(NotificationService ns) {
        this.ns = ns;
    }

    @GetMapping("{userid}")
    public List<Notification> getNotificationsByUserid(@PathVariable("userid") String userid) {
        return ns.getNotificationsByUserid(userid);
    }

    @PostMapping
    public void addNotification(@RequestBody Notification noti) {
        ns.addNotification(noti);
    }
}