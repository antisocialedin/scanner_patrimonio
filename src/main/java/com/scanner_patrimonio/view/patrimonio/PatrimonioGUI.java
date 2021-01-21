package com.scanner_patrimonio.view.patrimonio;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import com.scanner_patrimonio.model.models.Area;
import com.scanner_patrimonio.model.models.Patrimonio;
import com.scanner_patrimonio.model.service.AreaService;
import com.scanner_patrimonio.model.service.PatrimonioService;
import com.scanner_patrimonio.struct.util.VariaveisProjeto;
import com.scanner_patrimonio.view.area.BuscaArea;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class PatrimonioGUI extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8328555936072590247L;
	
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCodigo;
	private JTextField textFieldCodigoId;
	private JTextField textFieldEstado;
	private JTextField textFieldEstado_1;
	private JTextField textFieldArea;
	
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;
	private JButton btnBuscarArea;
	
	private JLabel checkName;
	private JLabel checkCodigo;
	private JLabel checkEstado;
	private JLabel lblArea;
	
	private boolean status = true;
	
	private JTable tabelaPatrimonio;
	private TabelaPatrimonioModel tabelaPatrimonioModel;
    private int linha = 0;
    private int acao  = 0;
    
    private Area area;
    private JPanel panel;
    private JButton btnRelatrio;
    
	/**
	 * Launch the application.
	 */
   /*
	*public static void main(String[] args) {
	*	EventQueue.invokeLater(new Runnable() {
	*		public void run() {
	*			try {
	*				PatrimonioGUI frame = new PatrimonioGUI();
	*				frame.setVisible(true);
	*			} catch (Exception e) {
	*				e.printStackTrace();
	*			}
	*		}
	*	});
	*}
	*/
    
    
	/**
	 * Create the frame.
	 */
	public PatrimonioGUI(JFrame frame, boolean modal, JTable tabelaPatrimonio, TabelaPatrimonioModel tabelaPatrimonioModel, int linha, int acao) {
		
		
		super(frame, modal);

		initComponents();
		
		this.tabelaPatrimonio = tabelaPatrimonio;
		this.tabelaPatrimonioModel = tabelaPatrimonioModel;
		this.linha = linha;
        this.acao = acao;
		
		limpaTextoCampo();
		
		desabilitaCheckCampos();
		
		configuraAcaoPatrimonio();
		
	}

	//--------------------------------------------------------------------------------------------------//
	private void configuraAcaoPatrimonio() {
		
		if (this.acao == VariaveisProjeto.INCLUSAO) {
			btnIncluir.setVisible(true);
			btnAlterar.setVisible(false);
			btnExcluir.setVisible(false);
		}
		if (this.acao == VariaveisProjeto.ALTERACAO) {
			btnAlterar.setVisible(true);
			btnExcluir.setVisible(false);
			btnIncluir.setVisible(false);
			buscarPatrimonio();
		}
		if (this.acao == VariaveisProjeto.EXCLUSAO) {
			btnExcluir.setVisible(true);
			btnIncluir.setVisible(false);
			btnAlterar.setVisible(false);
			buscarPatrimonio();
		}
	}

//-----------------------------------------------------------------------------------------------------------------//
	private void initComponents() {
		setTitle("Cadastro de Patrimonio");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
//------------------------------------------------------------------------------------------------------------------	
		JLabel lblNome = new JLabel("Nome:");
		
		textFieldNome = new JTextField();
		textFieldNome.addFocusListener(new FocusAdapter() {
			@Override
            public void focusLost(FocusEvent e) {
                if( verificaDigitacaoNome() ) {
                    textFieldNome.requestFocus();
                } else {
                    digitacaoNomeValido();
                }
            }
        });

        textFieldNome.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if( verificaDigitacaoNome() ) {
                        textFieldNome.requestFocus();
                    } else {
                        digitacaoNomeValido();
                    }
                }
            }
        });
		textFieldNome.setColumns(10);
		
