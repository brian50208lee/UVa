import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.transform.Templates;


public class uva_10261 {
static Scanner input =new Scanner(System.in);
static ArrayList<Integer> seq=new ArrayList<Integer>();
static int L;
static int MaxK;
static String MaxPath;
	public static void main(String[] args) {
		int n=input.nextInt();
		input.nextLine();
		while (n--!=0) {
			seq.clear();
			L=input.nextInt()*100;
			MaxK=0;
			MaxPath="";
			int tmp=input.nextInt();
			while (tmp!=0) {
				seq.add(tmp);
				tmp=input.nextInt();
			}
			check(0,0, 0, "");
			System.out.println(MaxPath);
			
		}


	}
	public static void check(int i,int j, int maxK,String path){
		//System.out.println("maxK:"+maxK);
		if (maxK>MaxK) {
			MaxK=maxK;
			MaxPath=maxK+"\n"+path;
		}
		if(maxK<seq.size()){
			int NextI=i+seq.get(maxK);
			int NextJ=j+seq.get(maxK);
			int NextK=maxK+1;
			if (NextI<=L) {
				check(NextI,j, NextK, path+"port\n");
			}
			if (NextJ<=L) {
				check(i,NextJ, NextK, path+"starboard \n");
			}
		}
	}
	

}
