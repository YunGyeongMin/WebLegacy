package kr.gudi.blog;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.gudi.util.HttpUtil;

@Controller
@RequestMapping(value="/blog")
public class ModelController {
	
	@Autowired private BlogService bs;
	@Autowired private HttpUtil util;
	private String resultCode;

	@RequestMapping(value="/SignUp", method=RequestMethod.POST)
	public String signUp(HttpSession session, HttpServletRequest req, HttpServletResponse res) throws Exception {
		int status = bs.signUp(util.getParam(req));
		if(status > 0) return "redirect:/blog/Login";
		else       	   return "redirect:/blog/SignUp";
	}
	
	@RequestMapping(value="/Login", method=RequestMethod.POST)
	public void login(HttpSession session, HttpServletRequest req, HttpServletResponse res) throws Exception {
		util.sendViewData(res, bs.login(session, util.getParam(req)));		
	}
	
	@RequestMapping(value="/Logout", method=RequestMethod.POST)
	public void logout(HttpSession session, HttpServletResponse res) throws Exception {
		session.invalidate();
		util.sendViewData(res, "1");
	}
	
	@RequestMapping(value="/LoginCheck", method=RequestMethod.POST)
	public void loginCheck(HttpSession session, HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(util.loginCheck(session)) resultCode = "1"; 
		else 						 resultCode = "0";
		util.sendViewData(res, resultCode);
	}
	
}
