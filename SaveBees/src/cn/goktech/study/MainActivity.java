package cn.goktech.study;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.rmi.activation.ActivationInstantiator;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainActivity extends JPanel{
	public static BufferedImage background;
	public static BufferedImage bee;
	public static BufferedImage bullet;
	public static BufferedImage myAirPlane0;
	public static BufferedImage myAirPlane1;
	public static BufferedImage enemyTypeOne;
	public static BufferedImage enemyTypeTwo;
	public static BufferedImage enemyTypeThree;
	public static BufferedImage enemyBullet;
	public static BufferedImage	start;
	public static BufferedImage	pause;
	public static BufferedImage	gameover;
	
	static {
		try {
			background=ImageIO.read(MainActivity.class.getResource("/image/background.png"));
			bee=ImageIO.read(MainActivity.class.getResource("/image/bee.png"));
			bullet=ImageIO.read(MainActivity.class.getResource("/image/bullet.png"));
			myAirPlane0=ImageIO.read(MainActivity.class.getResource("/image/myAirPlane0.png"));
			myAirPlane1=ImageIO.read(MainActivity.class.getResource("/image/myAirPlane1.png"));
			enemyTypeOne=ImageIO.read(MainActivity.class.getResource("/image/enemyTypeOne.png"));
			enemyTypeTwo=ImageIO.read(MainActivity.class.getResource("/image/enemyTypeTwo.png"));
			enemyTypeThree=ImageIO.read(MainActivity.class.getResource("/image/enemyTypeThree.png"));
			enemyBullet=ImageIO.read(MainActivity.class.getResource("/image/enemyBullet.png"));
			start=ImageIO.read(MainActivity.class.getResource("/image/start.png"));
			pause=ImageIO.read(MainActivity.class.getResource("/image/pause.png"));
			gameover=ImageIO.read(MainActivity.class.getResource("/image/gameover.png"));


		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
//	�õ������Ĵ�С
	public static final int WIDTH=background.getWidth();
	public static final int HEIGHT=background.getHeight();
//	��Ϸ״̬����
	public static final int START=1;
	public static final int RUNNING=2;
	public static final int PAUSE=3;
	public static final int GAMEOVER=4;
	
	int state=START;
	
//	�����ӵ���ķ����Ｏ��
	FlyingObject []flyingObjects= {};
	
	MyAirPlane myAirPlane=new MyAirPlane();
	
	Bullet bullets[] = {};
	
	EnemyBullet enemyBullets[]= {};
	
	int score=0;
	
//	����ʵ�壬����˵�ǽ����ǵ�ͼƬ��ʾ����
	@Override
	public void paint(Graphics g) {
		// TODO �Զ����ɵķ������
		super.paint(g);
		g.drawImage(background, 0, 0, null);
//		Ϊ�˿������ǵ������������λ�ã����Ǳ���дһ�������趨����xPoint,yPoint;
		paintFlyingObject(g);
		paintBullet(g);
		paintMyAirPlane(g);
		paintEnemyBullet(g);
		paintScoreAndLife(g);
		paintState(g);
		
	}
	public void paintFlyingObject(Graphics g) {
		for (int i = 0; i < flyingObjects.length; i++) {
			FlyingObject f = flyingObjects[i];
			g.drawImage(f.image,f.pointX, f.pointY, null);
		}
	}
	public void paintBullet(Graphics g) {
		for (int i = 0; i < bullets.length; i++) {
			Bullet bullet = bullets[i];
			g.drawImage(bullet.image, bullet.pointX, bullet.pointY, null);
		}
	}
	public void paintEnemyBullet(Graphics g) {
		for (int i = 0; i < enemyBullets.length; i++) {
			EnemyBullet enemyBullet = enemyBullets[i];
			g.drawImage(enemyBullet.image, enemyBullet.pointX, enemyBullet.pointY, null);
		}
	}
	public void paintMyAirPlane(Graphics g){
		g.drawImage(myAirPlane.image, myAirPlane.pointX, myAirPlane.pointY, null);
	}
	public void paintScoreAndLife(Graphics g) {
		g.setColor(Color.BLUE);
		Font font=new Font(Font.SERIF,Font.BOLD, 25);
		g.setFont(font);
		g.drawString("Score"+score, 10, 25);
		g.drawString("Life"+myAirPlane.getLife(), 10, 55);
	}
	public void paintState(Graphics g) {
		switch(state) {
		case START:
			g.drawImage(start, 0, 0, null);
			break;
		case PAUSE:
			g.drawImage(pause, 0, 0, null);
			break;
		case GAMEOVER:
			g.drawImage(gameover, 0, 0, null);
			break;
		}
	}
	
//	дһ�������ʼ����Ҫ�ķ����������ƶ���
	public void action() {
		MouseAdapter adapter=new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO �Զ����ɵķ������
				if(state==RUNNING) {
					int x=e.getX();
					int y=e.getY();
					myAirPlane.moveTo(x, y);
				}
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				switch(state) {
				case START:
					state=RUNNING;
					break;
				case GAMEOVER:
					score=0;
					myAirPlane=new MyAirPlane();
					flyingObjects=new FlyingObject[0];
					bullets=new Bullet[0];
					enemyBullets=new EnemyBullet[0];
					state=START;
					break;
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(state==RUNNING) {
					state=PAUSE;
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				if(state==PAUSE)
					state=RUNNING;
			}
		};
		this.addMouseListener(adapter);
		this.addMouseMotionListener(adapter);
		
		
		Timer timer=new Timer();
		int interTime =10;
		TimerTask task=new TimerTask() {
			
			@Override
			public void run() {
				if(state==RUNNING) {
				enterAction();
				flyingObjectMoveAction();
				shootAction();
				outOfBoundsAction();
				bangAction();
				checkGameOverAction();
				}
				repaint();
			}
		};
		timer.schedule(task, interTime, interTime);
	}
	
	/**
	 * ��������������������FlyingObjects[]
	 * 
	 */
	public FlyingObject nextOne() {
		Random random=new Random();
		int inter=random.nextInt(100);
		if(inter<40) {
			return new EnemyTypeOne();
		}else {
			if(inter<60)
				return new EnemyTypeTwo();
			else {
				if(inter<80)
					return new EnemyTypeThree();
				else
					return new Bee();
			}
		}
		
	}
	/**
	 * �����������Ƶ�ʿ���
	 */
	int interTime=0;
	public void enterAction() {
		interTime++;
		if(interTime%40==0) {
			FlyingObject one =nextOne();
			flyingObjects=Arrays.copyOf(flyingObjects, flyingObjects.length+1);
			flyingObjects[flyingObjects.length-1]=one;
		}
	}
	/**
	 * �����ƶ��Ĺ���
	 */
	public void flyingObjectMoveAction() {
		myAirPlane.step();
		for (int i = 0; i < flyingObjects.length; i++) {
			flyingObjects[i].step();
		}
		for (int i = 0; i < bullets.length; i++) {
			 bullets[i].step();	
		}
		for (int i = 0; i < enemyBullets.length; i++) {
			enemyBullets[i].step();
			
		}
	}
	/**
	 *�ӵ�װ�صķ��� 
	 */
	int interShoot=0;
	public void shootAction() {
		interShoot++;
		if(interShoot%30==0) {
			Bullet []bs=myAirPlane.shoot();
			bullets=Arrays.copyOf(bullets, bullets.length+bs.length);
			System.arraycopy(bs, 0, bullets, bullets.length-bs.length, bs.length);
			for (int i = 0; i < flyingObjects.length; i++) {
				FlyingObject enemyB = flyingObjects[i];
				if(enemyB instanceof EnemyTypeThree) {
					EnemyTypeThree test=(EnemyTypeThree)enemyB;
					EnemyBullet []enemybs=test.shoot();
					enemyBullets=Arrays.copyOf(enemyBullets, enemyBullets.length+enemybs.length);
					System.arraycopy(enemybs, 0, enemyBullets, enemyBullets.length-enemybs.length, enemybs.length);
					//Hahahhahahahahhahahahhah~~~~~~~,��������˲ţ��ⶼ��д������hahahahahahahahahahahahahahahahahha,�������ģ�
					for (int j = 0; j < enemyBullets.length; j++) {
						EnemyBullet killBullet = enemyBullets[j];
						if((killBullet.pointX>myAirPlane.pointX && killBullet.pointX<myAirPlane.pointX+myAirPlane.width)&&(killBullet.pointY>myAirPlane.pointY-killBullet.height&& killBullet.pointY<myAirPlane.pointY+myAirPlane.height))
							myAirPlane.stractLife();
					}
				}
				
			}
		}
	}
	/**
	 * 
	 */
	public void outOfBoundsAction() {
		int index=0;
		FlyingObject []flyingLives=new FlyingObject[flyingObjects.length];
		for (int i = 0; i < flyingObjects.length; i++) {
			FlyingObject obj = flyingObjects[i];
			if(!obj.outOfBounds()) {
				flyingLives[index]=obj;
				index++;
			}
		}
		flyingObjects=Arrays.copyOf(flyingLives, index);
		
		//�ӵ�Խ��
		index=0;
		Bullet []bsLives=new Bullet[bullets.length];
		for (int i = 0; i < bullets.length; i++) {
			Bullet bul = bullets[i];
			if(!bul.outOfBounds()) {
				bsLives[index]=bul;
				index++;
			}
		}
		bullets=Arrays.copyOf(bsLives, index);
		
		index=0;
		EnemyBullet []enemybsLives=new EnemyBullet[enemyBullets.length];
		for (int i = 0; i < enemyBullets.length; i++) {
			EnemyBullet enemybul = enemyBullets[i];
			if(!enemybul.outOfBounds()) {
				enemybsLives[index]=enemybul;
				index++;
			}
		}
		enemyBullets=Arrays.copyOf(enemybsLives, index);
		
	}
	/**
	 * 
	 * 
	 */
	
	public void bangAction() {
		for (int i = 0; i < bullets.length; i++) {
			Bullet bullet = bullets[i];
			bang(bullet);
			
		}
	}
	public void bang(Bullet b) {
		int index=-1;
		for (int i = 0; i < flyingObjects.length; i++) {
			FlyingObject f = flyingObjects[i];
			if(f.shootBy(b)) {
				index=i;
				break;
			}
			
		}
		if(index!=-1) {
			FlyingObject one =flyingObjects[index];
			if(one instanceof EnemyTypeOne) {
				EnemyTypeOne s =new EnemyTypeOne();
				score+=s.getScore();
			}
			if(one instanceof EnemyTypeTwo) {
				myAirPlane.addLife();
			}
			if(one instanceof EnemyTypeThree) {
				myAirPlane.addDoubleFire();
			}
			if(one instanceof Bee) {
				myAirPlane.stractLife();
				myAirPlane.clearDoubleFire();
			}
			
			//�������ײ����(ͨ������ײ�����������һ������
			FlyingObject f=flyingObjects[index];
			flyingObjects[index]=flyingObjects[flyingObjects.length -1];
			flyingObjects[flyingObjects.length-1]=f;
			flyingObjects=Arrays.copyOf(flyingObjects, flyingObjects.length-1);	
		}
	}
	
	/**
	 * 
	 */
	public void checkGameOverAction() {
		if(isGameOver()) {
			state=GAMEOVER;
		}
	}
	public boolean isGameOver() {
		for (int i = 0; i < flyingObjects.length; i++) {
			FlyingObject f = flyingObjects[i];
			if(myAirPlane.hit(f)) {
				myAirPlane.stractLife();
				myAirPlane.clearDoubleFire();
				FlyingObject s=flyingObjects[i];
				flyingObjects[i]=flyingObjects[flyingObjects.length-1];
				flyingObjects[flyingObjects.length-1]=s;
				flyingObjects=Arrays.copyOf(flyingObjects, flyingObjects.length-1);	
			}
		}
		return myAirPlane.getLife()<=0;
	}
	
	
	public static void main(String[] args) {
		JFrame frame=new JFrame("SaveBees");
		frame.setSize(WIDTH, HEIGHT);
		MainActivity activity =new MainActivity();
		frame.add(activity);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		activity.action();
	}
}
