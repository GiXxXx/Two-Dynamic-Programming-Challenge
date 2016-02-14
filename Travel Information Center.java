import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// TODO: Implement your program
		Scanner sc = new java.util.Scanner(System.in);                                               
		
		int n = sc.nextInt();                                                                                                                 
		int m = sc.nextInt();                                                                                              
		
		int[][] path = new int[n-1][2];                                                          // initiate 2D array path to store adjacent cities                         
		int[][] info = new int[m][2];                                                            // initiate 2D array info to store command "1" or "2", and city code
		boolean[] isFestive = new boolean[n+1];                                                  // initiate an array to store the isFestive information of a city
		
		for (int x = 0; x <= n; x++) {
			isFestive[x] = false;                                                                
		}
		
		isFestive[1] = true;                                                                     //at the beginning, only city No.1 is festive
		
		for (int i = 0; i < m+n-1; i++) {                
			sc = new Scanner(System.in);
			for (int j = 0; j < 2; j++) {
				if (i < n - 1) {
					path[i][j] = sc.nextInt();                                                   //enter data for path
				} else {
					info[i - n + 1][j] = sc.nextInt();                                           //enter data for info
				}
			}
		}
				
		sc.close();
		
		for (int e = 0; e < m; e++) {
			if (info[e][0] == 1) {                                                               
				updateCity(info[e][1], isFestive);                                               //if command in info is '1', make a city from non-festive to festive
			} else {
				System.out.println(getShortestPath(info[e][1], path, n, isFestive));             //if the command is '2', find the shortest path from given city to a festive city
			}
		}
	}
	
	public static int getShortestPath (int city, int[][] path, int n, boolean[] isFestive) {
		int min = 0;
		Queue<Integer> festiveCity = new LinkedList<Integer>();                        
		int[] distance = new int[n+1];                                                           //store distance from given city to destination city
		int current = 0;                                                                         //initiate current city as No. 0
		int next = 0;                                                                            //initiate next adjacent city as No. 0
		String adjacentCity = "";                                                                //string used to store adjacent cities' codes
		
		for (int j = 0; j <= n; j++) {
			distance[j] = -1;                                                                    //make distance to all cities to be -1, indicating they are not visited yet in the path
		}
		
		distance[city] = 0;                                                                      //make distance from current city to current city as 0
		festiveCity.add(city);
		
		if (!isFestive[city]) {		                                                             //if current city is festive, the min distance is 0, no need to go on
			while (festiveCity.size() != 0) {                        
				current = festiveCity.remove();
				adjacentCity = getAdjacentCity(current, path, n);                                
			
				for (int i = 0; i < adjacentCity.length(); i++) {                                
					next = Character.getNumericValue(adjacentCity.charAt(i));
				
					if (distance[next] == -1) {
						distance[next] = distance[current] + 1;                                  //distance to an non-visited adjacent city = distance to current city + 1
						festiveCity.add(next);
					}

					if (isFestive[next]) {
						min = distance[next];                                                    //the first Festive city we found will have the shortest distance
						break;
					}
				}

				if (min != 0) {
					break;                                                                       //if we found the minimum distance, no need to go on
				}	
			}
		}
		return min;
	}

	public static void updateCity (int city, boolean[] isFestive) {                              //update a city from non-festive to festive, no more change to this city onwards
		isFestive[city] = true;	
		return;
	}

	public static String getAdjacentCity(int city, int[][] path, int n) {                       //get adjacent cities based on paths
		String adjacentCity = "";
	
		for (int i = 0; i < n-1; i++) {
			if (path[i][0] == city) {
				adjacentCity += Integer.toString(path[i][1]);
			} else if (path[i][1] == city) {	
				adjacentCity += Integer.toString(path[i][0]);
			}
		}
		
		return adjacentCity;
	}
}
		
