package com.lumen.ebonding.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lumen.ebonding.dto.ApiDetailDTO;
import com.lumen.ebonding.model.ApiDetail;
import com.lumen.ebonding.model.CustomerConfiguration;

/**
 * Services for {@link ApiDetail}
 * 
 * @author Devesh Joshi
 * @since Oct 14, 2020
 *
 */
public interface ApiDetailsService extends GenericService<ApiDetail, Integer, ApiDetailDTO>{

//	/**
//	 * Method to save the {@link ApiDetail} with file
//	 * @param apiDetail {@link ApiDetailDTO}
//	 * @param sourceFile
//	 * @param targetFile
//	 * @return
//	 */
//	ApiDetailDTO saveOrUpdate(ApiDetailDTO apiDetail, MultipartFile sourceFile, MultipartFile targetFile);
	
	/**
	 * Method to fetch List of {@link ApiDetailDTO} by {@link CustomerConfiguration} 
	 * @param configId
	 * @return List of {@link ApiDetailDTO}
	 */
	List<ApiDetailDTO> getAllByConfig(Integer configId);
	
	/**
	 * Method to fetch list of all active {@link ApiDetailDTO} from {@link CustomerConfiguration}
	 * @param active
	 * @param configId
	 * @return List of {@link ApiDetailDTO}
	 */
	List<ApiDetailDTO> getAllActiveApiByConfig(Integer configId, boolean active);
	
	/**
	 * Method to remove the uploaded content by api detail id and type (source or target)
	 * 
	 * @param apiDetailId
	 * @param type
	 * @return status boolean
	 */
	boolean deleteUploadedContentById(Integer apiDetailId, String type);
	
	List<String> getFieldsListByConfigId(Integer configId, String from);
	public ApiDetail getFileDownload(int custConfigApiId);
}