//------------------------------------------------------------------------------------------------------------------	
		JLabel lblCodigo = new JLabel("Código:");
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.addFocusListener(new FocusAdapter() {
			@Override
            public void focusLost(FocusEvent e) {
                if( verificaDigitacaoCodigo() ) {
                    textFieldCodigo.requestFocus();
                } else {
                    digitacaoCodigoValido();
                }
            }
        });

        textFieldCodigo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if( verificaDigitacaoCodigo() ) {
                        textFieldNome.requestFocus();
                    } else {
                        digitacaoCodigoValido();
                    }
                }
            }
        });
		textFieldCodigo.setText("");
		textFieldCodigo.setColumns(10);
		
//------------------------------------------------------------------------------------------------------------------	
		JLabel lblEstado = new JLabel("Estado:");
		
		textFieldEstado = new JTextField();
		textFieldEstado.addFocusListener(new FocusAdapter() {
			@Override
            public void focusLost(FocusEvent e) {
                if( verificaDigitacaoEstado() ) {
                    textFieldEstado.requestFocus();
                } else {
                    digitacaoEstadoValido();
                }
            }
        });

        textFieldEstado.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if( verificaDigitacaoEstado() ) {
                        textFieldEstado.requestFocus();
                    } else {
                        digitacaoEstadoValido();
                    }
                }
            }
        });
        textFieldEstado.setColumns(10);
//------------------------------------------------------------------------------------------------------------------
		btnIncluir = new JButton("Incluir");
		btnIncluir.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/application_add.png")));
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirPatrimonio();
			}
		});
		
//------------------------------------------------------------------------------------------------------------------	
		btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/application_edit.png")));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarPatrimonio();
			}
		});
		
//------------------------------------------------------------------------------------------------------------------	
		btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/iconFechar.png")));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirPatrimonio();
			}
		});
//------------------------------------------------------------------------------------------------------------------
		btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/sair.png")));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharPatrimonio();
			}
		});

//------------------------------------------------------------------------------------------------------------------	
		JLabel lblCodigoId = new JLabel("ID:");
		
		textFieldCodigoId = new JTextField();
		textFieldCodigoId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					//buscarPatrimonio();
					textFieldNome.requestFocus();
				}
			}
		});
		textFieldCodigoId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				//buscarPatrimonio();
			}
		});
		textFieldCodigoId.setColumns(10);
		
		textFieldEstado_1 = new JTextField();
		textFieldEstado_1.setColumns(10);
		
//------------------------------------------------------------------------------------------------------------------
		checkName = new JLabel("");
		checkName.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/ok.png")));
		checkName.setHorizontalAlignment(SwingConstants.CENTER);
		
		checkCodigo = new JLabel("");
		checkCodigo.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/ok.png")));
		checkCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		
		checkEstado = new JLabel("");
		checkEstado.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/ok.png")));
		checkEstado.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblArea = new JLabel("Area:");
		
		textFieldArea = new JTextField();
		textFieldArea.setEditable(false);
		textFieldArea.setColumns(10);
		
		btnBuscarArea = new JButton("");
		btnBuscarArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscaArea();
			}
		});
		btnBuscarArea.setMnemonic(KeyEvent.VK_A);
		btnBuscarArea.setToolTipText("Buscar Area");
		btnBuscarArea.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/search.png")));
		
		panel = new JPanel();
