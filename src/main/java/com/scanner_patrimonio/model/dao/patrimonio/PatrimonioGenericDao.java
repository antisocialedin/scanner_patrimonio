package com.scanner_patrimonio.model.dao.patrimonio;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class PatrimonioGenericDao <Patrimonio, Id extends Serializable>{
	
	private EntityManager entityManager;	
	
	private final Class<Patrimonio> classePersistencia;
	
	@SuppressWarnings("unchecked")
	public PatrimonioGenericDao(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.classePersistencia = (Class<Patrimonio>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void save(Patrimonio entity) {
		this.getEntityManager().persist(entity);
	}
	
	public void update(Patrimonio entity) {
		this.getEntityManager().merge(entity);
	}
	
	public void remove(Patrimonio entity) {
		this.getEntityManager().remove(entity);
	}
	
	public Patrimonio findById(Id idPatrimonio) {
		return this.getEntityManager().find(getClassePersistencia(), idPatrimonio);
	}
	
	@SuppressWarnings("unchecked")
	 public List<Patrimonio> findAll(Class<Patrimonio> classe){
		 List<Patrimonio> lista = new ArrayList<>();
		 Query query = this.getEntityManager().createQuery("SELECT o FROM "+classe.getSimpleName()+" o");
		 
		 lista = query.getResultList();
		 
		 return lista;		 
	 }
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Class<Patrimonio> getClassePersistencia() {
		return classePersistencia;
	}

}

