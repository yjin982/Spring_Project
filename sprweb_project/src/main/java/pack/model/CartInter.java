package pack.model;

import java.util.List;

public interface CartInter {
	public List<CartDto> cartlist(String num);
	public void insertCart(CartBean bean);
	public CartDto checkCart(CartBean bean);
	public void updateCart(CartBean bean);
	public void deleteCartItem(CartBean bean);
	public void deleteAllCart(String id);
	public DishDto getInfo(String num);
	public void confirmOrder(String id);
	public void insertDetail(String id);
	public String getMaxNo();
}
