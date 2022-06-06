package com.ezen.springfeed.follow;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowService {
    private final FollowRepository fr;

    public FollowService(FollowRepository fr) {
        this.fr = fr;
    }

    public List<Follow> getAllFollowerByUserid(String userid) {
        return fr.findAllByFollowerOrderByFollowDate(userid);
    }

    public List<Follow> getAllFollowingByUserid(String userid) {
        return fr.findAllByFollowerOrderByFollowDate(userid);
    }

    public boolean getIsFollowing(String follower, String following) {
        Optional<Follow> follow = fr.findByFollowerAndFollowed(follower, following);

        return follow.isPresent();
    }

    public void addFollow(Follow follow) {
        fr.save(follow);
    }

    public void deleteFollow(String follower, String following) {
        Optional<Follow> follow = fr.findByFollowerAndFollowed(follower, following);

        if(!follow.isPresent()) {
            throw new IllegalStateException("언팔로우 할 수 없어요");
        }

        fr.deleteByFollowerAndFollowed(follower, following);
    }
}
