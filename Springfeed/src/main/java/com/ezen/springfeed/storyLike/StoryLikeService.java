package com.ezen.springfeed.storyLike;

import org.springframework.stereotype.Service;

@Service
public class StoryLikeService {
    private final StoryLikeRepository slr;

    public StoryLikeService(StoryLikeRepository slr) {
        this.slr = slr;
    }

    public Integer getStoryLikeCount(Long storyNum) {
        return slr.countByStoryNum(storyNum);
    }

    public void addStoryLike(StoryLike storyLike) {
        slr.save(storyLike);
    }

    public void deleteStory(Long storyLikeNum) {
        slr.deleteById(storyLikeNum);
    }
}
