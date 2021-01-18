package com.scanner_patrimonio.view.servidor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.scanner_patrimonio.model.models.Servidor;
import com.scanner_patrimonio.model.service.ServidorService;
import com.scanner_patrimonio.struct.util.VariaveisProjeto;
import com.scanner_patrimonio.view.patrimonio.PatrimonioGUI;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class ServidorGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6411661632302434554L;
	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldProntuario;
	private JTextField textFieldCodigo;
	private JPasswordField passwordFieldSenha;
	
	private JButton btnIncluir;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;
	
	private JRadioButton rdbtnVoluntario;
	private JRadioButton rdbtnAdmin;
	
	private JLabel checkName;
	private JLabel checkProntuario;
	private JLabel checkSenha;
	
	private boolean status = true;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServidorGUI frame = new ServidorGUI();
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
	public ServidorGUI() {
		setTitle("Cadastro de Servidor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 615, 317);
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
		JLabel lblProntuario = new JLabel("Prontuário:");
		
		textFieldProntuario = new JTextField();
		textFieldProntuario = new JTextField();
		textFieldProntuario.addFocusListener(new FocusAdapter() {
			@Override
            public void focusLost(FocusEvent e) {
                if( verificaDigitacaoProntuario() ) {
                    textFieldCodigo.requestFocus();
                } else {
                    digitacaoProntuarioValido();
                }
            }
        });

        textFieldProntuario.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if( verificaDigitacaoProntuario() ) {
                        textFieldNome.requestFocus();
                    } else {
                        digitacaoProntuarioValido();
                    }
                }
            }
        });
		textFieldProntuario.setText("");
		textFieldProntuario.setColumns(10);
		//------------------------------------------------------------------------------------------------------------------
		JLabel lblSenha = new JLabel("Senha:");

		passwordFieldSenha = new JPasswordField();
		
		passwordFieldSenha.addFocusListener(new FocusAdapter() {
			@Override
            public void focusLost(FocusEvent e) {
                if( verificaDigitacaoSenha() ) {
                    passwordFieldSenha.requestFocus();
                } else {
                    digitacaoSenhaValida();
                }
            }
        });

        passwordFieldSenha.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                	if( verificaDigitacaoSenha() ) {
                        passwordFieldSenha.requestFocus();
                        passwordFieldSenha.requestFocus();
                    } else {
                        digitacaoSenhaValida();
                    }
                }
        	}
        });
		//------------------------------------------------------------------------------------------------------------------
		rdbtnVoluntario = new JRadioButton("Voluntário");
		
		rdbtnVoluntario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					rdbtnAdmin.requestFocus();
				}
			}
		});
		
		//-----------------------------------------------------------------//
		
		rdbtnAdmin = new JRadioButton("Administrador");
				
				rdbtnAdmin.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						
						if(e.getKeyCode() == KeyEvent.VK_ENTER) {
							btnIncluir.requestFocus();
						}
					}
				});
		//------------------------------------------------------------------------------------------------------------------
		btnIncluir = new JButton("Incluir");
		btnIncluir.setIcon(new ImageIcon(ServidorGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/application_add.png")));
		
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				incluirServidor();
			}
		});
		//------------------------------------------------------------------------------------------------------------------
		btnAlterar = new JButton("Alterar");
		btnAlterar.setIcon(new ImageIcon(ServidorGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/application_edit.png")));
		
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarServidor();
			}
		});
		//------------------------------------------------------------------------------------------------------------------
		btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(ServidorGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/iconFechar.png")));
		//------------------------------------------------------------------------------------------------------------------
		btnSair = new JButton("Sair");
		btnSair.setIcon(new ImageIcon(ServidorGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/sair.png")));
		
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fecharServidor();
			}
		});
		//------------------------------------------------------------------------------------------------------------------
		JLabel lblCodigo = new JLabel("ID:");
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER) {
					buscarServidor();
					textFieldNome.requestFocus();
				}
			}
		});
		textFieldCodigo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				buscarServidor();
			}

			
		});
		textFieldCodigo.setColumns(10);
//------------------------------------------------------------------------------------------------------------------
		checkName = new JLabel("");
		checkName.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/ok.png")));
		checkName.setHorizontalAlignment(SwingConstants.CENTER);
		
		checkProntuario = new JLabel("");
		checkProntuario.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/ok.png")));
		checkProntuario.setHorizontalAlignment(SwingConstants.CENTER);
		
		checkSenha = new JLabel("");
		checkSenha.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/ok.png")));
		checkSenha.setHorizontalAlignment(SwingConstants.CENTER);
