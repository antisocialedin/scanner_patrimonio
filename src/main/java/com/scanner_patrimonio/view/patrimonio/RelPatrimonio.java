package com.scanner_patrimonio.view.patrimonio;

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
import com.scanner_patrimonio.model.models.Patrimonio;
import com.scanner_patrimonio.model.service.JasperReportsService;
import com.scanner_patrimonio.model.service.PatrimonioService;

public class RelPatrimonio extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -23482492054485004L;

	
	private final JPanel contentPanel = new JPanel();
	private JButton btnRelatorio;

	
	public RelPatrimonio(JFrame frame, boolean modal) {

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
		printJasperReport.setFile("rel_patrimonio_sql");
		setVisible(false);
		jasperReportsService.generateSQLReports(printJasperReport);
	}

	private void imprimeRelatorioPorLista() {
		PatrimonioService patrimonioService = new PatrimonioService();
		PrintJasperReport printJasperReport = new PrintJasperReport();
		JasperReportsService jasperReportsService = new JasperReportsService();

	    List<Patrimonio> listaPatrimonio = patrimonioService.findAll();
		
		printJasperReport.setFile("rel_patrimonio");
		printJasperReport.setCollection(listaPatrimonio);
		setVisible(false);
		jasperReportsService.generateListReports(printJasperReport);
	}
	
	
	
	
}

