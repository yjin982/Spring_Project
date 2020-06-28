package pack.controller;

import org.apache.ibatis.annotations.Select;


public interface SqlMapperInter {
	
	@Select("select count(*) from menutbl where menu_category='국'")
	public int countgook();
	@Select("select count(*) from menutbl where menu_category='김치'")
	public int countkimchi();
	@Select("select count(*) from menutbl where menu_category='메인'")
	public int countmain();
	@Select("select count(*) from menutbl where menu_category='반찬'")
	public int countbanchan();
	@Select("select count(*) from menutbl where menu_category='샐러드'")
	public int countsallad();
	@Select("select count(*) from menutbl where menu_category='육류'")
	public int countmeat();
	
	@Select("select sum(menu_price)*menu_quantity from menutbl inner join order_detailtbl on menu_no = menu_num inner join ordertbl on order_num = order_no where menu_category='국'")
	public int sumgook();
	@Select("select sum(menu_price)*menu_quantity from menutbl inner join order_detailtbl on menu_no = menu_num inner join ordertbl on order_num = order_no where menu_category='김치'")
	public int sumkimchi();
	@Select("select sum(menu_price)*menu_quantity from menutbl inner join order_detailtbl on menu_no = menu_num inner join ordertbl on order_num = order_no where menu_category='메인'")
	public int summain();
	@Select("select sum(menu_price)*menu_quantity from menutbl inner join order_detailtbl on menu_no = menu_num inner join ordertbl on order_num = order_no where menu_category='반찬'")
	public int sumbanchan();
	@Select("select sum(menu_price)*menu_quantity from menutbl inner join order_detailtbl on menu_no = menu_num inner join ordertbl on order_num = order_no where menu_category='샐러드'")
	public int sumsallad();
	@Select("select sum(menu_price)*menu_quantity from menutbl inner join order_detailtbl on menu_no = menu_num inner join ordertbl on order_num = order_no where menu_category='육류'")
	public int summeat();
}
