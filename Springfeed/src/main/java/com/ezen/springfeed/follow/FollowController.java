package com.ezen.springfeed.follow;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/follow")
public class FollowController {
    private final FollowService fs;

    public FollowController(FollowService fs) {
        this.fs = fs;
    }

    @GetMapping("/{userid}/follower")
    public List<Follow> getFollowerByUserid(@PathVariable("userid") String userid) {
        return fs.getAllFollowerByUserid(userid);
    }

    @GetMapping("/{userid}/following")
    public List<Follow> getFollowingByUserid(@PathVariable("userid") String userid) {
        return fs.getAllFollowingByUserid(userid);
    }

    @GetMapping
    public boolean getIsFollowing(@RequestParam("follower") String follower,
                                  @RequestParam("following") String following) {
        return fs.getIsFollowing(follower, following);
    }

    @PostMapping
    public void addFollow(@RequestBody Follow follow) {
        fs.addFollow(follow);
    }

    @DeleteMapping
    public void deleteFollow(@RequestParam("follower") String follower, @RequestParam("following") String following) {
        fs.deleteFollow(follower, following);
    }
}
