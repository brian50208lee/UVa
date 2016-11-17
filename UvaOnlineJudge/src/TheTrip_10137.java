import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class TheTrip_10137 {
	static Scanner input = new Scanner(System.in);
	static ArrayList<String> ans = new ArrayList<String>();
	
	public static void main(String[] args) {

		
		int N=input.nextInt();
		input.nextLine();
		while (N!=0) {
			int[] money = new int[N];
			int averange=0;
			int r=0;
			for (int i = 0; i < money.length; i++) {
				String S=input.nextLine();
				if (S.contains(".")) {
					String S1=S.split("\\.")[0];
					String S2=S.split("\\.")[1];
					money[i]=Integer.parseInt(S1.concat(S2));
				}else {
					money[i]=Integer.parseInt(S)*100;
				}
				//money[i]=(int)(input.nextDouble()*100);
				averange+=money[i];
			}
			r=averange%N;
			averange=(averange-r)/N ;
			//System.out.println(averange+","+r);
			int count=0;
			Arrays.sort(money);
			for (int i = money.length-1; i >=0 ; i--) {
				if (money[i]>averange) {
					count+=(money[i]-averange);
					if (r>0) {
						r--;
						count--;
					}
				}
			}
			System.out.println(String.format("$%d.%02d", count/100,count%100));
			//ans.add(String.format("$%d.%02d", count/100,count%100));
			N=input.nextInt();
			input.nextLine();
		}
		
/*		for (String s :ans) {
			System.out.println(s);
		}*/
	}

}
