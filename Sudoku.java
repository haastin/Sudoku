public class Sudoku {

	int[][] map;


	boolean isValid(int guess, int guess_x, int guess_y, int[][] map){


		//check each row and column our guess is in to see if any box also in that row/column contains our guess 

		for(int i = 0; i < 9; i++){

			if(map[guess_x][i] == guess){ //check row
				return false;
			}
			if(map[i][guess_y] == guess){ //check column
				return false;
			}


			
			/*check the 3x3 box the number is in
			 * 
			 * So to make sure we know what indices to check we need to know exactly where the guess' x and y coordinates are within the 3x3 box.
			 * You could create a new array of the 9 elements in the box and loop through it, but that would cause us to use more memory. It 
			 * doesn't help with speed either, since both creating a new array and looping through it versus finding how far to the left/right or
			 * up/down we have to go from our guess inside the box can be implemented in constant time since we know there will always be 9 elements
			 * in the 3x3 box. So, we choose the latter option for memory efficiency. 
			 *  
			 */
			


			//Case: x is at the left most edge of the box

			if(guess_x % 3 == 0){  
				

				if(guess_y % 3 == 0){  //Case: y is at the top of the box
					
					for(int y = guess_y; y < guess_y + 3; y++){
						
						for(int x = guess_x; x < guess_x + 3; x++){
							
							if(map[x][y] == guess){ //check specific box
								return false;
							}

						}
					}
				}


				else if( (guess_x + 1) % 3 == 0){ //Case: y is at the bottom of the box
					
					
					for(int y = guess_y - 2; y < guess_y + 1; y++){
						
						
						for(int x = guess_x; x < guess_x + 3; x++){

							if(map[x][y] == guess){ //check specific box
								return false;
							}

						}
				
					}

				}

				else{ //Case: y is at the middle of the box
					

					for(int y = guess_y - 1; y < guess_y + 2; y++){
						
						
						for(int x = guess_x; x < guess_x + 3; x++){

							if(map[x][y] == guess){ //check specific box
								return false;
							}
							
						}
				
					}

				}
			}


			//Case: x is at the right most edge of the box

			else if( (guess_x + 1) % 3 == 0){ 

				if(guess_y % 3 == 0){  //Case: y is at the top of the box
					
					for(int y = guess_y; y < guess_y + 3; y++){
						
						for(int x = guess_x - 2; x < guess_x + 1; x++){
							
							if(map[x][y] == guess){ //check specific box
								return false;
							}

						}
					}
				}


				else if( (guess_x + 1) % 3 == 0){ //Case: y is at the bottom of the box
					
					
					for(int y = guess_y - 2; y < guess_y + 1; y++){
						
						
						for(int x = guess_x - 2; x < guess_x + 1; x++){

							if(map[x][y] == guess){ //check specific box
								return false;
							}

						}
				
					}

				}

				else{ //Case: y is at the middle of the box
					

					for(int y = guess_y - 1; y < guess_y + 2; y++){
						
						
						for(int x = guess_x - 2; x < guess_x + 1; x++){

							if(map[x][y] == guess){ //check specific box
								return false;
							}
							
						}
				
					}

				}
			}


			//Case: x is at the middle of the box

			else{ 


				if(guess_y % 3 == 0){  //Case: y is at the top of the box
					
					for(int y = guess_y; y < guess_y + 3; y++){
						
						for(int x = guess_x - 1; x < guess_x + 2; x++){
							
							if(map[x][y] == guess){ //check specific box
								return false;
							}

						}
					}
				}


				else if( (guess_x + 1) % 3 == 0){ //Case: y is at the bottom of the box
					
					
					for(int y = guess_y - 2; y < guess_y + 1; y++){
						
						
						for(int x = guess_x - 1; x < guess_x + 2; x++){

							if(map[x][y] == guess){ //Case: check specific box
								return false;
							}

						}
				
					}

				}

				else{ //Case: y is at the middle of the box
					

					for(int y = guess_y - 1; y < guess_y + 2; y++){
						
						
						for(int x = guess_x - 1; x < guess_x + 2; x++){

							if(map[x][y] == guess){ //check specific box
								return false;
							}
							
						}
				
					}

				}
			}


		}



		return true;
		
	} 

	boolean isComplete(int[][] map){

		for(int x = 0; x < 9; x ++){
			for(int y = 0; y < 9; y++){
				if(map[x][y] == 0){
					return false;
				}
			}
		}

		return true;
	}

	public Sudoku(){

		map = new int[9][9];

		
		for(int x = 0; x < 9; x++){

			for(int y = 0; y < 9; y++){

				map[x][y] = 0;
			}
		}

	}

	static int random_x(){

		int x = (int) Math.random()*10; 

			//all indices must be between 0-8

			while(x > 8){
				
				x = (int) Math.random()*10;

			}
		
			return x;
	}

	static int random_y(){

		int y = (int) Math.random()*10; 

			//all indices must be between 0-8

			while(y > 8){
				
				y = (int) Math.random()*10;

			}
		
			return y;
	}

	static int random_number(){

		int starting_number = (int) Math.random()*10;

			//all numbers must be between 1-9, inclusive, so if the first random number isn't, keep trying

			while(starting_number == 0){
				
				starting_number = (int) Math.random()*10;

			}
		
			return starting_number;
	}

	void print_Map(int[][] map){

		for(int y = 0; y < 9; y ++){
			for(int x = 0; x < 9; x++){
				
				System.out.print(map[x][y]);
			}
			System.out.println();
		}

	}

    public static void main(String[] args)  {
        

		/* 
		 * For a sudoku to have one unique solution, there must be 17 starting numbers in the sudoku. However, we will populate these 17  
		 * numbers randomly, and at random spots in the map, so that each map will be different than the one before it (most likely).
		 * 
		*/
		Sudoku sudoku = new Sudoku();

		for(int i = 0; i < 17; i++){
	
			while(true){

				int x = random_x();
				int y = random_y();
				int num = random_number();
				
				if(sudoku.isValid(num, x, y, sudoku.map)){

					sudoku.map[x][y] = num;
					break;
				}

			}
		}

		sudoku.print_Map(sudoku.map);


			


		//map is now set up, and we can check user guesses to see if they are correct. 
		//initialize map
		//need a way to display the current state of the map (this will be a pre-GUI build)
		//need a way to ask the user for input
		//need to check if the guess is correct by the rules of sudoku
		//need some way to track whether the map is complete or not

		//make GUI here and launch



	}
    

}
