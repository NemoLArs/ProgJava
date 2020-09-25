package xmlconverter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.awt.event.*;
import java.io.*;

public class UIFrame extends JFrame implements ActionListener {
	
	public static String filepath;
	public static String filename;
	public static String savepath;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	JMenuBar mb;
	JMenu file;
	JButton butloadfile;
	JMenuItem exit;
	static JButton butgo;
	JButton butsavepath;
	JLabel labelinfo;
	JLabel labelloadpath;
	JLabel labelsavepath;
	
	
	//Setting all data and information to the UI components
	UIFrame() {
		exit = new JMenuItem("Exit Program");
		exit.addActionListener(this);
		file = new JMenu("File");
		file.add(exit);
		mb = new JMenuBar();
		mb.setBounds(0, 0, 300, 20);
		mb.add(file);
		butloadfile = new JButton();
		butloadfile.setBounds(0, 20, 300, 50);
		butloadfile.setText("Open CSV file");
		butloadfile.addActionListener(this);
		labelloadpath = new JLabel();
		labelloadpath.setBounds(0, 70, 300, 25);
		labelloadpath.setText("Selected file");
		butsavepath = new JButton();
		butsavepath.setBounds(0, 100, 300, 50);
		butsavepath.setText("Select save location");
		butsavepath.addActionListener(this);
		labelsavepath = new JLabel();
		labelsavepath.setBounds(0, 150, 300, 25);
		labelsavepath.setText("Selected save path");
		butgo = new JButton();
		butgo.setText("Convert now");
		butgo.setBounds(0, 175, 300, 50);
		butgo.setEnabled(false);
		butgo.addActionListener(this);
		labelinfo = new JLabel();	
		labelinfo.setText("<html>How to convert CSV file to XML file: <br><br>1: Click <b>open CSV file</b> and select your CSV file <br>2: Click <b>select save location</b> and select where you want to save <br>3: Click <b>Convert now</b></html>");
		labelinfo.setBounds(0, 250, 300, 100);
		
		// adding all the components to the UIFrame
		add(mb);
		add(butgo);
		add(butsavepath);
		add(butloadfile);
		add(labelinfo);
		add(labelloadpath);
		add(labelsavepath);
	}
	
	
	// User made Performed an action - Check if the action was something to trigger
	
	public void actionPerformed(ActionEvent e) {
		// User clicked Open in the menu - Open file browser on the computer
		if (e.getSource() == butloadfile) {
			JFileChooser fc = new JFileChooser();
			
			//Set the FileChooser filter to only accept CSV files and nothing else
			fc.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV", "csv");
			fc.setFileFilter(filter);
			int i = fc.showOpenDialog(this);
			if (i == JFileChooser.APPROVE_OPTION) {
				
				//Get some data from the file and set variables and references - Also enable the button "Convert Now"
				File f = fc.getSelectedFile();
				filepath = f.getPath();
				filename = f.getName();
				labelloadpath.setText("Selected CSV file: " + filename);
				butgo.setEnabled(true);

			}
		}
		// User clicked Exit in the menu - Closing program
		else if (e.getSource() == exit) {
			
			System.exit(0);
			
		}
		// User clicked Convert Button - program convert the CSV file to an XML file
		else if (e.getSource() == butgo) {
			
			
			try {
				XmlConverter.convertCSV();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (TransformerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		
		else if (e.getSource() == butsavepath) {
			
			JFileChooser fcsp = new JFileChooser();
		    fcsp.setCurrentDirectory(new java.io.File("."));
		    fcsp.setDialogTitle("Select Save Folder");
		    fcsp.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    fcsp.setAcceptAllFileFilterUsed(false);  
		    if (fcsp.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 

		      savepath = (fcsp.getSelectedFile()+ "/GeneratedXML.xml");
		      labelsavepath.setText("Selected savepath: " + savepath);
		      }
		    else {
		      System.out.println("No Selection ");
		      }
		    
		   }
			
		}
	
	
	static void displaySuccess() {
		
		butgo.setText("Successfully converted CSV to XML");
		
	}
	
		
	//main class - setting up the UIFrame to display the program

	public static void main(String[] args) {
		UIFrame uiframe = new UIFrame();
		uiframe.setSize(300, 500);
		uiframe.setLayout(null);
		uiframe.setVisible(true);
		uiframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
