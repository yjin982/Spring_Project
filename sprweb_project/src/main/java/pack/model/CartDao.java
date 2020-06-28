package pack.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDao extends SqlSessionDaoSupport implements CartInter{
	@Autowired
	public CartDao(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}//db연결 생성자 주입
	
	/////1. 카트 가져오기
	public List<CartDto> cartlist(String num){
		List<CartDto> list = getSqlSession().selectList("selectCart", num);
		return list;
	}
	
	
	/////2. 카트 추가
	public void insertCart(CartBean bean) {
		getSqlSession().insert("addCart", bean);
	}
	public CartDto checkCart(CartBean bean) {//2-1. 카트 중복 체크
		return getSqlSession().selectOne("check", bean);
	}
	public void updateCart(CartBean bean) {//2-2. 카트 수정
		getSqlSession().update("updateCnt", bean);
	}
	
	
	/////3. 카트 삭제
	public void deleteCartItem(CartBean bean) {
		getSqlSession().delete("deleteCartItem", bean);
	}
	
	
	/////4. 결제후 카트 전체 삭제
	public void deleteAllCart(String id) {
		getSqlSession().delete("deleteAllCart", id);
	}
	
	
	/////5. 메뉴 정보
	public DishDto getInfo(String num) {
		return getSqlSession().selectOne("selectMenuInfo", num);
	}
	
	
	/////6. 결제
	public void confirmOrder(String id) { //order에 추가
		getSqlSession().insert("insertOrder", id);
	}
	public void insertDetail(String id) {//6-1. order_detail에 추가
		getSqlSession().insert("insertOrderDetail", id);
	}
	public String getMaxNo() {//order_detail에 추가하기 위해 order 최근 번호 가져오기
		return getSqlSession().selectOne("getMaxOrderNo");
	}

}
