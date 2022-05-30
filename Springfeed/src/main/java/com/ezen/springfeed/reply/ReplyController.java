package com.ezen.springfeed.reply;

import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/reply")
public class ReplyController {
    private final ReplyService rs;

    public ReplyController(ReplyService rs) {
        this.rs = rs;
    }

    @GetMapping("/{postnum}")
    public List<Reply> getAllReplyByPostnum(@PathVariable("postnum") Long postnum) {
        return rs.getAllReplyByPostnum(postnum);
    }

    @PostMapping
    public void addReply(@RequestBody Reply reply) {
        rs.addReply(reply);
    }

    @PutMapping
    public void editReply(@RequestBody Reply reply) {
        rs.editReply(reply);
    }

    @DeleteMapping
    public void deleteReply(Long replyNum) {
        rs.deleteReply(replyNum);
    }
}
