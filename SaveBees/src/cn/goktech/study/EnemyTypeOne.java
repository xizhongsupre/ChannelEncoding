package cn.goktech.study;

import java.util.Random;

/**
 * ����а�������ɻ�1
 * �ص㣺��С�����ٶȲ��죬�������£����н���Ϊ�ӷ�
 * @author ASUS
 *
 */
public class EnemyTypeOne extends FlyingObject implements BeatenEnemyAward{
	public int steep=2;
	
//	д�����췽���趨���Ƿ����������λ������
	public EnemyTypeOne() {
		image=MainActivity.enemyTypeOne;
		height=image.getHeight();
		width=image.getWidth();
		Random random=new Random();
		pointX=random.nextInt(MainActivity.WIDTH-this.width);
		pointY=-this.height;
		
	}
	@Override
	public void step() {
		this.pointY+=steep;
		
	}

	@Override
	public int getScore() {
		// TODO �Զ����ɵķ������
		return 5;
	}

	@Override
	public boolean getLife() {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	public boolean getDouobleFire() {
		// TODO �Զ����ɵķ������
		return false;
	}
	@Override
	public boolean outOfBounds() {
		return this.pointY>MainActivity.HEIGHT;
	}

}
