package pack.model;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface MemberInter {
	MemberDto getLoginInfo(String login_id);
	int joinMember(MemberBean bean);
	int joinIdCheck(String join_id);
	MemberDto getUpdateInfo(String myinfoupdate_id);
	int updateMember(MemberBean bean);
	int updateMember2(MemberBean bean);
	int deleteMember(String login_id);
	List<OrderDto> myOrderList(String login_id);
	List<OrderDto> myOrderList2(String member_id);
	List<DishDto> myOrderDetail(String order_no);
	List<ReviewDto> myreview(String login_id);
	int myreviewdelete(String review_no);
	
	List<MemberDto> memberList();
	MemberDto selectMemberPart(String member_no);
	
	
	
}

