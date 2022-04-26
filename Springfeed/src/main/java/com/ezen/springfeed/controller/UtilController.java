package com.ezen.springfeed.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ezen.springfeed.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.springfeed.service.UtilService;
import org.springframework.web.util.HtmlUtils;

@Controller
public class UtilController {

	@Autowired
	UtilService us;

	
	
	// Message
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public MessageDto greeting(MessageDto message) throws Exception {
		Thread.sleep(1000);
		MessageDto mdto = new MessageDto();
		mdto.setContent("안녕 나 메세지야 " + HtmlUtils.htmlEscape(message.getMessageFrom()));
		mdto.setMessageFrom("jinkpark");
		mdto.setMessageTo("jinkpark");
		return mdto;
	}
}