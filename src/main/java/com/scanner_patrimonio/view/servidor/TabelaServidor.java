package com.scanner_patrimonio.view.servidor;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import com.scanner_patrimonio.model.models.Servidor;
import com.scanner_patrimonio.model.service.ServidorService;


public class TabelaServidor extends JInternalFrame  {

	
	private static final long serialVersionUID = -4130970188758740022L;
	
	
	
	private static final int ID = 0;
	private static final int NOME = 1;
	private static final int PRONTUARIO = 2;
		
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tabelaServidor;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton brnExcluir;
	private JButton btnSair;
	private JPanel panel;
	private JButton btnPrimeiro;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnUltimo;
	private JLabel lblNewLabel;
	private JComboBox<String> comboBox;
	private JLabel lblPesquisar;
	private JTextField textField;
	private JButton btnPesquisar;
	private JLabel lblPagina;
	private JLabel lblInicio;
	private JLabel lblTotal;
	private JLabel lblfinal;
	
	
	
	
	
	private TabelaServidorModel tabelaServidorModel;
	private TableRowSorter<TabelaServidorModel> sortTabelaServidor;
	
	
	private Integer totalData = 0;
	private Integer defaultPagina = 5;
	private Integer totalPagina = 1;
	private Integer numeroPagina = 1;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabelaServidor frame = new TabelaServidor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TabelaServidor() {

		initComponents();
		iniciaPaginacao();
	}
	
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 941, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		
		btnIncluir = new JButton("Incluir");
		btnIncluir.setMnemonic(KeyEvent.VK_I);
		btnIncluir.setIcon(new ImageIcon(TabelaServidor.class.getResource("/com/scanner_patrimonio/struct/imagens/book_add.png")));
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.setMnemonic(KeyEvent.VK_A);
		btnAlterar.setIcon(new ImageIcon(TabelaServidor.class.getResource("/com/scanner_patrimonio/struct/imagens/book_edit.png")));
		
		brnExcluir = new JButton("Excluir");
		brnExcluir.setMnemonic(KeyEvent.VK_E);
		brnExcluir.setIcon(new ImageIcon(TabelaServidor.class.getResource("/com/scanner_patrimonio/struct/imagens/book_delete.png")));
		
		btnSair = new JButton("Sair");
		btnSair.setMnemonic(KeyEvent.VK_S);
		btnSair.setIcon(new ImageIcon(TabelaServidor.class.getResource("/com/scanner_patrimonio/struct/imagens/book_go.png")));
		
		panel = new JPanel();
		
