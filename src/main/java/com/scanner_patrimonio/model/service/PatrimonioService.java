package com.scanner_patrimonio.model.service;

import java.util.List;
import javax.persistence.EntityTransaction;

import com.scanner_patrimonio.model.dao.patrimonio.PatrimonioDao;
import com.scanner_patrimonio.model.models.Patrimonio;
import com.scanner_patrimonio.struct.util.VariaveisProjeto;


public class PatrimonioService extends ConexaoBancoService{

	private PatrimonioDao patrimonioDao;
	
	public PatrimonioService() {
		this.patrimonioDao = new PatrimonioDao(this.getEntityManager());
	}
	//-------------------------------------------------------------------
	public Integer save(Patrimonio patrimonio) {
		
		Integer toReturn = 0;
		
		EntityTransaction trx = this.getTransaction();
		
		if(validarDigitacao(patrimonio) == VariaveisProjeto.DIGITACAO_OK) {
		
			try {
				trx.begin();
				this.getPatrimonioDao().save(patrimonio);
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
	public Integer update(Patrimonio patrimonio) {
		
		Integer toReturn =0;
		
		EntityTransaction trx = this.getTransaction();
		
		if(validarDigitacao(patrimonio)== VariaveisProjeto.DIGITACAO_OK) {
		
			try {
				trx.begin();
				this.getPatrimonioDao().update(patrimonio);
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
	public Integer delete(Patrimonio patrimonio) {
		
		Integer toReturn =0;
		
		EntityTransaction trx = this.getTransaction();
		
		
			try {
				trx.begin();
				Patrimonio patrimonioEncontrado = this.getPatrimonioDao().findById(patrimonio.getId());
				this.getPatrimonioDao().remove(patrimonioEncontrado);
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
	public Patrimonio findById(Integer idPatrimonio) {
		return this.getPatrimonioDao().findById(idPatrimonio);
	}
	
	public List<Patrimonio> findAll(){
		return this.getPatrimonioDao().findAll(Patrimonio.class);
	}
	
	public Integer validarDigitacao(Patrimonio patrimonio) {
		if(VariaveisProjeto.digitacaoCampo(patrimonio.getName())) {
			return VariaveisProjeto.CAMPO_VAZIO;
		}		
		return VariaveisProjeto.DIGITACAO_OK;
	}	

	public PatrimonioDao getPatrimonioDao() {
		return patrimonioDao;
	}
}
