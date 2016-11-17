
import java.util.Scanner;


public class uva_10139_factovisor {
static Scanner input =new Scanner(System.in);
static long N=0,M=0;
static int pt[]=new int[10000];
static int ptSize=0;

	public static void main(String[] args) {
		pt[0]=2;ptSize++;
		pt[1]=3;ptSize++;
		while(input.hasNextLong()){
			N=input.nextLong();
			M=input.nextLong();
			if(solve())System.out.printf("%d divides %d!\n",M,N);
			else System.out.printf("%d does not divide %d!\n",M,N);
		}
	}
	public static boolean solve(){
		long m=M,n=N;
		int i=0,p=getPrime(i),bound=(int)Math.sqrt(m),count1=0,count2=0;
		if(m==0)return false;
		if(n==0)n=1;
		while (p<=bound) {
			//System.out.println(i+","+p+","+m);
			while(m%p==0){
				//System.out.println(1);
				count1++;
				m/=p;
			}
			if (count1 !=0) {
				//System.out.println(2);
				long t=p;
				while (t<=n) {
					//System.out.println(N+",,"+t);
					count2+=(n/t);
					//System.out.println(t+","+p);
					t*=p;
					//System.out.println(t);
				}
				if(count1>count2)return false;
				i++;
				p=getPrime(i);
				count1=0;
				count2=0;
			}
			else {
				//System.out.println(3);
				i++;
				p=getPrime(i);
			}
		}
		//System.out.println(m);
		if(m>n)return false;
		return true;
	}
	
	
	
	public static int getPrime(int index){
		while (pt[index]==0)creatPt();
		return pt[index];
	}
	public static void creatPt(){
		int nextP=pt[ptSize-1];
		int bound;
		boolean find=false;
		while (!find) {
			nextP+=2;
			bound=(int)Math.sqrt(nextP);
			for (int i = 0; pt[i] <= bound; i++) {
				if(nextP%pt[i]==0){
					break;
				}
				if(pt[i+1]>bound)find=true;
			}
		}
		ptSize++;
		pt[ptSize-1]=nextP;
	}
	
}
