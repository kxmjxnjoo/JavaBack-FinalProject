package com.ezen.springfeed.v2.faq;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/faq")
public class FaqController {
    private final FaqService fs;

    public FaqController(FaqService fs) {
        this.fs = fs;
    }

    @GetMapping
    public List<Faq> getAllFaq() {
        return fs.getAllFaq();
    }

    @GetMapping("{id}")
    public Faq getFaqById(@RequestParam("id") Long id) {
        return fs.selectFaqById(id);
    }

    @PostMapping
    public void addFaq(@RequestBody Faq faq) {
        fs.addFaq(faq);
    }

    @PutMapping("{id}")
    public void updateFaq(@RequestBody Faq faq, @RequestParam("id") Long id) {
        fs.updateFaq(faq, id);
    }

    @DeleteMapping("{id}")
    public void deleteFaq(@PathVariable("id") Long num) {
        fs.deleteFaq(num);
    }
}
