package com.ezen.springfeed.postLike;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Integer countByPostNum(Long postNum);

    Optional<PostLike> findByPostNumAndUserid(Long postNum, String userid);
}
