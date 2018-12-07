import java.util.*;
import java.io.*;

public class Hw8{
	
	//check Move will take in a boolean array, a peg position, and the amount to add to that position to get a jump
	//It reurns a bool to say if it is a valid move or not
	private static boolean checkMove(boolean[] board, int d, int pos){
		if (board[pos] == false){
			if(board[pos + d] == true){
				return true;
			}else{
				return false;
			}
		}else{return false;}
	}
	
	//make move is a function call that calls check move on the array and returns it
	public static boolean[] makeMove(boolean[] board, int d, int pos){
		if(checkMove(board, d, pos) == true){
			double midpoint = Math.floor((pos + d)/2);
			board[pos + d] = false;
			board[(int)midpoint] = false;
			board[pos] = true;
		}
		return board;
	}
	
	//get level returns the level of the position in a triangle
	public static int getLevel (int x){
		if(x < 2){
			return 1;	
		}
		else if(x < 4){
			return 2;
		}
		else if(x < 7){
			return 3;
		}
		else if(x < 11){
			return 4;
		}else{return 5;}
		
	}
	
	//get SUb returns the subtriangle that determins the move set for each peg
	public static int getSub (int x){//x is pos, reutrns sub triangle{
		if(x < 6){
			if(x == 4){
				return 2;
			}else{return 1;}
			
		}else if(x < 13){// includes 9 and 10 
			if(x == 6){
				return 4;
			}else{return 3;}
			
		}else if(x < 16){
			if(x == 13){
				return 6;
			}else{return 5;}
		}else{
			return 0;
		}
		
	}
	
	//peg count gets peg count and is for the game play state
	public static int pegCount (boolean[] board){
		int count = 0;
		while(count+1 < board.length){
			if(board[count] == true){
				++count;}
		}
		return count;
	}
	
	//restarts the pegs to all true
	public static void restart(boolean[] board){
		int count = 0;
		while(count+1 < board.length){
			board[count] = true;
			++count;
		}
	}
	
	//print function to print in triangle shape
	public static void print(boolean[] board){
		// outer loop to handle number of rows 
        //  n in this case 
		int n = 5;
		int space = 0;
        for (int i=0; i<n; i++) 
        { 
  
            // inner loop to handle number spaces 
            // values changing acc. to requirement 
            for (int j=n-i; j>1; j--) 
            { 
                // printing spaces 
                System.out.print(" "); 
            } 
   
            //  inner loop to handle number of columns 
            //  values changing acc. to outer loop 
            for (int j=0; j<=i; j++ ) 
            { 
                // printing stars 
                System.out.print(board[space]);
				space++;
            } 
   
            // ending line after each row 
            System.out.println(); 
        } 
		
	}
	
