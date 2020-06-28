package pack.controller;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import pack.controller.SqlMapperInter;
import pack.mybatis.SqlMapConfig;

public class ChartMgr {
	private SqlSessionFactory factory = SqlMapConfig.getSqlSession();

	public int countGook() {
		SqlSession sqlSession = factory.openSession();
		int gook = 0;
		try {
			SqlMapperInter inter = sqlSession.getMapper(SqlMapperInter.class);
			gook = inter.countgook();
		} catch (Exception e) {
			System.out.println("selectReview err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return gook;
	}
	public int countKimchi() {
		SqlSession sqlSession = factory.openSession();
		int kimchi = 0;
		try {
			SqlMapperInter inter = sqlSession.getMapper(SqlMapperInter.class);
			kimchi = inter.countkimchi();
		} catch (Exception e) {
			System.out.println("selectReview err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return kimchi;
	}
	public int countMain() {
		SqlSession sqlSession = factory.openSession();
		int main = 0;
		try {
			SqlMapperInter inter = sqlSession.getMapper(SqlMapperInter.class);
			main = inter.countmain();
		} catch (Exception e) {
			System.out.println("selectReview err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return main;
	}
	public int countBanchan() {
		SqlSession sqlSession = factory.openSession();
		int banchan = 0;
		try {
			SqlMapperInter inter = sqlSession.getMapper(SqlMapperInter.class);
			banchan = inter.countbanchan();
		} catch (Exception e) {
			System.out.println("selectReview err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return banchan;
	}
	public int countSallad() {
		SqlSession sqlSession = factory.openSession();
		int sallad = 0;
		try {
			SqlMapperInter inter = sqlSession.getMapper(SqlMapperInter.class);
			sallad = inter.countsallad();
		} catch (Exception e) {
			System.out.println("selectReview err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return sallad;
	}

	public int countMeat() {
		SqlSession sqlSession = factory.openSession();
		int meat = 0;
		try {
			SqlMapperInter inter = sqlSession.getMapper(SqlMapperInter.class);
			meat = inter.countmeat();
		} catch (Exception e) {
			System.out.println("selectReview err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return meat;
	}
	
	public int sumGook() {
		SqlSession sqlSession = factory.openSession();
		int gook = 0;
		try {
			SqlMapperInter inter = sqlSession.getMapper(SqlMapperInter.class);
			gook = inter.sumgook();
		} catch (Exception e) {
			System.out.println("selectReview err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return gook;
	}

	public int sumKimchi() {
		SqlSession sqlSession = factory.openSession();
		int kimchi = 0;
		try {
			SqlMapperInter inter = sqlSession.getMapper(SqlMapperInter.class);
			kimchi = inter.sumkimchi();
		} catch (Exception e) {
			System.out.println("selectReview err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return kimchi;
	}
	public int sumMain() {
		SqlSession sqlSession = factory.openSession();
		int main = 0;
		try {
			SqlMapperInter inter = sqlSession.getMapper(SqlMapperInter.class);
			main = inter.summain();
		} catch (Exception e) {
			System.out.println("selectReview err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return main;
	}
	public int sumBanchan() {
		SqlSession sqlSession = factory.openSession();
		int banchan = 0;
		try {
			SqlMapperInter inter = sqlSession.getMapper(SqlMapperInter.class);
			banchan = inter.sumbanchan();
		} catch (Exception e) {
			System.out.println("selectReview err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return banchan;
	}
	public int sumSallad() {
		SqlSession sqlSession = factory.openSession();
		int sallad = 0;
		try {
			SqlMapperInter inter = sqlSession.getMapper(SqlMapperInter.class);
			sallad = inter.sumsallad();
		} catch (Exception e) {
			System.out.println("selectReview err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return sallad;
	}
	public int sumMeat() {
		SqlSession sqlSession = factory.openSession();
		int meat = 0;
		try {
			SqlMapperInter inter = sqlSession.getMapper(SqlMapperInter.class);
			meat = inter.summeat();
		} catch (Exception e) {
			System.out.println("selectReview err : " + e);
			sqlSession.rollback();
		} finally {
			if(sqlSession != null) sqlSession.close();
		}
		return meat;
	}
}
