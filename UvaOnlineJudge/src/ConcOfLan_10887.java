import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ConcOfLan_10887 {
	public static void main(String[] args) {
		String a[] = new String[1500];
		String b[] = new String[1500];
		Scanner input = new Scanner(System.in);
		int t = input.nextInt();
		Set<String> set = new HashSet();
		for (int k = 0; k < t; k++) {
			set.clear();
			int m = input.nextInt();
			int n = input.nextInt();
			input.nextLine();
			for (int i = 0; i < m; i++)a[i] = input.nextLine();
			for (int i = 0; i < n; i++)b[i] = input.nextLine();
			
			for (int i = 0; i < m; i++) 
			for (int j = 0; j < n; j++) 
				set.add(a[i] + b[j]);
			System.out.println("Case " + (k + 1) + ": " + set.size());
		}
	}

}
