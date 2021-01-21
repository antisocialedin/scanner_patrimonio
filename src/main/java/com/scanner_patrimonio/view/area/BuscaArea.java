package com.scanner_patrimonio.view.area;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import com.scanner_patrimonio.model.models.Area;
import com.scanner_patrimonio.model.service.AreaService;

public class BuscaArea extends JDialog {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 255481063902919635L;
	private static final int CODIGO = 0;
	private static final int NOME = 1;

	private final JPanel contentPanel = new JPanel();
	private JLabel lblPesquisaArea;
	private JTextField textField;
	private JScrollPane scrollPane;
	private JTable tableArea;

	private TabelaAreaModel tabelaAreaModel;
	private TableRowSorter<TabelaAreaModel> sortTabelaArea;
	private List<Area> listaArea;

	private Integer codigoArea;
	private String nomeArea;
	private boolean selectArea;
	
	private JButton btnInserirArea;

//	public static void main(String[] args) {
//		try {
//			BuscaArea dialog = new BuscaArea();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public BuscaArea(JFrame frame, boolean modal) {
		super(frame, modal);
		initComponents();
		setResizable(false);
		iniciarDados();
		
		
	}
	
	private void iniciarDados(){
		listaArea = new ArrayList<Area>();
	}
	

	private void initComponents() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 818, 511);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		lblPesquisaArea = new JLabel("Pesquisar Area:");

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 String filtro = textField.getText();
	             filtraNomeArea(filtro);
			}

			
		});
		
		textField.setColumns(10);

		scrollPane = new JScrollPane();

		btnInserirArea = new JButton("Cadastrar Area");
		btnInserirArea.setMnemonic(KeyEvent.VK_C);
		btnInserirArea.setIcon(new ImageIcon(
				BuscaArea.class.getResource("/com/scanner_patrimonio/struct/imagens/application_form_add.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup().addGap(38)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)
								.addComponent(btnInserirArea).addGroup(Alignment.TRAILING,
										gl_contentPanel.createSequentialGroup().addComponent(lblPesquisaArea)
												.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(textField,
														GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)))
						.addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addGap(38)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblPesquisaArea)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnInserirArea)
				.addContainerGap(34, Short.MAX_VALUE)));

		tableArea = new JTable();
	    tabelaAreaModel = new TabelaAreaModel();
		
		tabelaAreaModel.setListaArea(carregarListaArea());
		tableArea.setModel(tabelaAreaModel);
		scrollPane.setViewportView(tableArea);
	
		tableArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sortTabelaArea = new TableRowSorter<TabelaAreaModel>(tabelaAreaModel);
		tableArea.setRowSorter(sortTabelaArea);
		tableArea.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//		tableArea.getColumnModel().getColumn(CODIGO).setWidth(11);
//		tableArea.getColumnModel().getColumn(NOME).setWidth(100);
//		
		
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selecionaArea();
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setSelectArea(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

	}

	protected void selecionaArea() {
		if ( tableArea.getSelectedRow() != -1 && 
			 tableArea.getSelectedRow() < tabelaAreaModel.getRowCount() ) {
			 setCodigoArea(Integer.valueOf(tableArea.getValueAt(tableArea.getSelectedRow(), CODIGO).toString()));
			 setNomeArea(tableArea.getValueAt(tableArea.getSelectedRow(), NOME).toString());
			 setSelectArea(true);
			 dispose();
		} else {
			setSelectArea(false);	
		}
		
	}

	private List<Area> carregarListaArea() {
		AreaService areaService = new AreaService();
		return areaService.findAll();
	}
	
	
	private void filtraNomeArea(String filtro) {
		RowFilter<TabelaAreaModel, Object> rowFilter = null;
		try {
			rowFilter = RowFilter.regexFilter(filtro);
		} catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaArea.setRowFilter(rowFilter);
		
	}

	public Integer getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(Integer codigoArea) {
		this.codigoArea = codigoArea;
	}

	public String getNomeArea() {
		return nomeArea;
	}

	public void setNomeArea(String nomeArea) {
		this.nomeArea = nomeArea;
	}
	
	public boolean isSelectArea() {
		return selectArea;
	}

	public void setSelectArea(boolean selectArea) {
		this.selectArea = selectArea;
	}

	
	
	
	
	
}
