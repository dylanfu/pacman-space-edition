package controller;

import model.Level;
import view.Alien;
import view.LevelVisuals;
import view.Spaceman;
import view.StorySlides;
//import view.levelController;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
//import java.util.Timer;
//import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class LevelController {

	private InterfaceController interfaceCtrl;
	private LevelVisuals currentView;
	private StorySlides scenarioDisp;
	private Level levelModel;
	
	public Timeline timeline;
	public int startTimer = 3;
	public int timeElapsed = 0;
	
	public boolean paused = false;
	//private int pauseMenuOption = 0;
	
	
	public ArrayList<LevelVisuals> levelList;
	public int levelListIndex =0;
	public ArrayList<Level> modelList;
	//private boolean exitScreenOn = false;
	public int exitOption = 0;
	
	private int currentMode;
	public boolean ghostPlayerRed = false;
	public boolean ghostPlayerPink = false;
	
	public int levelWins = 0;
	
	private int powerUpTimeOut;
	


	public LevelController(InterfaceController controller) {
		interfaceCtrl = controller;
		//new
		levelList = new ArrayList<LevelVisuals>();
		LevelVisuals primaryLevel = new LevelVisuals(this);
		levelList.add(primaryLevel);
		currentView = primaryLevel;
		
		//
//		currentView = new LevelVisuals(this); //old
		scenarioDisp = new StorySlides(this);
		
		modelList = new ArrayList<Level>();
		Level primaryModel = new Level();
//		levelModel = new Level();
		modelList.add(primaryModel);
		levelModel = primaryModel;
		
		timeline = makeTimeline();
		
		
		
		//maybe make this a function
//		currentView.returnScene().setOnKeyPressed(new EventHandler <KeyEvent> () {
//			public void handle(KeyEvent input) {
//				
//				//temp, trying to get cycle sound to work consistently
//				currentView.stopCycleClip();
//				
//				if (input.getCode() == KeyCode.LEFT) {
//					//When in exit screen, controls option selection instead
//					if (exitScreenOn) {
//						if (exitOption > 0) {
//							exitOption--;
//							currentView.playCycleSound();
//						}
//						currentView.cycleOptions(exitOption);
//							
//					} else {
//						currentView.spaceman.setKeyInput(0);
//						
//					}
//					
//				} else if(input.getCode() == KeyCode.RIGHT) {
//					//When in exit screen controls option selection instead
//					if (exitScreenOn) {
//						if (exitOption < 1) {
//							exitOption++;
//							currentView.playCycleSound();
//						}
//						currentView.cycleOptions(exitOption);
//						
//					} else {
//						currentView.spaceman.setKeyInput(2);
//						
//					}
//					
//				} else if(input.getCode() == KeyCode.UP) {
//					currentView.spaceman.setKeyInput(1);
//					
//				} else if(input.getCode() == KeyCode.DOWN) {
//					currentView.spaceman.setKeyInput(3);
//					
//				} else if(input.getCode() == KeyCode.PAGE_DOWN) {
//					
//					//Sets time to 0 and stops the game when not in endless mode
//					if (currentMode != 3) {
//						timeElapsed = levelModel.getTimeLimit();
//						currentView.updateTime(levelModel.getTimeLimit() - timeElapsed);
//						currentView.spaceman.stop();
//						
//						//added with createghostPlayer
//						currentView.red.stop();
//						currentView.pink.stop();
//
//						currentView.blue.stop();
//						currentView.orange.stop();
//						//disp gameover screen?
//					}
//					
//				} else if(input.getCode() == KeyCode.ENTER) {
//					currentView.playCycleSound();
//					//When in exit screen, executes selected options instead
//					if (exitScreenOn) {		
//						//Quits the game if yes is selected, otherwise will go back to pause menu
//						if (exitOption == 1){
//
//							currentView.spaceman.stop();
//
//							currentView.red.stop();
//							currentView.pink.stop();
//							currentView.blue.stop();
//							currentView.orange.stop();
//							timeline.stop();
//							
//							//Resets initial level states //consider an init() func instead
//							startTimer = 3;
//							exitOption = 0;
//							timeElapsed = 0;
//							ghostPlayerRed = false;
//							ghostPlayerPink = false;
//							currentView.resetCountdown();
//							controller.showHome();
//							paused = !paused;
//						}
//						exitScreenOn = !exitScreenOn;
//						currentView.updateExitScreen(exitScreenOn);
//					} else {
//						timeline.play();
//					}
//				} else if(input.getCode() == KeyCode.P) {
//					currentView.playCycleSound();
//					
//					//Toggles pause screen when not in the exit screen
//					if (!exitScreenOn) {
//						paused = !paused;
//						currentView.controlPause();//add currentview.
//					}
//					
//				} else if (input.getCode() == KeyCode.ESCAPE) {
//					//Turns on/off exit screen and pauses if not already
//					if (!exitScreenOn) {
//						paused = true;
//						currentView.controlPause(); //added currentView.
//
//					}
//					currentView.playCycleSound();
//					exitScreenOn = !exitScreenOn;
//					currentView.updateExitScreen(exitScreenOn);
//				}
//			}
//		});
	}
	
	//consider seperating timelines for time and countdown
	private Timeline makeTimeline() {
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (timeElapsed == powerUpTimeOut & timeElapsed != 0) {
					currentView.red.start();
					currentView.pink.start();
					currentView.blue.start();
					currentView.orange.start();
				}
				
				if (levelModel.getTimeLimit() != timeElapsed) {
					//Play initial CountDown sound
					if (startTimer == 3) { //seems to be synchronised with the countdown here
						currentView.playCountdown();
					}
					
					//While still in CountDown State
					if (startTimer >= -1) {
						currentView.updateMessage(startTimer);
						if ((startTimer == -1)) {
							currentView.spaceman.start();
							//player or ai ones
							currentView.red.start();
							currentView.pink.start();
							currentView.blue.start();
							currentView.orange.start();

						}
						startTimer--;
					
					//In game timer
					} else if (levelModel.getTimeLimit() != timeElapsed) {
						currentView.spaceman.start();
						timeElapsed++;
						currentView.updateTime(levelModel.getTimeLimit());
						currentView.updateLives(levelModel.lives);
//						respawnCollectables();
						
					}
				
				//When time runs out
				} else {
					currentView.updateMessage(-1);
					timeline.stop();
					currentView.pauseCountdown();
					//prob stop sounds here
					//gameover screen
				}
			}

		});
		timeline.getKeyFrames().add(keyFrame);

		return timeline;
	}
	
	public Level getLevel() {
		return levelModel;
	}
	public void setLevel(int type){
		//levelModel.makeMaps();
		//currentMode = type;
		//levelModel.setMap(type);
		levelModel.initLevel(type, levelWins);
		currentView.generateMap();
		if (currentMode == 3) {
			currentView.updateTime(-1);
		}
		
		if ((type == 4) & (levelList.size() == 1)) {
			Random rand = new Random();
			
			LevelVisuals secondMap = new LevelVisuals(this);
//			levelModel.initLevel(4, (rand.nextInt(4)+0));
			//currentView = secondMap;
//			levelModel.initLevel(2, 0);
			Level secondModel = new Level();
			secondModel.initLevel(2, 0);
			levelModel = secondModel;
			modelList.add(secondModel);
			secondMap.generateMap();
			System.out.println("asas");
			levelList.add(secondMap);
			
			LevelVisuals thirdMap = new LevelVisuals(this);
//			levelModel.initLevel(4, (rand.nextInt(4)+0));
			//currentView = thirdMap;
//			levelModel.initLevel(3, 0);
			Level thirdModel = new Level();
			thirdModel.initLevel(3, 0);
			levelModel = thirdModel;
			modelList.add(thirdModel);
			thirdMap.generateMap();
			System.out.println("asas");
			levelList.add(thirdMap);
			
			
			currentView = levelList.get(levelListIndex);
		}
		interfaceCtrl.getMainApp().changeScene(currentView.returnScene()); // possible dont call getmainAPp()
	}																		//create method in intCtrller to change scenes
	
	public int checkMap(int x, int y) {
		return levelModel.getCurrentMap().getData(y, x);
	}
	
	public void updateMap(int dx, int dy,int posX, int posY) {
		int checkedTile = levelModel.getCurrentMap().getData(posY+dy, posX+dx);
		if (checkedTile == 2) {
			if (currentView.hideCorrespondingPellet(posX+dx, posY + dy)) {
			
				levelModel.addPoints(10);
				currentView.updateScore(levelModel.getScore());
				//levelModel.getCurrentMap().updateData(dx, dy, posX, posY);
			}
			
		} else if (checkedTile == 10 || checkedTile == 11 || checkedTile ==12 || checkedTile ==13|| checkedTile ==14) {
			//do power up stuff
			if (currentView.hideCorrespondingPowerUp(posX+dx, posY + dy)) {
				if (checkedTile == 10) {
					currentView.playCycleSound();//temp change to another sound
					currentView.red.changeToFrightMode();
					currentView.pink.changeToFrightMode();
					currentView.blue.changeToFrightMode();
					currentView.orange.changeToFrightMode();
					levelModel.addPoints(50);
					currentView.updateScore(levelModel.getScore());
					levelModel.getCurrentMap().updateData(dx, dy, posX, posY);//this doesnt do anyhting btw
			
				} else if (checkedTile == 11) {
					levelModel.addLives(1);
					currentView.playCycleSound(); //temp change to somehting else
					currentView.updateLives(levelModel.lives);
				} else if (checkedTile == 12) {
					currentView.playCycleSound();
					levelModel.addPoints(500);
					currentView.updateScore(levelModel.getScore());
				} else if (checkedTile == 13) {
					currentView.playCycleSound();
					//levelModel.addPoints(500);
					//currentView.updateScore(levelModel.getScore());
					currentView.spaceman.updateShieldStatus();
				}  else if (checkedTile == 14) {
					currentView.playCycleSound();
					//levelModel.addPoints(500);
					//currentView.updateScore(levelModel.getScore());
//					currentView.spaceman.updateShieldStatus();
					currentView.red.stop();
					currentView.pink.stop();
					currentView.blue.stop();
					currentView.orange.stop();
					powerUpTimeOut = timeElapsed + 5;
					
					
				}
			}
		}
		//levelModel.getCurrentMap().updateData(dx, dy, posX, posY);  no need to change map array
		//this function is messing up the tunnel because its removing tele
		//but if using updateData function then must be in the if statements
	}
	
	public int getTimeElapsed() {
		return timeElapsed;
	}
	
	public int getCountdown() {
		return startTimer;
	}
	
	public int getMode() {
		return currentMode;
	}
	
	public int getTimeLimit() {
		return levelModel.getTimeLimit();
	}
			
	public void respawnCollectables() {
		currentView.respawnPellet();
	}
	
