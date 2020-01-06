package kr.gudi.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;

@Component
public class HttpUtil {

	public Map<String, Object> getParam(HttpServletRequest req) throws Exception{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		req.setCharacterEncoding("UTF-8");
		Enumeration<?> enume = req.getParameterNames();
		while (enume.hasMoreElements()) {
		       String paramName = enume.nextElement().toString();
		       String paramValue = req.getParameter(paramName);
		       paramMap.put(paramName, paramValue);
		}
		return paramMap;
	}
	
	public boolean loginCheck(HttpSession session) {
		Object user = session.getAttribute("user");
		if(user == null) return false;
		else 			 return true;
	}
	
	public void sendViewData(HttpServletResponse res, String code) throws Exception {
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/text; charset=utf-8");
		res.getWriter().write(code);
	}
	
}
