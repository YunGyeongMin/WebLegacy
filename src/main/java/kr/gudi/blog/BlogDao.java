package kr.gudi.blog;

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
	
}
