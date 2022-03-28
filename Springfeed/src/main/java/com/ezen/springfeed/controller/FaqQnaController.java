package com.ezen.springfeed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ezen.springfeed.service.FaqQnaService;

@Controller
public class FaqQnaController {

	@Autowired
	FaqQnaService fas;
}