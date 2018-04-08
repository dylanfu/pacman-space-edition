package view;

import controller.LevelController;
import javafx.animation.Animation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Spaceman extends CharacterAnimate{

	public LevelController levelController;
	private static final int[] ROTATION_DEGREE = new int[] {0, 90, 180, 270};
	private int currentDirection;
	public ImageView imageView;
	private double graphicalX;
	private double graphicalY;
	private int keyInput;
	private int currentRotation;

	public Spaceman(LevelController levelController, int x, int y) {
		keyInput = -1;
		
		this.levelController = levelController;

		// Intialise Spaceman grid position
		this.x = x;
		this.y = y;
		graphicalX = x*TILE_WIDTH + GRAPHICAL_X_OFFSET;
		graphicalY = y*TILE_HEIGHT + GRAPHICAL_Y_OFFSET;

		// Intialise Spaceman moving left
		dx = -1;
		dy = 0;

		Image startImage = new Image(getClass().getResourceAsStream("res/left1.png")); 
		images = new Image[] {
				startImage,
				new Image(getClass().getResourceAsStream("res/left2.png")),
				startImage,
				new Image(getClass().getResourceAsStream("res/round.png"))
		};
		imageIndex = 0;
		currentImage = images[imageIndex];
		currentDirection = MOVE_LEFT;
		currentRotation = ROTATION_DEGREE[currentDirection];

		imageView = new ImageView(startImage);
		imageView.setX(graphicalX);
		imageView.setY(graphicalY);
		imageView.setImage(images[imageIndex]);
		imageView.setRotate(currentRotation);

		getChildren().add(imageView);//QUENTIN: causing issues when I try put spaceman in level Visuals
		//maybe extend off level visual instead of parent in char animate class? 

		// remove later when movement logic is completed
		status = MOVING;

	}

	@Override
	public void moveOneStep() {

		if (imageIndex == 0) {
			changeCurrentDirection(keyInput);
		}
		
		if (imageIndex < images.length-1) {
			imageIndex++;
			currentImage = images[imageIndex];
			imageView.setImage(currentImage);
//			imageView.setX(graphicalX);
//			imageView.setY(graphicalY);
//			imageView.setRotate(currentRotation);
			

		} else {
			imageIndex = 0;
			currentImage = images[imageIndex];
			imageView.setImage(currentImage);
//			imageView.setX(graphicalX);
//			imageView.setY(graphicalY);
//			imageView.setRotate(currentRotation);
			
		}
		
		if (status == MOVING) {

			if (dx != 0 && dy == 0) {
				moveXAxis();
			} 
			if (dx == 0 && dy != 0) {
				moveYAxis();
			}
			
			imageView.setX(graphicalX);
			imageView.setY(graphicalY);
			imageView.setRotate(currentRotation);
		}
	}

	private void moveXAxis() {
		//Wallcheck logic: If next destination is wall, do not move, else move as normal
		int nextX = x + dx;
		if (levelController.checkMap(nextX, y) == 1) {
			//pause animation here
			imageIndex--;
		} else {
			//start();
			moveCounter++;
			if (moveCounter < ANIMATION_STEP) {
				graphicalX = graphicalX + (dx * MOVE_SPEED);
			} else {
				moveCounter = 0;
				x = x + dx;
				graphicalX = x*TILE_WIDTH + GRAPHICAL_X_OFFSET;
				
				//Not sure the point of this code tbh
//				//int nextX = x + dx;
//				if (levelController.checkMap(nextX, y) == 1) {
//					status = STOPPED;
//				}
//				// HARDCODED VALUES FOR TUNNEL X COORDINATE - CHANGE LATER
//				if (nextX < 2) {
//					x = 20;
//				} else if (nextX > 18) {
//					x = 0;
//				}
			}
		}
		//w/o quentin changes
//		moveCounter++;
//		if (moveCounter < ANIMATION_STEP) {
//			graphicalX = graphicalX + (dx * MOVE_SPEED);
//		} else {
//			moveCounter = 0;
//			x = x + dx;
//			graphicalX = x*TILE_WIDTH + GRAPHICAL_X_OFFSET;
//			
//			int nextX = x + dx;
//			if (levelController.checkMap(nextX, y) == 1) {
//				status = STOPPED;
//			}
//			// HARDCODED VALUES FOR TUNNEL X COORDINATE - CHANGE LATER
//			if (nextX < 2) {
//				x = 20;
//			} else if (nextX > 18) {
//				x = 0;
//			}
//		}
	}

	private void moveYAxis() {
		moveCounter++;
		if (moveCounter < ANIMATION_STEP) {
			graphicalY = graphicalY + (dy * MOVE_SPEED);
		} else {
			moveCounter = 0;
			y = y + dy;
			graphicalY = y*TILE_HEIGHT + GRAPHICAL_Y_OFFSET;
			
			int nextY = y + dy;
			if (levelController.checkMap(x,nextY) == 1) {
				status = STOPPED;
			}
		}
	}

	private void moveLeft() {
		// Prevent invalid direction changes
		int nextX = x - 1;
		if (levelController.checkMap(nextX, y) == 1) {
			return;
		}
		
		// Changes direction
		dx = -1;
		dy = 0;
		
		currentDirection = MOVE_LEFT;
		currentRotation = ROTATION_DEGREE[currentDirection];
		status = MOVING;
	}

	private void moveRight() {
		int nextX = x + 1;
		if (levelController.checkMap(nextX, y) == 1) {
			return;
		}

		dx = 1;
		dy = 0;
		currentDirection = MOVE_RIGHT;
		currentRotation = ROTATION_DEGREE[currentDirection];
		status = MOVING;
	}

	private void moveUp() {
		int nextY = y - 1;
		if (levelController.checkMap(x,nextY) == 1) {
			return;
		}
		
		dx = 0;
		dy = -1;
		currentDirection = MOVE_UP;
		currentRotation = ROTATION_DEGREE[currentDirection];
		status = MOVING;
	}

	private void moveDown() {
		int nextY = y + 1;
		if (levelController.checkMap(x,nextY) == 1) {
			return;
		}
		
		dx = 0;
		dy = 1;
		currentDirection = MOVE_DOWN;
		currentRotation = ROTATION_DEGREE[currentDirection];
		status = MOVING;
	}

	public void setKeyInput(int keyInput) {
		this.keyInput  = keyInput;
	}

	private void changeCurrentDirection(int keyInput) {
		if (keyInput == MOVE_LEFT) {
			moveLeft();
		} else if (keyInput == MOVE_RIGHT) {
			moveRight();
		} else if (keyInput == MOVE_UP) {
			moveUp();
		} else if (keyInput == MOVE_DOWN) {
			moveDown();
		}
	}

}
