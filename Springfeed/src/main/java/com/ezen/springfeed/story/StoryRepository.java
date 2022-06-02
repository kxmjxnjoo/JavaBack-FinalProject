package com.ezen.springfeed.story;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
    Story findStoryByUseridOrderByCreateDateDesc();

    List<Story> findAllStoryByUseridOrderByCreateDateDesc(String userid);
}
