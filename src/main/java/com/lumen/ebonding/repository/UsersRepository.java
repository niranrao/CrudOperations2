package com.lumen.ebonding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.lumen.ebonding.model.Users;


public interface UsersRepository extends JpaRepository<Users, Integer>{
		@Query(nativeQuery = true , value = "Select user_id,cu_id,email_id,sup_user,status,modified_by,modified_date from config_portal.users where upper(cu_id) = upper(:cuid)")
		public Users getByCuId(@Param("cuid") String cuid);
		@Query(nativeQuery = true , value = "Select user_id,cu_id,email_id,sup_user,status,modified_by,modified_date from config_portal.users where user_id = :userId")
		public Users findByUserId(@Param("userId") int userId);
		@Modifying
		@Transactional
		@Query(nativeQuery = true , value = "update config_portal.users set user_password = :password,modified_date=now() where cu_id = :username")
		public void updatePassword(@Param ("username")String username,@Param("password") String password);
		@Query(nativeQuery = true , value = "Select count(*) from config_portal.users where cu_id = :cuId")
		public int searchUser(@Param("cuId") String cuId);
		@Query(nativeQuery = true , value = "Select count(*) from config_portal.users where cu_id = :cuId and user_password = :oldPassword")
		public int validateOldPassword(@Param("cuId") String cuId,@Param("oldPassword") String oldPassword);
		@Query(nativeQuery = true , value = "Select user_password from config_portal.users where cu_id = :cuId")
		public String getPasswordHash(@Param("cuId") String cuId);
}