//------------------------------------------------------------------------------------------------------------------	
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblArea)
							.addComponent(lblCodigoId)
							.addComponent(lblEstado)
							.addComponent(lblCodigo)
							.addComponent(lblNome))
						.addComponent(btnIncluir))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textFieldCodigoId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(textFieldNome, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 465, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textFieldCodigo)
										.addComponent(textFieldEstado_1, GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
										.addComponent(textFieldArea))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(checkEstado, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
										.addComponent(checkCodigo, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnBuscarArea, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(checkName))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addComponent(btnAlterar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnExcluir)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSair)
							.addGap(40)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 120, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodigoId)
						.addComponent(textFieldCodigoId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(checkName)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNome)
								.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblCodigo)
									.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(checkCodigo, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEstado)
								.addComponent(textFieldEstado_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkEstado, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblArea)
							.addComponent(textFieldArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnBuscarArea))
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnIncluir)
							.addComponent(btnAlterar)
							.addComponent(btnExcluir)
							.addComponent(btnSair))
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(17))
		);
		
		btnRelatrio = new JButton("Relatório");
		btnRelatrio.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/pdf.png")));
		btnRelatrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimeRelatorio();
			}
		});
		panel.add(btnRelatrio);
		contentPane.setLayout(gl_contentPane);
		
		/*
		 * btnAlterar.setEnabled(false); btnIncluir.setEnabled(false);
		 * btnExcluir.setEnabled(false);
		 */
	}

	//---------------------------------------------------------------------------
	protected void buscaArea() {
		
		area = new Area();
		
		BuscaArea buscaArea = new BuscaArea(new JFrame(), true);
		buscaArea.setLocationRelativeTo(null);
		buscaArea.setVisible(true);
		
		if (buscaArea.isSelectArea()) {
			AreaService areaService = new AreaService();
			area = areaService.findById(buscaArea.getCodigoArea());
			textFieldArea.setText(area.getNome());
		}
	}

//---------------------------------------------------------------------------
	private void fecharPatrimonio() {
		dispose();
	}

//---------------------------------------------------------------------------
	protected void imprimeRelatorio() {
		
		RelPatrimonio relPatrimonio = new RelPatrimonio(new JFrame(), true);
		relPatrimonio.setLocationRelativeTo(null);
		setVisible(false);
		relPatrimonio.setVisible(true);
		
	}
//---------------------------------------------------------------------------
	private void buscarPatrimonio() {
		Patrimonio patrimonio = new Patrimonio();
		
		/*
		 *if (VariaveisProjeto.digitacaoCampo(textFieldCodigoId.getText())){
		 *	textFieldCodigoId.requestFocus();
		 *	return;
		 *}
		 *
		 *Integer id = Integer.valueOf(textFieldCodigoId.getText());
		 */
		
	
		patrimonio = tabelaPatrimonioModel.getPatrimonio(this.linha);
		
		System.out.println(patrimonio.toString());
		
		//PatrimonioService patrimonioService = new PatrimonioService();
		
		//patrimonio = patrimonioService.findById(id);
		
		textFieldCodigo.setText(String.valueOf(patrimonio.getId()));
		textFieldNome.setText(patrimonio.getName());
		textFieldCodigo.setText(patrimonio.getCodigo());
		textFieldEstado_1.setText(patrimonio.getEstado());
		
	}
	
//-----------------------------------------------------------------------------------------------------------
	public Patrimonio pegarDadosPatrimonio() {
		 Patrimonio servidor = new Patrimonio();
		 
		 if (VariaveisProjeto.digitacaoCampo(textFieldCodigoId.getText())){
				textFieldCodigoId.requestFocus();
			}
		 
		 servidor.setId(Integer.valueOf(textFieldCodigoId.getText()));
		 servidor.setName(textFieldNome.getText());
		 servidor.setCodigo(textFieldCodigo.getText());
		 servidor.setEstado(textFieldEstado_1.getText());
		
		 return servidor;
	 }

//-------------------------------------------------------------------------------------------------------
		private void limpaTextoCampo() {
			textFieldCodigoId.setText(VariaveisProjeto.LIMPA_CAMPO);
			textFieldNome.setText(VariaveisProjeto.LIMPA_CAMPO);
			textFieldCodigo.setText(VariaveisProjeto.LIMPA_CAMPO);
			textFieldEstado_1.setText(VariaveisProjeto.LIMPA_CAMPO);
		}

//-------------------------------------------------------------------------------------------------------
		private void desabilitaCheckCampos() {
		    checkName.setVisible(false);
		    checkCodigo.setVisible(false);
		    checkEstado.setVisible(false);
		}
		
