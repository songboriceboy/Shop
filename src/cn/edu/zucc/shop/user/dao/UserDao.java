package cn.edu.zucc.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.zucc.shop.user.vo.User;

/*
 * �û�ģ��־ò�Ĵ���
 */
public class UserDao extends HibernateDaoSupport {

	// �����Ʋ�ѯ�Ƿ��и��û�

	public User findByUsername(String username){
		String hql = "from User where username = ?";
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	public void save(User user) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(user);
	}

}
