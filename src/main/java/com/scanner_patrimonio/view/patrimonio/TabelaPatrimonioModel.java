package com.scanner_patrimonio.view.patrimonio;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.scanner_patrimonio.model.models.Patrimonio;

public class TabelaPatrimonioModel extends AbstractTableModel{
	
/**
	 * 
	 */
	private static final long serialVersionUID = -2394854102330705138L;


private final String colunas[] = {"ID","Nome","Código","Estado"};
	
	
	private static final int ID = 0;
	private static final int NOME = 1;
	private static final int CODIGO = 2;
	private static final int ESTADO = 3;
	
	private List<Patrimonio> listaPatrimonio;
	
		
	public List<Patrimonio> getListaPatrimonio() {
		return listaPatrimonio;
	}

	public void setListaUsuario(List<Patrimonio> listaPatrimonio) {
		this.listaPatrimonio = listaPatrimonio;
	}
	
	
	public Patrimonio getPatrimonio(int rowIndex) {
		return getListaPatrimonio().get(rowIndex);
	}

	
	public void savePatrimonio(Patrimonio patrimonio) {
		getListaPatrimonio().add(patrimonio);
		fireTableRowsInserted(getRowCount()-1, getColumnCount()-1);
	}
	
	public void updatePatrimonio(Patrimonio patrimonio, int rowIndex) {
		getListaPatrimonio().set(rowIndex, patrimonio);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	
	public void removePatrimonio(int rowIndex) {
		getListaPatrimonio().remove(rowIndex);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	
	public void removeAll() {
		getListaPatrimonio().clear();
		fireTableDataChanged();
	}
	
	
	@Override
	public int getRowCount() {
		return getListaPatrimonio().size();
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
		Patrimonio patrimonio = getListaPatrimonio().get(rowIndex);
		switch(columnIndex) {
		case ID:
			return  patrimonio.getId();
		case NOME:
			return patrimonio.getName();
		case CODIGO:
			return patrimonio.getCodigo();
		case ESTADO:
			return patrimonio.getEstado();
		default:
			return patrimonio;
		}
	}
	
	
	public Class<?> getColumnClass(int columnIndex){
		switch(columnIndex) {
		case ID:
			return  Integer.class;
		case NOME:
			return String.class;
		case CODIGO:
			return String.class;
		case ESTADO:
			return String.class;
		default:
			return null;
		}
		
	}
	

	public String[] getColunas() {
		return colunas;
	}
	
	
	
}
