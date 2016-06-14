package cn.edu.zucc.shop.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.shop.adminuser.dao.AdminUserDao;
import cn.edu.zucc.shop.adminuser.vo.AdminUser;

/*
 * ��̨��½ҵ���Ĵ���
 */
@Transactional
public class AdminUserService {

	// ע��Dao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}

}
