package pack.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import pack.model.ReviewBean;

@Repository
public class ReviewDaoImpl extends SqlSessionDaoSupport implements ReviewDaoInter {

	@Autowired
	public ReviewDaoImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}

	// 리뷰 목록보기
	@Override
	public List<ReviewDto> getdataPart(String menu_no) throws DataAccessException {
		return getSqlSession().selectList("selectPart", menu_no);
	}

	// 추가시 메뉴 보기
	@Override
	public DishDto selectPart(String menu_no) {
		return getSqlSession().selectOne("selectPartReview1", menu_no);
	}

	// 리뷰 추가
	@Override
	public int insert(ReviewBean bean) throws DataAccessException {
		return getSqlSession().insert("insertData", bean);

	}

	// 수정시 메뉴 보기
	@Override
	public ReviewDto selectPartup(String review_no) {
		return getSqlSession().selectOne("selectPartReview2", review_no);
	}
	
	// 리뷰 수정
	@Override
	public int update(ReviewBean bean) {
		return getSqlSession().update("updateData", bean);
	}


	// 전체목록
	@Override
	public List<ReviewDto> selectAll() {
		return getSqlSession().selectList("selectDataAll");
	}
	
	@Override
	public boolean deleteReview(String review_no) {
		try {
			getSqlSession().delete("deleteReview",review_no);
			return true;
		} catch (Exception e) {
			System.out.println("deleteReview Err : " + e);
			return false;
		}		
	}
}
