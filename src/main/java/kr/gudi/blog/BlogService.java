package kr.gudi.blog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BlogService {

	@Autowired BlogDao bd;
	private String resultCode;
	private Map<String, Object> paramMap;
	private Map<String, Object> resultMap;
	private final String ROOT = "D:/GDJ21/IDE/workspace/WebLegacy/src/main/webapp/resources/files/";
	
	public int signUp(Map<String, Object> paramMap) {
		if(bd.userCheck(paramMap) > 0) return 0;
		else		  				   return bd.signUp(paramMap);
	}
	
	public String login(HttpSession session, Map<String, Object> paramMap) throws Exception {
		resultMap = bd.login(paramMap);		
		if(resultMap == null) {
			resultCode = "0";
		} else {
			session.setAttribute("user", resultMap);
			resultCode = "1";
		}		
		return resultCode;
	}
	
	public int userUpdate(HttpSession session, Map<String, Object> paramMap) {
		Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("user");
		paramMap.put("no", userMap.get("no"));
		
		userMap.put("interests", paramMap.get("interests"));
		session.setAttribute("user", userMap);
		
		return bd.userUpdate(paramMap);
	}
	
	public int fileUpload(MultipartFile file, HttpSession session) throws Exception {
		
		UUID id = UUID.randomUUID();
		String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
		String path = ROOT + id + ext;
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path));	
		
		paramMap = new HashMap<String, Object>();
		paramMap.put("fileName", id + ext);
		paramMap.put("originName", file.getOriginalFilename());
		int userImg = bd.fileUpload(paramMap);
		
		Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("user");
		paramMap = new HashMap<String, Object>();
		paramMap.put("userImg", userImg);
		paramMap.put("no", userMap.get("no"));
		int status = bd.setUserImg(paramMap);
		
		userMap.put("img", userImg);
		session.setAttribute("user", userMap);
		
		return status;
	}
	
	public void getFile(HttpServletResponse res, String no) {
		try {
			Map<String, Object> resultMap = bd.getFile(no);
			if(resultMap != null) {
				String path = ROOT + resultMap.get("fileName");
				res.setHeader("Content-Disposition", "inline; filename=\"" + resultMap.get("originName") + "\"");
				FileUtils.copyFile(new File(path), res.getOutputStream());
				return;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			res.sendRedirect("/resources/img/man.png");
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		
	}
	
	public int setMessage(HttpSession session, Map<String, Object> paramMap) {
		Map<String, Object> userMap = (Map<String, Object>) session.getAttribute("user");
		paramMap.put("no", userMap.get("no"));
		
		String comment = paramMap.get("comment").toString();
		comment = comment.replace("\n", "<br>");
		paramMap.put("comment", comment);
		
		return bd.setMessage(paramMap);
	}
	
	public int getMessage(Map<String, Object> paramMap, Model model) {
		Object paging = paramMap.get("paging");
		System.out.println(paging);
		int index = 0;
		if(paging == null) {
			paging = 1;
		} else {
			index = (Integer.parseInt(paging.toString()) - 1) * 3;
			if(Integer.parseInt(paging.toString()) % 5 != 1) {
				double a = Integer.parseInt(paging.toString());
				paging = ((int) Math.ceil(a / 5) * 5) - 4;
			}
		}
		model.addAttribute("rows", bd.getMessage(index));
		model.addAttribute("size", bd.getPagingCnt());
		model.addAttribute("point", paging);
		return 1;
	}

}
