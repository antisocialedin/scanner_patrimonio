package com.scanner_patrimonio.model.dao.servidor;

import javax.persistence.EntityManager;

import com.scanner_patrimonio.model.models.Servidor;

public class ServidorDao extends ServidorGenericDao <Servidor, Integer> {
	
	public ServidorDao(EntityManager entityManager) {
		super(entityManager);
	}

}
