package kr.gudi.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController {
	
	@RequestMapping("/blog/main")
	public String main() {
		return "blog/main";
	}

}
