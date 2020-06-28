package pack.mybatis;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import pack.controller.SqlMapperInter;

public class SqlMapConfig {
	public static SqlSessionFactory sqlSession;		//sql 사용 시 필요 메소드를 가짐
	
	static {
		String source = "pack/mybatis/Configuration.xml";
		
		try {
			Reader reader = Resources.getResourceAsReader(source);
			sqlSession = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
			
			//MyBatis Annotation을 사용할 경우 추가 코드
			Class[] mappers = {SqlMapperInter.class};
			for(Class m:mappers) {
				//Mapper 등록
				sqlSession.getConfiguration().addMapper(m);
			}
		} catch (Exception e) {
			System.out.println("SqlMapConfig err : " + e);
		}
	}
	
	public static SqlSessionFactory getSqlSession() {
		return sqlSession;
	}
}
