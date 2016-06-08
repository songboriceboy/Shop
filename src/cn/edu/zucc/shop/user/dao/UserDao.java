package cn.edu.zucc.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.zucc.shop.user.vo.User;

/*
 * 用户模块持久层的代码
 */
public class UserDao extends HibernateDaoSupport {

	// 按名称查询是否有该用户

	public User findByUserame(String username) {
		String hql = "from user where username =?";
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
