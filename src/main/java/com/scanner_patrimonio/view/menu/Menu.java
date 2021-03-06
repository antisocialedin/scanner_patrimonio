package com.scanner_patrimonio.view.menu;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import com.scanner_patrimonio.main.Login;
import com.scanner_patrimonio.view.patrimonio.TabelaPatrimonio;
import com.scanner_patrimonio.view.servidor.TabelaServidor;

public class Menu extends JFrame {



	/**
	 * 
	 */
	private static final long serialVersionUID = -7498788367421801305L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu arquivo;
	private JMenuItem servidor;
	private JMenuItem logout;
	
	
	private Login login;
	private JMenu sair;
	private JMenuItem sair_sistema;
	private JMenuItem patrimonio;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Menu frame = new Menu();
	 * frame.setVisible(true); } catch (Exception e) { e.printStackTrace(); } } });
	 * }
	 */

	/**
	 * Create the frame.
	 */
	public Menu(Login login) {
		this.login = login;
		initComponents();
	}
	
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1115, 612);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		arquivo = new JMenu("Arquivo");
		menuBar.add(arquivo);
		
		servidor = new JMenuItem("Servidor");
		servidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaServidor tabelaServidor = new TabelaServidor();
				centralizaForm(tabelaServidor);
				contentPane.add(tabelaServidor);
				tabelaServidor.setVisible(true);
				
			}
		});
		arquivo.add(servidor);
		
		patrimonio = new JMenuItem("Patrimonio");
		patrimonio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaPatrimonio tabelaPatrimonio = new TabelaPatrimonio();
				centralizaForm(tabelaPatrimonio);
				contentPane.add(tabelaPatrimonio);
				tabelaPatrimonio.setVisible(true);
				
			}
		});
		arquivo.add(patrimonio);
		
		logout = new JMenuItem("logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				login.setVisible(true);
				login.setLocationRelativeTo(null);
			}
		});
		arquivo.add(logout);
		

		sair = new JMenu("Sair");
		menuBar.add(sair);
		
		sair_sistema = new JMenuItem("Sair");
		sair_sistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		sair.add(sair_sistema);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1089, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 489, Short.MAX_VALUE)
		);
		contentPane.setLayout(gl_contentPane);
	}

	
	private void centralizaForm(JInternalFrame frame) {
		Dimension desktopSize = this.getSize();
		Dimension internalFrameSize = frame.getSize();
		frame.setLocation((desktopSize.width - internalFrameSize.width) / 2, (desktopSize.height - internalFrameSize.height) /2 );
	}
	
	
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
