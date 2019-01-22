package cn.goktech.study;

import java.util.Random;

/**
 * �л����Ͷ���
 * �ص㣺�����У��ٶȽϿ죬����λ���Լ�������������п��Լ�����ֵ
 * ��������ɵ�Ҫ����Ҫ���ǻ�����ܶ�βŻ���ʧ
 * @author ASUS
 *
 */
public class EnemyTypeTwo extends FlyingObject implements BeatenEnemyAward{
	public int xSteep=2;
	public int ySteep=2;
	Random random=new Random();
	public EnemyTypeTwo() {
		image=MainActivity.enemyTypeTwo;
		height=image.getHeight();
		width=image.getWidth();
		pointX=random.nextInt(MainActivity.WIDTH-this.width);
		pointY=random.nextInt((MainActivity.HEIGHT+this.height)/2);
	}
	@Override
	public void step() {
		this.pointX+=xSteep;
		this.pointY+=ySteep;
		if(this.pointX>MainActivity.WIDTH-this.width) {
			xSteep=-2;ySteep=random.nextInt(5);
		}
		if(this.pointX<0) {
			xSteep=2;ySteep=random.nextInt(3);
		}
		
	}

	@Override
	public boolean getLife() {
		// TODO �Զ����ɵķ������
		return true;
	}

	@Override
	public int getScore() {
		return 0;
	}
	
	@Override
	public boolean getDouobleFire() {
		return false;
	}

	@Override
	public boolean outOfBounds() {
		return this.pointY>MainActivity.HEIGHT;
	}

}
