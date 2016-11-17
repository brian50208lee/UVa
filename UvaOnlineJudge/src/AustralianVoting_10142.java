import java.util.ArrayList;
import java.util.Scanner;


public class AustralianVoting_10142 {
	static Scanner input = new Scanner(System.in);
	static ArrayList<String> ans = new ArrayList<String>();

	public static void main(String[] args) {
		int M = Integer.parseInt(input.nextLine());
		input.nextLine();

		int N;
		for (int i = 0; i < M; i++) {
			N = Integer.parseInt(input.nextLine());
			String name[] = new String[N];
			for (int j = 0; j < name.length; j++) {
				name[j] = input.nextLine();
			}

			int[][] vote = new int[1000][N];
			int count = 0;
			String temp = input.nextLine();
			while (!temp.equals("")) {
				for (int j = 0; j < N; j++) {
					vote[count][j] = Integer.parseInt(temp.split(" ")[j]);
				}
				count++;
				temp = input.nextLine();
			}
			
			String sAns="";
			boolean[] isVoter = new boolean[vote[0].length];
			for (int j = 0; j < isVoter.length; j++) {
				isVoter[j]=true;
			}
			sAns=solve(vote,isVoter,name,sAns);
			ans.add(sAns);
/*			for (int j = 0; j < count; j++) {
				for (int j2 = 0; j2 < N; j2++) {
					System.out.print(vote[j][j2] + " ");
				}
				System.out.println();
			}*/

		}

		for (String s : ans) {
			System.out.println(s);
		}
	}

	public static String solve(int[][] vote,boolean[] isVoter,String[] name,String sAns) {
		int people = vote[0].length;
		
		// count vote
		int totalVote = 0;
		int[] pVote = new int[people];
		for (int i = 0; i < vote.length && vote[i][0] != 0; i++) {
			for (int j = 0; j < people; j++) {
				if (vote[i][j] == 1) {
					pVote[j]++;
					totalVote++;
				}
			}
		}
		for (int i = 0; i < pVote.length; i++) {
			System.out.print(pVote[i]+" ");
		}
		System.out.println();
		
		//get Max and Min voter
		int MaxVoter[]={-1,0};
		int MinVoter[]={-1,1000000};
		for (int i = 0; i < people; i++) {
			if (isVoter[i]) {
				if(pVote[i]>MaxVoter[1]){
					MaxVoter[0]=i;
					MaxVoter[1]=pVote[i];
				}else if(pVote[i]<MinVoter[1]){
					MinVoter[0]=i;
					MinVoter[1]=pVote[i];
				}
			}
		}
		System.out.println("MaxVoter:"+MaxVoter[0]+","+MaxVoter[1]);
		System.out.println("MinVoter:"+MinVoter[0]+","+MinVoter[1]);
		
		//end ?
		if (MaxVoter[1]/1.0/totalVote>0.5) {
			//System.out.println(1);
			sAns=sAns+name[MaxVoter[0]]+"\n";
			return sAns;
		}else if (MaxVoter[1]==MinVoter[1]) {
			for (int i = 0; i < pVote.length; i++) {
				if (pVote[i]==MaxVoter[1]) {
					sAns=sAns+name[i]+"\n";
				}
			}
			return sAns;
		}else {
			int low=MinVoter[1];
			for (int k = 0; k < pVote.length; k++) {
				if (low==pVote[k]) {
					MinVoter[0]=k;
					isVoter[MinVoter[0]]=false;
					for (int i = 0; i < vote.length && vote[i][0] != 0; i++) {
						int order=vote[i][MinVoter[0]];
						System.out.println("order:"+order);
						
						for (int j = 0; j < people; j++) {
							if (vote[i][j]>order) {
								vote[i][j]--;
							}
						}
						vote[i][MinVoter[0]]=people;
					}

				}
			}
			for (int j = 0; j < vote.length && vote[j][0] != 0; j++) {
				for (int j2 = 0; j2 < people; j2++) {
					System.out.print(vote[j][j2] + " ");
				}
			System.out.println();
			}
			
			return solve(vote,isVoter,name,sAns);
		}
	}


}
