package pack.model;

import java.util.List;

public interface DishDaoInter {
	List<DishDto> getDataAll();
	List<DishDto> getSoup(String menu_category);
	List<DishDto> getSide(String menu_category);
	List<DishDto> getMain(String menu_category);
	List<DishDto> getMeat(String menu_category);
	
	List<DishDto> sortLow(String menu_category);
	List<DishDto> sortHigh(String menu_category);
	List<DishDto> sortLately(String menu_category);
	
	List<DishDto> sortAllLow();
	List<DishDto> sortAllHigh();
	List<DishDto> sortAllReg();
	
	//관리자
	List<DishDto> menuList();
    AdminDto getLoginInfo(String id);
	DishDto selectPart(String num);
	boolean insertData(DishBean bean);
	boolean updateData(DishBean bean);
	boolean deleteData(String num);
}
