package com.scanner_patrimonio.model.dao.servidor;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class ServidorGenericDao <Servidor, Id extends Serializable>{
	
	private EntityManager entityManager;	
	
	private final Class<Servidor> classePersistencia;
	
	@SuppressWarnings("unchecked")
	public ServidorGenericDao(EntityManager entityManager) {
		this.entityManager = entityManager;
		this.classePersistencia = (Class<Servidor>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public void save(Servidor entity) {
		this.getEntityManager().persist(entity);
	}
	
	public void update(Servidor entity) {
		this.getEntityManager().merge(entity);
	}
	
	public void remove(Servidor entity) {
		this.getEntityManager().remove(entity);
	}
	
	public Servidor findById(Id idServidor) {
		return this.getEntityManager().find(getClassePersistencia(), idServidor);
	}
	
	@SuppressWarnings("unchecked")
	 public List<Servidor> findAll(Class<Servidor> classe){
		 List<Servidor> lista = new ArrayList<>();
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
	
	public Class<Servidor> getClassePersistencia() {
		return classePersistencia;
	}

}
