package cn.goktech.study;
/**
 * �����ӵ�
 * �ص㣺�ٶȽ���,�������ϣ�����������ͨ�������Լ�����
 * ������ﵽ��Ŀ�꣺�ܹ���ĳһ�ڵ�ﵽ�������ӻ����ٶȵ�Ҫ��
 * @author ASUS
 *
 */
public class Bullet extends FlyingObject {
	public int steep=2;
	public Bullet(int pointX,int pointY) {
		image=MainActivity.bullet;
		height=image.getHeight();
		width=image.getWidth();
		this.pointX=pointX;
		this.pointY=pointY;
	
	}
	@Override
	public void step() {
		this.pointY-=steep;
		
	}
	@Override
	public boolean outOfBounds() {
		// TODO �Զ����ɵķ������
		return this.pointY<-this.height;
	}

}
