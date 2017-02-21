package Access;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

import Double.DoubleGame;
import Information.DbConnect;
import Information.HistoryGame;
import Information.PersonInf;
import Information.Zhanji;
import Single.SingleGuess;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Mode extends JFrame {

	private JPanel contentPane;
	BufferedImage groundBack ;

//	public static void main(String[] args){
//		new Mode().show();
//	}
	/**
	 * Create the frame.
	 */
	public Mode() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				DbConnect dbConnect = new DbConnect();
				try {
					dbConnect.Save(FormLogin.member.getAccount());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		
		
		getContentPane().setLayout(null);
		JRadioButton rdbPC = new JRadioButton("\u4EBA\u673A\u5BF9\u6218");
		rdbPC.setSelected(true);
		rdbPC.setBackground(null);
		rdbPC.setOpaque(false);
		rdbPC.setFont(new Font("华文琥珀", Font.BOLD, 20));
		rdbPC.setBounds(173, 167, 122, 23);
		getContentPane().add(rdbPC);
		
		JRadioButton rdbPP = new JRadioButton("\u73A9\u5BB6\u5BF9\u6218");
		rdbPP.setBackground(null);
		rdbPP.setOpaque(false);
		rdbPP.setFont(new Font("华文琥珀", Font.BOLD, 20));
		rdbPP.setBounds(173, 195, 122, 23);
		getContentPane().add(rdbPP);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 510, 439);
		
		ButtonGroup radRadGrp = new ButtonGroup();
		radRadGrp.add(rdbPP);
		radRadGrp.add(rdbPC);
		
		JButton btnGame = new JButton("\u8FDB\u5165\u6E38\u620F");
		btnGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbPP.isSelected()){
					Mode.this.setVisible(false);
					new DoubleGame(Mode.this).setVisible(true);
					
				}
				if(rdbPC.isSelected()){
					Mode.this.setVisible(false);
					new SingleGuess(Mode.this).setVisible(true);
				}
			}
		});
		btnGame.setFont(new Font("华文琥珀", Font.BOLD, 15));
		btnGame.setBounds(162, 266, 130, 41);
		getContentPane().add(btnGame);
		
		
		JLabel lblMenu = new JLabel();
		
		JButton btnPersonInf = new JButton("\u4E2A\u4EBA\u4FE1\u606F");
		btnPersonInf.setBounds(0, 0, 130, 32);
		
		JButton btnBarInfo = new JButton("\u67E5\u8BE2\u6218\u7EE9");
		btnBarInfo.setBounds(0, 64, 130, 32);
		
		JButton btnHisInfo = new JButton("\u5386\u53F2\u6E38\u620F\u4FE1\u606F");
		btnHisInfo.setBounds(0, 32, 130, 32);
		
		
		lblMenu.add(btnPersonInf);
		lblMenu.add(btnHisInfo);
		lblMenu.add(btnBarInfo);
		lblMenu.setBounds(0, 167, 130, 96);
		getContentPane().add(lblMenu);
		lblMenu.setVisible(false);
		
		//查询个人信息
		btnPersonInf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mode.this.setVisible(false);
				new PersonInf(Mode.this).show();
			}
		});
		
		//查询历史游戏信息
		btnHisInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mode.this.setVisible(false);
				new HistoryGame(Mode.this).show();
			}
		});
		//查询战绩
		btnBarInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mode.this.setVisible(false);
				new Zhanji(Mode.this).show();
			}
		});
		
		//边界隐藏事件
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(e.getX()>0&&e.getX()<93){
					lblMenu.setVisible(true);
				}
				else lblMenu.setVisible(false);
			}
		});
		
		//背景
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("./Image/guessNumber.jpg"));
		lblNewLabel.setBounds(0, 0, 504, 409);
		getContentPane().add(lblNewLabel, new Integer(Integer.MIN_VALUE));
		
		
		//托盘功能
		Image img = Toolkit.getDefaultToolkit().getImage("./Image/托盘.jpg");
		TrayIcon trayIcon = new TrayIcon(img);
		trayIcon.setToolTip("猜数字游戏");
		trayIcon.setImageAutoSize(true);
		try {
			SystemTray.getSystemTray().add(trayIcon);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PopupMenu pmenu = new PopupMenu();
		MenuItem open = new MenuItem("打开");
		open.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(true);
			}
		});
		pmenu.add(open);
		
		MenuItem exit = new MenuItem("关闭");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);  
			}
		});
		pmenu.add(exit);
		trayIcon.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount() == 2)
					setVisible(true);
			}
		});
		trayIcon.setPopupMenu(pmenu);
		
		
		setLocationRelativeTo(this);
		setResizable(false);
	}
}