//	public void addGhost(int ghostType) {
//		if (ghostType == 0) {
//			currentView.createGhostPlayer(0);
//		} else if (ghostType == 1) {
//			currentView.createGhostPlayer(1);
//		}  else if (ghostType == 2) {
//			currentView.createGhostPlayer(2);
//		}
//	}
//	private void controlPause() {
//		//paused = !paused;
//		//exitOption = 0;
//		
//		
//		if (paused) {
//			//Pauses the game
//			currentView.pauseCountdown();
//			timeline.pause();
//			currentView.spaceman.pause();
//			currentView.blue.pause();
//			
//			//added with createghostPlayer
//			currentView.red.pause();
//			currentView.pink.pause();
//		
//		} else {
//			//Resumes the level is counted was started
//			if (startTimer != 3) {
//				timeline.play();
//
//				//Resumes Countdown Sound if interrupted
//				if (startTimer>= 0) {
//					currentView.playCountdown();
//				}
//
//				//maybe make a bool var isCountdown isntead for clarity
//				//Spaceman starts moving when not in Countdown Stage and there is time remaining
//				if (levelModel.getTimeLimit()!=timeElapsed & startTimer<= -2) { 
//					currentView.spaceman.start();
//					currentView.blue.start();
//
//					//added with createghostPlayer
//					currentView.red.start();
//					currentView.pink.start();
//				}
//			}
//		}
//		
//		currentView.updatePauseScreen(paused);
//	}
	
	public void resetToStartState() {
		startTimer = 3;
		exitOption = 0;
		timeElapsed = 0;
//		levelWins = 0;
		ghostPlayerRed = false;
		ghostPlayerPink = false;
		currentView.resetCountdown();
		interfaceCtrl.showHome();
		paused = false;
	}


	private boolean ifSpacemanMetAlien (Alien alien) {
		int alienX = alien.getX();
		int spacemanX = currentView.spaceman.getX();
		int alienY = alien.getY();
		int spacemanY = currentView.spaceman.getY();
		
		if (alienX == spacemanX && alienY == spacemanY) {
			return true;
		} else {
			return false;
		}
	}
	
	private void consumeAlien(Alien alien) {
		levelModel.addPoints(200);
		alien.stop();
		alien.resetAlien();
		alien.start();
	}
	
	public void checkSpacemanAndAliens () {
		Alien[] aliens = currentView.aliens;
		for (Alien i : aliens) {
			if (ifSpacemanMetAlien(i)) {
				if (i.frightenedFlag) {
					consumeAlien(i);
				} else if (currentView.spaceman.shieldStatus) {
					currentView.spaceman.updateShieldStatus();
					consumeAlien(i);
				} else {
					currentView.playDeathSound();
					levelModel.minusLives(1);
					currentView.updateLives(levelModel.lives);
					if (levelModel.lives > 0) {
						currentView.stopAllChars();
						currentView.playCycleSound();
						timeline.pause();
						currentView.spaceman.resetSpaceman();
						currentView.red.resetAlien();
						currentView.pink.resetAlien();
						currentView.blue.resetAlien();
						currentView.orange.resetAlien();
//						currentView.generateMap();
						//currentView.countDownView = currentView.addCountDown();
						//currentView.root.getChildren().add(currentView.countDownView);
						
						startTimer = 3;
						currentView.resetCountdown();
						currentView.countDownView.setVisible(true);
					}
					else if (levelModel.lives == 0) {
						currentView.playGameOver();
						currentView.stopAllChars();
						timeline.pause();
						currentView.spaceman.resetSpaceman();
						currentView.red.resetAlien();
						currentView.pink.resetAlien();
						currentView.blue.resetAlien();
						currentView.orange.resetAlien();
						resetToStartState();
						levelWins =  0;
						setLevel(getMode());
						currentView.gameOverPopUp.setVisible(true);

					}
				}
			}
		}
	}
		
	public void playStory(int levelWins) {
		// TODO Auto-generated method stub
		//currentMode = mode;
		//scenarioDisp.setScenario(levelWins);
		scenarioDisp.generateScenario(levelWins);
		interfaceCtrl.changeScene(scenarioDisp.returnScene());
	}

	public void setMode(int mode) {
		// TODO Auto-generated method stub
		currentMode = mode;
	}
	
	public void setBgView(Image image) {
		currentView.setBg(image);
	}
	
	public LevelVisuals getCurrentView() {
		return currentView;
	}
	

	public void changeMap(int direction) {
		// TODO Auto-generated method stub
		if (direction < 0) {
			if (levelListIndex == 0) {
				levelListIndex = 2;
			} else {
				levelListIndex--;
			}
//			currentView.spaceman.setKeyInput(0);
			//currentView.spaceman.mo
			
		} else if (direction > 0) {
			if (levelListIndex == 2) {
				levelListIndex = 0;
			} else {
				levelListIndex++;
			}
//			currentView.spaceman.setKeyInput(2);

		}
		currentView.spaceman.setKeyInput(direction); //only way it works for some reason
														//works unless you enter tunnel without pressing anything
														//which will cause it to move up
		
		int prevLives = levelModel.getLives();
		int prevScore = levelModel.getScore();
		boolean prevShieldStat = currentView.spaceman.shieldStatus;
		
		//cbf tbh
		//boolean prevFrightModeRed = currentView.red.frightenedFlag;
		//int prevFMTime = currentView.red.
		
		currentView = levelList.get(levelListIndex);
		levelModel = modelList.get(levelListIndex);
		
		levelModel.setScore(prevScore);
		currentView.updateScore(prevScore);
		
		levelModel.setLives(prevLives);
		currentView.updateLives(prevLives);
		
		currentView.spaceman.shieldStatus = prevShieldStat;
		
		interfaceCtrl.changeScene(currentView.returnScene());
		//System.out.println("asas");
		currentView.startAllChars(); //comment while testing
		currentView.spaceman.setNewPosition(direction);
//		currentView.spaceman.start(); //uncomment for testing
		
	}
}
