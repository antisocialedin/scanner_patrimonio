package com.scanner_patrimonio.model.dao.patrimonio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.scanner_patrimonio.model.models.Area;

public class AreaDao extends PatrimonioGenericDao<Area, Integer >{

	public AreaDao(EntityManager entityManager) {
		super(entityManager);
	}

	
	@SuppressWarnings("unchecked")
	public List<Area> listAreaPaginacao(Integer numeroPagina, Integer defaultPagina) {
		
		List<Area> listaArea = new ArrayList<Area>();
		
		
		Query query = this.getEntityManager().createQuery("SELECT u FROM Area")
											 .setFirstResult(numeroPagina)
											 .setMaxResults(defaultPagina);
		listaArea = query.getResultList();
		
		return listaArea;
	}
	
}
