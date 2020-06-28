package pack.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pack.model.CartBean;
import pack.model.CartDao;
import pack.model.CartDto;
import pack.model.DishDto;

@Controller
public class CartController {
	@Autowired
	private CartDao dao;
	
	@RequestMapping(value = "cart", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> addCart(HttpSession session, @RequestParam("menu_num") String num, @RequestParam("qty") String qty){
		String login_id = (String)session.getAttribute("login_id");
		DishDto dto = dao.getInfo(num); //가져온 메뉴번호를 가지고 이름과 가격 가져오기
		CartBean bean = null;
		
		bean = new CartBean();
		bean.setMember_id(login_id);
		bean.setMenu_no(num);
		CartDto ck = dao.checkCart(bean);
		
		if(ck != null) {//카트에 있는 상품인지 확인
			bean = new CartBean();
			int temp = Integer.parseInt(ck.getMenu_quantity());
			int temp2 = Integer.parseInt(qty) + temp;
			bean.setMember_id(login_id);
			bean.setMenu_no(num);
			bean.setMenu_quantity(Integer.toString(temp2));
			dao.updateCart(bean);
		}else {
			//bean에 정보를 담아서 cart 테이블에 추가
			bean = new CartBean();
			bean.setMember_id(login_id);
			bean.setMenu_no(num);
			bean.setMenu_name(dto.getMenu_name());
			bean.setMenu_price(dto.getMenu_price());
			bean.setMenu_quantity(qty);
			dao.insertCart(bean);
		}
		
		//카트에 추가한 후에 다시 카트 리스트 가져오기(화면에서 카트에 담긴 것을 보여주기 위해)
		List<Map<String, String>> cartList = new ArrayList<Map<String,String>>();
		Map<String, String> data = null;	
		List<CartDto> list = dao.cartlist(login_id); 
		
		for(CartDto d : list) {
			data = new HashMap<String, String>();
			data.put("member_id", d.getMember_id());
			data.put("menu_no", d.getMenu_no());
			data.put("menu_name", d.getMenu_name());
			data.put("menu_price", d.getMenu_price());
			data.put("menu_quantity", d.getMenu_quantity());
			cartList.add(data);
		}
		
		Map<String, Object> carts = new HashMap<String, Object>();
		carts.put("list", cartList);
		carts.put("loginyn",session.getAttribute("login_id"));
		return carts;
	}
	
	
	//결제진행, order, order_detail에 추가하고 cart를 비우기
	@RequestMapping(value = "proceedBuy", method = RequestMethod.POST)
	public String processBuy(HttpSession session) {
		String login_id = (String)session.getAttribute("login_id");
		
		dao.confirmOrder(login_id);    //order테이블에 추가 한 후
		dao.insertDetail(login_id);   //orderno 번호를 가져와서 detail에 추가
		dao.deleteAllCart(login_id);   // 결제 후 카트 삭제
		return "redirect:aa";
	}
	
	
	@RequestMapping("delCart")
	@ResponseBody
	public Map<String, Object> delProcess(HttpSession session, @RequestParam("menu_num") String menu_num){
		//카트 아이템 삭제
		String login_id = (String)session.getAttribute("login_id");
		CartBean bean = null;
		bean = new CartBean();
		bean.setMember_id(login_id);
		bean.setMenu_no(menu_num);
		dao.deleteCartItem(bean);
		
		//삭제 된 후 다시 카트 정보 뿌리기
		List<Map<String, String>> cartList = new ArrayList<Map<String,String>>();
		Map<String, String> data = null;		
		List<CartDto> list = dao.cartlist(login_id);
		
		for(CartDto d : list) {
			data = new HashMap<String, String>();
			data.put("member_id", d.getMember_id());
			data.put("menu_no", d.getMenu_no());
			data.put("menu_name", d.getMenu_name());
			data.put("menu_price", d.getMenu_price());
			data.put("menu_quantity", d.getMenu_quantity());
			cartList.add(data);
		}
		
		Map<String, Object> carts = new HashMap<String, Object>();
		carts.put("list", cartList);
		return carts;
	}
}
