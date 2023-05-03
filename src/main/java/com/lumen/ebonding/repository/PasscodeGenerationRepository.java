package com.lumen.ebonding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.lumen.ebonding.model.PasscodeGenerator;

public interface PasscodeGenerationRepository extends JpaRepository<PasscodeGenerator, Integer> {
	@Query(nativeQuery = true , value = "Select id from config_portal.passcode_generation where "
			+ "cu_id = :cuId and passcode = :passcode and generated_time > DATE_SUB(NOW(), INTERVAL 3 MINUTE)")
	int getValidationId(@Param("cuId") String cuId,@Param("passcode") String passcode);
	@Query(nativeQuery = true , value = "Select count(*) from config_portal.passcode_generation where "
			+ "cu_id = :cuId and passcode = :passcode and generated_time > DATE_SUB(NOW(), INTERVAL 3 MINUTE)")
	int validate(@Param("cuId") String cuId,@Param("passcode") String passcode);
	@Modifying
	@Transactional
	@Query(nativeQuery = true , value = "update config_portal.passcode_generation set validation = 1, validation_time = NOW() where id = :validationId")
	void updateValidation(@Param("validationId") int validationId);
	@Query(nativeQuery = true, value = "select count(*) from config_portal.passcode_generation where id = :validationId and validation_time > DATE_SUB(NOW(), INTERVAL 2 MINUTE)")
	int validateSession(@Param("validationId") int validationId);

	@Modifying
	@Transactional
	@Query(nativeQuery = true,value="delete from config_portal.passcode_generation where upper(cu_id) = upper(:cuId)")
	void deletePasscodes(@Param("cuId") String cuId);
}
