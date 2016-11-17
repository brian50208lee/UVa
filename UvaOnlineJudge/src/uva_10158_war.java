import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

6
1 0 1
1 2 3
3 1 2
2 0 4
2 0 5
1 1 3
4 0 2
1 2 4
0 0 0

*/

public class uva_10158_war {
	static final int MAX=10000;
	static final int EMPTY=MAX;
	static int[] parent=new int[MAX+1];
	static int[] enemy=new int[MAX+1];
	static int N,c,x,y;
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); 
	public static void main(String[] args) throws IOException {

		init();
		
		while (getInput()) {
			int setX=find(x),eneX=enemy[setX];
			int setY=find(y),eneY=enemy[setY];

			switch (c) {
			case 1://setFriend(x,y)
				if (setX==eneY)System.out.println("-1");//�w�g�O�ĤH
				else {
					union(setX, setY);		//�B�ͪ��B�� �O �ڪ��B��
					union(eneX, eneY);	//�B�ͪ��ĤH �O �ڪ��ĤH
					if (eneY==EMPTY) {
						enemy[setY]=eneX;
						enemy[eneX]=setY;
					}
				}
				break;
				
			case 2://setEnemy(x,y)
				if (setX==setY)System.out.println("-1");//�w�g�O�B��
				else {
					union(eneY,setX);		//���@�P���ĤH �h���B��
					union(eneX,setY);		//���@�P���ĤH �h���B��
					enemy[setX]=setY;		//���]���ĤH
					enemy[setY]=setX;		//���]���ĤH
				}
				break;
				
			case 3:
				if (setX==setY)System.out.println("1");
				else System.out.println("0");
				break;
				
			case 4:
				if (setX==eneY)System.out.println("1");
				else System.out.println("0");
				break;
				
				
			default:break;
			}
			for (int i = 0; i < N; i++) {
				System.out.print(i+"\t");
			}System.out.println();		
			for (int i = 0; i < N; i++) {
				System.out.print(parent[i]+"\t");
			}System.out.println();			
			for (int i = 0; i < N; i++) {
				System.out.print(enemy[i]+"\t");
			}System.out.println();
		}
	}

	
	public static boolean getInput() throws IOException{
		String in=input.readLine();
		c=Integer.parseInt(in.split(" ")[0]);
		x=Integer.parseInt(in.split(" ")[1]);
		y=Integer.parseInt(in.split(" ")[2]);
		return c!=0;
	}
	
	public static void init() throws IOException{
		N=Integer.parseInt(input.readLine());
		for (int i = 0; i < N; i++) {
			parent[i]=i;
			enemy[i]=EMPTY;
		}
		
		parent[EMPTY]=EMPTY;
		enemy[EMPTY]=EMPTY;
	}
	
	public static void union(int setA ,int setB){
		if(setA==setB||setA==EMPTY||setB==EMPTY)return;
		parent[setA]=setB;
	}
	
	public static int find(int elm){
		if(parent[elm]!=elm)parent[elm]=find(parent[elm]);
		return parent[elm];
	}
}
