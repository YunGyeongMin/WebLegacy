package kr.gudi.blog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/blog")
public class ModelController {
	
	@Autowired
	BlogService bs;

	@RequestMapping(value="/SignUp", method=RequestMethod.POST)
	public String signUp(HttpSession session, HttpServletRequest req, HttpServletResponse res) throws Exception {
		boolean status = bs.signUp(session, req, res);
		if(status) return "redirect:/blog/Login";
		else       return "redirect:/blog/SignUp";
	}
	
	@RequestMapping(value="/Login", method=RequestMethod.POST)
	public void login(HttpSession session, HttpServletRequest req, HttpServletResponse res) throws Exception {
		bs.login(session, req, res);
	}
	
	@RequestMapping(value="/Logout", method=RequestMethod.POST)
	public void logout(HttpSession session, HttpServletResponse res) throws Exception {
		session.invalidate();
		res.getWriter().print("1");
	}
	
	@RequestMapping(value="/LoginCheck", method=RequestMethod.POST)
	public void loginCheck(HttpSession session, HttpServletRequest req, HttpServletResponse res) throws Exception {
		req.setCharacterEncoding("UTF-8");
		Object id = session.getAttribute("id");
		if(id == null) res.getWriter().print("0");
		else           res.getWriter().print("1");
	}
	
}
