package com.scanner_patrimonio.model.dao.servidor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.scanner_patrimonio.model.models.Servidor;

public class ServidorDao extends ServidorGenericDao <Servidor, Integer> {
	
	public ServidorDao(EntityManager entityManager) {
		super(entityManager);
	}

	
	@SuppressWarnings("unchecked")
	public List<Servidor> listServidorPaginacao(Integer numeroPagina, Integer defaultPagina) {
		
		List<Servidor> listaServidor = new ArrayList<Servidor>();
	
		
		Query query = this.getEntityManager().createQuery("SELECT u FROM Servidor u "
											+ "LEFT JOIN FETCH u.roles ")
											 .setFirstResult(numeroPagina)
											 .setMaxResults(defaultPagina);	

		listaServidor = query.getResultList();
		
		return listaServidor;
	}
	
	
	
	
	
	
}
