package com.acat.spring.boot.blog.repository;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.acat.spring.boot.blog.domain.User;

import java.util.Collection;
import java.util.List;

/**
 * 用户仓库.
 *

 */
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * 根据用户姓名分页查询用户列表
	 *
	 * @param name
	 * @param pageable
	 * @return
	 */
	Page<User> findByNameLike(String name, Pageable pageable);

	/**
	 * 根据用户账号查找数据
	 *
	 * @param username
	 * @return
	 */
	User findUserByUsername(String username);

	List<User> findByUsernameIn(Collection<String> usernames);
}
