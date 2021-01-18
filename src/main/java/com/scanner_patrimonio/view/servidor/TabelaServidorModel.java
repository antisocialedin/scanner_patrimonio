package com.scanner_patrimonio.view.servidor;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.scanner_patrimonio.model.models.Servidor;

public class TabelaServidorModel extends AbstractTableModel{
	

	
	private static final long serialVersionUID = -678870640561423680L;


	private final String colunas[] = {"ID","Nome","Prontuario"};
	
	
	private static final int ID = 0;
	private static final int NOME = 1;
	private static final int PRONTUARIO = 2;
	
	private List<Servidor> listaServidor;
	
		
	public List<Servidor> getListaServidor() {
		return listaServidor;
	}

	public void setListaServidor(List<Servidor> listaServidor) {
		this.listaServidor = listaServidor;
	}
	
	
	public Servidor getServidor(int rowIndex) {
		return getListaServidor().get(rowIndex);
	}

	
	public void saveServidor(Servidor servidor) {
		getListaServidor().add(servidor);
		fireTableRowsInserted(getRowCount()-1, getColumnCount()-1);
	}
	
	public void updateServidor(Servidor servidor, int rowIndex) {
		getListaServidor().set(rowIndex, servidor);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	
	public void removeServidor(int rowIndex) {
		getListaServidor().remove(rowIndex);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	
	public void removeAll() {
		getListaServidor().clear();
		fireTableDataChanged();
	}
	
	
	@Override
	public int getRowCount() {
		return getListaServidor().size();
	}
	

	@Override
	public int getColumnCount() {
		return getColunas().length;
	}
	
	
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Servidor servidor = getListaServidor().get(rowIndex);
		switch(columnIndex) {
		case ID:
			return  servidor.getId();
		case NOME:
			return servidor.getName();
		case PRONTUARIO:
			return servidor.getProntuario();
		default:
			return servidor;
		}
	}
	
	
	public Class<?> getColumnClass(int columnIndex){
		switch(columnIndex) {
		case ID:
			return  Integer.class;
		case NOME:
			return String.class;
		case PRONTUARIO:
			return String.class;
		default:
			return null;
		}
		
	}
	

	public String[] getColunas() {
		return colunas;
	}
}
