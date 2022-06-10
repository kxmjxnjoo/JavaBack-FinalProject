package com.ezen.springfeed.v2.bookmark;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/bookmark")
public class BookmarkController {
    private final BookmarkService bs;

    public BookmarkController(BookmarkService bs) {
        this.bs = bs;
    }

    @GetMapping("/{userid}")
    public List<Bookmark> getBookmark(@PathVariable("userid") String userid) {
        return bs.getAllBookmarkByUserid(userid);
    }

    @PostMapping
    public void addBookmark(@RequestBody Bookmark bookmark) {
        bs.addBookmark(bookmark);
    }

    @DeleteMapping("/{userid}/{postnum}")
    public void deleteBookmark(@PathVariable("userid") String userid, @PathVariable("postnum") Integer postNum) {
        bs.deleteBookmark(userid, postNum);
    }
}
