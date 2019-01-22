package cn.goktech.study;

import java.util.Random;

/**
 * ����������Ҫ���ȵ��۷�
 * �ص㣺
 * �����ٶȽ��������з������������������һ���С�۷�Ļ����Ǿͻ�ʧȥһ����
 * @author ASUS
 *
 */
public class Bee extends FlyingObject implements SorryForBee{
	public int xSteep=2;
	public int ySteep=2;
	public Bee() {
		image=MainActivity.bee;
		height=image.getHeight();
		width=image.getWidth();
		Random random=new Random();
		pointX=random.nextInt(MainActivity.WIDTH-this.width);
		pointY=this.height;
	}
	
	@Override
	public void step() {
		this.pointX+=xSteep;
		this.pointY+=ySteep;
		if(this.pointX>MainActivity.WIDTH-this.width) {
			xSteep=-2;
		}
		if(this.pointX<0) {
			xSteep=2;
		}
		
		
	}
	
	@Override
	public boolean isErrorKill() {
		// TODO �Զ����ɵķ������
		return true;
	}
	
	@Override
	public boolean outOfBounds() {
		return this.pointY>MainActivity.HEIGHT;
	}


}
