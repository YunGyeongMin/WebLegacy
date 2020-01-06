package kr.gudi.blog;

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
			return sqlSession.selectOne("blog.getUserImg");
		}
		return -1;
	}
	
	public int setUserImg(Map<String, Object> param) {
		return sqlSession.update("blog.setUserImg", param);
	}
	
	public Map<String, Object> getFile(String no) {
		return sqlSession.selectOne("blog.getFile", no);
	}
	
}
