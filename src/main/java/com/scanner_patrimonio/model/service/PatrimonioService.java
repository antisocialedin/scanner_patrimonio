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
		}
		
		return toReturn;
	}
	//-------------------------------------------------------------------
	public Integer update(Patrimonio patrimonio) {
		
		Integer toReturn =0;
		
		EntityTransaction trx = this.getTransaction();
		
		toReturn = validarDigitacao(patrimonio);
		
		if  ( toReturn == VariaveisProjeto.DIGITACAO_OK) {

			try {

				trx.begin();
				this.getPatrimonioDao().update(patrimonio);
				trx.commit();
				toReturn = VariaveisProjeto.ALTERACAO_REALIZADA;

			} catch (Exception ex) {
				ex.printStackTrace();
				if ( trx.isActive() ) {
					trx.rollback();
				}
				toReturn = VariaveisProjeto.ERRO_ALTERACAO;

			} finally {
				this.close();
			}
		} 
		return toReturn; 
	}
	//-------------------------------------------------------------------
	public Integer delete(Patrimonio patrimonio) {
		Integer toReturn = 0;
		EntityTransaction trx = this.getTransaction();
		try {

			trx.begin();
			Patrimonio patrimonioEncontrado = this.getPatrimonioDao().findById(patrimonio.getId());
			this.getPatrimonioDao().remove(patrimonioEncontrado);
			trx.commit();
			toReturn = VariaveisProjeto.EXCLUSAO_REALIZADA;

		} catch (Exception ex) {
			ex.printStackTrace();
			if ( trx.isActive() ) {
				trx.rollback();
			}
			toReturn = VariaveisProjeto.ERRO_EXCLUSAO;

		} finally {
			this.close();
		}

		return toReturn; 
	}
	//-------------------------------------------------------------------
	public Patrimonio findById(Integer idPatrimonio) {
		return this.getPatrimonioDao().findById(idPatrimonio);
	}
	//-------------------------------------------------------------------
	public List<Patrimonio> findAll(){
		return this.getPatrimonioDao().findAll(Patrimonio.class);
	}
	//-------------------------------------------------------------------
	public Integer validarDigitacao(Patrimonio patrimonio) {

		if ( VariaveisProjeto.digitacaoCampo(patrimonio.getName())) {
			return VariaveisProjeto.PATRIMONIO_NAME;
		}
		if ( VariaveisProjeto.digitacaoCampo(patrimonio.getCodigo())) {
			return VariaveisProjeto.PATRIMONIO_CODIGO;
		}
		if ( VariaveisProjeto.digitacaoCampo(patrimonio.getEstado())) {
			return VariaveisProjeto.PATRIMONIO_ESTADO;
		}
		return VariaveisProjeto.DIGITACAO_OK;
	}
	//-------------------------------------------------------------------
	public PatrimonioDao getPatrimonioDao() {
		return patrimonioDao;
	}
	//-------------------------------------------------------------------
	public Integer countTotalRegister() {
		return patrimonioDao.countTotalRegister(Patrimonio.class);
	}
	//-------------------------------------------------------------------
	public List<Patrimonio> listPatrimonioPaginacao(Integer numeroPagina, Integer defaultPagina) {
		
		return patrimonioDao.listPatrimonioPaginacao(numeroPagina,defaultPagina);
	}
	
	
}
