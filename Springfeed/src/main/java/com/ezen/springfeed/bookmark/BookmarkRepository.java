package com.ezen.springfeed.bookmark;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    List<Bookmark> getAllByUserid(String userid);

    Optional<Bookmark> getByUseridAndPostNum(String userid, Integer postNum);
}