//------------------------------------------------------------------------------------------------------------------

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblCodigo)
						.addComponent(lblSenha)
						.addComponent(lblProntuario)
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
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, 465, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(passwordFieldSenha, Alignment.LEADING)
							.addComponent(textFieldProntuario, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(rdbtnVoluntario)
							.addGap(18)
							.addComponent(rdbtnAdmin))
						.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodigo)
						.addComponent(textFieldCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(textFieldNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProntuario)
						.addComponent(textFieldProntuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSenha)
						.addComponent(passwordFieldSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnVoluntario)
						.addComponent(rdbtnAdmin))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnIncluir)
						.addComponent(btnAlterar)
						.addComponent(btnExcluir)
						.addComponent(btnSair))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		limpaTextoCampo();
		desabilitaCheckCampo();
	}
	
	
	//------------------------------------------------------------------------------------------------------------------
	protected void excluirServidor() {
		
		Servidor servidor = pegarDadosServidor();
		
		ServidorService servidorService = new ServidorService();
		
		servidorService.delete(servidor);
		
		limpaTextoCampo();
		
	}

	//------------------------------------------------------------------------------------------------------------------
	protected void alterarServidor() {
		
	    Servidor servidor = pegarDadosServidor();
	    
	    ServidorService servidorService = new ServidorService();
		
		servidorService.update(servidor);
		
		limpaTextoCampo();
	}
	//------------------------------------------------------------------------------------------------------------------
	private void fecharServidor() {
		dispose();
	}
	//------------------------------------------------------------------------------------------------------------------
	private void buscarServidor() {
		Servidor servidor = new Servidor();
		
		if (VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())){
			textFieldCodigo.requestFocus();
			return;
		}
		
		Integer id = Integer.valueOf(textFieldCodigo.getText());
		
		ServidorService servidorService = new ServidorService();
		servidor = servidorService.findById(id);
		
		textFieldNome.setText(servidor.getName());
		textFieldProntuario.setText(servidor.getProntuario());
		passwordFieldSenha.setText(servidor.getPassword());
		
		if (servidor.isAdmin())
			rdbtnAdmin.setSelected(true);
		
		if (servidor.isVoluntario())
			rdbtnVoluntario.setSelected(true);
	}
	//------------------------------------------------------------------------------------------------------------------
	@SuppressWarnings("deprecation") 
	public Servidor pegarDadosServidor() {
		 Servidor servidor = new Servidor();
		 
		 if (VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())){
				textFieldCodigo.requestFocus();
			}
		 
		 servidor.setId(Integer.valueOf(textFieldCodigo.getText()));
		 servidor.setName(textFieldNome.getText());
		 servidor.setProntuario(textFieldProntuario.getText());
		 servidor.setPassword(passwordFieldSenha.getSelectedText());
		 
		 if (rdbtnVoluntario.isSelected()) {
			 servidor.setVoluntario(true);
		 } else {
			 servidor.setVoluntario(false);
		 }
		 
		 if (rdbtnAdmin.isSelected()) {
			 servidor.setAdmin(true);
		 } else {
			 servidor.setAdmin(false);
		 }
		 //rdbtnVoluntario.isSelected() ? servidor.setVoluntario(true): servidor.setVoluntario(false);
		 return servidor;
	 }
	//------------------------------------------------------------------------------------------------------------------
		private void limpaTextoCampo() {
			textFieldCodigo.setText(VariaveisProjeto.LIMPA_CAMPO);
			textFieldNome.setText(VariaveisProjeto.LIMPA_CAMPO);
			textFieldProntuario.setText(VariaveisProjeto.LIMPA_CAMPO);
			passwordFieldSenha.setText(VariaveisProjeto.LIMPA_CAMPO);
			rdbtnAdmin.setSelected(false);
			rdbtnVoluntario.setSelected(false);
		}
	//------------------------------------------------------------------------------------------------------------------
		private void desabilitaCheckCampo() {
		    checkName.setVisible(false);
		    checkProntuario.setVisible(false);
		    checkSenha.setVisible(false);
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
	//-------------------------------------Prontuario--------------------------------------------------//

  	    private void digitacaoProntuarioValido() {
  	        status = true;
  	        mudaStatusCheckProntuario();
  	        checkProntuario.setVisible(true);
  	        textFieldProntuario.requestFocus();
  	    }

  //-------------------------------------------------------------------------------------------------------//

  	    private boolean verificaDigitacaoProntuario() {

  	        if(VariaveisProjeto.digitacaoCampo(textFieldCodigo.getText())) {
  	            status = false;
  	            mudaStatusCheckProntuario();
  	            return true;
  	        }
  	        return false;
  	    }
  	    
  //------------------------------------------------------------------------------------------------------//

  	    private void mudaStatusCheckProntuario() {

        checkProntuario.setVisible(true);

        if(status == false) {
            checkProntuario.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/iconFechar.png")));
        } else {
            checkProntuario.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/ok.png")));
        }
  	    }		    
  	 	//-------------------------------------------------------------------------------------------------------//
  		//-------------------------------------Senha--------------------------------------------------//

  	    private void digitacaoSenhaValida() {
  	        status = true;
  	        mudaStatusCheckSenha();
  	        checkSenha.setVisible(true);
  	    }

  //-------------------------------------------------------------------------------------------------------//
  	    @SuppressWarnings("deprecation")
  	    private boolean verificaDigitacaoSenha() {

  	        if(VariaveisProjeto.digitacaoCampo(passwordFieldSenha.getSelectedText())) {
  	            status = false;
  	            mudaStatusCheckSenha();
  	            return true;
  	        }
  	        return false;
  	    }
		  	    
  //------------------------------------------------------------------------------------------------------//

  	    private void mudaStatusCheckSenha() {

        checkSenha.setVisible(true);

        if(status == false) {
            checkSenha.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/iconFechar.png")));
        } else {
            checkSenha.setIcon(new ImageIcon(PatrimonioGUI.class.getResource("/com/scanner_patrimonio/struct/imagens/ok.png")));
        }
    }
  	   
  	//------------------------------------------------------------------------------------------------------//	
  	  protected void incluirServidor() {
			
		Integer toReturn = 0;
		
		Servidor servidor = pegarDadosServidor();
		
		ServidorService servidorService = new ServidorService();
		
		toReturn = servidorService.save(servidor);
		
		erroDigitacao(toReturn);
		
		if ( toReturn == VariaveisProjeto.ERRO_INCLUSAO ) {
			showMensagem("Erro na Inclusão do Registro, verifique com seu administrador!",
					   	 "Erro",JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.INCLUSAO_REALIZADA) {
			showMensagem("Inclusão do Registro realizada com sucesso!",
					     "OK",JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaServidorModel.fireTableDataChanged();
			servidor = new Servidor();
		}
  	  }
  	  
    	//------------------------------------------------------------------------------------------------------/	
	protected void alterarUsuario() {
		Integer toReturn = 0;
		
	    Servidor servidor = pegarDadosServidor();
	    
	    ServidorService servidorService = new ServidorService();
		
		toReturn = servidorService.update(servidor);
		
		erroDigitacao(toReturn);
		
		if ( toReturn == VariaveisProjeto.ERRO_ALTERACAO ) {
			showMensagem("Erro na Alteração do Registro, verifique com seu administrador!",
					   	 "Erro",JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.ALTERACAO_REALIZADA) {
			showMensagem("Alteração do Registro realizada com sucesso!",
					     "OK",JOptionPane.OK_OPTION);
			
			tabelaServidorModel.fireTableDataChanged();
			
			limpaTextoCampo();
			servidor = new Servidor();
		}
	}

  	//------------------------------------------------------------------------------------------------------/
	private void erroDigitacao(Integer toReturn) {
		if ( toReturn == VariaveisProjeto.SERVIDOR_USER_NAME ) {
			 status = false;
			 mudaStatusCheckNome();
			 showMensagem("Erro na digitação do Nome, verifique!","Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.SERVIDOR_PRONTUARIO ) {
			 status = false;
			 mudaStatusCheckProntuario();
			 showMensagem("Erro na digitação do Prontuario, verifique!","Erro", JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.SERVIDOR_PASSWORD ) {
			 status = false;
			 mudaStatusCheckSenha();
			 showMensagem("Erro na digitação da Senha, verifique!","Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
  	//------------------------------------------------------------------------------------------------------/	
	protected void excluirUsuario() {
		
		Integer toReturn = 0;
		
		Servidor servidor = pegarDadosServidor();
		
		ServidorService servidorService = new ServidorService();
		
		toReturn = servidorService.delete(servidor);
		
		if ( toReturn == VariaveisProjeto.ERRO_EXCLUSAO ) {
			showMensagem("Erro na Exclusão do Registro, verifique com seu administrador!",
					   	 "Erro",JOptionPane.ERROR_MESSAGE);
		}
		if ( toReturn == VariaveisProjeto.EXCLUSAO_REALIZADA) {
			showMensagem("Exclusão do Registro realizada com sucesso!",
					     "OK",JOptionPane.OK_OPTION);
			limpaTextoCampo();
			tabelaServidorModel.fireTableDataChanged();
			servidor = new Servidor();
		}
		
	}
	
	//------------------------------------------------------------------------------------------------------//
	private void showMensagem(String mensagem, String status, int option ) {
		JOptionPane.showMessageDialog(null, mensagem, status, option );
	}
		
		
		
		
		
}
