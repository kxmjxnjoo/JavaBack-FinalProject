package com.ezen.springfeed.storyLike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryLikeRepository extends JpaRepository<StoryLike, Long> {
    Integer countByStoryNum(Long storyNum);
}
