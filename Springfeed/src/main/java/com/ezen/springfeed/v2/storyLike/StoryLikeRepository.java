package com.ezen.springfeed.v2.storyLike;

import com.ezen.springfeed.model.StoryLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryLikeRepository extends JpaRepository<StoryLike, Long> {
    Integer countByStoryNum(Long storyNum);
}
