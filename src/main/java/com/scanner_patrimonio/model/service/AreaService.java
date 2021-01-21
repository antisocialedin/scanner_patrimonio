package com.scanner_patrimonio.model.service;

import java.util.List;

import javax.persistence.EntityTransaction;

import com.scanner_patrimonio.struct.util.VariaveisProjeto;
import com.scanner_patrimonio.model.dao.patrimonio.AreaDao;
import com.scanner_patrimonio.model.models.Area;


public class AreaService extends ConexaoBancoService {

	private AreaDao areaDao;

	public AreaService() {
		this.areaDao = new AreaDao(this.getEntityManager());
	}

	public Integer save(Area area) {

		Integer toReturn = 0;

		EntityTransaction trx = this.getTransaction();
		
		toReturn = validarDigitacao(area);

		if ( toReturn == VariaveisProjeto.DIGITACAO_OK) {

			try {

				trx.begin();
				this.getAreaDao().save(area);
				trx.commit();
                toReturn = VariaveisProjeto.INCLUSAO_REALIZADA;
			} catch (Exception ex) {
				ex.printStackTrace();
				if ( trx.isActive() ) {
					trx.rollback();
				}
				toReturn = VariaveisProjeto.ERRO_INCLUSAO;

			} finally {
				this.close();
			}
		} 
		return toReturn; 
	}


	public Integer update(Area area) {

		Integer toReturn = 0;

		EntityTransaction trx = this.getTransaction();

		toReturn = validarDigitacao(area);
		
		if  ( toReturn == VariaveisProjeto.DIGITACAO_OK) {

			try {

				trx.begin();
				this.getAreaDao().update(area);
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


	public Integer delete(Area area) {
		Integer toReturn = 0;
		EntityTransaction trx = this.getTransaction();
		try {

			trx.begin();
			Area areaEncontrada = this.getAreaDao().findById(area.getId());
			this.getAreaDao().remove(areaEncontrada);
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





	public Area findById(Integer id) {
		return this.getAreaDao().findById(id);
	}


	public List<Area> findAll(){
		return this.getAreaDao().findAll(Area.class);
	}



	public Integer validarDigitacao(Area area) {

		if ( VariaveisProjeto.digitacaoCampo(area.getNome())) {
			return VariaveisProjeto.AREA_NOME;
		}
		
		return VariaveisProjeto.DIGITACAO_OK;
	}


	public AreaDao getAreaDao() {
		return areaDao;
	}

	public Integer countTotalRegister() {
		return areaDao.countTotalRegister(Area.class);
	}

	public List<Area> listDepartamentoPaginacao(Integer numeroPagina, Integer defaultPagina) {
		
		return areaDao.listAreaPaginacao(numeroPagina,defaultPagina);
	}









}
