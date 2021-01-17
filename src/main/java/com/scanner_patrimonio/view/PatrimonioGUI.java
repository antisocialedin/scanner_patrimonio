package com.scanner_patrimonio.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.scanner_patrimonio.model.models.Patrimonio;
import com.scanner_patrimonio.model.service.PatrimonioService;
import com.scanner_patrimonio.struct.util.VariaveisProjeto;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class PatrimonioGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8328555936072590247L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCodigo;
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;
	private JTextField textFieldCodigoId;
	private JTextField textFieldEstado;
	private JLabel checkName;
	private JLabel checkCodigo;
	private JLabel checkEstado;
	
	private boolean status = true;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatrimonioGUI frame = new PatrimonioGUI();
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
	public PatrimonioGUI() {
		setTitle("Cadastro de Patrimonio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 222);
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
					buscarPatrimonio();
					textFieldNome.requestFocus();
				}
			}
		});
		textFieldCodigoId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				buscarPatrimonio();
			}
		});
		textFieldCodigoId.setColumns(10);
		
		textFieldEstado = new JTextField();
		textFieldEstado.setColumns(10);
		
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
//------------------------------------------------------------------------------------------------------------------	
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCodigoId)
						.addComponent(lblEstado)
						.addComponent(lblCodigo)
						.addComponent(lblNome))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnIncluir)
							.addGap(18)
							.addComponent(btnAlterar)
							.addGap(18)
							.addComponent(btnExcluir)
							.addGap(18)
							.addComponent(btnSair))
						.addComponent(textFieldCodigoId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 465, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(textFieldEstado, Alignment.LEADING)
										.addComponent(textFieldCodigo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(checkEstado, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
										.addComponent(checkCodigo, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(checkName)))
					.addContainerGap(29, Short.MAX_VALUE))
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
								.addComponent(textFieldEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(checkEstado, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addGap(16)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnIncluir)
								.addComponent(btnAlterar)
								.addComponent(btnExcluir)
								.addComponent(btnSair))))
					.addGap(8))
		);
		contentPane.setLayout(gl_contentPane);
		limpaTextoCampo();
		desabilitaCheckCampo();
	}

	
//---------------------------------------------------------------------------
	protected void excluirPatrimonio() {
		
		Patrimonio patrimonio = pegarDadosPatrimonio();
		
		PatrimonioService patrimonioService = new PatrimonioService();
		
		patrimonioService.delete(patrimonio);
		
		limpaTextoCampo();
		
	}
	
//---------------------------------------------------------------------------	
	protected void incluirPatrimonio() {
		Patrimonio patrimonio = pegarDadosPatrimonio();
		//-> Teste
		//System.out.println(servidor.toString());  
		
		PatrimonioService patrimonioService = new PatrimonioService();
		
		patrimonioService.save(patrimonio);
	}

//---------------------------------------------------------------------------
	protected void alterarPatrimonio() {
		
	    Patrimonio patrimonio = pegarDadosPatrimonio();
	    
	    PatrimonioService patrimonioService = new PatrimonioService();
		
		patrimonioService.update(patrimonio);
		
		limpaTextoCampo();
	}
	
//---------------------------------------------------------------------------
	private void fecharPatrimonio() {
		dispose();
	}

//---------------------------------------------------------------------------
	private void buscarPatrimonio() {
		Patrimonio patrimonio = new Patrimonio();
		
		if (VariaveisProjeto.digitacaoCampo(textFieldCodigoId.getText())){
			textFieldCodigoId.requestFocus();
			return;
		}
		
		Integer id = Integer.valueOf(textFieldCodigoId.getText());
		
		PatrimonioService patrimonioService = new PatrimonioService();
		patrimonio = patrimonioService.findById(id);
		
		textFieldNome.setText(patrimonio.getName());
		textFieldCodigo.setText(patrimonio.getCodigo());
		textFieldEstado.setText(patrimonio.getEstado());
		
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
		 servidor.setEstado(textFieldEstado.getText());
		 
		 return servidor;
	 }

//-------------------------------------------------------------------------------------------------------
		private void limpaTextoCampo() {
			textFieldCodigoId.setText(VariaveisProjeto.LIMPA_CAMPO);
			textFieldNome.setText(VariaveisProjeto.LIMPA_CAMPO);
			textFieldCodigo.setText(VariaveisProjeto.LIMPA_CAMPO);
			textFieldEstado.setText(VariaveisProjeto.LIMPA_CAMPO);
		}

//-------------------------------------------------------------------------------------------------------
		private void desabilitaCheckCampo() {
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
	  	        textFieldEstado.requestFocus();
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

		  	        if(VariaveisProjeto.digitacaoCampo(textFieldEstado.getText())) {
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
		
		
		
		
		
		
}
