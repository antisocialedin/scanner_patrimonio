package com.scanner_patrimonio.model.dao.patrimonio;

import javax.persistence.EntityManager;

import com.scanner_patrimonio.model.models.Patrimonio;

public class PatrimonioDao extends PatrimonioGenericDao <Patrimonio, Integer> {
	
	public PatrimonioDao(EntityManager entityManager) {
		super(entityManager);
	}

}
