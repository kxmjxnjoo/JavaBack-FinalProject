package com.ezen.springfeed.v2.post;

import com.ezen.springfeed.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findAllByUseridOrderByCreateDate(String userid);
    List<Post> findAllByOrderByCreateDate();
    List<Post> findAllByOrderByLikeCount();
}