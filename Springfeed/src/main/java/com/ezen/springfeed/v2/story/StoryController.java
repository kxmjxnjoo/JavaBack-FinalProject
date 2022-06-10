package com.ezen.springfeed.v2.story;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/story")
public class StoryController {
    private final StoryService ss;

    public StoryController(StoryService ss) {
        this.ss = ss;
    }

    @GetMapping("{storynum}")
    public Story getStoryByStoryNum(@PathVariable("storynum") Long storyNum) {
        return ss.getStoryByStoryNum(storyNum);
    }

    @GetMapping("{userid}")
    public Story getStoryByUserid(@PathVariable("userid") String userid) {
        return ss.getStoryByUserid(userid);
    }

    @GetMapping("{userid}/all")
    public List<Story> getStoriesByUserid(@PathVariable("userid") String userid) {
        return ss.getAllStoryByUserid(userid);
    }

    @PostMapping
    public void addStory(@RequestBody Story story) {
        ss.addStory(story);
    }

    @PutMapping
    public void editStory(@RequestBody Story editedStory) {
        ss.editStory(editedStory);
    }

    @DeleteMapping("{storynum}")
    public void deleteStory(@PathVariable("storynum") Long storyNum) {
        ss.deleteStory(storyNum);
    }
}
