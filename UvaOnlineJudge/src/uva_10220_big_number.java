import java.math.BigInteger;
import java.util.Scanner;


public class uva_10220_big_number {
static Scanner input=new Scanner(System.in);
	public static void main(String[] args) {
		int num,sum;
		BigInteger bAns;
		while (input.hasNext()) {
			num=input.nextInt();
			sum=0;
			bAns=new BigInteger(num+"");
			for (int i = num-1; i >1; i--) {
				bAns=bAns.multiply(new BigInteger(i+""));
			}
			while (bAns.compareTo(new BigInteger(0+""))!=0) {
				sum+=bAns.mod(new BigInteger(10+"")).intValue();
				bAns=bAns.divide(new BigInteger(10+""));
			}
			System.out.println(sum);
		}
	}

}
