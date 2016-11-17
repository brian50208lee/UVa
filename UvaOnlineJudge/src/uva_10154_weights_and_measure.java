import java.util.Scanner;


public class uva_10154_weights_and_measure {
	static Scanner input =new Scanner(System.in);
	static int n=0,i,j;
	static int INF=1<<30;//2^31
	static int dp[][]=new int[5700][5700];
	
	static class Turtle{
		int w,s;
	}
	
	public static void main(String[] args) {
		Turtle turtle[]=new Turtle[5700];
		
		//input
		while (input.hasNextInt()) {
			turtle[n]=new Turtle();
			turtle[n].w=input.nextInt();
			turtle[n].s=input.nextInt();
			n++;
		}
		
		//sort
		sort(turtle, n);
		
		
		//initial dp table
		for (int i = 0; i <= n; i++)dp[0][i]=INF;
		for (int i = 0; i < n; i++)dp[i][0]=0;
		
		
		//initial first turtle
		dp[0][1]=turtle[0].w;
		
		//dp for 2 to n 
		for (i = 1; i < n; i++) {
			for (j = 1; j <= n; j++) {
	            if(dp[i-1][j-1]+turtle[i].w <= turtle[i].s) { //can chose
	                dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1]+turtle[i].w);
	            }else { //can't chose
	            	dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		//find ans
	    for(j = n; j >= 0; j--) if(dp[n-1][j] != INF)break;
	    
	    //output
	    System.out.println(j);
		
		
		
	}
	public static void sort(Turtle turtle[] , int size){
		Turtle temp=new Turtle();
		for (int i = 0; i < size; i++) {
			for (int j = i+1; j < size; j++) {
				if (turtle[i].s > turtle[j].s) {
					temp=turtle[i];
					turtle[i]=turtle[j];
					turtle[j]=temp;
				}
			}
		}
	}

}
