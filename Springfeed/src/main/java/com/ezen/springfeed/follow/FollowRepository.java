package com.ezen.springfeed.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByFollowedOrderByFollowDate(String userid);

    List<Follow> findAllByFollowerOrderByFollowDate(String userid);

    Optional<Follow> findByFollowerAndFollowed(String follower, String following);

    void deleteByFollowerAndFollowed(String follower, String following);
}
