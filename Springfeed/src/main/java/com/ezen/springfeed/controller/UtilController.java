package com.ezen.springfeed.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ezen.springfeed.dto.MessageDto;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

//	@RequestMapping("/images/{name}", produces = MediaType.IMAGE_PNG_VALUE)
//	public byte[] getImage(@PathVariable("name") String name) throws IOException {
//		InputStream in = getClass().getResourceAsStream("/images/" + name.replaceAll("\\s+", ""));
//
//		if(in == null) {
//			in = getClass().getResourceAsStream("/images/noimg.png");
//		}
//
//		return IOUtils.toByteArray(in);
//	}

}