//-------------------------------------------------------------------------------------------------------//
		//----------------------------------Name--------------------------------------------------//

	    private void digitacaoNomeValido() {
	        status = true;
	        mudaStatusCheckNome();
	        checkName.setVisible(true);
	        textFieldCodigo.requestFocus();
	    }

//-------------------------------------------------------------------------------------------------------//

	    private boolean verificaDigitacaoNome() {

	        if(VariaveisProjeto.digitacaoCampo(textFieldNome.getText())) {
	            status = false;
	            mudaStatusCheckNome();
	            return true;
	        }
	        return false;
	    }
	    
//------------------------------------------------------------------------------------------------------//

	    private void mudaStatusCheckNome() {

	        checkName.setVisible(true);

	        if(status == false) {
	            checkName.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/iconFechar.png")));
	        } else {
	            checkName.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/ok.png")));
	        }
	    }	
	    
  //-------------------------------------------------------------------------------------------------------//
  		//-------------------------------------Codigo--------------------------------------------------//

	  	    private void digitacaoCodigoValido() {
	  	        status = true;
	  	        mudaStatusCheckCodigo();
	  	        checkCodigo.setVisible(true);
	  	        textFieldEstado_1.requestFocus();
	  	    }

	  //-------------------------------------------------------------------------------------------------------//

	  	    private boolean verificaDigitacaoCodigo() {

	  	        if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())) {
	  	            status = false;
	  	            mudaStatusCheckCodigo();
	  	            return true;
	  	        }
	  	        return false;
	  	    }
	  	    
	  //------------------------------------------------------------------------------------------------------//

	  	    private void mudaStatusCheckCodigo() {

	  	        checkCodigo.setVisible(true);

	  	        if(status == false) {
	  	            checkCodigo.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/iconFechar.png")));
	  	        } else {
	  	            checkCodigo.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/ok.png")));
	  	        }
	  	    }	
	  	    
  	//-------------------------------------------------------------------------------------------------------//
  		//-------------------------------------Estado--------------------------------------------------//

		  	    private void digitacaoEstadoValido() {
		  	        status = true;
		  	        mudaStatusCheckEstado();
		  	        checkEstado.setVisible(true);
		  	    }

  //-------------------------------------------------------------------------------------------------------//
		  	    private boolean verificaDigitacaoEstado() {

		  	        if(VariaveisProjeto.digitacaoCampo(textFieldEstado_1.getText())) {
		  	            status = false;
		  	            mudaStatusCheckEstado();
		  	            return true;
		  	        }
		  	        return false;
		  	    }
		  	    
  //------------------------------------------------------------------------------------------------------//

	  	    private void mudaStatusCheckEstado() {

	  	        checkEstado.setVisible(true);

	  	        if(status == false) {
	  	            checkEstado.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/iconFechar.png")));
	  	        } else {
	  	            checkEstado.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/ok.png")));
	  	        }
	  	    }

