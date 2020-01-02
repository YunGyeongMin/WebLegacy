package kr.gudi.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping("/error/{code}")
	public String error(@PathVariable("code") String code, 
//						HttpServletResponse res
						Model model)  {
		System.out.println(code);
//		res.getWriter().println("Error Code : " + code);
		model.addAttribute("code", code);
		return "error";
	}
	
}
