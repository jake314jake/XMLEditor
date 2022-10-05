package EdirotUi;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;



import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class Editor implements ActionListener {

	private JFrame frame;
	private JTextArea textArea;
	private JScrollPane scrollPan,ResaultscrollPan ;
	private JSpinner fontSizeSpinner;
	private JLabel lblNewLabel;
	private JButton fontColorButton;
	private JComboBox fontStyleBox;
    private String[] fontStyle;
    private JLabel lblNewLabel_1;
    private String defaultFont="SimSun";
    private String defaultTxt="<?xml version=\"1.0\" encoding=\"UTF-8\"?> "+"\n"
    		 +"<Greeting> Wellcome to XML Tags extractor (XTE) @2022 </Greeting>" ;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem openItem,saveItem,closeItem;
    private JButton addTagBtn;
    private JTextArea ResaultArea;
    private JButton extractBtn;
    private JButton ClearBtn;
    private JLabel CopyrightLab;
    
	/**
	 * Launch the application.
	 */
	public static void Open() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Editor window = new Editor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Editor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 729, 675);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frame.setTitle("XML Tags extractor (XTE)");
		
		
		
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(new Font(defaultFont,Font.PLAIN,16));
		textArea.setText(defaultTxt);
		
		scrollPan = new JScrollPane(textArea);
		scrollPan.setPreferredSize( new Dimension(480, 400));
		scrollPan.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		fontSizeSpinner = new JSpinner();
		fontSizeSpinner.setPreferredSize( new Dimension(50, 25));
		fontSizeSpinner.setValue(20);
		fontSizeSpinner.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,(int)fontSizeSpinner.getValue()));
				
			}
			
		});
		
		fontColorButton = new JButton("Color");
		fontColorButton.setPreferredSize( new Dimension(70, 40));
		fontColorButton.addActionListener(this);
		
		fontStyle=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		fontStyleBox = new JComboBox(fontStyle);
		fontStyleBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		fontStyleBox.setSelectedItem(defaultFont);
		fontStyleBox.addActionListener(this);
		
		
		lblNewLabel = new JLabel("Font Size:");
		lblNewLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		
		lblNewLabel_1 = new JLabel("Font Style:");
		lblNewLabel_1.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
		
		menuBar=new JMenuBar();
		fileMenu=new JMenu("File");
		openItem=new JMenuItem("Open");saveItem=new JMenuItem("Save");closeItem=new JMenuItem("Exit");
		openItem.addActionListener(this);saveItem.addActionListener(this);saveItem.addActionListener(this);
		fileMenu.add(openItem);fileMenu.add(saveItem);fileMenu.add(closeItem);
		menuBar.add(fileMenu);
		
		frame.setJMenuBar(menuBar);
		
		addTagBtn = new JButton("<></>");
		addTagBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int caretPosition = textArea.getCaretPosition();
				textArea.insert(" <> </> ", caretPosition);
			}
		});
		ResaultArea = new JTextArea();
		ResaultArea.setLineWrap(true);
		ResaultArea.setWrapStyleWord(true);
		ResaultArea.setFont(new Font(defaultFont,Font.PLAIN,16));
		ResaultscrollPan = new JScrollPane(ResaultArea);
		ResaultscrollPan.setPreferredSize( new Dimension(470, 100));
		ResaultscrollPan.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		extractBtn = new JButton("Extract");
		extractBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResaultArea.setText(RegExExtractXMLTagText.XMLTagPattern(textArea.getText(), ResaultArea.getText()));
			}
		});
		CopyrightLab = new JLabel("@Copyright-Yakoub Anouar-2022");
		CopyrightLab.setFont(new Font("Tahoma", Font.PLAIN, 14));
		frame.getContentPane().add(extractBtn);
		frame.getContentPane().add(addTagBtn);
		frame.getContentPane().add(lblNewLabel_1);
		frame.getContentPane().add(fontStyleBox);
		frame.getContentPane().add(fontColorButton);
		frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add( fontSizeSpinner);
		
		ClearBtn = new JButton("Clear");
		ClearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResaultArea.setText("");
			}
		});
		frame.getContentPane().add(ClearBtn);
		frame.getContentPane().add(scrollPan);
		frame.getContentPane().add(ResaultscrollPan);
		frame.getContentPane().add(CopyrightLab);
	}

	@Override
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==fontColorButton) {
			Color color=JColorChooser.showDialog(null,"Choose a color:",Color.black);
			textArea.setForeground(color);
			return;
		}
		if(e.getSource()==fontStyleBox) {
		textArea.setFont(new Font((String) fontStyleBox.getSelectedItem(),Font.PLAIN,(int)textArea.getFont().getSize()));
		return;
		}
		 if(e.getSource()==openItem) {
			   JFileChooser fileChooser = new JFileChooser();
			   fileChooser.setCurrentDirectory(new File("."));
			   FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
			   fileChooser.setFileFilter(filter);
			   
			   int response = fileChooser.showOpenDialog(null);
			   
			   if(response == JFileChooser.APPROVE_OPTION) {
			    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			    Scanner fileIn = null;
			    
			    try {
			     fileIn = new Scanner(file);
			     if(file.isFile()) {
			      while(fileIn.hasNextLine()) {
			       String line = fileIn.nextLine()+"\n";
			       textArea.append(line);
			      }
			     }
			    } catch (FileNotFoundException e1) {
			     // TODO Auto-generated catch block
			     e1.printStackTrace();
			    }
			    finally {
			     fileIn.close();
			    }
			   }
			   return;
			  }
			  if(e.getSource()==saveItem) {
			   JFileChooser fileChooser = new JFileChooser();
			   fileChooser.setCurrentDirectory(new File("."));
			   
			   int response = fileChooser.showSaveDialog(null);
			   
			   if(response == JFileChooser.APPROVE_OPTION) {
			    File file;
			    PrintWriter fileOut = null;
			    
			    file = new File(fileChooser.getSelectedFile().getAbsolutePath());
			    try {
			     fileOut = new PrintWriter(file);
			     fileOut.println(textArea.getText());
			    } 
			    catch (FileNotFoundException e1) {
			     // TODO Auto-generated catch block
			     e1.printStackTrace();
			    }
			    finally {
			     fileOut.close();
			    }   
			   }
			   return;
			  }
			  if(e.getSource()==closeItem) {
			   System.exit(0);
			   return;
			  }  
	}
	 
	
}
