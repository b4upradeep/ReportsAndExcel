package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.ui.RefineryUtilities;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class FileUpload extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileUpload frame = new FileUpload();
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
	public FileUpload() {
		setTitle("Upload Excel File");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(201, 237, 150, 30);
		lblNewLabel.setVisible(false);
		contentPane.add(lblNewLabel);
		JButton externalExamReport = new JButton("External Exam Performance Report");
		externalExamReport.setBounds(300, 300, 250,30);
		externalExamReport.setVisible(false);
		contentPane.add(externalExamReport);
		
		JButton performanceLowVariationReport = new JButton("Performance Variation Report I");
		performanceLowVariationReport.setBounds(300, 340, 250, 30);
		performanceLowVariationReport.setVisible(false);
		contentPane.add(performanceLowVariationReport);
		
		JButton btnChooseFileTo = new JButton("Choose File to Upload");
		btnChooseFileTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Set Excel File to Upload");
				int checker = chooser.showOpenDialog(null);
				if (checker == JFileChooser.APPROVE_OPTION) {
					String excelPath = chooser.getSelectedFile().getPath();
					if ((!excelPath.endsWith(".xlsx")) && (!excelPath.endsWith(".xls"))) {
						externalExamReport.setVisible(false);
						performanceLowVariationReport.setVisible(false);
						lblNewLabel.setText("");
						JOptionPane.showMessageDialog(null, "Upload a valid excel file");
					} else {
							ExcelReader reader = new ExcelReader();
							Course courseDetails = reader.readDataFromExcelFile(excelPath);
							
							lblNewLabel.setText("File Upload Successful");
							externalExamReport.setVisible(true);
							performanceLowVariationReport.setVisible(true);
							performanceLowVariationReport.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent arg0) {
									String message = lblNewLabel.getText();
									if("File Upload Successful".equals(message)){
										
										
										List<Integer> variationParams = AppUtils.findPerformanceVariation(courseDetails);
										List<Integer> variationParamIntSet = new ArrayList<Integer>();
										List<String> variationParamStringSet = new ArrayList<String>();
										variationParamIntSet.add(variationParams.get(1));
										variationParamStringSet.add("Greater than 10%");
										variationParamStringSet.add("less than 10 %");
										int totalStudents = courseDetails.getStudents().size();
										variationParamIntSet.add(totalStudents - variationParams.get(1));
										ReportGenerator reports = new ReportGenerator("10% Variation between External and Internal Marks",variationParamStringSet,variationParamIntSet);
										reports.setSize(560,367);
										RefineryUtilities.centerFrameOnScreen(reports);
										reports.setVisible(true);

									} else {
										JOptionPane.showMessageDialog(null, "Report Generated only \n when file upload is successful");
									}
								}

							});
							externalExamReport.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent arg0) {
									String message = lblNewLabel.getText();
									if("File Upload Successful".equals(message)){
										
										
										List<Integer> variationParams = AppUtils.findPerformanceVariation(courseDetails);
										List<Integer> variationParamIntSet = new ArrayList<Integer>();
										List<String> variationParamStringSet = new ArrayList<String>();
										variationParamIntSet.add(variationParams.get(0));
										variationParamStringSet.add("External > Internal");
										variationParamStringSet.add("Internal > External");
										int totalStudents = courseDetails.getStudents().size();
										variationParamIntSet.add(totalStudents - variationParams.get(0));
										ReportGenerator reports = new ReportGenerator("External Marks Vs Internal Marks",variationParamStringSet,variationParamIntSet);
										reports.setSize(560,367);
										RefineryUtilities.centerFrameOnScreen(reports);
										reports.setVisible(true);

									} else {
										JOptionPane.showMessageDialog(null, "Report Generated only \n when file upload is successful");
									}
								}
							});
							
						
					}
				}

			}
		});
		btnChooseFileTo.setBounds(201, 121, 158, 23);
		contentPane.add(btnChooseFileTo);
			
	}
}
