package Game;

import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Color;
import Env.*;
import Material.*;

public class Map {
	private static Map singleton = new Map();
	
	private int player_x;
	private int player_y;
	protected int buttom;
	Structure floor1; 
	Structure floor2;
  static Item item[] = new Item[2];

	private Map() {
	}

  public int getPlayerX() { return player_x; }
  
  public int getPlayerY() { return player_y; }
	
	public static Map getInstance(int width, int height) {
		singleton.buttom = height - 50;
    // �v���C���̏����ʒu
		singleton.player_x = 10;
		singleton.player_y = singleton.buttom;
    
    // ���̐���
		singleton.floor1 = new Structure(300, height - singleton.buttom, 0, singleton.buttom+10, Color.ORANGE);
		singleton.floor2 = new Structure(100, height - singleton.buttom, 350, singleton.buttom+10, Color.ORANGE);

    //**�ǉ�** �A�C�e���̐���
    item[0] = new Item(10, 10, 100, singleton.buttom, Color.BLUE);
		return singleton;
	}
	
  // �e�v�f�̕`��
	public void draw(Graphics g, Player p) {
		p.draw(g);
		//g.fillRect(0, buttom+p.height+1, 600, 100);
		floor1.draw(g);
		floor2.draw(g);
    if ( item[0].isVisible() ) {
      item[0].draw(g);  //�ǉ�
    }
	}
	
  // �v���C���̈ړ�
	public void playerMove(Player p) {
    try {
      // �v���C���̈ړ������̎擾
      Vector v = p.getMoveDir();
      
      // �d�͂ɂ�闎��
      v.vertical += 1; 
      
      // ���C�͂ɂ�錴��
      if ( v.horizontal > 0 ) {
        v.horizontal -=1;
      } else if ( v.horizontal < 0 ) {
        v.horizontal += 1;
      }
      
      // ���R�̗v�f(�d�́A���C��)�ɂ��ړ������̏C��
      p.setMoveDir(v);
      p.setX(p.getX() + v.horizontal);
      p.setY(p.getY() + v.vertical);
      // ���n����
      if ( p.colidWithStructure(floor1) || p.colidWithStructure(floor2) ) {
        p.setY(buttom);
        v.vertical = 0;
        p.landing();
      }
      // �A�C�e���Փ˔���
      if ( p.colidWithItem(item[0]) ) {
        item[0].toInvisible();
      }
    } catch( MaterialsException e ) {
    }
	}
}
