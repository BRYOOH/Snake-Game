import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Panel extends JPanel implements ActionListener {

	static final int SCREEN_WIDTH= 600;
	static final int SCREEN_HEIGHT= 600;
	static final int UNIT_SIZE= 15;
	static final int GAME_UNITS=((SCREEN_WIDTH* SCREEN_HEIGHT)/UNIT_SIZE);
	static final int DELAY=60;
	int x[]= new int [GAME_UNITS]; 
	int y[]= new int [GAME_UNITS];
	int bodyParts=6;
	int ballX;
	int ballY;
	int score=0;
	
	Timer timer;
	Random random;
	boolean running= false;
	char direction= 'R';
	
	
	Panel(){
		random= new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setFocusable(true);
		this.setBackground(Color.BLACK);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
		
	}
	
	public void startGame() {
		newBall();
		running= true;
		timer= new Timer(DELAY,this);
		timer.start();
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g); 
		Draw(g);
		
	}
	
	public void Draw(Graphics g) {
         if(running) {
			//draw ball
		    g.setColor(Color.BLUE);
			g.fillOval(ballX, ballY, UNIT_SIZE, UNIT_SIZE);
			
			
			for(int i=0;i<bodyParts;i++) {
				if(i==0) {
					//draw the head
					g.setColor(new Color(0,255,0));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
				else {
					//draw the body
					g.setColor(new Color(0,255,0));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
         }
         else {
        	 gameOver(g);
        	 }
         
		}
		
	
	
	
	public void Move() {
		for(int i= bodyParts;i>0;i--) {
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		
		switch(direction) {
		case 'U':
			y[0]= y[0]-UNIT_SIZE;
			break;
			
		case 'D':
			y[0]= y[0]+ UNIT_SIZE;
			break;
			
		case 'L':
			x[0]= x[0]- UNIT_SIZE;
			break;
			
		case 'R':
			x[0]=x[0]+ UNIT_SIZE;
			break;
		}
		
	}
	
	public void newBall() {
		ballX= random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		ballY= random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
	}
	
	public void checkBall() {
		if(x[0]==ballX &&y[0]==ballY) {
			newBall();
			score++;
			bodyParts++;
		}
		
	}
	
	public void checkCollisions() {
		for(int i= bodyParts;i>0;i--) {
			//checks head collision with body
			if(x[0]==x[i]&& y[0]==y[i]) {
				running=false;
			}
			
			//check collision with right border
			if(x[0]>SCREEN_WIDTH) {
				running=false;
			}
	
			//check collision with left border
			if(x[0]<0) {
				running=false;
			}
			
			//check collision with bottom border
			if(y[0]>SCREEN_HEIGHT) {
				running=false;
			}
			
			//check collision with top border
			if(y[0]<0) {
				running=false;
			}
			
			if(!running) {
			timer.stop();

		}
		}
		
		
		
	}
	
	public void Score(Graphics g) {
	g.setColor(Color.green);
	g.setFont(new Font("InkFree",Font.BOLD,40));
	
	FontMetrics metrics=getFontMetrics(g.getFont());
	g.drawString("Score is:"+ score, (SCREEN_WIDTH- metrics.stringWidth("Score is:"+ score))/2, g.getFont().getSize());	
	}
	public void gameOver(Graphics g) {
		Score(g);
		
		FontMetrics metrics=getFontMetrics(g.getFont());
		g.drawString("Game Over",(SCREEN_WIDTH-metrics.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2);
		
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(running) {
		
		Move();
		checkBall();
		checkCollisions();
		
		}
		
		repaint();
		
		
	}
	
	public class MyKeyAdapter extends KeyAdapter {
		// TODO Auto-generated method stub
		@Override
		public void keyPressed (KeyEvent e) {
		switch(e.getKeyCode()) {
		
		case KeyEvent.VK_LEFT:
			if(direction!='R') {
				direction ='L';
			}
			break;
		case KeyEvent.VK_RIGHT:
			if(direction!='L') {
				direction ='R';
				}
			break;
		case KeyEvent.VK_UP:
			if(direction!='D') {
				direction ='U';
				}
			break;
		case KeyEvent.VK_DOWN:
			if(direction!='U') {
				direction ='D';}
			break;
		}
		}
		}
		
	}



