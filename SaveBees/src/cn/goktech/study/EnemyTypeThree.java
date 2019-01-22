package cn.goktech.study;

import java.util.Random;

/**
 * ���ǵ����ֵл���
 * �ص㣺����ٶȽϿ죬�����������������Ϊ�ӻ���
 * ����Ŀ�꣺���Է������
 *
 * @author ASUS
 *
 */
public class EnemyTypeThree extends FlyingObject implements BeatenEnemyAward{
	public int xSteep=2;
	public int ySteep=2;
	
	public EnemyTypeThree() {
		image=MainActivity.enemyTypeThree;
		height=image.getHeight();
		width=image.getWidth();
		Random random=new Random();
		pointX=random.nextInt(MainActivity.WIDTH-this.width);
		pointY=-this.height;
	}
	
	@Override
	public void step() {
		this.pointY+=ySteep;
	}
	

	@Override
	public boolean getDouobleFire() {
		// TODO �Զ����ɵķ������
		return true;
	}

	@Override
	public int getScore() {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public boolean getLife() {
		// TODO �Զ����ɵķ������
		return false;
	}
	
	@Override
	public boolean outOfBounds() {
		return this.pointY>MainActivity.HEIGHT;
	}

	public EnemyBullet[] shoot(){
		EnemyBullet []enemyBullets=new EnemyBullet[1];
		enemyBullets[0]=new EnemyBullet(this.pointX+this.width/2-20, this.pointY+this.height);
		return enemyBullets;
	}
}
