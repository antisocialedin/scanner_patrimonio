package com.scanner_patrimonio.view.servidor;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

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
import javax.swing.RowFilter;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableRowSorter;

import com.scanner_patrimonio.struct.util.VariaveisProjeto;
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
	private JTextField textFieldNome;
	private JButton btnPesquisar;
	private JLabel lblPagina;
	private JLabel lblInicio;
	private JLabel lblde;
	private JLabel lblfinal;

	private TabelaServidorModel tabelaServidorModel;
	private TableRowSorter<TabelaServidorModel> sortTabelaServidor;
	
	
	private Integer totalData = 0;
	private Integer defaultPagina = 5;
	private Integer totalPagina = 1;
	private Integer numeroPagina = 1;
	private JLabel lblNewLabel_1;
	private JLabel totalRegistros;	
	

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
//------------------------------------------------------------------------------------------//		
		btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirServidor();
				iniciaPaginacao();
			}
			
		});
		btnIncluir.setMnemonic(KeyEvent.VK_I);
		btnIncluir.setIcon(new ImageIcon(TabelaServidor.class.getResource("/com/scanner_patrimonio/struct/imagens/book_add.png")));

		//------------------------------------------------------------------------------------------//
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			alterarServidor();
			iniciaPaginacao();
			}
		});
		btnAlterar.setMnemonic(KeyEvent.VK_A);
		btnAlterar.setIcon(new ImageIcon(TabelaServidor.class.getResource("/com/scanner_patrimonio/struct/imagens/book_edit.png")));
		
		//------------------------------------------------------------------------------------------//
		brnExcluir = new JButton("Excluir");
		brnExcluir.setMnemonic(KeyEvent.VK_E);
		brnExcluir.setIcon(new ImageIcon(TabelaServidor.class.getResource("/com/scanner_patrimonio/struct/imagens/book_delete.png")));
		
		//------------------------------------------------------------------------------------------//
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setMnemonic(KeyEvent.VK_S);
		btnSair.setIcon(new ImageIcon(TabelaServidor.class.getResource("/com/scanner_patrimonio/struct/imagens/book_go.png")));
		//------------------------------------------------------------------------------------------//
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
		
		//------------------------------------------------------------------------------------------//	
		lblPesquisar = new JLabel("Pesquisar:");
		
		textFieldNome = new JTextField();
		textFieldNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
	             String filtro = textFieldNome.getText();
	             filtraNomeServidor(filtro);
	             
	             
				
			}
		});
		textFieldNome.setColumns(10);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPesquisar.setMnemonic(KeyEvent.VK_P);
		btnPesquisar.setToolTipText("Pesquisar usuário cadastrado");
		btnPesquisar.setIcon(new ImageIcon(TabelaServidor.class.getResource("/com/scanner_patrimonio/struct/imagens/search.png")));
		
		lblPagina = new JLabel("Página ");
		
		lblInicio = new JLabel("10");
		
		lblde = new JLabel("de");
		
		lblfinal = new JLabel("50");
		
		lblNewLabel_1 = new JLabel("total de Registros:");
		
		totalRegistros = new JLabel("");
		
		JPanel panel_1 = new JPanel();
		
		//------------------------------------------------------------------------------------------//
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
							.addComponent(btnSair)
							.addGap(115)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPesquisar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 494, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnPesquisar))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(51)
								.addComponent(lblPagina)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblInicio)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblde)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblfinal)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel_1)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(totalRegistros)
								.addGap(2))
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 820, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(74, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPesquisar)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel))
								.addGap(45))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(panel, 0, 0, Short.MAX_VALUE)
								.addGap(36)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPagina)
								.addComponent(lblInicio)
								.addComponent(lblde)
								.addComponent(lblfinal)
								.addComponent(lblNewLabel_1)
								.addComponent(totalRegistros))
							.addGap(54)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnIncluir)
								.addComponent(btnAlterar)
								.addComponent(brnExcluir)
								.addComponent(btnSair))
							.addGap(19))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		
		JButton btnRelatrio = new JButton("Relatório");
		btnRelatrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RelServidor relServidor = new RelServidor(new JFrame(), true);
				relServidor.setLocationRelativeTo(null);
				setVisible(false);
				relServidor.setVisible(true);
			}
		});
		btnRelatrio.setIcon(new ImageIcon(TabelaServidor.class.getResource("/com/scanner_patrimonio/struct/imagens/pdf.png")));
		panel_1.add(btnRelatrio);
		
		//------------------------------------------------------------------------------------------//		
		btnPrimeiro = new JButton("");
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = 1;
				iniciaPaginacao();
			}
		});
		btnPrimeiro.setToolTipText("Primeiro");
		btnPrimeiro.setIcon(new ImageIcon(TabelaServidor.class.getResource("/com/scanner_patrimonio/struct/imagens/go-first.png")));
		
		//------------------------------------------------------------------------------------------//		
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
		
		//------------------------------------------------------------------------------------------//
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
		
		//------------------------------------------------------------------------------------------//
		btnUltimo = new JButton("");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numeroPagina = totalPagina;
				iniciaPaginacao();
			}
		});
		btnUltimo.setToolTipText("Último");
		btnUltimo.setIcon(new ImageIcon(TabelaServidor.class.getResource("/com/scanner_patrimonio/struct/imagens/go-last.png")));
		
		//------------------------------------------------------------------------------------------//
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
	
	//------------------------------------------------------------------------------------------//
	protected void filtraNomeServidor(String filtro) {
		RowFilter<TabelaServidorModel, Object> rowFilter = null;
		try {
			rowFilter = RowFilter.regexFilter(filtro);
		} catch(PatternSyntaxException e) {
			return;
		}
		sortTabelaServidor.setRowFilter(rowFilter);
	}
	//------------------------------------------------------------------------------------------//
	protected void alterarServidor() {
		if ( tabelaServidor.getSelectedRow() != -1 && tabelaServidor.getSelectedRow() < tabelaServidorModel.getRowCount()) {
			int linha = tabelaServidor.getSelectedRow();
			ServidorGUI servidor = new ServidorGUI(new JFrame(), true, tabelaServidor, tabelaServidorModel, linha, VariaveisProjeto.ALTERACAO);
			servidor.setLocationRelativeTo(null);
			servidor.setVisible(true);
		}
		
	}

	//------------------------------------------------------------------------------------------//
	private void incluirServidor() {
		ServidorGUI servidor = new ServidorGUI(new JFrame(), true, tabelaServidor, tabelaServidorModel, 0, VariaveisProjeto.INCLUSAO);
		servidor.setLocationRelativeTo(null);
        servidor.setResizable(false);
		servidor.setVisible(true);
	}
	
	//------------------------------------------------------------------------------------------//
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
		lblfinal.setText(String.valueOf(totalPagina));
		totalRegistros.setText(String.valueOf(totalData));
		
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
