package Double;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.*;

import Access.FormLogin;
import Information.DbConnect;
import Information.Judge;
import java.net.*;
import java.sql.*;
import java.util.Date;
import java.awt.event.*;

public class DoubleGame extends JFrame {
	private boolean isServer;
	private JPanel contentPane;
	private JTextField txtNumber;
	static PrintStream ps;
	Socket s;
	BufferedReader br;
	int time = 0;
	int time1 = 0;
	public static Boolean flag = false;
	private Timer timer = null;
	private Timer timer1 = null;
	private JButton btnOk;
	JButton btnStart;
	JLabel lblScoreP1;
	Thread thread1 = null;
	Thread thread2 = null;
	ClientThread clientThread = null;
	Boolean isGuess1 = false; //玩家1猜对
	public static Boolean isGuess2 = false; //玩家2猜对
	JLabel lblIcon2;
	JLabel lblScoreP2;
	JLabel lblP2;
	Boolean isClose = false; //是否关闭
	/**
	 * Create the frame.
	 */
	public DoubleGame(JFrame mode) {
		//生成随机数
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		try {
			isServer = Client();
		} catch (Exception e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		setBounds(100, 100, 585, 533);
		setLocationRelativeTo(this);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtAP1 = new JTextArea();
		txtAP1.setFont(new Font("华文楷体", Font.BOLD, 15));
		txtAP1.setBorder (BorderFactory.createLineBorder(Color.gray,5));
		txtAP1.setBounds(0, 190, 236, 316);
		contentPane.add(txtAP1);
		
		JTextArea txtAP2 = new JTextArea();
		txtAP2.setFont(new Font("华文楷体", Font.BOLD, 15));
		txtAP2.setBorder (BorderFactory.createLineBorder(Color.gray,5));
		txtAP2.setBounds(343, 190, 236, 316);
		contentPane.add(txtAP2);
		
		txtNumber = new JTextField();
		txtNumber.setBounds(0, 20, 122, 21);
		contentPane.add(txtNumber);
		txtNumber.setColumns(10);
		
		btnOk = new JButton("\u786E\u5B9A");
		btnOk.setFont(new Font("宋体", Font.BOLD, 16));
		btnOk.setBounds(127, 11, 79, 38);
		contentPane.add(btnOk);
		btnOk.setEnabled(false);
		
		btnStart = new JButton("\u5F00\u59CB\u6E38\u620F");
		btnStart.setFont(new Font("华文琥珀", Font.BOLD, 16));
		btnStart.setBounds(412, 8, 110, 33);
		contentPane.add(btnStart);
		btnStart.setFocusable(false);
		
		JLabel label = new JLabel("\u65F6 \u95F4\uFF1A");
		label.setFont(new Font("华文中宋", Font.BOLD, 20));
		label.setBounds(107, 59, 70, 21);
		contentPane.add(label);
		
		JLabel labTime = new JLabel(" 0");
		labTime.setFont(new Font("华文琥珀", Font.PLAIN, 23));
		labTime.setBounds(171, 55, 29, 33);
		contentPane.add(labTime);
		
		Timer timerServer = new Timer(1,new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Timer com =(Timer) e.getSource();
				if(!isServer){
					dispose();
					mode.setVisible(true);
					com.stop();
					return;
				}
			}
		});
		timerServer.start();
		//玩家一的时
		timer = new Timer(1000,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time--;
				labTime.setText(""+time);
				if(isGuess2){
					clientThread.exit = false;
					JOptionPane.showMessageDialog(DoubleGame.this, "恭喜"+lblP2.getText()+"回答正确！");
					btnStart.setText("开始游戏");
					timer.stop();
					timer1.stop();
					btnOk.setEnabled(false);
				}
				if((time <= 0||time1<=0)&&!isClose){
					clientThread.exit = false;
					time = 0;
					if(!isGuess1&&!isGuess2){
						JOptionPane.showMessageDialog(DoubleGame.this, "恭喜"+lblP2.getText()+"取得胜利，正确答案为："+Judge.number);
						btnStart.setText("开始游戏");
					}
					Timer t = (Timer)e.getSource();
					btnOk.setEnabled(false);
					t.stop();
				}
			}
		});
		timer.stop();
		
		//玩家2的时间
		JLabel labTime1 = new JLabel(" 0");
		labTime1.setFont(new Font("华文琥珀", Font.PLAIN, 23));
		labTime1.setBounds(366, 55, 29, 33);
		contentPane.add(labTime1);
		timer1 = new Timer(1000,new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time1--;
				labTime1.setText(""+time1);
				if(isGuess2){
					clientThread.exit = false;
					JOptionPane.showMessageDialog(DoubleGame.this, "恭喜"+lblP2.getText()+"回答正确！");
					btnStart.setText("开始游戏");
					timer.stop();
					timer1.stop();
					btnOk.setEnabled(false);
				}
				if((time <= 0||time1<=0)&&!isClose){
					clientThread.exit = false;
					time1 = 0;
					if(!isGuess1&&!isGuess2){
						JOptionPane.showMessageDialog(DoubleGame.this, "恭喜您取得胜利，正确答案为："+Judge.number);
						btnStart.setText("开始游戏");
					}
					Timer t = (Timer)e.getSource();
					btnOk.setEnabled(false);
					t.stop();
				}
			}
		});
		timer1.stop();
		
		JLabel lblP1 = new JLabel("\u73A9\u5BB61");
		lblP1.setText(FormLogin.member.getName());
		lblP1.setFont(new Font("华文中宋", Font.BOLD, 20));
		lblP1.setBounds(130, 106, 76, 21);
		contentPane.add(lblP1);
		
		JLabel label_2 = new JLabel("\u5206\u6570\uFF1A");
		label_2.setFont(new Font("华文中宋", Font.BOLD, 20));
		label_2.setBounds(50, 158, 70, 21);
		contentPane.add(label_2);
		
		lblScoreP1 = new JLabel(" 0");
		lblScoreP1.setFont(new Font("华文琥珀", Font.PLAIN, 23));
		lblScoreP1.setBounds(118, 155, 29, 33);
		contentPane.add(lblScoreP1);
		lblScoreP1.setText(""+FormLogin.member.getScore());
		
		JLabel label_4 = new JLabel("VS");
		label_4.setFont(new Font("华文琥珀", Font.BOLD, 27));
		label_4.setBounds(273, 312, 50, 33);
		contentPane.add(label_4);
		
		lblP2 = new JLabel("\u73A9\u5BB6");
		lblP2.setFont(new Font("华文中宋", Font.BOLD, 20));
		lblP2.setBounds(366, 106, 60, 21);
		contentPane.add(lblP2);
		
		JLabel label_6 = new JLabel("\u5206\u6570\uFF1A");
		label_6.setFont(new Font("华文中宋", Font.BOLD, 20));
		label_6.setBounds(403, 158, 70, 21);
		contentPane.add(label_6);
		
		lblScoreP2 = new JLabel(" 0");
		lblScoreP2.setFont(new Font("华文琥珀", Font.PLAIN, 23));
		lblScoreP2.setBounds(471, 155, 29, 33);
		contentPane.add(lblScoreP2);
		
		//玩家1头像
		JLabel lblIcon1 = new JLabel("\u73A9\u5BB61\u5934\u50CF");
		lblIcon1.setBorder (BorderFactory.createLineBorder(Color.black,2));
		lblIcon1.setBounds(24, 51, 79, 107);
		String str = FormLogin.member.getIcon();
		ImageIcon imageIconPl = new ImageIcon(str); 
		imageIconPl.setImage(imageIconPl.getImage().getScaledInstance(lblIcon1.getWidth(), lblIcon1.getHeight(), Image.SCALE_DEFAULT));
		lblIcon1.setIcon(imageIconPl);
		contentPane.add(lblIcon1);
		
		//玩家2头像
		lblIcon2 = new JLabel("\u73A9\u5BB6\u4E8C\u5934\u50CF");
		lblIcon2.setBorder (BorderFactory.createLineBorder(Color.black,2));
		lblIcon2.setBounds(459, 51, 79, 107);
		ImageIcon imageIcon2 = new ImageIcon("./Image/标准.jpg");
		imageIcon2.setImage(imageIcon2.getImage().getScaledInstance(lblIcon2.getWidth(), lblIcon2.getHeight(), Image.SCALE_DEFAULT));
		lblIcon2.setIcon(imageIcon2);
		contentPane.add(lblIcon2);
		
		JLabel label_1 = new JLabel("\uFF1A\u65F6 \u95F4");
		label_1.setFont(new Font("华文中宋", Font.BOLD, 20));
		label_1.setBounds(388, 59, 70, 21);
		contentPane.add(label_1);
		
		
		
		//背景
		JLabel lbl = new JLabel("New label");
		lbl.setBounds(0, 0, 579, 505);
		ImageIcon imageIcon = new ImageIcon("./Image/1.jpg");
		imageIcon.setImage(imageIcon.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
		lbl.setIcon(imageIcon);
		getContentPane().add(lbl);
		
		//开始按钮事件
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				DoubleGame.flag = false;
				if(time>0&&time1>0&&!isGuess1&&!isGuess2){
					JOptionPane.showMessageDialog(DoubleGame.this, "游戏还未结束！");
				}
				else{
					labTime.setText("21");
					labTime1.setText("21");
					txtAP1.setText("");//清空面板内容
					txtAP2.setText("");
					isGuess1 = false;
					isGuess2 = false;
					time = 21;
					time1 = 21;
					flag = false;
					if(thread1!=null){
						if(thread1.isAlive()){
							try{
								thread1.stop();
							}
							catch(Exception ex){
								ex.printStackTrace();
							}
						}
					}
					JOptionPane.showMessageDialog(DoubleGame.this, "准备！");
					btnStart.setText("准备！！");
					clientThread = new ClientThread(DoubleGame.this,s,txtAP2,timer,timer1,btnOk,lblIcon2,lblScoreP2,lblP2);
					thread1 = new Thread(clientThread);
					thread1.start();
					thread2 = new Thread(new ConnectThread());
					thread2.start();
					
				}
				
			}
		});
		
		//确定按钮事件
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(txtNumber.getText());
				
				if(txtNumber.getText().equals("")){
					JOptionPane.showMessageDialog(DoubleGame.this, "请输入所猜测数值！");
				}
				else if(!Judge.distinguish(txtNumber.getText())){
					JOptionPane.showMessageDialog(DoubleGame.this, "您输入的值有误！");
				}
				else{
					String strJudge = Judge.judge(txtNumber.getText().trim());
					String str = txtNumber.getText()+"----------"+strJudge+"\n" ;
					txtAP1.append(str);
					Score(strJudge);
					ps.print(str);
					ps.println(lblScoreP1.getText());
					txtNumber.setText("");
					btnOk.setEnabled(false);
					timer.stop();
					timer1.start();
					if(isGuess1||isGuess2){
						timer1.stop();
					}
				}
			}
		});
		
		//窗口关闭事件
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mode.setVisible(true);
				isClose = true;
				DbConnect dbConnect = new DbConnect();
				try {
					dbConnect.Save(FormLogin.member.getAccount());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				String fileName = "./历史游戏信息/"+FormLogin.member.getAccount()+".txt";
				File file = new File(fileName);
				if(file.exists()){
					Save(file);
				}
				else {
					try {
						if(file.createNewFile()){
							Save(file);
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		setResizable(false);
		
	}
	public boolean Client(){
		try {
			s = new Socket("127.0.0.1",30000);
			ps = new PrintStream(s.getOutputStream());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "未连接到服务器！");
			return false;
		} 
		String line = null;
		return true;
	}
	
	//分数
	public void Score(String str){
		int x = FormLogin.member.getScore();
		int a = (int)str.charAt(0)-48;
		int b = (int)str.charAt(2)-48;
		if(a!=0||b!=0){
			if(b==4){
				x += 4;
			}
			else if(a==4){
				x += 5;
				isGuess1 = true;
				if(isGuess1)
					ps.println("赢");
					timer.stop();
					timer1.stop();
					clientThread.exit = false;
					JOptionPane.showMessageDialog(this, "恭喜您回答正确！");
					btnStart.setText("开始游戏");
			}
			else if(a+b ==4){
				x += 3;
			}
			else if(a+b >2){
				x += 2;
			}else if(a+b >=1){
				x += 1;
			}
		}
		FormLogin.member.setScore(x);
		lblScoreP1.setText(""+x);
	}
	
	//与对方电脑连接的确认多线程
	public	class ConnectThread extends Thread{
			public void run(){
				while(true){
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}ps.println("连接");
					if(DoubleGame.flag){
						break;
					}
				}
				JDialog jDialog = new JDialog();
				JLabel lblPrepare = new JLabel("游戏即将开始...");
				lblPrepare.setBounds(100,30, 100, 50);
				jDialog.setBounds(DoubleGame.this.getX()+200,DoubleGame.this.getY()+200, 200, 100);
				jDialog.getContentPane().add(lblPrepare);
				jDialog.setVisible(true);
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				btnStart.setText("游戏中！！");
				jDialog.setVisible(false);
				ps.println("time");
			}
		}
	public void Save(File file){
		try {  
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件  
            FileWriter writer = new FileWriter(file, true);  
            Date date = new Date();
            String s = String.format("%tY-%<tm-%<td %<tT",date);
            if(!lblP2.getText().equals("玩家2"))
            	writer.write(""+FormLogin.member.getScore()+","+lblP2.getText()+","+lblScoreP2.getText()+","+s+"\r\n");  
            writer.close();
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
	}
	
}
