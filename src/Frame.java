/*
 * Trevor Ryles P3 Java 6/8/18
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
public class Frame extends JFrame{
	public static void main(String[] args){
		new Frame();
	}
	//TODO
                        	private static final long serialVersionUID = 255695738819603772L;
	private JMenu menu;
	private JMenuItem exit;
	private JMenuBar bar;
	private ListenForButton lForButton;
	private JButton[][] arr, arr2;
	private JButton again, oneShow, twoShow;
	private JTextArea midLabel, one, two;
	private JLabel label1a, label1b, label2a,label2b;
	private JTextField textField1a, textField1b,textField2a,textField2b; 
	private JPanel rightGrid, leftGrid, middle;
	private JCheckBox hBoxR, hBoxL, vBoxL, vBoxR;
	private JButton oneButt, twoButt;
	private BattleShip ship;
	private String lPos, rPos, x, y="";
	private boolean worksP1, worksP2;
	private boolean lPicked, rPicked, isLShow, isRShow = false;
	private int i,j = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int P1 = 4;
	private final int P2 = 5;
	private int turn = LEFT;
	public Frame(){		
		try {
			this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("bg.png")))));
		}catch(Exception e) {};
		this.setSize(new Dimension(1300,1000));		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		this.setTitle("Battleship");	
		this.setLayout(new FlowLayout());
		menu = new JMenu("Menu");
		exit= new JMenuItem("Exit");
		bar= new JMenuBar();
		menu.add(exit);
		bar.add(menu);
		setJMenuBar(bar);
		this.setJMenuBar(bar);
		MenuItemHandler menuItemHandler = new MenuItemHandler();
		exit.addActionListener(menuItemHandler);
		leftGrid = new JPanel();
		leftGrid.setPreferredSize(new Dimension(610,690));
		leftGrid.setBackground(new Color(72, 146, 194));
//		JLabel label = new JLabel(new ImageIcon("bp.png"));
//		leftGrid.add(label);
		rightGrid = new JPanel();
		rightGrid.setPreferredSize(new Dimension(610,690));
		rightGrid.setBackground(new Color(72, 146, 194));
		middle = new JPanel();
		midLabel = new JTextArea("Please Enter Player 1's Ships");
		midLabel.setWrapStyleWord(true);
		midLabel.setLineWrap(true);
		midLabel.setPreferredSize(new Dimension(120,80));
		midLabel.setEditable(false);
		midLabel.setBackground(new Color(224,224,224));
		midLabel.setFont(new Font(midLabel.getFont().getName(), midLabel.getFont().getStyle(), 20));
		middle.add(midLabel);
		middle.setPreferredSize(new Dimension(120,140));
		middle.setBackground(new Color(224,224,224));
//		middle.setBackground(Color.BLUE);
		lForButton = new ListenForButton();
		arr = new JButton[10][10];
		again = new JButton("Again?");
		again.setPreferredSize(new Dimension(90,35));
		again.addActionListener(lForButton);
		middle.add(again);
		again.setVisible(false);
		for(int i=0;i<arr.length;i++) {//CREATES LEFT BUTTON GRID
			for(int j=0;j<arr[i].length;j++) {
				arr[i][j] = new JButton("");
				arr[i][j].setPreferredSize(new Dimension(53,40));
				arr[i][j].addActionListener(lForButton);
				arr[i][j].setHorizontalTextPosition(JButton.CENTER);
				arr[i][j].setVerticalTextPosition(JButton.CENTER);
				leftGrid.add(arr[i][j]);
				arr[i][j].setBackground(Color.CYAN);
				arr[i][j].setFont(new Font(arr[i][j].getFont().getName(), arr[i][j].getFont().getStyle(), 22));
				arr[i][j].setIcon(new ImageIcon("images.jpeg"));
			}
		}		
		label1a = new JLabel("Row 1");
		label1a.setPreferredSize(new Dimension(60,50));
		label1a.setFont(new Font(label1a.getFont().getName(), label1a.getFont().getStyle(), 15));
		textField1a = new JTextField("", 5);	
		textField1a.setPreferredSize(new Dimension(50,30));
		textField1a.setFont(new Font(textField1a.getFont().getName(), textField1a.getFont().getStyle(), 15));
		label1b = new JLabel("Column 1");	
		label1b.setPreferredSize(new Dimension(80,50));
		label1b.setFont(new Font(label1b.getFont().getName(), label1b.getFont().getStyle(), 15));
		vBoxL = new JCheckBox("Vertical");
		vBoxR = new JCheckBox("Vertical");
		hBoxL = new JCheckBox("Horizontal");
		hBoxR = new JCheckBox("Horizontal");
		hBoxL.setFont(new Font(hBoxL.getFont().getName(), hBoxL.getFont().getStyle(), 15));
		hBoxR.setFont(new Font(hBoxR.getFont().getName(), hBoxR.getFont().getStyle(), 15));
		vBoxR.setFont(new Font(vBoxR.getFont().getName(), vBoxR.getFont().getStyle(), 15));
		vBoxL.setFont(new Font(vBoxL.getFont().getName(), vBoxL.getFont().getStyle(), 15));
		vBoxR.setBackground(null);
		vBoxL.setBackground(null);
		hBoxR.setBackground(null);
		hBoxL.setBackground(null);
		vBoxL.addItemListener(new CheckBoxHandler());
		hBoxL.addItemListener(new CheckBoxHandler());
		vBoxR.addItemListener(new CheckBoxHandler());
		hBoxR.addItemListener(new CheckBoxHandler());
		textField1b = new JTextField("", 5);	
		textField1b.setPreferredSize(new Dimension(50,30));
		textField1b.setFont(new Font(textField1b.getFont().getName(), textField1b.getFont().getStyle(), 15));
		arr2 = new JButton[10][10];
		for(int i=0;i<arr.length;i++) {//CREATES RIGHT BUTTON GRID
			for(int j=0;j<arr[i].length;j++) {
				arr2[i][j] = new JButton("");
				arr2[i][j].setIcon(new ImageIcon("ocean.jpeg"));
				arr2[i][j].setHorizontalTextPosition(JButton.CENTER);
				arr2[i][j].setVerticalTextPosition(JButton.CENTER);
				arr2[i][j].setPreferredSize(new Dimension(53,40));
				arr2[i][j].addActionListener(lForButton);
				rightGrid.add(arr2[i][j]);
				arr2[i][j].setBackground(Color.CYAN);
				arr2[i][j].setFont(new Font(arr2[i][j].getFont().getName(), arr2[i][j].getFont().getStyle(), 22));
				arr2[i][j].setIcon(new ImageIcon("images.jpeg"));
			}
		}
		label2a= new JLabel("Row 1");
		label2a.setPreferredSize(new Dimension(60,50));
		label2a.setFont(new Font(label2a.getFont().getName(), label2a.getFont().getStyle(), 15));
		textField2a = new JTextField("", 5);
		textField2a.setPreferredSize(new Dimension(50,30));
		textField2a.setFont(new Font(textField2a.getFont().getName(), textField2a.getFont().getStyle(), 15));
		label2b = new JLabel("Column 1");
		label2b.setPreferredSize(new Dimension(80,50));
		label2b.setFont(new Font(label2b.getFont().getName(),label2b.getFont().getStyle(), 15));
		textField2b = new JTextField("", 5);
		textField2b.setPreferredSize(new Dimension(50,30));
		textField2b.setFont(new Font(textField2b.getFont().getName(), textField2b.getFont().getStyle(), 15));
		textField1a.requestFocus();
		textField1b.requestFocus();
		one = new JTextArea("");
		one.setWrapStyleWord(true);
		one.setLineWrap(true);
		one.setPreferredSize(new Dimension(300,170));
		one.setEditable(false);
		one.setFont(new Font(one.getFont().getName(), one.getFont().getStyle(), 20));
		one.setBackground(new Color(70, 92, 152));
		two = new JTextArea("");
		two.setWrapStyleWord(true);
		two.setLineWrap(true);
		two.setPreferredSize(new Dimension(300,170));
		two.setEditable(false);
		two.setFont(new Font(one.getFont().getName(), one.getFont().getStyle(), 20));
		two.setBackground(new Color(70, 92, 152));
		oneButt = new JButton("Enter");
		oneShow = new JButton("Show P1 Ships");
		oneShow.setVisible(false);
		twoButt = new JButton("Enter");
		twoShow = new JButton("Show P2 Ships");
		twoShow.setVisible(false);
		oneButt.addActionListener(new ListenForButton());
		twoButt.addActionListener(new ListenForButton());
		oneShow.addActionListener(new ListenForButton());
		twoShow.addActionListener(new ListenForButton());
		//Adds elements to left player screen
		leftGrid.add(label1a);	
		leftGrid.add(textField1a);
		leftGrid.add(label1b);	
		leftGrid.add(textField1b);
		leftGrid.add(vBoxL);
		leftGrid.add(hBoxL);
		leftGrid.add(one);
		leftGrid.add(oneButt);
		leftGrid.add(oneShow);
		//Adds elements to right player screen
		rightGrid.add(label2a);	
		rightGrid.add(textField2a);
		rightGrid.add(label2b);
		rightGrid.add(textField2b);
		rightGrid.add(vBoxR);
		rightGrid.add(hBoxR);
		rightGrid.add(two); 
		rightGrid.add(twoButt);
		rightGrid.add(twoShow);
		lPos="-1";
		rPos="-1";
		this.add(leftGrid, BorderLayout.EAST);
		this.add(middle, BorderLayout.SOUTH);
		this.add(rightGrid, BorderLayout.WEST);
		this.setVisible(true);
		this.pack();
		ship = new BattleShip();
//		ship.run();
	}
	public void start() {
		int count=1;
		for(int i=0;i<5;i++) {
			ship.addP1(i,count,i+1,0);
			count++;
		}
		count=1;
		for(int j=0;j<5;j++) {
			ship.addP2(j,count,j+1,0);
			count++;
		}
		vBoxL.setVisible(false);
        hBoxL.setVisible(false);
    	vBoxR.setVisible(false);
        hBoxR.setVisible(false);
        rPicked=true;
           	midLabel.setText("Player 1's turn");
        	label1a.setText("Row");
        	label1b.setText("Column");
        	label2a.setText("Row");
        	label2b.setText("Column");
        	turn=P1;
        	hideL();
        	hideR();
        	x="";
        	y="";
        	oneShow.setVisible(true);
        	twoShow.setVisible(true);
	}
	//hideL shows the score screen and hides the ships screen
	public void hideL() {
		one.setText("Player 1 Score: " + ship.getP1().getNumSunk() + "\n");
		one.setFont(new Font(one.getFont().getName(), one.getFont().getStyle(), 40));
	}
	//hideR shows the score screen and hides the ships screen
	public void hideR() {
		two.setText("Player 2 Score: " + ship.getP2().getNumSunk() + "\n");
		two.setFont(new Font(two.getFont().getName(), two.getFont().getStyle(), 40));
	}
	//showLShips shows the left ships screen and hides the score screen
	public void showLShips(){
		x="";
		one.setFont(new Font(one.getFont().getName(), one.getFont().getStyle(), 11));
       	x+="Player 1 Score: " + ship.getP2().getNumSunk() + "\n";
	    x+="   ";
			for (int l=1;l<=9;l++) {
				x+=" " + l + " ";
			}
			x+=" 10";
			x+="\n";
			char hold = 'A';
			Location[][] grid =ship.getP2().getGrid().getLocGrid();
			for (int i=0;i<grid.length;i++) {
				if(hold>='A' &&hold<='F') {
					x+=hold + "  ";
				}else if(hold>='I'&&hold<='J') {
					x+=hold + "  ";
				}else {
					x+=hold + " ";
				}
				hold++;
				for (int j=0;j<grid[i].length;j++) {
					if(grid[i][j].hasShip()){
					    x+="X  ";
					}else{
					    x+="--  ";
					}
				}
				x+="\n";
	}
			one.setText(x);
	}
	//showRShips shows the right ships screen and hides the score screen
	public void showRShips(){
		y="";
		two.setFont(new Font(two.getFont().getName(), two.getFont().getStyle(), 11));
       	y+="Player 2 Score: " + ship.getP1().getNumSunk() + "\n";
	    y+="   ";
			for (int l=1;l<=9;l++) {
				y+=" " + l + " ";
			}
			y+=" 10";
			y+="\n";
			char hold = 'A';
			Location[][] grid =ship.getP1().getGrid().getLocGrid();
			for (int i=0;i<grid.length;i++) {
				if(hold>='A' &&hold<='F') {
					y+=hold + "  ";
				}else if(hold>='I'&&hold<='J') {
					y+=hold + "   ";
				}else {
					y+=hold + " ";
				}
				hold++;
				for (int j=0;j<grid[i].length;j++) {
					if(grid[i][j].hasShip()){
					    y+="X  ";
					}else{
					    y+="--  ";
					}
				}
				y+="\n";
	}
			two.setText(y);
	}
	//Resets the buttons, ships, guesses, and score
	public void reset() {
		BattleShip ship2 = new BattleShip();
		lPos="-1";
		rPos="-1";
		ship=ship2;
		x="";
		y="";
		midLabel.setText("Please enter Player 1's Ships");
		again.setText("Again?");
		again.setVisible(false);
		label1a.setText("Row 1");
		textField1a.setText("");	
		label1b.setText("Column 1");	
		vBoxL.setText("Vertical");
		vBoxR.setText("Vertical");
		hBoxL.setText("Horizontal");
		hBoxR.setText("Horizontal");
		textField1b.setText("");	
		label2a.setText("Row 1");
		label2b.setText("Column 1");
		textField2b.setText("");
		oneButt.setText("Enter");
		twoButt.setText("Enter");
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				arr[i][j].setText("");
				arr[i][j].setBackground(Color.CYAN);
				arr[i][j].setIcon(new ImageIcon("images.jpeg"));
			}
			oneShow.setVisible(false);
			twoShow.setVisible(false);
		}
		for(int i=0;i<arr2.length;i++) {
			for(int j=0;j<arr2[i].length;j++) {
				arr2[i][j].setText("");
				arr2[i][j].setBackground(Color.CYAN);
				arr2[i][j].setIcon(new ImageIcon("images.jpeg"));
			}
		}
			turn=LEFT;
			vBoxR.setVisible(true);
			vBoxL.setVisible(true);
			hBoxL.setVisible(true);
			hBoxR.setVisible(true);
			one.setText("");
			two.setText("");
			i=0;
			j=0;
	}
	private class ListenForButton implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==oneButt){//left Enter button
//				start();
				if(turn==P1) {//Player 1's turn to guess
					if((!ship.getP1().allHit())&&(!ship.getP1().allHit())){//If no player has won the game yet
						try {
						//gets numbers from the left input boxes
						int row = Integer.parseInt(textField1a.getText());
						int col = Integer.parseInt(textField1b.getText());;
						//Player calls guess for hits on the grid
						ship.getP1().askForGuess(row-1,col-1);
			            textField1a.setText("");
			            textField1b.setText("");
			            }catch(Exception a) {};
					}
				}else if (turn==LEFT){//If it is player 1's turn to enter ships
				try{
					//Gets numbers from left input fields 
					int row = Integer.parseInt(textField1a.getText());
					int col = Integer.parseInt(textField1b.getText());
					if(i<5) {
				                int dir = -1;
				                if(lPos.equals("vertical")){
				                    dir=1;
				                    worksP1=true;
				                }else if(lPos.equals("horizontal")){
				                    dir=0;
				                    worksP1=true;
				                }else{
				                    worksP1=false;
				                }
				            try{
				                if(worksP1){
				                    boolean a = ship.addP2(i,row,col,dir);
				                   if(!a) {
				                	   throw new Exception();
				                   }
				                   i++;
				                    if(i<5) {
				                    label1a.setText("Row " + (i+1));
				                    label1b.setText("Column " + (i+1));
				                    one.setText("Location " + (row) + ", " + (col) + " Works");
				                    }else {
					                    lPicked=true;
				                    	hBoxL.setVisible(false);
					                    vBoxL.setVisible(false);
					                    if(lPicked&&!rPicked) {
					                    	midLabel.setText("Player 2: Please enter ships");
					                    	turn=RIGHT;
					                    	one.setText("");
					                    	oneShow.setVisible(true);
					                    }
				                    }
				                }
				               }catch(Exception b){
				                    worksP1=false;
				                    one.setFont(new Font(one.getFont().getName(), one.getFont().getStyle(), 20));
				                    one.setText("Location Doesn't Work");
				                }
					}
				}catch(Exception excep){
				}	
				textField1a.setText("");
				textField1b.setText("");
				vBoxL.setSelected(false);
				hBoxL.setSelected(false);
				}
			}else if(e.getSource()==twoButt){
					if(turn==P2) {
						if((!ship.getP2().allHit())&&(!ship.getP2().allHit())){
							try {
							int row = Integer.parseInt(textField2a.getText());
							int col = Integer.parseInt(textField2b.getText());
							ship.getP2().askForGuess(row-1,col-1);
				            textField2a.setText("");
				            textField2b.setText("");
				            }catch(Exception a) {};
						}
					}else if(turn==RIGHT){
				try{
					int row = Integer.parseInt(textField2a.getText());
					int col = Integer.parseInt(textField2b.getText());
					if(j<5) {
			                int dir = -1;
			                if(rPos.equals("vertical")){
			                    dir=1;
			                    worksP2=true;
			                }else if(rPos.equals("horizontal")){
			                    dir=0;
			                    worksP2=true;
			                }else{
			                    worksP2=false;
			                }
			            try{
			            	if(worksP2){
			                    boolean b = ship.addP1(j,row,col,dir);
			                    if(!b) {
				                	   throw new Exception();
				                   }
					                    j++;
			                    if(j<5) {
			                    label2a.setText("Row " + (j+1));
			                    label2b.setText("Column " + (j+1));
			                    two.setText("Location " + (row) + ", " + (col) + " Works");
			                    }else {
			                    	vBoxR.setVisible(false);
				                    hBoxR.setVisible(false);
				                    rPicked=true;
				                    if(lPicked&&rPicked) {
				                       	midLabel.setText("Player 1's turn");
				                    	label1a.setText("Row");
				                    	label1b.setText("Column");
				                    	label2a.setText("Row");
				                    	label2b.setText("Column");
				                    	turn=P1;
							            twoShow.setVisible(true);
					                    hideL();
					                    hideR();
//				                    }else if(lPicked&&!rPicked) {
//				                    	midLabel.setText("Player 2: Please enter ships");
//				                    	turn=RIGHT;
//				                    	hideR();
//				                    	oneShow.setVisible(true);
				                    }else{
				                    	midLabel.setText("Player 2: Please enter ships");
				                    	turn=RIGHT;
					                    two.setText("Location works");
				                    }
			                    }
			                }
			               }catch(Exception b){
			                    worksP2=false;
			                    two.setFont(new Font(two.getFont().getName(), two.getFont().getStyle(), 20));
			                    two.setText("Location Doesn't Work");
			                }
				}
				}catch(Exception excep){
				}
				textField2a.setText("");
				textField2b.setText("");
				vBoxR.setSelected(false);
				hBoxR.setSelected(false);
					}
			}else if(e.getSource()==again){
				reset();
			}else if(e.getSource()==oneShow){
				if(!isLShow) {
					showLShips();
					oneShow.setText("Hide P1 Ships");
					isLShow=true;
				}else {
					hideL();
					oneShow.setText("Show P1 Ships");
					isLShow=false;
				}
			}else if(e.getSource()==twoShow){
				if(!isRShow) {
					showRShips();
					twoShow.setText("Hide P2 Ships");
					isRShow=true;
				}else {
					hideR();
					twoShow.setText("Show P2 Ships");
					isRShow=false;
				}
			}else {

				if(turn==P1) {
					for(int i=0;i<arr.length;i++) {
						for(int j=0;j<arr[i].length;j++) {
							try {
								if(e.getSource()==arr[i][j]) {
									if((!ship.getP1().allHit())&&(!ship.getP2().allHit())){
										try {
										int row = i;
										int col = j;
										ship.getP1().askForGuess(row,col);
							            }catch(Exception a) {};
										if(ship.getP1().getGrid().hasShip(i,j)){
											if(ship.getP1().getGrid().get(i,j).getStatus()==Location.HIT||(ship.getP1().getGrid().get(i,j).getStatus()==Location.SUNK)){
									            arr[i][j].setText("X");
									            hideL();
									            arr[i][j].setBackground(Color.RED);
									            arr[i][j].setIcon(new ImageIcon("explosion.jpg"));
									        }else if((ship.getP1().getGrid().get(i,j).getStatus())==Location.SUNK) {
									            arr[i][j].setText("X");
									            hideL();
									            arr[i][j].setBackground(Color.RED);
									            arr[i][j].setIcon(new ImageIcon("explosion.jpg"));
									        }
										}else {
									        arr[i][j].setText("O");
									        arr[i][j].setBackground(new Color(0,150,255));
								            arr[i][j].setIcon(new ImageIcon("splash.jpg"));
									    }
								        if(ship.getP1().allHit()){
								            midLabel.setText("Player 1 wins!");
						                 	again.setVisible(true);
								        }else {
								          	turn=P2;
						                   	midLabel.setText("Player 2's turn");
								        }
							            textField1a.setText("");
							            textField1b.setText("");
									}
								}
							}catch(Exception b) {};
						}
					}
				}else if(turn==P2) {
					for(int i=0;i<arr2.length;i++) {
						for(int j=0;j<arr2[i].length;j++) {
							try {
								if(e.getSource()==arr2[i][j]) {
									if((!ship.getP1().allHit())&&(!ship.getP2().allHit())){
										try {
										int row = i;
										int col = j;
										ship.getP2().askForGuess(row,col);
					            }catch(Exception a) {};
										if(ship.getP2().getGrid().hasShip(i,j)){
											if((ship.getP2().getGrid().get(i,j).getStatus())==Location.HIT){
									            arr2[i][j].setText("X");
									            hideR();
									            arr2[i][j].setBackground(Color.RED);
									            arr2[i][j].setIcon(new ImageIcon("explosion.jpg"));
									        }else if((ship.getP2().getGrid().get(i,j).getStatus())==Location.SUNK) {
									            arr2[i][j].setText("X");
									            hideR();
									            arr2[i][j].setBackground(Color.RED);
									            arr2[i][j].setIcon(new ImageIcon("explosion.jpg"));
									        }
										}else {
								        	arr2[i][j].setText("O");
								            arr2[i][j].setBackground(Color.BLUE);
								            arr2[i][j].setIcon(new ImageIcon("splash.jpg"));
								        }
							            if(ship.getP2().allHit()){
							                midLabel.setText("Player 2 wins!");
					                    	again.setVisible(true);
							            }else {
							            	turn=P1;
					                    	midLabel.setText("Player 1's turn");
							            }
							            textField2a.setText("");
							            textField2b.setText("");
									}
								}
							}catch(Exception c) {};
						}	                    	
					}
				}
			}
		}
	}
    private class CheckBoxHandler implements ItemListener {
        public void itemStateChanged (ItemEvent event) {
        	
            if (event.getSource() == hBoxL) {
                if (event.getStateChange() == ItemEvent.SELECTED) {
                    lPos="horizontal";
                    vBoxL.setSelected(false);
                }
            }else if(event.getSource() == vBoxL) {
            	 if (event.getStateChange() == ItemEvent.SELECTED) {
                     lPos="vertical";
                     hBoxL.setSelected(false);
            	 }
            }else if(event.getSource() == vBoxR) {
            	 if (event.getStateChange() == ItemEvent.SELECTED) {
                     rPos="vertical";
                     hBoxR.setSelected(false);
            	 }
            }else if(event.getSource() == hBoxR) {
            	if (event.getStateChange() == ItemEvent.SELECTED) {
                 rPos="horizontal";
                 vBoxR.setSelected(false);
            	}
            }   
        }
    }
	 private class MenuItemHandler implements ActionListener {
	        public void actionPerformed (ActionEvent event) {
	          
	           if (event.getSource() == exit ) {
	                System.exit(0);     
	            }
	        }   
	    }
}
	