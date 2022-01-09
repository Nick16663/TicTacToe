// Jennifer Tsan, Josh Mallari, Nicholas Wade
// Group Project
// War Games checkWin.java
// 4.23.2020
/*class to check if the player or ai has won or lose or tied
 *
 */
public class checkWin {
	//check the columns one by one
	public static int horizWin(int[][] arr) {
		for(int row = 0; row< arr.length; row++) {
			if(((arr[row][0]==arr[row][1])&&(arr[row][1]==arr[row][2]))&&(!(arr[row][0]==0))) {
				System.out.println("Player ID "+arr[row][0]+" wins!");
				return arr[row][0];
			}
		}
		return 0;
	}
	//check the rows one by one
	public static int vertWin(int[][] arr) {
		for(int col = 0; col< arr.length; col++) {
			if((arr[0][col]==arr[1][col])&&(arr[1][col]==arr[2][col])&&(!(arr[0][col]==0))) {
				System.out.println("Player ID "+arr[0][col]+" wins!");
				return arr[0][col];
			}
		}
		return 0;
	}
	//check the diagonals
	public static int diagWin(int[][] arr) {
		//top left corner to bottom right corner
			if((arr[0][0]==arr[1][1])&&(arr[1][1]==arr[2][2])&&(!(arr[1][1]==0))) {
				System.out.println("Player ID "+arr[0][0]+" wins!");
				return arr[0][0];
			}else {
				//top right corner to bottom left corner
				if((arr[0][2]==arr[1][1])&&(arr[1][1]==arr[2][0])&&(!(arr[1][1]==0))) {
					System.out.println("Player ID "+arr[2][0]+" wins!");
					return arr[0][2];
				}else {
					return 0;
				}
			}
	}
	
	
}
