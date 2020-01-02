package kr.gudi.blog;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/blog")
public class ViewController {
	
	@Autowired
	BlogService bs;
	
	private String root = "blog/";
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String root() {
		return root + "main";
	}
	
	@RequestMapping(value="/Login", method=RequestMethod.GET)
	public String login() {
		return root + "login";
	}
	
	@RequestMapping(value="/Logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/blog/Login";
	}
	
	@RequestMapping(value="/Message", method=RequestMethod.GET)
	public String message(HttpSession session) {
		if(bs.loginCheck(session)) {
			return root + "message";
		} else {
			return "redirect:/blog/Login";
		}
	}
	
	@RequestMapping(value="/MyEdit", method=RequestMethod.GET)
	public String myEdit(HttpSession session) {
		if(bs.loginCheck(session)) {
			return root + "myEdit";
		} else {
			return "redirect:/blog/Login";
		}
	}
	
	@RequestMapping(value="/MyList", method=RequestMethod.GET)
	public String myList() {
		return root + "myList";
	}
	
	@RequestMapping(value="/Profile", method=RequestMethod.GET)
	public String profile() {
		return root + "profile";
	}
	
	@RequestMapping(value="/SignUp", method=RequestMethod.GET)
	public String signUp() {
		return root + "signUp";
	}

}
