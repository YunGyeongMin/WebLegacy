package kr.gudi.blog;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.gudi.util.HttpUtil;

@Controller
@RequestMapping("/blog")
public class ViewController {
	
	@Autowired private HttpUtil util;
	private String root = "blog/";
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String root() {
		return root.concat("main");
	}
	
	@RequestMapping(value="/Login", method=RequestMethod.GET)
	public String login() {
		return root.concat("login");
	}
	
	@RequestMapping(value="/Logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/blog/Login";
	}
	
	@RequestMapping(value="/Message", method=RequestMethod.GET)
	public String message(HttpSession session) {
		if(util.loginCheck(session)) {
			return root.concat("message");
		} else {
			return "redirect:/blog/Login";
		}
	}
	
	@RequestMapping(value="/MyEdit", method=RequestMethod.GET)
	public String myEdit(HttpSession session) {
		if(util.loginCheck(session)) {
			return root.concat("myEdit");
		} else {
			return "redirect:/blog/Login";
		}
	}
	
	@RequestMapping(value="/MyList", method=RequestMethod.GET)
	public String myList() {
		return root.concat("myList");
	}
	
	@RequestMapping(value="/Profile", method=RequestMethod.GET)
	public String profile() {
		return root.concat("profile");
	}
	
	@RequestMapping(value="/SignUp", method=RequestMethod.GET)
	public String signUp() {
		return root.concat("signUp");
	}

}
