package com.ezen.springfeed.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByFollowedOrderByFollowDate(String userid);

    List<Follow> findAllByFollowingOrderByFollowDate(String userid);

    Optional<Follow> findByFollowerAndFollowing(String follower, String following);

    void deleteByFollowerAndFollowing(String follower, String following);
}
