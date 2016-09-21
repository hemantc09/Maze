/*
 * Author : Hemant Choudhari
 * Date: 09/21/2016
 * Contact: Hemant 
 */
import java.util.Stack;


class Maze
{
	int visted= 5; 	//variable to check if the array index is visited or not
	char finalPath=9; 	// for valid path values
	String finalPathString; 		// stores final detailed path from start to end
	Stack<String> st=new Stack<>(); // to store the final path 
	int [][] mazeRunnerArray={ 			//mazeRunner to indicate the maze structure 6x6 2D array 
			{1,1,1,0,1,1},			// start point is (0,0) index and end point is (5,5) 
			{1,0,1,1,1,0},			//i.e. Top left corner and bottom right corner of array
			{0,0,0,1,1,0},
			{1,1,1,1,0,1},
			{1,0,1,0,1,0},
			{1,1,1,1,1,1}
		};
	public boolean traverse(int row, int column) { //traverse function is for to traverse the mazeRunner 
		// TODO Auto-generated method stub		   // recursively 
		boolean done=false;		// done is for to check if the maze is traversed or not
		if(valid(row,column))	//valid is for to check the point of row and column are valid or not
		{						// and based on the assign the values to indexes e.g. visited 
			mazeRunnerArray[row][column]=visted;	
			if(row==mazeRunnerArray.length-1 && column==mazeRunnerArray[0].length-1)
			{
				done=true;  //if the maze is traversed set true 
			}
			else			//else choose down,right,up,left next position 
			{
				done=traverse(row+1,column);//down
				if(!done)
					done=traverse(row, column+1);//right
				if(!done)
					done=traverse(row-1, column);//up
				if(!done)
					done=traverse(row, column-1);//left
			}
			
			if(done)
			{
				mazeRunnerArray[row][column]=finalPath;
				finalPathString=""+"["+row+"]["+column+"]";
				st.push(finalPathString);	//push the resulted path string in to the maze for future display	
			}								//st is the stack to store the result 
		}
		return done;
	}
	
	private boolean valid(int row, int column) {	//valid checks if the array index position is valid or not
		// TODO Auto-generated method stub
		boolean result=false;				
		//check if the room  is in the bounds of the matrix
		if(row>=0 && row<mazeRunnerArray.length && column>=0&&column<mazeRunnerArray[row].length)
			if(mazeRunnerArray[row][column]==1)
				result=true;
		return result; // return the result true if the location is valid
	}
	
	//Returns the string representation of the maze
	public String toString()
	{
		String result="\n";
		
		for(int row=0;row<mazeRunnerArray.length;row++)
		{
			for(int column=0;column<mazeRunnerArray[row].length;column++)
			{
				result +=" "+mazeRunnerArray[row][column] + " ";
			}
			result +="\n";
		}
		return result;	
	}

	public void finalPath() {
		// TODO Auto-generated method stub
		System.out.println("final Path is:");
		while(!st.empty())  	//until stack is empty print all the stack elements 
		{
			System.out.print("->"+st.pop()+" ");
		}
		
	}
}

public class MazeSearchMainClass { 	//Main function to call the Maze class and call finalPath funtion

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Maze mazeObject=new Maze();
		System.out.println("Maze");
		System.out.println(mazeObject.toString());
		if(mazeObject.traverse(0,0))
		{
			System.out.println("MazeRunner traversed successfully");
		}
		else
		{
			System.out.println("There is no possible path for mazeRunner");
		}
		System.out.println(mazeObject.toString());
		mazeObject.finalPath();
	}

}
