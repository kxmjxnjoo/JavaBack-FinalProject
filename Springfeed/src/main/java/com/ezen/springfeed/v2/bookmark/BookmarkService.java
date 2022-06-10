package com.ezen.springfeed.v2.bookmark;

import com.ezen.springfeed.model.Bookmark;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {
    private final BookmarkRepository br;

    public BookmarkService(BookmarkRepository br) {
        this.br = br;
    }

    public List<Bookmark> getAllBookmarkByUserid(String userid) {
        return br.getAllByUserid(userid);
    }

    public void addBookmark(Bookmark bookmark) {
        br.save(bookmark);
    }

    public void deleteBookmark(String userid, Integer postNum) {
        Bookmark bookmark = br.getByUseridAndPostNum(userid, postNum)
                .orElseThrow(() -> new IllegalStateException(
                        "지워야 할 저장된 포스트가 없어요"
                ));

        br.deleteById(bookmark.getNum());
    }
}