	//main or solver for puzzle
	public static void main(String[] args){	
		//variables add and temp are to dictate how much to add to each position given sub triangle
		//sub, pos, and peg count all just holder ints for position, sub triangle, and peg count
		//size holds the size of the returned array, for copy purposes
		int add = 0, pos = 0, sub = 0, pegCt = 0, temp = 0, size = 0;
		boolean[] arr = new boolean[15];
		restart(arr);//clear array

		arr[0] = false;//set hole
		
		pegCt = pegCount(arr);//get peg number
		while(pegCt > 1){//if that number bigger than 1
			
			//find first 0
			for(int x = 0; x < arr.length; x++){
				if (arr[x] == false){
					pos = x;
				}
			
			}
			//get sub triangle
			sub = getSub(pos);
			
			//depending on subtriangle
			if(sub == 1){//positions 1,2,3,5
				temp = 1 ;
				temp = temp + (getLevel(pos) * 2);
				//check specific positions based on +2 at every level and direction from sub
				if(arr[pos + temp] == true){
				    size = makeMove(arr, temp, pos).length;
					System.arraycopy(makeMove(arr, temp, pos), 0, arr, 0, size);
				}
				else if(arr[pos + temp + 2] == true){
					temp = temp + 2;
					size = makeMove(arr, temp, pos).length;
					System.arraycopy(makeMove(arr, temp, pos), 0, arr, 0, size);
				}
			}
			else if(sub == 2){//pos 4
				temp = -3;
				if(arr[pos + temp]){
					size = makeMove(arr, temp, pos).length;
					System.arraycopy(makeMove(arr, temp, pos), 0, arr, 0, size);
				}
				else if(arr[pos - temp + 2] == true){
					temp = 2;
					size = makeMove(arr, temp, pos).length;
					System.arraycopy(makeMove(arr, temp, pos), 0, arr, 0, size);
				}
				else if(arr[pos - temp + 5] == true){
					temp = 7;
					size = makeMove(arr, temp, pos).length;
					System.arraycopy(makeMove(arr, temp, pos), 0, arr, 0, size);
				}
				else if(arr[pos - temp + 7] == true){
					temp = 9;
					size = makeMove(arr, temp, pos).length;
					System.arraycopy(makeMove(arr, temp, pos), 0, arr, 0, size);
				}
			}
			else if(sub == 3){//pos 7,8,10,11
				temp = 2;
				if(pos == 11 || pos == 12){
					add = 2;}
				if(arr[pos + temp + add] == true){
					temp = 2;
					size = makeMove(arr, temp, pos).length;
					System.arraycopy(makeMove(arr, temp, pos), 0, arr, 0, size);
				}
				else if(arr[pos - 5 - temp - add] == true){
					temp = -5;
					size = makeMove(arr, temp, pos).length;
					System.arraycopy(makeMove(arr, temp, pos), 0, arr, 0, size);
				}
				
			}
			else if(sub == 4){//pos 6
				temp = -2;
				if(arr[pos + temp]){
					size = makeMove(arr, temp, pos).length;
					System.arraycopy(makeMove(arr, temp, pos), 0, arr, 0, size);
				}
				else if(arr[pos + temp - 3] == true){
					temp = -5;
					size = makeMove(arr, temp, pos).length;
					System.arraycopy(makeMove(arr, temp, pos), 0, arr, 0, size);
				}
				else if(arr[pos - temp + 5] == true){
					temp = 7;
				    size = makeMove(arr, temp, pos).length;
					System.arraycopy(makeMove(arr, temp, pos), 0, arr, 0, size);
				}
				else if(arr[pos - temp + 7] == true){
					temp = 9;
					size = makeMove(arr, temp, pos).length;
					System.arraycopy(makeMove(arr, temp, pos), 0, arr, 0, size);
				}
			}
			else if(sub == 5){//pos 9,10,14,15
				temp = -2;
				add = 0;
				if(pos == 14 || pos == 15){
					add = 2;
				    
				}
					
				if(arr[pos - temp - add] == true){
					temp = 2;
					size = makeMove(arr, temp, pos).length;
					System.arraycopy(makeMove(arr, temp, pos), 0, arr, 0, size);
				}
				else if(arr[pos - 5 +  temp - add] == true){
					temp = -5;
					size = makeMove(arr, temp, pos).length;
					System.arraycopy(makeMove(arr, temp, pos), 0, arr, 0, size);
				}
				
			}
			else if(sub == 6){//pos 13
				temp-=2;
				if(arr[pos + temp]){
				    size = makeMove(arr, temp, pos).length;
					System.arraycopy(makeMove(arr, temp, pos), 0, arr, 0, size);
				}
				else if(arr[pos + temp - 5] == true){
					temp = -7;
					size = makeMove(arr, temp, pos).length;
					System.arraycopy(makeMove(arr, temp, pos), 0, arr, 0, size);
				}
				else if(arr[pos + temp - 7] == true){
					temp = -9;
					size = makeMove(arr, temp, pos).length;
					System.arraycopy(makeMove(arr, temp, pos), 0, arr, 0, size);
				}
				else if(arr[pos + temp + 2] == true){
					temp = 2;
					size = makeMove(arr, temp, pos).length;
					System.arraycopy(makeMove(arr, temp, pos), 0, arr, 0, size);
				}
			}
			else{}//print dead
			temp = 0;//reset adder position
			pegCt = pegCount(arr);//get new peg count
			print(arr);//print
		}//while peg count
					
	}//main
	
	
}//class

