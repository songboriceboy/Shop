package cn.edu.zucc.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.shop.user.dao.UserDao;
import cn.edu.zucc.shop.user.vo.User;
import cn.edu.zucc.shop.utils.UUIDUtils;

/*
 * 用户模块业务层的代码,要加@transaction
 */
@Transactional
public class UserService {
	// 注入userDao

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// 按用户名去查询用户的方法
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	//业务层完成用户注册的代码
	public void save(User user) {
		// TODO Auto-generated method stub
		//将数据存到数据库
		user.setState(0);//0表示用户未激活，1代表激活
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		
		userDao.save(user);
	}

	//登陆时
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}
	
	
}
