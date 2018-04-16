package model;

public class Map {
	private int mapArray[][];
	/*NOTE: map looks like 		col1 | col2
							row1| X		X
							row2| X		X
	 */

	private int GROUND 	= 0;
	private int WALL 	= 1;
	private int PELLET 	= 2;
	private int POWERUP 	= 3;
	private int TELE = 5;
	private int SPACEMAN_SPAWN = 7;//maybe add ghost spawn aswell
	private int ALIEN_SPAWN = 8;
	private int GATE = 9;


	public void initMap(int type) {
		if (type == 1) {
			//mapArray = new int [21][21];
			//					  0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20
			int temp[][] =  { 	{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },		//index:0
								{ 1, 1, 3, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 3, 1, 1 },		//		1
								{ 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1 },		//		2
								{ 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1 },		//		3
								{ 1, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 1 },		//		4
								{ 1, 1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 1, 1 },		//		5
								{ 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1 },		//		6
								{ 1, 1, 1, 1, 1, 2, 1, 2, 2, 2, 0, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1 },		//		7
								{ 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 9, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1 },		//		8
								{ 5, 0, 0, 0, 0, 2, 2, 2, 1, 0, 8, 0, 1, 2, 2, 2, 0, 0, 0, 0, 5 },		//		9
								{ 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1 },		//		10
								{ 1, 1, 1, 1, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 1, 1, 1, 1 },		//		11
								{ 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1 },		//		12
								{ 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1 },		//		13
								{ 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1 },		//		14
								{ 1, 1, 2, 2, 1, 2, 2, 2, 2, 2, 7, 2, 2, 2, 2, 2, 1, 2, 2, 1, 1 },		//		15
								{ 1, 1, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 1, 1 },		//		16
								{ 1, 1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 1, 1 },		//		17
								{ 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1 },		//		18
								{ 1, 1, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 1, 1 },		//		19
								{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }	};  //		20
			mapArray = temp;
		} else if (type == 2) {
			int temp[][] = {	{ 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0 },
								{ 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0 },
								{ 0, 1, 2, 2, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 2, 2, 1, 0 },
								{ 1, 2, 2, 1, 1, 1, 1, 2, 2, 2, 7, 2, 2, 2, 1, 1, 1, 1, 2, 2, 1 },
								{ 1, 2, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 2, 1 },
								{ 1, 2, 1, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 1, 2, 1 },
								{ 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1 },
								{ 1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 1, 1, 2, 2, 1, 2, 2, 1, 1, 2, 1 },
								{ 1, 2, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 2, 1 },
								{ 1, 1, 2, 1, 1, 1, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 1, 1, 2, 1, 1 },
								{ 5, 0, 2, 2, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2, 0, 5 },
								{ 1, 1, 1, 1, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 1, 1, 1, 1 },
								{ 1, 2, 2, 2, 2, 2, 2, 2, 1, 1, 9, 1, 1, 2, 2, 2, 2, 2, 2, 2, 1 },
								{ 1, 2, 1, 2, 1, 1, 1, 2, 1, 0, 8, 0, 1, 2, 1, 1, 1, 2, 1, 2, 1 },
								{ 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1 },
								{ 1, 2, 1, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 1, 1, 2, 2, 1, 2, 1 },
								{ 1, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 1, 1, 2, 2, 2, 2, 1, 1, 2, 1 },
								{ 1, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 1 },
								{ 0, 1, 2, 2, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 2, 2, 1, 0 },
								{ 0, 0, 1, 2, 2, 2, 2, 2, 2, 1, 0, 1, 2, 2, 2, 2, 2, 2, 1, 0, 0 },
								{ 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0 },	};
								//{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			mapArray = temp;
		} else if (type == 3) {
			int temp[][] =  { 	{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },		//index:0
								{ 1, 1, 3, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 3, 1, 1 },		//		1
								{ 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1 },		//		2
								{ 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1 },		//		3
								{ 1, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 1 },		//		4
								{ 1, 1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 1, 1 },		//		5
								{ 1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1 },		//		6
								{ 1, 0, 0, 0, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 0, 0, 0, 1 },		//		7
								{ 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 9, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1 },		//		8
								{ 5, 0, 0, 0, 0, 2, 2, 2, 1, 0, 8, 0, 1, 2, 2, 2, 0, 0, 0, 0, 5 },		//		9
								{ 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1 },		//		10
								{ 1, 0, 0, 0, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 0, 0, 0, 1 },		//		11
								{ 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1 },		//		12
								{ 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1 },		//		13
								{ 1, 1, 2, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 2, 1, 1 },		//		14
								{ 1, 1, 2, 2, 1, 2, 2, 2, 2, 2, 7, 2, 2, 2, 2, 2, 1, 2, 2, 1, 1 },		//		15
								{ 1, 1, 1, 2, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 2, 1, 1, 1 },		//		16
								{ 1, 1, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 1, 1 },		//		17
								{ 1, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 2, 1, 1 },		//		18
								{ 1, 1, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 1, 1 },		//		19
								{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }	};  //		20
				mapArray = temp;
		} else if (type == 4) {
			int temp[][] =  { 	{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },		//index:0
					{ 1, 1, 1, 1, 1, 1, 1, 2, 2, 7, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },		//		1
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },		//		2
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },		//		3
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },		//		4
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },	//		5
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },	//		6
					{ 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 },		//		7
					{ 1, 1, 1, 1, 1, 1, 0, 0, 1, 9, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },	//		8
					{ 1, 1, 1, 1, 1, 1, 0, 1, 0, 8, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 },		//		9
					{ 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1 },	//		10
					{ 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1 },		//		11
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },	//		12
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },	//		13
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },		//		14
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },	//		15
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },		//		16
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },		//		17
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },		//		18
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },		//		19
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },	};  //		20
			mapArray = temp;
		} else if (type == 5) {
			int temp[][] =  { 	{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },		//index:0
					{ 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 },		//		1
					{ 0, 0, 0, 0, 0, 0, 1, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0, 0, 0 },		//		2
					{ 0, 0, 0, 0, 0, 1, 2, 2, 1, 2, 1, 2, 1, 2, 2, 1, 0, 0, 0, 0, 0 },		//		3
					{ 0, 0, 0, 0, 1, 2, 2, 1, 2, 2, 3, 2, 2, 1, 2, 2, 1, 0, 0, 0, 0 },		//		4
					{ 0, 0, 0, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 0, 0, 0 },	//		5
					{ 0, 0, 1, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 2, 1, 2, 2, 1, 0, 0 },	//		6
					{ 0, 0, 1, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 1, 2, 2, 1, 2, 1, 0, 0 },		//		7
					{ 0, 0, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 0, 0 },	//		8
					{ 1, 1, 1, 7, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1 },		//		9
					{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 },	//		10
					{ 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 0, 0, 0 },		//		11
					{ 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },	//		12
					{ 1, 1, 1, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 1, 1, 1 },	//		13
					{ 0, 0, 1, 2, 1, 1, 2, 1, 1, 1, 9, 1, 1, 1, 2, 1, 1, 2, 1, 0, 0 },		//		14
					{ 0, 0, 1, 2, 1, 1, 2, 1, 1, 0, 8, 0, 1, 1, 2, 1, 1, 2, 1, 0, 0 },	//		15
					{ 0, 0, 1, 2, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 1, 0, 0 },		//		16
					{ 0, 0, 0, 1, 2, 1, 3, 2, 2, 2, 2, 2, 2, 2, 3, 1, 2, 1, 0, 0, 0 },		//		17
					{ 0, 0, 0, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 0, 0, 0 },		//		18
					{ 0, 0, 0, 0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 1, 1, 0, 0, 0, 0 },		//		19
					{ 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 },	};  //		20
			mapArray = temp;
		}  else if (type == 6) {
			int temp[][] =  {{0, 1, 3, 1, 0, 3, 0, 0, 0, 0, 1, 0, 0, 0, 0, 3, 0, 1, 3, 1, 0},
					{0, 3, 1, 3, 0, 0, 3, 0, 0, 1, 3, 1, 0, 0, 3, 0, 0, 3, 1, 3, 0},
					{0, 1, 3, 1, 0, 0, 0, 3, 1, 2, 2, 2, 1, 3, 0, 0, 0, 1, 3, 1, 0},
					{3, 0, 0, 0, 0, 0, 0, 1, 2, 2, 1, 2, 2, 1, 0, 0, 0, 0, 0, 0, 3},
					{0, 3, 0, 0, 1, 0, 1, 2, 2, 1, 1, 1, 2, 2, 1, 0, 1, 0, 0, 3, 0},
					{0, 0, 3, 0, 1, 1, 2, 2, 1, 1, 1, 1, 1, 2, 2, 1, 1, 0, 3, 0, 0},
					{0, 0, 0, 3, 1, 2, 2, 1, 2, 2, 3, 2, 2, 1, 2, 2, 1, 3, 0, 0, 0},
					{0, 0, 0, 1, 2, 2, 1, 2, 2, 1, 1, 1, 2, 2, 1, 2, 2, 1, 0, 0, 0},
					{0, 0, 0, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 0, 0, 0},
					{0, 0, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1, 0, 0},
					{0, 0, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 0, 0},
					{0, 1, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 1, 0},
					{0, 1, 2, 1, 1, 2, 1, 2, 1, 1, 9, 1, 1, 2, 1, 2, 1, 1, 2, 1, 0},
					{0, 1, 3, 2, 2, 2, 1, 2, 1, 0, 8, 0, 1, 2, 1, 2, 2, 2, 3, 1, 0},
					{0, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 0},
					{0, 1, 2, 1, 1, 2, 2, 2, 2, 2, 7, 2, 2, 2, 2, 2, 1, 1, 2, 1, 0},
					{0, 1, 2, 1, 2, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 2, 1, 2, 1, 0},
					{0, 1, 2, 2, 2, 1, 1, 0, 1, 2, 1, 2, 1, 0, 1, 1, 2, 2, 2, 1, 0},
					{0, 1, 1, 1, 1, 1, 1, 0, 1, 2, 1, 2, 1, 0, 1, 1, 1, 1, 1, 1, 0},
					{0, 1, 1, 1, 0, 0, 1, 0, 1, 2, 1, 2, 1, 0, 1, 0, 0, 1, 1, 1, 0},
					{0, 1, 1, 0, 0, 0, 1, 0, 1, 2, 3, 2, 1, 0, 1, 0, 0, 0, 1, 1, 0},
					{0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0},
			};  //		20},
			mapArray = temp;
		} 
	}

	public int getData(int row, int col) {
		return mapArray[row][col];
	}

	public int getStartX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getStartY() {
		// TODO Auto-generated method stub
		return 0;
	}
	////note Y determines the rows(up and down) //X determines the col (left and right)
	public void updateData(int dx, int dy, int posX, int posY) { 
		//int temp = mapArray[posY+dy][posX+dx]; //if we want non linear tele then uncomment these code
		//mapArray[posY+dy][posX+dx] = getData(posY, posX); //dont need to update player

		//mapArray[posY+dy][posX+dx] = GROUND;	//removed 13/4/18

		//if (temp == TELE) {
		//	mapArray[posY][posX] = TELE;
		//} else {
		//mapArray[posY][posX] = GROUND;
		//}
	}
}
