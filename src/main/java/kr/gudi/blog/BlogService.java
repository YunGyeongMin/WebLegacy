package kr.gudi.blog;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BlogService {

	@Autowired BlogDao bd;
	private String resultCode;
	private Map<String, Object> paramMap;
	private Map<String, Object> resultMap;
	
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
		return bd.userUpdate(paramMap);
	}
	
	public int fileUpload(MultipartFile[] files) throws Exception {
		
		String root = "D:/GDJ21/IDE/workspace/WebLegacy/src/main/webapp/resources/files/";
		for(MultipartFile file : files) {
			UUID id = UUID.randomUUID();
			String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
			String path = root + file.getOriginalFilename();
			path = root + id + ext;
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path));	
			
			paramMap = new HashMap<String, Object>();
			paramMap.put("fileName", id + ext);
			paramMap.put("originName", file.getOriginalFilename());
			int status = bd.fileUpload(paramMap);
			System.out.println(file.getOriginalFilename() + " : " + status);
		}		
		
		return 1;
	}

}
