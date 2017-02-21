package Single;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.*;

import Information.Judge;

public class PKGuess extends Thread{
	JTextArea txtAPC;
	public static Boolean flag = false;
	JLabel labScorePC;
	JFrame jFrame;
	public PKGuess(JTextArea txtAPC,JLabel labScorePC,String name,JFrame jFrame){
		super(name);
		this.txtAPC = txtAPC;
		this.labScorePC = labScorePC;
		this.jFrame = jFrame;
	}
	public static String generate(){
		String number = "";
		Random rdm = new Random();
		Set<Integer> hashSet = new HashSet<>();
		int t = 0;
		while(t!=4){
			int a = rdm.nextInt(10);
			if(hashSet.add(a)){
				number += ""+a;
				t++;
			}
		}
		return number;
	}
	
	@Override
	public void run(){
		while(true){
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(flag){
				if(!SingleGuess.isGuess){
					txtAPC.append(generate());
					String strJudge = Judge.judge(generate());
					txtAPC.append("----------"+strJudge+"\n");
					Score(strJudge);
				}
				flag = false;
			}
		}
	}
	public void Score(String str){
		String s = labScorePC.getText().trim();
		int x = Integer.parseInt(s);
		System.out.println("x"+x);
		int a = (int)str.charAt(0)-48;
		int b = (int)str.charAt(2)-48;
		if(a!=0||b!=0){
			if(b==4){
				x += 4;
			}
			else if(a==4){
				x += 5;
				SingleGuess.isGuess = true; 
				JOptionPane.showMessageDialog(jFrame, "电脑回答正确");
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
		
		labScorePC.setText(""+x);
		
	}
}
