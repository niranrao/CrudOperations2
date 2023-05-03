package com.lumen.ebonding.service.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPMessage;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lumen.ebonding.dto.ApiDetailDTO;
import com.lumen.ebonding.model.ApiDetail;
import com.lumen.ebonding.model.CustomerConfiguration;
import com.lumen.ebonding.repository.ApiDetailsRepository;
import com.lumen.ebonding.repository.CustomerConfigRepository;
import com.lumen.ebonding.service.ApiDetailsService;

/**
 * Service implementation of {@link ApiDetailsService}
 * 
 * @author Devesh Joshi
 * @since Oct 14, 2020
 *
 */
@Service
public class ApiDetailsServiceImpl extends GenericServiceImpl<ApiDetail, Integer, ApiDetailDTO>
		implements ApiDetailsService {

	@Autowired
	private ApiDetailsRepository apiDetailsRepository;

	@Autowired
	private CustomerConfigRepository configRepository;

	private ModelMapper mapper = new ModelMapper();

	private Logger log = LoggerFactory.getLogger(ApiDetailsServiceImpl.class);

	@Override
	public ApiDetail toEntity(ApiDetailDTO dto) {
		ApiDetail entity = new ApiDetail();
		mapper.map(dto, entity);

		return entity;
	}

	@Override
	public ApiDetailDTO toDTO(ApiDetail entity) {
		ApiDetailDTO dto = new ApiDetailDTO();
		mapper.map(entity, dto);

		return dto;
	}

	@Override
	public JpaRepository getRepository() {
		return apiDetailsRepository;
	}

	@Override
	public List<ApiDetailDTO> getAllByConfig(Integer configId) {
		log.trace("Fetching all configurations for config id: " + configId);

		// creating a proxy object for query
		CustomerConfiguration config = configRepository.getOne(configId);

		List<ApiDetail> apiDetails = apiDetailsRepository.findAllByConfig(config);
		return toDTOList(apiDetails);
	}

	@Override
	public List<ApiDetailDTO> getAllActiveApiByConfig(Integer configId, boolean active) {
		// creating a proxy object for query
		CustomerConfiguration config = configRepository.getOne(configId);

		List<ApiDetail> apiDetails = apiDetailsRepository.findAllByConfigAndActive(config, active);
		return toDTOList(apiDetails);
	}

	@Override
	public ApiDetailDTO saveOrUpdate(ApiDetailDTO apiDetail) {
		log.info("Saving or Updating the apidetails with uploadedfiles");

		try {
			if (apiDetail != null && apiDetail.getId() != null) {
				ApiDetailDTO existingDetail = get(apiDetail.getId());

				if (existingDetail != null) {

					// setting existing details for source
					if (apiDetail.getSourceFileName() == null || apiDetail.getSourceFileName().isEmpty()) {
						apiDetail.setSourceFileName(existingDetail.getSourceFileName());
						apiDetail.setSourceFileType(existingDetail.getSourceFileType());
						apiDetail.setSourcePayload(existingDetail.getSourcePayload());
					}

					// setting existing details for target
					if (apiDetail.getTargetFileName() == null || apiDetail.getTargetFileName().isEmpty()) {
						apiDetail.setTargetFileName(existingDetail.getTargetFileName());
						apiDetail.setTargetFileType(existingDetail.getTargetFileType());
						apiDetail.setTargetPayload(existingDetail.getTargetPayload());
					}
				}
			}
		} catch (Exception err) {
			log.error("Error Occured : " + err.getMessage());
			log.error("Error Cause : " + err.getCause());
			err.printStackTrace();
		}

		return super.saveOrUpdate(apiDetail);
	}

	@Override
	public boolean deleteUploadedContentById(Integer apiDetailId, String type) {
		log.trace("Removing uploaded content for id: " + apiDetailId + " type: " + type);

		// not using get() because no need to convert this to dto
		ApiDetail detail = apiDetailsRepository.getOne(apiDetailId);

		if (detail != null && type != null && !type.isEmpty()) {
			if (type.equalsIgnoreCase("source")) {
				detail.setSourceFileName(null);
				detail.setSourceFileType(null);
				detail.setSourcePayload(null);
			}

			if (type.equalsIgnoreCase("target")) {
				detail.setTargetFileName(null);
				detail.setTargetFileType(null);
				detail.setTargetPayload(null);

			}

			// using save method of CRUDRepository to persist entity directly
			apiDetailsRepository.save(detail);
			return true;
		}

		return false;
	}

	@Override
	public List<String> getFieldsListByConfigId(Integer configId, String from) {
		CustomerConfiguration config = configRepository.getOne(configId);

		List<ApiDetailDTO> apiDetails = toDTOList(apiDetailsRepository.findAllByConfig(config));

		if (apiDetails != null && !apiDetails.isEmpty()) {

			if (apiDetails.get(0) != null && apiDetails.get(0).getSourcePayload() != null) {

				if (from.equalsIgnoreCase("source")) {
					return getXMLElementsFromXMLPayload(apiDetails.get(0).getSourcePayload());
				} else if (from.equalsIgnoreCase("target")) {
					return getXMLElementsFromXMLPayload(apiDetails.get(0).getTargetPayload());
				}
			}
		}

		return new ArrayList<String>();
	}

	List<String> getXMLElementsFromXMLFile(byte[] xmlPayload) {
		List<String> elementList = new ArrayList<String>();
		log.info("===   Start getXMLElementsFromXMLFile   ===");
		try {

			// create message factory
			MessageFactory mf = MessageFactory.newInstance();
			// headers for a SOAP message
			MimeHeaders header = new MimeHeaders();
			header.addHeader("Content-Type", "text/xml");

			// read xml file
			InputStream is = new ByteArrayInputStream(xmlPayload);
			SOAPMessage soapMessage = mf.createMessage(header, is);
			// get the body
			SOAPBody soapBody = soapMessage.getSOAPBody();
			// find your node based on tag name
			NodeList nodes = soapBody.getChildNodes();
			if (nodes != null && nodes.getLength() > 0) {
				for (int i = 0; i < nodes.getLength(); i++) {
					Node node = nodes.item(i);
					if (node.hasChildNodes()) {
						NodeList childnodes = node.getChildNodes();
						if (childnodes != null && childnodes.getLength() > 0) {
							for (int j = 0; j < childnodes.getLength(); j++) {

								if (childnodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
									elementList.add(childnodes.item(j).getNodeName());

								}

							}

						}

					}

				}

			}

		} catch (Exception ex) {
			log.error("Could not read the content Please try again!" + ex);
			// throw new DaoException(DaoExceptionCode.SELECT_FAILED, ex);
			return null;
		}
		Collections.sort(elementList);
		log.info("element list " + elementList.toString());
		log.info("===   End getXMLElementsFromXMLFile   ===");
		return elementList;

	}

	public List<String> getXMLElementsFromXMLPayload(byte[] xmlPayload) {
		List<String> elementList = new ArrayList<String>();
		log.info("===   Start getXMLElementsFromXMLFile   ===");
		try {
			String[] tickets = { "incident", "request" };
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new ByteArrayInputStream(xmlPayload));
			if (doc != null) {
				doc.getDocumentElement().normalize();
				List<String> ticketTypeList = Arrays.asList(tickets);
				for (String ticket : ticketTypeList) {
					NodeList nodes = doc.getElementsByTagName(ticket);
					if (nodes != null && nodes.getLength() > 0) {
						for (int i = 0; i < nodes.getLength(); i++) {
							Node node = nodes.item(i);
							if (node.hasChildNodes()) {
								NodeList childnodes = node.getChildNodes();
								if (childnodes != null && childnodes.getLength() > 0) {
									for (int j = 0; j < childnodes.getLength(); j++) {

										if (childnodes.item(j).getNodeType() == Node.ELEMENT_NODE) {

											elementList.add(childnodes.item(j).getNodeName());
											// System.out.println(childnodes.item(j).getNodeName());
										}
//										else if(childnodes.item(j).getNodeType() > Node.ELEMENT_NODE){
										NodeList childnodes2 = childnodes.item(j).getChildNodes();
										if (childnodes2 != null && childnodes2.getLength() > 0) {
											for (int k = 0; k < childnodes2.getLength(); k++) {

												if (childnodes2.item(k).getNodeType() == Node.ELEMENT_NODE) {
													elementList.add(childnodes.item(j).getNodeName() + "_"
															+ childnodes2.item(k).getNodeName());
													// System.out.println(childnodes.item(j).getNodeName());
												}

												NodeList childnodes3 = childnodes2.item(k).getChildNodes();
												if (childnodes3 != null && childnodes3.getLength() > 0) {
													for (int n = 0; n < childnodes3.getLength(); n++) {

														if (childnodes3.item(n).getNodeType() == Node.ELEMENT_NODE) {
															elementList.add(childnodes.item(j).getNodeName() + "_"
																	+ childnodes2.item(k).getNodeName() + "_"
																	+ childnodes3.item(n).getNodeName());
															// System.out.println(childnodes.item(j).getNodeName());
														}
														NodeList childnodes4 = childnodes3.item(n).getChildNodes();
														if (childnodes4 != null && childnodes4.getLength() > 0) {
															for (int m = 0; m < childnodes4.getLength(); m++) {
																if (childnodes4.item(m)
																		.getNodeType() == Node.ELEMENT_NODE) {
																	elementList.add(childnodes.item(j).getNodeName()
																			+ "_" + childnodes2.item(k).getNodeName()
																			+ "_" + childnodes3.item(n).getNodeName()
																			+ "_" + childnodes4.item(m).getNodeName());

																}
															}
														}

													}
												}
											}
										}
									}

								}

							}

						}

					}
				}
				return elementList;

			}
		} catch (Exception ex) {
			log.error("Invalid xml format " + ex);
		}
		return null;
	}

	@Override
	public ApiDetail getFileDownload(int custConfigApiId) {
		log.info("===Start Download XML data  ===");

		CustomerConfiguration config = configRepository.getOne(custConfigApiId);

		List<ApiDetail> apiDetails = apiDetailsRepository.findAllByConfig(config);
		if (apiDetails != null) {

			return apiDetails.get(0);
		}
		log.info("===End Download XML data  ===");
		return null;
	}

}