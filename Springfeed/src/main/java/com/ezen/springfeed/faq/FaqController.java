package com.ezen.springfeed.faq;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/faq")
public class FaqController {
    private final FaqService fs;

    public FaqController(FaqService fs) {
        this.fs = fs;
    }

    @GetMapping
    public List<Faq> getAllFaq() {
        return fs.getAllFaq();
    }

    @PostMapping
    public void addFaq(@RequestBody Faq faq) {
        fs.addFaq(faq);
    }

    @PutMapping
    public void updateFaq(@RequestBody Faq faq) {
        fs.updateFaq(faq);
    }

    @DeleteMapping("/{num}")
    public void deleteFaq(@PathVariable("num") Long num) {
        fs.deleteFaq(num);
    }
}
