import java.util.Scanner;


public class uva_10712_Count_the_Numbers {
static Scanner input=new Scanner(System.in);
static int A,B,N,stateNum;
static int digN[];
static String subN[];
static int digR[];
static String subR[];


static int dp[][];//dp[length][state]=num


	public static void main(String[] args) {
		while (getInput()) {
			stateNum=(N+"").length()+1;
			digN=toDigitArray(N);
			subN=toSubString(N+"");
			
			System.out.println(solve(B)-solve(A-1));
			//System.out.println(test());
		}
	}
	
	
	public static boolean getInput(){
		A=input.nextInt();//min
		B=input.nextInt();//max
		N=input.nextInt();//contain
		return N!=-1;
	}
	
	
	public static String[] toSubString(String str) {
		// "123" -> { "" , "1" , "12" , "123" }
		int size=str.length();
		String subArray[]=new String[size+1];
		for (int i = size; i >=0; i--)subArray[i]=str.substring(0, i);
		return subArray;
	}
	
	
	public static int[] toDigitArray(int n){
		// 123 -> { null , 1 , 2 , 3 }
		int size=(n+"").length();
		int digitArray[]=new int[size+1];
		for (int i = size; i >0; i--) {
			digitArray[i]=n%10;
			n/=10;
		}
		return digitArray;
	}

	
	public static int solve(int R){
		//return how many N appear in [0,R]
		
		
		//check bound
		if (R<0)return 0;

		
		//preprocedure var
		int digLen=(R+"").length();
		digR=toDigitArray(R);
		subR=toSubString(R+"");
		dp=new int[digLen+1][stateNum];
		
		//dp[i][j] 在字串長度為i時  停留在state j的元素個數
		//state j表示字串中前j個字母符合
		//initial dp
		for (int i = 0; i < digR[1]; i++){
			if(i==digN[1])dp[1][1]++;
			else dp[1][0]++;
		}
		if(N==0)dp[1][1]=0;//N==0 exception
		
		
		//dp
		for (int i = 1; i <digLen; i++) {//All length
			//upper bound check
			for (int j = 0; j < digR[i+1]; j++)
				dp[i+1][findState(subR[i]+j)]+=1;
			//main dp
			for (int j = 0; j < stateNum; j++) //All state
				for (int k = 0; k <= 9; k++) //All concat digit
					dp[i+1][nextState(j,k)]+=dp[i][j];
			//N==0 exception
			if(N==0)dp[i+1][0]+=9;
		}
		//upper bound check
		dp[digLen][findState(subR[digLen])]+=1;
		//N==0 exception
		if(N==0&&R>0)dp[digLen][stateNum-1]+=1;
		

		printTable(R);
		
		return dp[digLen][stateNum-1];
	}
	
	

	public static int nextState(int state,int transfer){
		if(state==stateNum-1)return state; //already in final state
		if(digN[state+1]==transfer)return (state+1);//go to next state
		else {//find state to go
			String str=transfer+"";
			for (int i = state; i >=1; i--)str=digN[i]+str;
			return findState(str);
		}
	}
	
	
	public static int findState(String transfer){
		int l=transfer.length();
		if(transfer.contains(subN[stateNum-1]))return stateNum-1;//can accept by final state
		if (l>stateNum-1)return findState(transfer.substring(l-stateNum+1, l));//find state by match transfer's tail
		for (int i = l; i>0 ;i--) {
			if (transfer.substring(l-i, l).equals(subN[i]))return i;
		}
		return 0;
	}
	
	public static void printTable(int R){
		System.out.println("----------R="+R+"----------");
		System.out.print("Lenth\t");
		for (int i = 0; i <= stateNum-1; i++) {
			if (i==stateNum-1)System.out.print("Accept");
			else System.out.print((String.format("%-15s", "end:"+subN[i])));
		}
		System.out.println();
		for (int i = (R+"").length(); i >=0 ; i--) {
			System.out.print(i+"\t");
			for (int j = 0; j <= stateNum-1; j++) {
				System.out.print((String.format("%-15s",dp[i][j])));
			}
			System.out.println();
		}
	}
	
	
	public static int test(){//暴力解
		int ans=0;
		for (int i = A; i <=B; i++)if((i+"").contains(N+""))ans++;
		return ans;
	}


}
