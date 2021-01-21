package com.scanner_patrimonio.model.dao.patrimonio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.scanner_patrimonio.model.models.Patrimonio;

public class PatrimonioDao extends PatrimonioGenericDao <Patrimonio, Integer> {
	
	public PatrimonioDao(EntityManager entityManager) {
		super(entityManager);
	}
	
	@SuppressWarnings("unchecked")
	 
	public List<Patrimonio> listPatrimonioPaginacao(Integer numeroPagina, Integer defaultPagina) {
		
		List<Patrimonio> listaPatrimonio = new ArrayList<Patrimonio>();
		
		
		Query query = this.getEntityManager().createQuery("SELECT u FROM Patrimonio u "
											+ "LEFT JOIN FETCH u.area "
											+ "LEFT JOIN FETCH u.roles ")
											 .setFirstResult(numeroPagina)
											 .setMaxResults(defaultPagina);
		listaPatrimonio = query.getResultList();
		
		return listaPatrimonio;
	}
	

}
