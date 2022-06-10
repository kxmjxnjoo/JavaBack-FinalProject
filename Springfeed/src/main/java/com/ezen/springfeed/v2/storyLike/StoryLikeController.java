package com.ezen.springfeed.v2.storyLike;

import com.ezen.springfeed.model.StoryLike;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/story/like")
public class StoryLikeController {
    private final StoryLikeService sls;

    public StoryLikeController(StoryLikeService sls) {
        this.sls = sls;
    }

    @GetMapping("/{storynum}/count")
    public Integer getStoryLikeCount(@PathVariable("storynum") Long storyNum) {
        return sls.getStoryLikeCount(storyNum);
    }

    @PostMapping
    public void addStoryLike(@RequestBody StoryLike storyLike) {
        sls.addStoryLike(storyLike);
    }

    @DeleteMapping("{storylikenum}")
    public void deleteStoryLike(@PathVariable("storylikenum") Long storyLikeNum) {
        sls.deleteStory(storyLikeNum);
    }
}
