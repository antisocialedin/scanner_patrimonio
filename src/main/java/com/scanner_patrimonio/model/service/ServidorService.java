package com.scanner_patrimonio.model.service;

import java.util.List;
import javax.persistence.EntityTransaction;

import com.scanner_patrimonio.model.dao.servidor.ServidorDao;
import com.scanner_patrimonio.model.models.Servidor;
import com.scanner_patrimonio.struct.util.VariaveisProjeto;


public class ServidorService extends ConexaoBancoService{

	private ServidorDao servidorDao;
	
	public ServidorService() {
		this.servidorDao = new ServidorDao(this.getEntityManager());
	}
	//-------------------------------------------------------------------
	public Integer save(Servidor servidor) {
		
		Integer toReturn = 0;
		
		EntityTransaction trx = this.getTransaction();
		
		if(validarDigitacao(servidor) == VariaveisProjeto.DIGITACAO_OK) {
		
			try {
				trx.begin();
				this.getServidorDao().save(servidor);
				trx.commit();
				
			}catch(Exception ex) {
				ex.printStackTrace();
				if(trx.isActive()) {
					trx.rollback();
				}
				toReturn = VariaveisProjeto.ERRO_INCLUSAO;
			}finally {
				this.close();
			}
		} else {
			toReturn = VariaveisProjeto.CAMPO_VAZIO;
		}
		
		return toReturn;
	}
	//-------------------------------------------------------------------
	public Integer update(Servidor servidor) {
		
		Integer toReturn =0;
		
		EntityTransaction trx = this.getTransaction();
		
		if(validarDigitacao(servidor)== VariaveisProjeto.DIGITACAO_OK) {
		
			try {
				trx.begin();
				this.getServidorDao().update(servidor);
				trx.commit();
				
			}catch(Exception ex) {
				ex.printStackTrace();
				if(trx.isActive()) {
					trx.rollback();
				}
				toReturn = VariaveisProjeto.ERRO_ALTERACAO;
			}finally {
				this.close();
			}
		} else {
			toReturn = VariaveisProjeto.CAMPO_VAZIO;
		}
		
		return toReturn;
	}
	//-------------------------------------------------------------------
	public Integer delete(Servidor servidor) {
		
		Integer toReturn =0;
		
		EntityTransaction trx = this.getTransaction();
		
		
			try {
				trx.begin();
				Servidor usuarioEncontrado = this.getServidorDao().findById(servidor.getId());
				this.getServidorDao().remove(usuarioEncontrado);
				trx.commit();
				
			}catch(Exception ex) {
				ex.printStackTrace();
				if(trx.isActive()) {
					trx.rollback();
				}
				toReturn = VariaveisProjeto.ERRO_ALTERACAO;
			}finally {
				this.close();
			}
		
		return toReturn;
	}
	//-------------------------------------------------------------------
	public Servidor findById(Integer idServidor) {
		return this.getServidorDao().findById(idServidor);
	}
	
	public List<Servidor> findAll(){
		return this.getServidorDao().findAll(Servidor.class);
	}
	
	public Integer validarDigitacao(Servidor servidor) {
		if(VariaveisProjeto.digitacaoCampo(servidor.getName())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}		
		return VariaveisProjeto.DIGITACAO_OK;
	}	

	public ServidorDao getServidorDao() {
		return servidorDao;
	}
}
