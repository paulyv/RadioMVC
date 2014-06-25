package fi.pauli.radio.mvc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

public class View extends JFrame{
	
	private JPanel contentPane;
	private JButton PlayBtn;
	private JButton PauseBtn;
	private JButton StopBtn;
	private JLabel LcdLbl;
	private JLabel StateLbl;
	private JComboBox ChannelBox;
	private Channels ch = new Channels();
	private ImageIcon bg = new ImageIcon(View.class.getResource("/res/wood-texture-02.jpg"));
	
	public View() {
		
		setTitle("NettiRadio 2.0");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 223);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar jmb = new JMenuBar();
		jmb.setOpaque(false);
		jmb.setDoubleBuffered(true);
		jmb.setForeground(Color.LIGHT_GRAY);
		jmb.setBackground(Color.BLACK);
		this.setJMenuBar(jmb);
		
		JMenu file = new JMenu("File");
		jmb.add(file);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		
		file.add(exit);
		
		PlayBtn = new JButton("► Play");
		PlayBtn.setBounds(28, 121, 117, 44);
		contentPane.add(PlayBtn);
		
		PauseBtn = new JButton("|| Pause");
		PauseBtn.setPreferredSize(new Dimension(84, 29));
		PauseBtn.setMinimumSize(new Dimension(84, 29));
		PauseBtn.setMaximumSize(new Dimension(84, 29));
		PauseBtn.setBounds(157, 121, 117, 44);
		contentPane.add(PauseBtn);
		
		StopBtn = new JButton("█ Stop");
		StopBtn.setMinimumSize(new Dimension(84, 29));
		StopBtn.setMaximumSize(new Dimension(84, 29));
		StopBtn.setBounds(286, 121, 117, 44);
		contentPane.add(StopBtn);
		
		StateLbl = new JLabel("Stopped");
		StateLbl.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		StateLbl.setBackground(new Color(211, 211, 211));
		StateLbl.setBounds(220, 56, 73, 13);
		contentPane.add(StateLbl);
		
		LcdLbl = new JLabel("Cannes Radio 93.5 FM");
		LcdLbl.setForeground(new Color(47, 79, 79));
		LcdLbl.setHorizontalAlignment(SwingConstants.CENTER);
		LcdLbl.setDoubleBuffered(true);
		LcdLbl.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		LcdLbl.setFont(new Font("Abadi MT Condensed Extra Bold", Font.BOLD,
				17));
		LcdLbl.setOpaque(true);
		LcdLbl.setBackground(new Color(0, 191, 255));
		LcdLbl.setBounds(28, 25, 246, 45);
		contentPane.add(LcdLbl);
		
		ChannelBox = new JComboBox(ch.getChannels());
		ChannelBox.setOpaque(true);
		ChannelBox.setBackground(new Color(255, 255, 255));
		ChannelBox.setBounds(293, 35, 150, 27);
		contentPane.add(ChannelBox);
		
		JLabel lblBg = new JLabel(bg);
		lblBg.setBounds(0, 0, 449, 179);
		contentPane.add(lblBg);
		
	}
	

	
	// GETTERS & SETTERS
	public void setDisplayText(String x){
		LcdLbl.setText(x);
	}
	public void setStateText(String x){
		StateLbl.setText(x);
	}
	public String getChannelBoxText(){
		return ChannelBox.getSelectedItem().toString();
	}
	public int getChannelBoxIndex(){
		return ChannelBox.getSelectedIndex();
	}
	
	// ACTIONLISTENERS
	
	void addPlayBtnListener(ActionListener al){
		PlayBtn.addActionListener(al);
	}
	void addPauseBtnListener(ActionListener al){
		PauseBtn.addActionListener(al);
	}
	void addStopBtnListener(ActionListener al){
		StopBtn.addActionListener(al);
	}
	void addChannelBoxListener(ItemListener al){
		ChannelBox.addItemListener(al);
	}
}
