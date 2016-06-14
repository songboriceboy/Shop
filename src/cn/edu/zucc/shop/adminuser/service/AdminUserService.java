package cn.edu.zucc.shop.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.shop.adminuser.dao.AdminUserDao;
import cn.edu.zucc.shop.adminuser.vo.AdminUser;

/*
 * 后台登陆业务层的代码
 */
@Transactional
public class AdminUserService {

	// 注入Dao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}

}
