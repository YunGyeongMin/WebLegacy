package kr.gudi.blog;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDao {

	@Autowired private SqlSession sqlSession;
	
	public int userCheck(Map<String, Object> param) {
		return sqlSession.selectOne("blog.userCheck", param);
	}
	
	public int signUp(Map<String, Object> param) {
		return sqlSession.insert("blog.signUp", param);
	}
	
	public Map<String, Object> login(Map<String, Object> paramMap) {
		return sqlSession.selectOne("blog.login", paramMap);
	}
	
	public int userUpdate(Map<String, Object> param) {
		return sqlSession.update("blog.userUpdate", param);
	}
	
	public int fileUpload(Map<String, Object> param) {
		if(sqlSession.insert("blog.fileUpload", param) > 0) {
			return (int) param.get("userImg");
		}
		return -1;
	}
	
	public int setUserImg(Map<String, Object> param) {
		return sqlSession.update("blog.setUserImg", param);
	}
	
	public Map<String, Object> getFile(String no) {
		return sqlSession.selectOne("blog.getFile", no);
	}
	
	public int setMessage(Map<String, Object> param) {
		return sqlSession.insert("blog.setMessage", param);
	}
	
	public List<Map<String, Object>> getMessage(int index) {
		return sqlSession.selectList("blog.getMessage", index);
	}
	
	public int getPagingCnt() {
		return sqlSession.selectOne("blog.getPagingCnt");
	}
	
}
