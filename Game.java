import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// TODO: Implement your program
		Scanner sc = new java.util.Scanner(System.in);          
		
		int n = sc.nextInt();                                                                                                                        //Initiate dimension (rows)
		int m = sc.nextInt();                                                                                                                        //Initiate dimension (columns)
		
		int[][] vertexValue = new int[n][m];                                                                                                         //2D array to store vertex value  
		boolean[][] vertexVisit = new boolean[n][m];                                                                                                 //2D array to store vertex visit condition
		boolean[] reachDestination = new boolean[1];
		reachDestination[0] = false;                                                                                                                 //indicate if destination vertex (0, m-1) can be reached
		 
		for (int i = 0; i < n; i++) {                
			sc = new Scanner(System.in);
			for (int j = 0; j < m; j++) {
				vertexValue[i][j] = sc.nextInt();
				vertexVisit[i][j] = false;
				
				if(vertexValue[i][j] == -1) vertexVisit[i][j] = true;
			}	
		}
		sc.close();
		
		int longestPath = getLongestPath(vertexValue, vertexVisit, n, m, n-1, 0, reachDestination) + vertexValue[0][m-1];
		
		if (reachDestination[0]) {
			System.out.println(longestPath);
		} else {
			System.out.println(-1);                                                                                                                   //print -1 when destination cannot be reached
		}
	}


	public static int getLongestPath(int[][] vertexValue, boolean[][] vertexVisit, int n, int m, int i, int j, boolean[] reachDestination) {          //Method for returning longest path from vertex(i,j) to vertex(0, m-1)
			int dist = 0;                                                                                                                             //variable for path length
			int max = 0;                                                                                                                              //variable for longest path length
			
			int multiplier = 1;                                                                                                                       //multiplier = 1 or 0, for condition where vertex on the top or bottom, losing all values after moving
			
			int iCopy = i;                                                                                                                            //keep track of cuurent x-oordinates
			int jCopy = j;                                                                                                                            //keep track of cuurent y-oordinates
			
			vertexVisit[i][j] = true;                                                                                                                 //mark visit status for current vertex
			
			for (int edge = 0; edge < 3 && (i != 0 || j != m -1); edge++) {                                                                           //looping all available edges connecting current vertex, at most 3 direction, up, down, right
				i = iCopy;                                                                                                                            //if reaching vertex (0,m-1), loop ends, no moving after reaching destination
				j = jCopy;
				                                                                                                            
				if (edge == 0) {
					if (i == 0) {
						i = n - 1;                                                                                                                    //Also returning values of next vertex and multiplier
						multiplier = 0;
					} else {
						i += -1;
						multiplier = 1;
					}
				} else if (edge == 1) {
					if (i == n - 1) {
						i = 0;
						multiplier = 0;
					} else {
						i += 1;
						multiplier = 1;
					}
				} else if (edge == 2) {
					if (j < m - 1) {
						j += 1;
						multiplier = 1;
					}
				}
				
				if (i == 0 && j == m - 1 && vertexValue[0][m-1] != -1) reachDestination[0] = true;                                                   //As long as (0,m-1) can be reached once and (0,m-1) is not -1, the indicator will stay true
				
				if (!vertexVisit[i][j]) {			                                                                                                 //when vertex is not visited
					dist = vertexValue[iCopy][jCopy] + multiplier * getLongestPath(vertexValue, vertexVisit, n, m, i, j, reachDestination);          //longest path from vertex(i,j) to (0,m-1) = longgest path from vertex(i,j) to next vertex + longgest path from next vertex to vertex(0,m-1)
					
					if (dist > max) {
						max = dist;                                                                                                                  //update max distance
					}
				}
			}
			
			vertexVisit[iCopy][jCopy] = false;                                                                                                       //reset vertex visit condition
			
			return max;
	}
}
			
			
		
			

	
