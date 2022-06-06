package com.ezen.springfeed.story;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StoryService {
    private final StoryRepository sr;

    public StoryService(StoryRepository sr) {
        this.sr = sr;
    }

    public Story getStoryByStoryNum(Long storyNum) {
        return sr.findById(storyNum)
                .orElseThrow(() -> new IllegalStateException(
                        "해당 스토리가 존재하지 않아요"
                ));
    }

    public Story getStoryByUserid(String userid) {
        return sr.findStoriesByUseridOrderByCreateDateDesc(userid);
    }

    public List<Story> getAllStoryByUserid(String userid) {
        return sr.findAllStoryByUseridOrderByCreateDateDesc(userid);
    }

    public void addStory(Story story) {
        sr.save(story);
    }

    @Transactional
    public void editStory(Story editedStory) {
        Story story = sr.findById(editedStory.getStoryNum())
                .orElseThrow(() -> new IllegalStateException(
                        "수정할 스토리가 없어요"
                ));

        if(editedStory.getStoryImg() != null) {
            story.setStoryImg(editedStory.getStoryImg());
        }

        if(editedStory.getStoryContent() != null) {
            story.setStoryContent(editedStory.getStoryContent());
        }
    }

    public void deleteStory(Long storyNum) {
        sr.deleteById(storyNum);
    }
}
