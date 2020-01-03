package kr.gudi.blog;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BlogDao {

	@Autowired
	SqlSession sqlSession;
	
	public void test() {
		int result = sqlSession.selectOne("sql.test");
		System.out.println(result);
	}
	
	public int signUp(Map<String, Object> param) {
		return sqlSession.insert("sql.signUp", param);
	}
	
	public Map<String, Object> login(UserBean ub) {
		return sqlSession.selectOne("sql.login", ub);
	}
	
}
