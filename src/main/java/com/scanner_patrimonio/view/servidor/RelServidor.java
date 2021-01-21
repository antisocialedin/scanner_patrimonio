package com.scanner_patrimonio.view.servidor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.scanner_patrimonio.model.models.PrintJasperReport;
import com.scanner_patrimonio.model.models.Servidor;
import com.scanner_patrimonio.model.service.JasperReportsService;
import com.scanner_patrimonio.model.service.ServidorService;

public class RelServidor extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4625690938711204399L;
	
	
	private final JPanel contentPanel = new JPanel();
	private JButton btnRelatorio;

	
	public RelServidor(JFrame frame, boolean modal) {

		super(frame, modal);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		btnRelatorio = new JButton("Relatório SQL");
		btnRelatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				imprimeRelatorioPorSQL();
			}
		});
		btnRelatorio.setBounds(10, 194, 146, 23);
		btnRelatorio.setActionCommand("");
		contentPanel.add(btnRelatorio);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						imprimeRelatorioPorLista();
						
					}

					
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	protected void imprimeRelatorioPorSQL() {
		PrintJasperReport printJasperReport = new PrintJasperReport();
		JasperReportsService jasperReportsService = new JasperReportsService();
		printJasperReport.setFile("rel_servidor_sql");
		setVisible(false);
		jasperReportsService.generateSQLReports(printJasperReport);
	}

	private void imprimeRelatorioPorLista() {
		ServidorService servidorService = new ServidorService();
		PrintJasperReport printJasperReport = new PrintJasperReport();
		JasperReportsService jasperReportsService = new JasperReportsService();

	    List<Servidor> listaServidor = servidorService.findAll();
		
		printJasperReport.setFile("rel_servidor");
		printJasperReport.setCollection(listaServidor);
		setVisible(false);
		jasperReportsService.generateListReports(printJasperReport);
	}
	
	
	
	
}
