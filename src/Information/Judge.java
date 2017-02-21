package Information;
import java.util.*;
public class Judge {
	public static String number = "";
	public static String judge(String str){
		int m=0,n=0;
		for(int i=0;i<number.length();i++){
			if(number.charAt(i)==str.charAt(i)) m++;
			for(int j=0;j<number.length();j++){
				if(number.charAt(i)==str.charAt(j)&&i!=j) n++;
			}
		}
		return ""+m+"A"+n+"B";
	}
	
	public static boolean distinguish(String str){
		try{
			Double.parseDouble(str);
		}catch(Exception ex){
			return false;
		}
		HashSet<Character> set = new HashSet<>();
		if(str.length()!=4)
			return false;
		for(int i = 0; i < str.length();i++){
			if(!set.add(str.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	public static void generate(){
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
	}
}