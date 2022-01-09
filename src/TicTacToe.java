// Jennifer Tsan, Josh Mallari, Nicholas Wade
// Group Project
// War Games TicTacToe.java
// 4.23.2020
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class TicTacToe extends Application {
	//buttons for the tic tac toe board
	public static Button button1 = new Button(); //top left
	public static Button button2 = new Button(); //middle left
	public static Button button3 = new Button(); //bottom left
	public static Button button4 = new Button(); //top middle
	public static Button button5 = new Button(); //middle middle
	public static Button button6 = new Button(); //bottom middle
	public static Button button7 = new Button(); //top right
	public static Button button8 = new Button(); //middle right
	public static Button button9 = new Button(); //bottom right
	//GridPane
	public static GridPane gridPane = new GridPane();
	//Array for determining tick placements
	public static int[][] intArr = new int[3][3];
	
	public static boolean[][] boolArr = new boolean[3][3];
	
	public static final int PLAYER_TICK = 1;
	public static final int CPU_TICK = -1;
	public static final int EMPTY_TICK = 0;
	
	public static int LAST_XCOORD = 0;
	public static int LAST_YCOORD = 0;
	
	public static int turns = 0;
	
	public static int winnerID = 0;
	//color choice box
	public String[] colorsText = {"Red", "Orange", "Yellow", "Green", "Blue", "Indigo", "Violet"};
	public ComboBox<String> colorChoiceBox = new ComboBox<>();
	//vbox to hold the color choice box, new game button, and win/lose/draw tracker text
	public static VBox vbox = new VBox();
	public Text changeColorText = new Text("Change the Color\nof the\nBackground Here: ");
	//the insults
	//the text will be set up to different texts depending on what button is clicked
	public static Label comment = new Label();
	//text for the animations (win/lose/draw)
	public static Text youWin = new Text("You Win");
	public static Text youLose = new Text("You Lose");
	public static Text draw = new Text("Draw");
	//new game button
	public Button newGame = new Button("New Game");
	//track the number of wins/loses/draws
	public static int wins = 0;
	public static int loses = 0;
	public static int draws = 0;
	public static Text tracker = new Text();
@Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
	  /*adding items to gridpane
	   * (buttons, new game button, insults text(even if it may not be visible yet)
	   *win/lose/draw tracker ( may not be visible unitl new game button is clicked after a finished game), win/lose/draw animation text (even if it may not be visible util completely finished game),
	   *color choice box
	   */
	  addToGrid();
	  //setting up the Tic Tac Toe game
	  Scene scene = new Scene(gridPane, 1500, 900);
	  //setting the title of the stage
	  primaryStage.setTitle("Tic Tac Toe");
	  //set the stage to display the tic tac toe game scene
	  primaryStage.setScene(scene);
	  //making sure the array starts with all 0s at the beginning of the tic tac toe game
	  for(int row = 0; row<intArr.length;row++) {
		  for(int col = 0; col<intArr.length; col++)
		  intArr[row][col] = EMPTY_TICK;
	  }
	  //determines if the button has been clicked or not or if it is clickable
	  for(int row = 0; row<boolArr.length;row++) {
		  for(int col = 0; col<boolArr.length; col++)
		  boolArr[row][col] = true;
	  }
	  //actions within the gridPane
	  //new game button
		  newGame.setOnAction(e -> {
			  /*call the newGameButton method
			  *should clear the entire board, set everything in the intArr to all 0s, and all items in boolArr to be clickable again
			  *enable the buttons to be clicked, and stop the win/lose/draw animations if they are displayed
			  */
			  newGameButton();
		  });
		  //color choice box
		  colorChoiceBox.setOnAction(e ->{
			  /*call the setBackgroundColor method
			   * if player chooses a certain color from the list, the background should change to that color
			   */
              setBackgroundColor();
		  });
		  //actions when button is clicked
		  //if player clicks the top left corner button
		  button1.setOnAction(e -> {
			  //if the boolArr is clickable in the top left corner
			  if(boolArr[0][0]) {
				  //set top left corner to cat picture
			  button1.setGraphic(new ImageView("https://pbs.twimg.com/profile_images/590966305248784384/1gX6-SY6.jpg"));
			  //update the intArr[0][0] to 1
			  playerButton(0,0);
			  //update to show the correct array in the console
			  debugPrintGrid();
			  //ai places move in corresponding to the player move
			  aiDecision(turns,intArr);
			  //checked to see if player or ai has won, lose, or tied and display the animations
			  winScreen();
			  //update the debug print grid
			  debugPrintGrid();
			  //display the insults
			  comment.setVisible(true);
			  comment.setText("Loser");
			  }
		  });
		  //if the player clicks the middle row left button
		  button2.setOnAction(e -> {
			  //if the boolArr is clickable in the middle row left
			  if(boolArr[1][0]) {
				//set top left corner to cat picture
			  button2.setGraphic(new ImageView("https://pbs.twimg.com/profile_images/590966305248784384/1gX6-SY6.jpg"));
			  //update the intArr[1][0] to 1
			  playerButton(1,0);
			  //update to show the correct array in the console
			  debugPrintGrid();
			  //ai places move in corresponding to the player move
			  aiDecision(turns,intArr);
			  //checked to see if player or ai has won, lose, or tied and display the animations
			  winScreen();
			  //update the debug print grid
			  debugPrintGrid();
			  //display the insults
			  comment.setVisible(true);
			  comment.setText("Stupid");
			  }
		  });
		  //if player clicks the bottom row left
		  button3.setOnAction(e -> {
			  //if  the boolArr is clickable in the bottom row left
			  if(boolArr[2][0]) {
				//set top left corner to cat picture
			  button3.setGraphic(new ImageView("https://pbs.twimg.com/profile_images/590966305248784384/1gX6-SY6.jpg"));
			  //update the intArr[2][0] to 1
			  playerButton(2,0);
			  //update to show the correct array in the console
			  debugPrintGrid();
			  //ai places move in corresponding to the player move
			  aiDecision(turns,intArr);
			  //checked to see if player or ai has won, lose, or tied and display the animations
			  winScreen();
			  //update the debug print grid
			  debugPrintGrid();
			  //display the insults
			  comment.setVisible(true);
			  comment.setText("Am I playing a baby?");
			  }
		  });
		  
		  button4.setOnAction(e -> {
			  if(boolArr[0][1]) {
				//set top left corner to cat picture
			  button4.setGraphic(new ImageView("https://pbs.twimg.com/profile_images/590966305248784384/1gX6-SY6.jpg"));
			  //update the intArr[0][1] to 1
			  playerButton(0,1);
			  //update to show the correct array in the console
			  debugPrintGrid();
			  //ai places move in corresponding to the player move
			  aiDecision(turns,intArr);
			  //checked to see if player or ai has won, lose, or tied and display the animations
			  winScreen();
			  //update the debug print grid
			  debugPrintGrid();
			  //display the insults
			  comment.setVisible(true);
			  comment.setText("Are you even trying?");
			  }
		  });
		  
		  button5.setOnAction(e -> {
			  if(boolArr[1][1]) {
				//set top left corner to cat picture
			  button5.setGraphic(new ImageView("https://pbs.twimg.com/profile_images/590966305248784384/1gX6-SY6.jpg"));
			  //update the intArr[1][1] to 1
			  playerButton(1,1);
			  //update to show the correct array in the console
			  debugPrintGrid();
			  //ai places move in corresponding to the player move
			  aiDecision(turns,intArr);
			  //checked to see if player or ai has won, lose, or tied and display the animations
			  winScreen();
			  //update the debug print grid
			  debugPrintGrid();
			  //display the insults
			  comment.setVisible(true);
			  comment.setText("You suck!");
			  }
		  });
		  
		  button6.setOnAction(e -> {
			  if(boolArr[2][1]) {
				//set top left corner to cat picture
			  button6.setGraphic(new ImageView("https://pbs.twimg.com/profile_images/590966305248784384/1gX6-SY6.jpg"));
			  //update the intArr[2][1] to 1
			  playerButton(2,1);
			  //update to show the correct array in the console
			  debugPrintGrid();
			  //ai places move in corresponding to the player move
			  aiDecision(turns,intArr);
			  //checked to see if player or ai has won, lose, or tied and display the animations
			  winScreen();
			  //update the debug print grid
			  debugPrintGrid();
			  //display the insults
			  comment.setVisible(true);
			  comment.setText("Do better!");
			  }
		  });
		  
		  button7.setOnAction(e -> {
			  if(boolArr[0][2]) {
				//set top left corner to cat picture
			  button7.setGraphic(new ImageView("https://pbs.twimg.com/profile_images/590966305248784384/1gX6-SY6.jpg"));
			  //update the intArr[0][1] to 1
			  playerButton(0,2);
			  //update to show the correct array in the console
			  debugPrintGrid();
			  //ai places move in corresponding to the player move
			  aiDecision(turns,intArr);
			  //checked to see if player or ai has won, lose, or tied and display the animations
			  winScreen();
			  //update the debug print grid
			  debugPrintGrid();
			  //display the insults
			  comment.setVisible(true);
			  comment.setText("Do you even know\nhow to play?");
			  }
		  });
		  
		  button8.setOnAction(e -> {
			  if(boolArr[1][2]) {
				//set top left corner to cat picture
			  button8.setGraphic(new ImageView("https://pbs.twimg.com/profile_images/590966305248784384/1gX6-SY6.jpg"));
			  //update the intArr[1][2] to 1
			  playerButton(1,2);
			  //update to show the correct array in the console
			  debugPrintGrid();
			  //ai places move in corresponding to the player move
			  aiDecision(turns,intArr);
			  //checked to see if player or ai has won, lose, or tied and display the animations
			  winScreen();
			  //update the debug print grid
			  debugPrintGrid();
			  //display the insults
			  comment.setVisible(true);
			  comment.setText("Dumb");
			  }
		  });
		  
		  button9.setOnAction(e -> {
			  if(boolArr[2][2]) {
				//set top left corner to cat picture
			  button9.setGraphic(new ImageView("https://pbs.twimg.com/profile_images/590966305248784384/1gX6-SY6.jpg"));
			  //update the intArr[1][2] to 1
			  playerButton(2,2);
			  //update to show the correct array in the console
			  debugPrintGrid();
			  //ai places move in corresponding to the player move
			  aiDecision(turns,intArr);
			  //checked to see if player or ai has won, lose, or tied and display the animations
			  winScreen();
			  //update the debug print grid
			  debugPrintGrid();
			  //display the insults
			  comment.setVisible(true);
			  comment.setText("Just give up.");
			  }
		  });
		  //show the stage of the tic tac toe game
	  primaryStage.show();
  }

  //method for when someone wins
  public static void winScreen() {
	  //what happens when player wins
	  if(winnerID==1) {
		  //all buttons are disabled so no one can click on them
		  button1.setDisable(true);
		  button2.setDisable(true);
		  button3.setDisable(true);
		  button4.setDisable(true);
		  button5.setDisable(true);
		  button6.setDisable(true);
		  button7.setDisable(true);
		  button8.setDisable(true);
		  button9.setDisable(true);
		  //the insults are hidden 
		  comment.setVisible(false);
		  //the animations of you win start blinking
		  animations(youWin);
		  //you win text are added to the right side of the tic tac toe board
		  gridPane.add(youWin, 5, 1);
		  //the numbers of wins is increases by 1
		  wins++;
	  }else
		  //what happens when AI wins
		  if(winnerID==-1) {
			  //all buttons are disabled so no one can click on them 
			  button1.setDisable(true);
			  button2.setDisable(true);
			  button3.setDisable(true);
			  button4.setDisable(true);
			  button5.setDisable(true);
			  button6.setDisable(true);
			  button7.setDisable(true);
			  button8.setDisable(true);
			  button9.setDisable(true);
			  //insults are hidden
			  comment.setVisible(false);
			  //the animations of you lose start blinking
			  animations(youLose);
			  //you lose text is added ot the side of the tic tac toe board
			  gridPane.add(youLose, 5, 1);
			  //the numbers of loses is increased by 1
			  loses++;
		  }else
			  //what happens if there was a tie
			  if((winnerID==0)&&(turns>=9)) {
				//all buttons are disabled so no one can click on them 
				  button1.setDisable(true);
				  button2.setDisable(true);
				  button3.setDisable(true);
				  button4.setDisable(true);
				  button5.setDisable(true);
				  button6.setDisable(true);
				  button7.setDisable(true);
				  button8.setDisable(true);
				  button9.setDisable(true);
				  //comments are hidden
				  comment.setVisible(false);
				  //the animations of draw start blinking
				  animations(draw);
				  //draw text is added to the side of the tic tac toe board
				  gridPane.add(draw, 5, 1);
				  //the number of draws is increased by one
				  draws++;
			  }
  }
  
 //animations for win/lose/draw text
  public static void animations(Text text) {
	  //fade text 
      FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.1), text);
      //the opacity
      fadeTransition.setFromValue(1.0);
      fadeTransition.setToValue(0.0);
      //the opacity changes constantly 
      fadeTransition.setCycleCount(Animation.INDEFINITE);
      //play the actual animations
      fadeTransition.play();
  }
  
  //method for new button action
  public static void newGameButton() {
	  //resets the entire board to 0
	  for(int row = 0; row<intArr.length;row++) {
		  for(int col = 0; col<intArr.length; col++)
		  intArr[row][col] = EMPTY_TICK; 
	  }
	  //reseting the boolArr of determining if the button has already been clicked or is clickable
	  for(int row = 0; row<boolArr.length;row++) {
		  for(int col = 0; col<boolArr.length; col++)
		  boolArr[row][col] = true;
	  }
	  //button graphics set to nothing so there is no image
	  button1.setGraphic(null);
	  button2.setGraphic(null);
	  button3.setGraphic(null);
	  button4.setGraphic(null);
	  button5.setGraphic(null);
	  button6.setGraphic(null);
	  button7.setGraphic(null);
	  button8.setGraphic(null);
	  button9.setGraphic(null);
	  //removing win, lose, draw text
	  gridPane.getChildren().remove(youWin);
	  gridPane.getChildren().remove(youLose);
	  gridPane.getChildren().remove(draw);
	  //allowing buttons to be clicked again
	  button1.setDisable(false);
	  button2.setDisable(false);
	  button3.setDisable(false);
	  button4.setDisable(false);
	  button5.setDisable(false);
	  button6.setDisable(false);
	  button7.setDisable(false);
	  button8.setDisable(false);
	  button9.setDisable(false);
	  //removing the insult
	  comment.setVisible(false);
	  //updating the tracker to dispaly the correct number of wins, loses, and draws of the player
	  tracker.setText("\n\n wins: " + wins + "\tloses: " + loses + "\n\tties: " + draws);
	  //reset the number of turns
	  turns = 0;
	  //reset the winner id
	  winnerID =0;
  }
  
  //ai button method
  public static void aiButton(int row, int col, Button b) {
	  //place the -1 to the correct place in the array 
	  intArr[row][col] = CPU_TICK;
	  //set the button to be not clickable by the player
	  boolArr[row][col] = false;
	  //set that certain button to the dog image
	  b.setGraphic(new ImageView("https://static-s.aa-cdn.net/img/ios/1105425947/cba5dafa1699c1eea6794200712a0353?v=1"));
	  //check if the ai has won already on the tic tac toe board (horizontally/vertically/diagonally)
	  if((checkWin.horizWin(intArr)==-1)||(checkWin.vertWin(intArr)==-1)||(checkWin.diagWin(intArr)==-1)) {
		  winnerID = -1;
		  return;
	  }
	  //update the number of turns
	  turns++;
  }

  //player button method
  public static void playerButton(int row, int col) {
	  //set the certain array row and column to 1 corresponding to where the button was clicked
	  intArr[row][col] = PLAYER_TICK;
	  //set the button to me unclickable after it has been clicked
	  boolArr[row][col] = false;
	  LAST_XCOORD = col;
	  LAST_YCOORD = row;
	  //checking to see if the player has won (horizonatally, vertically, and diagonally)
	  if((checkWin.horizWin(intArr)==1)||(checkWin.vertWin(intArr)==1)||(checkWin.diagWin(intArr)==1)) {
		  winnerID = 1;
		  return;
	  }
	  //add 1 to the numbers of turns tracker
	  turns++;
  }
  
  //debug method
  /*prints the tic tac toe board into the console for us to see what happens if the buttons are clicked
   * to see if the 1, -1, 0 are in the correct place in the array and see the correct buttons correspond to the array 
   */
  
  public static void debugPrintGrid() {
	  System.out.println("============Turn "+turns+"=============");
	  for(int row = 0; row<intArr.length; row++) {
		  for(int col = 0; col<intArr.length; col++) {
			  System.out.print(intArr[row][col]+"  ");
		  }
		  System.out.println();
		  System.out.println();
	  }
  }
  
  //all the moves that the ai will make whenever the player clicks a button
  public static void aiDecision(int turnNum, int[][] arr) {
	//if its turn 1, and player did not pick the center	
	  if((turnNum==1)&&!(arr[1][1]==1)) {
		  //ai chooses the center piece
		 aiButton(1,1,button5); 
	  }else
		  //if its turn 1 and player chooses center
		if((turnNum==1)&&(arr[1][1]==1)) {
			switch((int)((Math.random()*4)+1)) {
			//ai chooses one of the corner pieces
			case 1: aiButton(0,0,button1); break;
			case 2: aiButton(2,0,button3); break;
			case 3: aiButton(0,2,button7); break;
			case 4: aiButton(2,2,button9); break;
			}
		}else {
			//if its turn 3 and the player choose the center
			//place ai button the opposite of the player move
			if((turnNum==3)&&(turnNum<=9)&&(arr[1][1]==1)) {
				//if the top left corner is player but bottom right corner is empty 
				if((arr[0][0]==1)&&(arr[2][2]==0)) {
					//ai places move at the bottom right corner
					aiButton(2,2,button9);
				}else
					//if the middle row left is player 1 and the middle row right is empty
				if((arr[1][0]==1)&&(arr[1][2]==0)) {
					//ai places move at middle row right
					aiButton(1,2,button8);
				}else
					//if the bottom left corner is player and the top right is empty
				if((arr[2][0]==1)&&(arr[0][2]==0)) {
					//ai places move in top right
					aiButton(0,2,button7);
				}else
					//if the bottom row middle is player and top row middle is empty
				if((arr[2][1]==1)&&(arr[0][1]==0)) {
					//ai move is top row middle
					aiButton(0,1,button4);
				}else
					//if bottom right corner is plaer and top left corner is empty
				if((arr[2][2]==1)&&(arr[0][0]==0)) {
					//ai places move in top left corner
					aiButton(0,0,button1);
				}else
					//if middle row right is player and middle row left is empty
				if((arr[1][2]==1)&&(arr[1][0]==0)) {
					//ai move is middle row left
					aiButton(1,0,button2);
				}else
					//if top right corner is player and bottom right corner is empty
				if((arr[0][2]==1)&&(arr[2][0]==0)) {
					//ai move is bottom right corner
					aiButton(2,0,button3);
				}else
					//if top middle is player and bottom row middle is empty
				if((arr[0][1]==1)&&(arr[2][1]==0)) {
					//ai move is bottom row middle 
					aiButton(2,1,button6);
				}else {
					if((arr[0][0]==1)&&(arr[0][1]==0)) {
						aiButton(0,1,button4);
					}else
					if((arr[0][0]==1)&&(arr[1][0]==0)) {
						aiButton(1,0,button2);
					}else
					if((arr[1][0]==1)&&(arr[0][0]==0)) {
						aiButton(0,0,button1);
					}else
					if((arr[1][0]==1)&&(arr[2][0]==0)) {
						aiButton(2,0,button3);
					}else
					if((arr[2][0]==1)&&(arr[1][0]==0)) {
						aiButton(1,0,button2);
					}else
					if((arr[2][0]==1)&&(arr[2][1]==0)) {
						aiButton(2,1,button6);
					}else
					if((arr[2][1]==1)&&(arr[2][0]==0)) {
						aiButton(2,0,button3);
					}else
					if((arr[2][1]==1)&&(arr[2][2]==0)) {
						aiButton(2,2,button9);	
					}else
					if((arr[2][2]==1)&&(arr[2][1]==0)) {
						aiButton(2,1,button6);
					}else
					if((arr[2][2]==1)&&(arr[1][2]==0)) {
						aiButton(1,2,button8);
					}else
					if((arr[1][2]==1)&&(arr[2][2]==0)) {
						aiButton(2,2,button9);
					}else
					if((arr[1][2]==1)&&(arr[0][2]==0)) {
						aiButton(0,2,button7);
					}else
					if((arr[0][2]==1)&&(arr[0][1]==0)) {
						aiButton(0,1,button4);
					}else
					if((arr[0][2]==1)&&(arr[1][2]==0)) {
						aiButton(1,2,button8);
					}else
					if((arr[0][1]==1)&&(arr[0][0]==0)) {
						aiButton(0,0,button1);
					}else
					if((arr[0][1]==1)&&(arr[0][2]==0)) {
						aiButton(0,2,button7);
					}				
				}
			}else
			if((arr[2][0]==1)&&(arr[0][2]==1)&&(arr[1][0]==0)) {
				aiButton(1,0,button2);
			}else
				if((arr[2][0]==1)&&(arr[1][2]==1)&&(arr[2][2]==0)){
                aiButton(2,2,button9);
            }else
				if((turnNum%2==1)&&(turnNum>=3)&&(turnNum<9)) {
					//if-else statements for blocking the player, forcing them to not win
					//horizontal
					if(((arr[0][0]==-1)&&(arr[0][1])==-1)&&(arr[0][2]==0)){
						aiButton(0,2,button7);
					}else
					if(((arr[1][0]==-1)&&(arr[1][1])==-1)&&(arr[1][2]==0)){
						aiButton(1,2,button8);
					}else
					if(((arr[2][0]==-1)&&(arr[2][1])==-1)&&(arr[2][2]==0)){
						aiButton(2,2,button9);
					}else
					if(((arr[0][1]==-1)&&(arr[0][2])==-1)&&(arr[0][0]==0)){
						aiButton(0,0,button1);
					}else
					if(((arr[1][1]==-1)&&(arr[1][2])==-1)&&(arr[1][0]==0)){
						aiButton(1,0,button2);
					}else

					if(((arr[2][1]==-1)&&(arr[2][2])==-1)&&(arr[2][0]==0)){
						aiButton(2,0,button3);
					}else
					if(((arr[0][0]==-1)&&(arr[0][2])==-1)&&(arr[0][1]==0)){
						aiButton(0,1,button4);
					}else

					if(((arr[1][0]==-1)&&(arr[1][2])==-1)&&(arr[1][1]==0)){
						aiButton(1,1,button5);
					}else

					if(((arr[2][0]==-1)&&(arr[2][2])==-1)&&(arr[2][1]==0)){
						aiButton(2,1,button6);
					}else

					//vertical
					if(((arr[0][0]==-1)&&(arr[1][0])==-1)&&(arr[2][0]==0)){
						aiButton(2,0,button3);
					}else
					if(((arr[0][1]==-1)&&(arr[1][1])==-1)&&(arr[2][1]==0)){
						aiButton(2,1,button6);
					}else
					if(((arr[0][2]==-1)&&(arr[1][2])==-1)&&(arr[2][2]==0)){
						aiButton(2,2,button9);
					}else

					if(((arr[0][0]==-1)&&(arr[2][0])==-1)&&(arr[1][0]==0)){
						aiButton(1,0,button2);
					}else
					if(((arr[0][1]==-1)&&(arr[2][1])==-1)&&(arr[1][1]==0)){
						aiButton(1,1,button5);
					}else
					if(((arr[0][2]==-1)&&(arr[2][2])==-1)&&(arr[1][2]==0)){
						aiButton(1,2,button8);
					}else

					if(((arr[1][0]==-1)&&(arr[2][0])==-1)&&(arr[0][0]==0)){
						aiButton(0,0,button1);
					}else
					if(((arr[1][1]==-1)&&(arr[2][1])==-1)&&(arr[0][1]==0)){
						aiButton(0,1,button4);
					}else
					if(((arr[1][2]==-1)&&(arr[2][2])==-1)&&(arr[0][2]==0)){
						aiButton(0,2,button7);
					}else

					//diagonal
					if(((arr[0][0]==-1)&&(arr[1][1])==-1)&&(arr[2][2]==0)){
						aiButton(2,2,button9);
					}else
					if(((arr[0][2]==-1)&&(arr[1][1])==-1)&&(arr[2][0]==0)){
						aiButton(2,0,button3);
					}else

					if(((arr[0][0]==-1)&&(arr[2][2])==-1)&&(arr[1][1]==0)){
						aiButton(1,1,button5);
					}else
					if(((arr[0][2]==-1)&&(arr[2][0])==-1)&&(arr[1][1]==0)){
						aiButton(1,1,button5);
					}else

					if(((arr[2][0]==-1)&&(arr[1][1])==-1)&&(arr[0][2]==0)){
						aiButton(0,2,button7);
					}else
					if(((arr[2][2]==-1)&&(arr[1][1])==-1)&&(arr[0][0]==0)){
						aiButton(0,0,button1);
					}else

					//***************
					//horizontal
					if(((arr[0][0]==1)&&(arr[0][1])==1)&&(arr[0][2]==0)){
						aiButton(0,2,button7);
					}else
					if(((arr[1][0]==1)&&(arr[1][1])==1)&&(arr[1][2]==0)){
						aiButton(1,2,button8);
					}else
					if(((arr[2][0]==1)&&(arr[2][1])==1)&&(arr[2][2]==0)){
						aiButton(2,2,button9);
					}else
					if(((arr[0][1]==1)&&(arr[0][2])==1)&&(arr[0][0]==0)){
						aiButton(0,0,button1);
					}else
					if(((arr[1][1]==1)&&(arr[1][2])==1)&&(arr[1][0]==0)){
						aiButton(1,0,button2);
					}else

					if(((arr[2][1]==1)&&(arr[2][2])==1)&&(arr[2][0]==0)){
						aiButton(2,0,button3);
					}else
					if(((arr[0][0]==1)&&(arr[0][2])==1)&&(arr[0][1]==0)){
						aiButton(0,1,button4);
					}else

					if(((arr[1][0]==1)&&(arr[1][2])==1)&&(arr[1][1]==0)){
						aiButton(1,1,button5);
					}else

					if(((arr[2][0]==1)&&(arr[2][2])==1)&&(arr[2][1]==0)){
						aiButton(2,1,button6);
					}else

					//vertical
					if(((arr[0][0]==1)&&(arr[1][0])==1)&&(arr[2][0]==0)){
						aiButton(2,0,button3);
					}else
					if(((arr[0][1]==1)&&(arr[1][1])==1)&&(arr[2][1]==0)){
						aiButton(2,1,button6);
					}else
					if(((arr[0][2]==1)&&(arr[1][2])==1)&&(arr[2][2]==0)){
						aiButton(2,2,button9);
					}else

					if(((arr[0][0]==1)&&(arr[2][0])==1)&&(arr[1][0]==0)){
						aiButton(1,0,button2);
					}else
					if(((arr[0][1]==1)&&(arr[2][1])==1)&&(arr[1][1]==0)){
						aiButton(1,1,button5);
					}else
					if(((arr[0][2]==1)&&(arr[2][2])==1)&&(arr[1][2]==0)){
						aiButton(1,2,button8);
					}else

					if(((arr[1][0]==1)&&(arr[2][0])==1)&&(arr[0][0]==0)){
						aiButton(0,0,button1);
					}else
					if(((arr[1][1]==1)&&(arr[2][1])==1)&&(arr[0][1]==0)){
						aiButton(0,1,button4);
					}else
					if(((arr[1][2]==1)&&(arr[2][2])==1)&&(arr[0][2]==0)){
						aiButton(0,2,button7);
					}else
					//diagonal
					if(((arr[0][0]==1)&&(arr[1][1])==1)&&(arr[2][2]==0)){
						aiButton(2,2,button9);
					}else
					if(((arr[0][2]==1)&&(arr[1][1])==1)&&(arr[2][0]==0)){
						aiButton(2,0,button3);
					}else

					if(((arr[0][0]==1)&&(arr[2][2])==1)&&(arr[1][1]==0)){
						aiButton(1,1,button5);
					}else
					if(((arr[0][2]==1)&&(arr[2][0])==1)&&(arr[1][1]==0)){
						aiButton(1,1,button5);
					}else

					if(((arr[2][0]==1)&&(arr[1][1])==1)&&(arr[0][2]==0)){
						aiButton(0,2,button7);
					}else
					if(((arr[2][2]==1)&&(arr[1][1])==1)&&(arr[0][0]==0)){
						aiButton(0,0,button1);
					}else {
						//if all scenerios do not happen, place ai button in empty and random square
						for(int row = 0; row<arr.length;row++) {
							for(int col = 0; col<arr.length;col++) {
								if(arr[row][col]==0) {
									aiButton(row,col, getButton(row,col));
									return;
								}
							}
						}
					}
				}					
		}
	}
  //get button method
  public static Button getButton(int row, int col) {
	  if((row==0)&&(col==0))
		  return button1;
	  else
	  if((row==1)&&(col==0))
		  return button2;
	  else
	  if((row==2)&&(col==0))
		  return button3;
	  else
	  if((row==0)&&(col==1))
		  return button4;
	  else
	  if((row==1)&&(col==1))
		  return button5;
	  else
	  if((row==2)&&(col==1))
		  return button6;
	  else
	  if((row==0)&&(col==2))
		  return button7;
	  else
	  if((row==1)&&(col==2))
		  return button8;
	  else
	  if((row==2)&&(col==2))
		  return button9;
	  else
		  return null;
  }
  //method for adding the items to the Grid Pane
  public void addToGrid() {
	  //setting up the button sizes
	  button1.setPrefSize(300, 300);
	  button2.setPrefSize(300, 300);
	  button3.setPrefSize(300, 300);
	  button4.setPrefSize(300, 300);
	  button5.setPrefSize(300, 300);
	  button6.setPrefSize(300, 300);
	  button7.setPrefSize(300, 300);
	  button8.setPrefSize(300, 300);
	  button9.setPrefSize(300, 300);
	  //adding Buttons to gridPane
	  gridPane.add(button1, 2, 0); //top left
	  gridPane.add(button2, 2, 1); //middle left
	  gridPane.add(button3, 2, 2); //bottom left
	  gridPane.add(button4, 3, 0); //top middle
	  gridPane.add(button5, 3, 1); //middle middle
	  gridPane.add(button6, 3, 2); //bottom middle
	  gridPane.add(button7, 4, 0); //top right
	  gridPane.add(button8, 4, 1); //middle right
	  gridPane.add(button9, 4, 2); //bottom right
	  //setting up colorChoice Box
	  vbox.setPadding(new Insets(20, 50, 50, 50));
	  vbox.setSpacing(10);
	  changeColorText.setTextAlignment(TextAlignment.CENTER);
	  vbox.getChildren().addAll(changeColorText, colorChoiceBox);
	  ObservableList<String> items = FXCollections.observableArrayList(colorsText);
	  colorChoiceBox.getItems().addAll(items);
	  //adding new game to gridPane
	  vbox.getChildren().add(newGame);
	  //adding color choice box to gridPane
	  gridPane.add(vbox, 1, 0);
	  //adding tracker to vbox
	  vbox.getChildren().add(tracker);
	  //setting the win, lose, draw text size
	  tracker.setStyle("-fx-font-size: 20px;");
	  //setting up win, lose, draw text size
	  youLose.setStyle("-fx-font-size: 100px;");
	  youWin.setStyle("-fx-font-size: 100px;");
	  draw.setStyle("-fx-font-size: 100px;");
	  //comments
	  //setting the comment to not be visible when the tic tac toe game is ran
	  comment.setVisible(false);
	  //padding so that it is not right next to the tic tac toe board
	  comment.setPadding(new Insets(5, 5, 5, 5));
	  //setting up the insult text size
	  comment.setStyle("-fx-font-size: 30px;");
	  //add insult to the gridpane but it will not be visible until a button is clicked
	  gridPane.add(comment, 5,  0);
  }
  //setting the background color action method
  public void setBackgroundColor() {
	  //when the user chooses RED the background changes to RED
	  if (colorChoiceBox.getValue() == "Red") {
		  gridPane.setStyle("-fx-background-color: RED");
		  //when the user chooses ORANGE background changes to ORANGE
	  } else if (colorChoiceBox.getValue() == "Orange") {
		  gridPane.setStyle("-fx-background-color: ORANGE");
		  //when user chooses yellow background changes to YELLOW
	  } else if (colorChoiceBox.getValue() == "Yellow") {
		  gridPane.setStyle("-fx-background-color: YELLOW");
		  //when user chooses Green background changes to GREEN
	  }else if (colorChoiceBox.getValue() == "Green") {
		  gridPane.setStyle("-fx-background-color: GREEN");
		  //when user chooses blue background changes to BLUE
	  }else if (colorChoiceBox.getValue() == "Blue") {
		  gridPane.setStyle("-fx-background-color: BLUE");
		  //when user chooses indigo background changes to INDIGO
	  }else if (colorChoiceBox.getValue() == "Indigo") {
		  gridPane.setStyle("-fx-background-color: INDIGO");
	  } else {
		  //when the user chooces violet background changes to VIOLET
		  gridPane.setStyle("-fx-background-color: VIOLET");
	  }
  }
 //main 
  public static void main(String[] args) {
	  /*debug in the console to see if the tic tac toe game works according to plan
	   * should display the number of turns 
	   */
	 debugPrintGrid();
	 //launch the tic tac toe game
	 Application.launch(args);
  }
}