		lblNewLabel = new JLabel("Página:");
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"5", "10", "15", "20"}));
		comboBox.setSelectedIndex(0);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				iniciaPaginacao();
			}
		});
		
		
		lblPesquisar = new JLabel("Pesquisar:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setMnemonic(KeyEvent.VK_P);
		btnPesquisar.setToolTipText("Pesquisar usuário cadastrado");
		btnPesquisar.setIcon(new ImageIcon(TabelaServidor.class.getResource("/com/scanner_patrimonio/struct/imagens/search.png")));
		
		lblPagina = new JLabel("Página ");
		
		lblInicio = new JLabel("10");
		
		lblTotal = new JLabel("total ");
		
		lblfinal = new JLabel("50");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnIncluir)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnAlterar)
							.addGap(18)
							.addComponent(brnExcluir)
							.addGap(18)
							.addComponent(btnSair))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPesquisar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 494, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnPesquisar))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(141)
								.addComponent(lblPagina)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblInicio)
								.addGap(18)
								.addComponent(lblTotal)
								.addGap(18)
								.addComponent(lblfinal))
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 820, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(78, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPesquisar)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addGap(29)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel))
									.addGap(45))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(panel, 0, 0, Short.MAX_VALUE)
									.addGap(36)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnIncluir)
								.addComponent(btnAlterar)
								.addComponent(brnExcluir)
								.addComponent(btnSair)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblfinal)
							.addComponent(lblTotal)
							.addComponent(lblInicio)
							.addComponent(lblPagina)))
					.addGap(19))
		);
		
		btnPrimeiro = new JButton("");
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = 1;
				iniciaPaginacao();
			}
		});
		btnPrimeiro.setToolTipText("Primeiro");
		btnPrimeiro.setIcon(new ImageIcon(TabelaServidor.class.getResource("/com/scanner_patrimonio/struct/imagens/go-first.png")));
		
		btnAnterior = new JButton("");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (numeroPagina > 1) {
					numeroPagina = numeroPagina - 1;
					iniciaPaginacao();
				}
			}
		});
		btnAnterior.setToolTipText("Anterior");
		btnAnterior.setIcon(new ImageIcon(TabelaServidor.class.getResource("/com/scanner_patrimonio/struct/imagens/go-previous.png")));
		
		btnProximo = new JButton("");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( numeroPagina < totalPagina ) {
					numeroPagina = numeroPagina + 1;
					iniciaPaginacao();
				}
			}
		});
		btnProximo.setToolTipText("Próximo");
		btnProximo.setIcon(new ImageIcon(TabelaServidor.class.getResource("/com/scanner_patrimonio/struct/imagens/go-next.png")));
		
		btnUltimo = new JButton("");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = totalPagina;
				iniciaPaginacao();
			}
		});
		btnUltimo.setToolTipText("Último");
		btnUltimo.setIcon(new ImageIcon(TabelaServidor.class.getResource("/com/scanner_patrimonio/struct/imagens/go-last.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnPrimeiro)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAnterior)
					.addGap(18)
					.addComponent(btnProximo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnUltimo)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnPrimeiro)
						.addComponent(btnAnterior)
						.addComponent(btnProximo)
						.addComponent(btnUltimo))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		tabelaServidor = new JTable();
		scrollPane.setViewportView(tabelaServidor);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	
	protected void iniciaPaginacao() {
	      
		totalData = buscaTotalRegistroServidor();
		
		defaultPagina = Integer.valueOf(comboBox.getSelectedItem().toString());
		
		Double totalPaginasExistenes = Math.ceil(totalData.doubleValue() / defaultPagina.doubleValue());
	
		totalPagina = totalPaginasExistenes.intValue();
		
		if ( numeroPagina.equals(1)) {
			btnPrimeiro.setEnabled(false);
			btnProximo.setEnabled(false);
		} else {
			btnPrimeiro.setEnabled(true);
			btnProximo.setEnabled(true);
		}
		
		if ( numeroPagina.equals(totalPagina)) {
			btnUltimo.setEnabled(false);
			btnProximo.setEnabled(false);
		} else {
			btnUltimo.setEnabled(true);
			btnProximo.setEnabled(true);
		}
		
		if (numeroPagina > totalPagina ) {
			numeroPagina = 1;
		}
			
		tabelaServidorModel = new TabelaServidorModel();
		
		tabelaServidorModel.setListaServidor(carregaListaServidor(numeroPagina, defaultPagina));
		
		tabelaServidor.setModel(tabelaServidorModel);
		
		tabelaServidor.setFillsViewportHeight(true);
		
		tabelaServidor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabelaServidorModel.fireTableDataChanged();
		
		sortTabelaServidor = new TableRowSorter<TabelaServidorModel>(tabelaServidorModel);
		
		tabelaServidor.setRowSorter(sortTabelaServidor);
		
			
		tabelaServidor.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
		
		tabelaServidor.getColumnModel().getColumn(ID).setWidth(11);
		tabelaServidor.getColumnModel().getColumn(NOME).setWidth(100);
		tabelaServidor.getColumnModel().getColumn(PRONTUARIO).setWidth(100);
		
		lblInicio.setText(String.valueOf(numeroPagina));
		lblTotal.setText(String.valueOf(totalPagina));
		lblfinal.setText(String.valueOf(totalData));
		
	}

	
	
	
	private List<Servidor> carregaListaServidor(Integer numeroPagina, Integer defaultPagina) {

		ServidorService servidorService = new ServidorService();

		List<Servidor> listaServidor  = new ArrayList<Servidor>();
		
		listaServidor = servidorService.listServidorPaginacao( ( defaultPagina * (numeroPagina - 1 )), defaultPagina);
		
		return listaServidor;
	}

	private Integer buscaTotalRegistroServidor() {
		
		Integer totalRegistro = 0;
		
		ServidorService servidorService = new ServidorService();
		
		totalRegistro = servidorService.countTotalRegister();
		
		return totalRegistro;
	}

	public JTable getTable() {
		return tabelaServidor;
	}
}
