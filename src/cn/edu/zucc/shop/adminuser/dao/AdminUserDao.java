package cn.edu.zucc.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.zucc.shop.adminuser.vo.AdminUser;

/*
 * ��̨��½dao����
 */
public class AdminUserDao extends HibernateDaoSupport {

	// Dao��ɵ�¼�Ĵ���
	public AdminUser login(AdminUser adminUser) {
		String hql = "from AdminUser where username = ? and password = ?";
		List<AdminUser> list = this.getHibernateTemplate().find(hql,
				adminUser.getUsername(), adminUser.getPassword());
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}