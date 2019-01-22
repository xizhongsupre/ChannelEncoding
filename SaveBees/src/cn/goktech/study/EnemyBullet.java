package cn.goktech.study;

public class EnemyBullet extends FlyingObject{
	private int steep=3;
	
	public EnemyBullet(int x,int y) {
		image=MainActivity.enemyBullet;
		height=image.getHeight();
		width=image.getWidth();
		this.pointX=x;
		this.pointY=y;
	}
	@Override
	public void step() {
		// TODO �Զ����ɵķ������
		this.pointY+=steep;
	}

	@Override
	public boolean outOfBounds() {
		// TODO �Զ����ɵķ������
		return this.pointY>MainActivity.HEIGHT;
	}

}
