package com.ezen.springfeed.v2.story;

import com.ezen.springfeed.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
    Story findStoriesByUseridOrderByCreateDateDesc(String userid);

    List<Story> findAllStoryByUseridOrderByCreateDateDesc(String userid);
}
