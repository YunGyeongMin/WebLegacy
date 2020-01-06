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
	
}
