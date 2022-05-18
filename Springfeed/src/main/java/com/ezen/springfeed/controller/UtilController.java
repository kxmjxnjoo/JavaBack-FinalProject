package com.ezen.springfeed.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.ezen.springfeed.service.UtilService;

@CrossOrigin(origins="http://springfeed.jinkyumpark.com")
@Controller
public class UtilController {

	@Autowired
	UtilService us;

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