package kr.gudi.web.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping("/error/{code}")
	public String error1(@PathVariable("code") String code, Model model)  {
		System.out.println(code);
		model.addAttribute("code", code);
		return "error";
	}
	
	@RequestMapping("/error")
	public String error2(Model model, HttpServletRequest req)  {
		String code = req.getAttribute("javax.servlet.error.status_code").toString();
		switch (code) {
		case "404":
		case "405":
		case "500":
			model.addAttribute("message", "페이지 오류 발생!<br>관리자에서 요청하세요.");
			model.addAttribute("page", req.getAttribute("javax.servlet.error.request_uri"));
			return "error";
		default:
			return "redirect:/";
		}
	}
	
}
