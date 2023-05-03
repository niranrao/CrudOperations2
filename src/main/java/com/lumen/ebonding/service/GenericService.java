package com.lumen.ebonding.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 
 * @author Devesh Joshi
 *
 * @param <E> Entity class
 * @param <I> Data type of Id 
 * @param <D> DTO class
 */
public interface GenericService<E, I, D> {

	/**
	 * Methods to get all records of type E
	 * @return List of DTO converted from List of Entity
	 */
	List<D> getAll();
	
	/**
	 * Methods to fetch record by id field 
	 * @return Dto with matching id
	 */
	D get(I id);

	/**
	 * Method to save new record and update existing record
	 * @return saved or updated record
	 */
	
	D saveOrUpdate(D dto);
	
	/**
	 * Method to save or update List of records
	 * @return saved or updated list of record
	 */
	
	List<D> saveOrUpdate(List<D> dtoList);
	
	/**
	 * Method to delete the record by the id field 
	 * @param id
	 * @return
	 */
	Boolean delete(I id);
	
	/**
	 * Method to convert dto object to entity object
	 * @param dto object
	 * @return Entity object 
	 */
	public E toEntity(D dto);


	/**
	 * Method to convert entity object to dto object
	 * @param entity object
	 * @return dto object
	 */
    public D toDTO(E entity);
    

	/**
	 * Method to convert dto List to entity List
	 * @param dto List
	 * @return Entity List 
	 */
	public List<E> toEntityList(List<D> dto);


	/**
	 * Method to convert entity List to dto List
	 * @param entity List
	 * @return dto List
	 */
    public List<D> toDTOList(List<E> entity);
    
    /**
     * Method to get repository object 
     * @return JPAResopitory
     */
    public JpaRepository<E, Serializable> getRepository();
}
