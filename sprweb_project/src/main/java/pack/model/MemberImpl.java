package pack.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class MemberImpl extends SqlSessionDaoSupport implements MemberInter {

	@Autowired
	public MemberImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}

	@Override
	public MemberDto getLoginInfo(String login_id) {
		return getSqlSession().selectOne("selectLogin", login_id);
	}

	@Override
	public int joinMember(MemberBean bean) {
		return getSqlSession().insert("joinMember", bean);
	}
	@Override
	public int joinIdCheck(String join_id){
		return getSqlSession().selectOne("joinIdCheck", join_id);
	}
	
	@Override
	public MemberDto getUpdateInfo(String myinfoupdate_id) {
		return getSqlSession().selectOne("selectMy", myinfoupdate_id);
	}
	@Override
	public int updateMember(MemberBean bean) {
		return getSqlSession().update("updateMember", bean);
	}
	@Override
	public int updateMember2(MemberBean bean) {
		return getSqlSession().update("updateMember2", bean);
	}
	@Override
	public int deleteMember(String login_id) {
		return getSqlSession().delete("deleteMember", login_id);
	}
	@Override
	public List<OrderDto> myOrderList(String login_id) {
		return getSqlSession().selectList("myOrderList", login_id);
	}
	
	@Override
	public List<OrderDto> myOrderList2(String member_id) {
		return getSqlSession().selectList("myOrderList", member_id);
	}
	@Override
	public List<DishDto> myOrderDetail(String order_no) {
		return getSqlSession().selectList("myOrderDetail", order_no);
	}
	@Override
	public List<ReviewDto> myreview(String login_id) {
		return getSqlSession().selectList("myreview", login_id);
	}
	@Override
	public int myreviewdelete(String review_no) {
		return getSqlSession().delete("myreviewdelete", review_no);
	}

	@Override
	public List<MemberDto> memberList() {
		return getSqlSession().selectList("selectMemberAll");
	}
	
	@Override
	public MemberDto selectMemberPart(String member_no) {
		return getSqlSession().selectOne("selectMemberPart",member_no);
	}
}
