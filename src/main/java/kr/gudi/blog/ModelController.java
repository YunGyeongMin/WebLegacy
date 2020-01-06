package kr.gudi.blog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	
	@RequestMapping(value="/UserUpdate", method=RequestMethod.POST)
	public void userUpdate(HttpSession session, HttpServletRequest req, HttpServletResponse res) throws Exception {
		int result = -1;
		if(util.loginCheck(session)) result = bs.userUpdate(session, util.getParam(req));
		System.out.println(result);
		util.sendViewData(res, result + "");
	}
	
	@RequestMapping(value="/UserImage", method=RequestMethod.POST)
	public void userImage(HttpSession session, HttpServletRequest req, HttpServletResponse res, @RequestParam("file") MultipartFile[] files) throws Exception {
		int status = bs.fileUpload(files);
		System.out.println(status);
	}
	
}
