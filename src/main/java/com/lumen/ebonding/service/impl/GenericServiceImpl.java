package com.lumen.ebonding.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import com.lumen.ebonding.service.GenericService;

/**
 * Abstract class which will take care of all the CRUD operations for passed types
 * 
 * @author Devesh Joshi
 *
 * @param <E> Entity Class
 * @param <I> Data type of id field
 * @param <D> DTO class
 */
abstract public class GenericServiceImpl<E, I, D> implements GenericService<E, I, D> {

	
	private Logger log = LoggerFactory.getLogger(GenericServiceImpl.class); 
	
    @Override
	public List<D> getAll() {
    	List<E> entityList = new ArrayList<E>();
  
    	if (getRepository() != null) {
    		entityList = getRepository().findAll(); 
    	}

    	return toDTOList(entityList);
	}

	@Override
	public D get(I id) {
		E entity = null;
		
		if (getRepository() !=  null) {
			entity = getRepository().getOne((Serializable) id);
		}
		
		return toDTO(entity);
	}

	@Override
	public D saveOrUpdate(D dto) {
		E entity = null;
		if(getRepository() != null) {
			entity = getRepository().save(toEntity(dto));
		}
		
		return toDTO(entity);
	}

	
	@Override
	public List<D> saveOrUpdate(List<D> dtoList) {
		List<D> persistedList = new ArrayList<D>();
		if(dtoList != null && !dtoList.isEmpty()) {
			for(D dto : dtoList) {
				persistedList.add(saveOrUpdate(dto));
			}
		}
		
		return persistedList;
	}

	@Override
	public Boolean delete(I id) {
		if(getRepository() != null) {
			try {
				getRepository().delete((Serializable) id);
				return true;
			}
			catch(Exception err) {
				log.error(">>>>>Some error occured while deleting the record for id : " + id);
				return false;
			}
		}
		return false;
	}

	@Override
	public List<E> toEntityList(List<D> dtoList) {
		if(dtoList != null && dtoList.isEmpty()) {
			return new ArrayList<E>();
		}
		
		List<E> entityList = new ArrayList<E>(dtoList.size());
		
		for(D dto: dtoList) {
			entityList.add(toEntity(dto));
		}
		
		return entityList;
	}

	@Override
	public List<D> toDTOList(List<E> entityList) {
		if(entityList != null && entityList.isEmpty()) {
			return new ArrayList<D>();
		}
		
		List<D> dtoList = new ArrayList<D>(entityList.size());
		
		for(E entity: entityList) {
			dtoList.add(toDTO(entity));
		}
		
		return dtoList;
	}


	@Override
	public E toEntity(D dto) {
		return null;
	}

	@Override
	public D toDTO(E entity) {
		return null;
	}
	
	@Override
	public JpaRepository<E, Serializable> getRepository() {
		return null;
	}
	
}
