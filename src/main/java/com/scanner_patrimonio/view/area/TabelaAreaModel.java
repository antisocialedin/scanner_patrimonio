package com.scanner_patrimonio.view.area;

import java.util.List;
import javax.swing.table.AbstractTableModel;

import com.scanner_patrimonio.model.models.Area;

public class TabelaAreaModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1052577058138834756L;


	private final String colunas[] = {"Código","Nome"};
	
	
	private static final int CODIGO = 0;
	private static final int NOME = 1;
	
	private List<Area> listaArea;
	
		
	public List<Area> getListaArea() {
		return listaArea;
	}

	public void setListaArea(List<Area> listaArea) {
		this.listaArea = listaArea;
	}
	
	
	public Area getArea(int rowIndex) {
		return getListaArea().get(rowIndex);
	}

	
	public void saveArea(Area area) {
		getListaArea().add(area);
		fireTableRowsInserted(getRowCount()-1, getColumnCount()-1);
	}
	
	public void updateArea(Area area, int rowIndex) {
		getListaArea().set(rowIndex, area);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	
	public void removeArea(int rowIndex) {
		getListaArea().remove(rowIndex);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	
	public void removeAll() {
		getListaArea().clear();
		fireTableDataChanged();
	}
	
	
	@Override
	public int getRowCount() {
		return getListaArea().size();
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
		Area area = getListaArea().get(rowIndex);
		switch(columnIndex) {
		case CODIGO:
			return  area.getId();
		case NOME:
			return area.getNome();
		default:
			return area;
		}
	}
	
	
	public Class<?> getColumnClass(int columnIndex){
		switch(columnIndex) {
		case CODIGO:
			return  Integer.class;
		case NOME:
			return String.class;
		default:
			return null;
		}
		
	}
	

	public String[] getColunas() {
		return colunas;
	}
	
	
	

}
