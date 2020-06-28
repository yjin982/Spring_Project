package pack.model;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import pack.controller.MenuBean;
import pack.model.DishBean;

@Repository
public class DishDaoImpl extends SqlSessionDaoSupport implements DishDaoInter {
	
	@Autowired
	public DishDaoImpl(SqlSessionFactory factory) {
		setSqlSessionFactory(factory);
	}
		
	//////분류별로 보기
	@Override
	public List<DishDto> getDataAll() {
		return getSqlSession().selectList("selectAll");
	}
	
	public List<DishDto> search(DishBean bean) {
		return getSqlSession().selectList("selectSearch",bean);
	}
	
	@Override
	public List<DishDto> getSoup(String menu_category) {
		return getSqlSession().selectList("selectSoup", menu_category);
	}
	
	@Override
	public List<DishDto> getSide(String menu_category) {
		return getSqlSession().selectList("selectSide", menu_category);
	}
	
	@Override
	public List<DishDto> getMain(String menu_category) {
		return getSqlSession().selectList("selectMain", menu_category);
	}
	
	@Override
	public List<DishDto> getMeat(String menu_category) {
		return getSqlSession().selectList("selectMeat", menu_category);
	}
	
	
	////// 정렬
	@Override
	public List<DishDto> sortHigh(String menu_category) {
		return getSqlSession().selectList("sortHigh", menu_category);
	}
	@Override
	public List<DishDto> sortLow(String menu_category) {
		return getSqlSession().selectList("sortLow", menu_category);
	}
	@Override
	public List<DishDto> sortLately(String menu_category) {
		return getSqlSession().selectList("sortLate", menu_category);
	}
	
	@Override
	public List<DishDto> sortAllHigh() {
		return getSqlSession().selectList("sortAlltoHigh");
	}
	@Override
	public List<DishDto> sortAllLow() {
		return getSqlSession().selectList("sortAlltoLow");
	}
	@Override
	public List<DishDto> sortAllReg() {
		return getSqlSession().selectList("sortAlltoReg");
	}
	
	
	
	@Override
	public List<DishDto> menuList() {
		return getSqlSession().selectList("selectMenuAll");
	}
	
	@Override
	public AdminDto getLoginInfo(String id) {
		return getSqlSession().selectOne("selectAdminLogin",id);
	}
	
	@Override
	public DishDto selectPart(String menu_no) {
		return getSqlSession().selectOne("selectMenuPart",menu_no);
	}
	
	@Override
	public boolean insertData(DishBean bean) {
		try {
			getSqlSession().selectOne("insertMenuData",bean);
			return true;
		} catch (Exception e) {
			System.out.println("insertData err : "+e);
			return false;
		}
	}
	
	@Override
	public boolean updateData(DishBean bean) {
		try {
			getSqlSession().selectOne("updateMenuData",bean);
			return true;
		} catch (Exception e) {
			System.out.println(" updateData err : "+e);
			return false;
		}
	}
	
	@Override
	public boolean deleteData(String menu_no) {
		try {
			getSqlSession().selectOne("deleteData",menu_no);
			return true;
		} catch (Exception e) {
			System.out.println(" deleteData err : "+e);
			return false;
		}
	}
}