//------------------------------------------------------------------------------------------------------//	
	    	 protected void incluirPatrimonio() {
	  			
	  		Integer toReturn = 0;
	  		
	  		Patrimonio patrimonio = pegarDadosPatrimonio();
	  		
	  		PatrimonioService patrimonioService = new PatrimonioService();
	  		
	  		toReturn = patrimonioService.save(patrimonio);
	  		
	  		erroDigitacao(toReturn);
	  		
	  		if ( toReturn == VariaveisProjeto.ERRO_INCLUSAO ) {
	  			showMensagem("Erro na Inclusão do Registro, verifique com seu administrador!",
	  					   	 "Erro",JOptionPane.ERROR_MESSAGE);
	  		}
	  		if ( toReturn == VariaveisProjeto.INCLUSAO_REALIZADA) {
	  			showMensagem("Inclusão do Registro realizada com sucesso!",
	  					     "OK",JOptionPane.OK_OPTION);
	  			limpaTextoCampo();
	  			tabelaPatrimonioModel.fireTableDataChanged();
	  			patrimonio = new Patrimonio();
	  		}
	    	  }
	    	 
	    	//---------------------------------------------------------------------------
	    		protected void excluirPatrimonio() {
	    			
	    			Integer toReturn = 0;
	    			
	    			Patrimonio patrimonio = pegarDadosPatrimonio();
	    			
	    			Area area = new Area();
	    			
	    			area.setId(1);
	    			area.setNome("C-181");
	    			
	    			PatrimonioService patrimonioService = new PatrimonioService();
	    			
	    			toReturn = patrimonioService.delete(patrimonio);
	    			
	    			if ( toReturn == VariaveisProjeto.ERRO_EXCLUSAO ) {
	    				showMensagem("Erro na Exclusão do Registro, verifique com seu administrador!",
	    						   	 "Erro",JOptionPane.ERROR_MESSAGE);
	    			}
	    			if ( toReturn == VariaveisProjeto.EXCLUSAO_REALIZADA) {
	    				showMensagem("Exclusão do Registro realizada com sucesso!",
	    						     "OK",JOptionPane.OK_OPTION);
	    				limpaTextoCampo();
	    				tabelaPatrimonioModel.fireTableDataChanged();
	    				patrimonio = new Patrimonio();
	    			}
	    			
	    		}
	    		
	    	//---------------------------------------------------------------------------
	    		protected void alterarPatrimonio() {
	    			
	    			Integer toReturn = 0;
	    		    
	    			Patrimonio patrimonio = pegarDadosPatrimonio();
	    		    
	    		    Area area = new Area();
	    		    
	    		    area.setId(1);
	    			
	    			patrimonio.setArea(area);
	    			
	    		    PatrimonioService patrimonioService = new PatrimonioService();
	    			
	    			toReturn = patrimonioService.update(patrimonio);
	    			
	    			erroDigitacao(toReturn);
	    			
	    			if ( toReturn == VariaveisProjeto.ERRO_ALTERACAO ) {
	    				showMensagem("Erro na Alteração do Registro, verifique com seu administrador!",
	    						   	 "Erro",JOptionPane.ERROR_MESSAGE);
	    			}
	    			if ( toReturn == VariaveisProjeto.ALTERACAO_REALIZADA) {
	    				showMensagem("Alteração do Registro realizada com sucesso!",
	    						     "OK",JOptionPane.OK_OPTION);
	    				
	    				tabelaPatrimonioModel.fireTableDataChanged();
	    				
	    				limpaTextoCampo();
	    				patrimonio = new Patrimonio();
	    			}
	    			
	    		}
	    		
	    	//------------------------------------------------------------------------------------------------------/
	    	private void erroDigitacao(Integer toReturn) {
		  		if ( toReturn == VariaveisProjeto.PATRIMONIO_NAME ) {
		  			 status = false;
		  			 mudaStatusCheckNome();
		  			 showMensagem("Erro na digitação do Nome, verifique!","Erro", JOptionPane.ERROR_MESSAGE);
		  		}
		  		if ( toReturn == VariaveisProjeto.PATRIMONIO_CODIGO ) {
		  			 status = false;
		  			 mudaStatusCheckCodigo();
		  			 showMensagem("Erro na digitação do Codigo, verifique!","Erro", JOptionPane.ERROR_MESSAGE);
		  		}
		  		if ( toReturn == VariaveisProjeto.PATRIMONIO_ESTADO ) {
		  			 status = false;
		  			 mudaStatusCheckEstado();
		  			 showMensagem("Erro na digitação do Estado, verifique!","Erro", JOptionPane.ERROR_MESSAGE);
		  		}
	  	}
	  	

	  	//------------------------------------------------------------------------------------------------------//
	  	private void showMensagem(String mensagem, String status, int option ) {
	  		JOptionPane.showMessageDialog(null, mensagem, status, option );
	  	}	  	    
}
