/**
 * д��ǰ�棬������������������ð����������ӵ�����ģ���ΪĳЩԭ�������
 */
package cn.goktech.study;
/**
 * �ҳ��Ը��ӵ�������ϼ�������Ҳ��֪���ܲ���ʵ��
 * 
 */
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Police implements KeyListener,FocusListener{

	@Override
	public void focusGained(FocusEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			MainActivity test=new MainActivity();
			test.bullets[test.bullets.length-1].step();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO �Զ����ɵķ������
		
	}
	

}
