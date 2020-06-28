package pack.model;


import java.util.List;

import org.springframework.dao.DataAccessException;

import pack.model.ReviewBean;

public interface ReviewDaoInter {
	List<ReviewDto> getdataPart(String menu_no) throws DataAccessException;
	List<ReviewDto> selectAll();
	
	DishDto selectPart(String menu_no);
	ReviewDto selectPartup(String menu_no);
	
	public int insert(ReviewBean bean);
	public int update(ReviewBean bean);
	
	boolean deleteReview(String review_no);
}
