import java.util.Arrays;

public class ExempleTableaux {

	public static void main(String[] args) {
		int[] monTableau = new int[10];
		int[] monTableau2 = {12,34,25,1};
		
		
		System.out.println(monTableau.length);
		System.out.println(monTableau2.length);
		
		for(int i = 0; i < monTableau2.length; i++) {
			System.out.println(monTableau2[i]);
		}
		
		for(int val : monTableau2) {
			System.out.println(val);
		}
		
		System.out.println(Arrays.toString(monTableau2));
		
		int[][] maMatrice = new int[20][20];
		int[][] maMatrice2 = {{1,2,3},{4,5,6}};
		
		for(int i = 0; i < maMatrice2.length; i++) {
			for(int j = 0; j < maMatrice2[i].length; j++ ) {
				System.out.print(maMatrice2[i][j] +  " ");
			}
			System.out.println();
		}
		
	}
}
