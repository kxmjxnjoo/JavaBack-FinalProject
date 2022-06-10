package com.ezen.springfeed.v2.notification;

import com.ezen.springfeed.model.Notification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/notification")
public class NotificationController {

    private final NotificationService ns;

    public NotificationController(NotificationService ns) {
        this.ns = ns;
    }

    @GetMapping("/{userid}")
    public List<Notification> getNotificationsByUserid(@PathVariable("userid") String userid) {
        return ns.getNotificationsByUserTo(userid);
    }

    @PostMapping
    public void addNotification(@RequestBody Notification noti) {
        ns.addNotification(noti);
    }
}