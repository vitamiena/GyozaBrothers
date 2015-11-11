package Game;

import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Color;
import Env.*;
import Material.*;

public class Map {

  private static Map singleton = new Map();
    
    private int width;
    private int height;
    private int player_x;
    private int player_y;
    protected int buttom;
    
    Structure floor1; 
    Structure floor2;
    private Item item[] = new Item[2];
    private Enemy enemy;
    
    private Map() {
    }
  
    public int getPlayerX() { return player_x; }
    
    public int getPlayerY() { return player_y; }
    
    public static Map getInstance(int w, int h) {
      singleton.width = w;
        singleton.height = h;
        singleton.buttom = singleton.height - 50;
        // �v���C���̏����ʒu
        singleton.player_x = singleton.width/2;
        singleton.player_y = singleton.buttom;
        
        // ���̐���
        singleton.floor1 = new Structure(500, singleton.height - singleton.buttom, 0, singleton.buttom+10, Color.ORANGE);
        singleton.floor2 = new Structure(500, singleton.height - singleton.buttom, 550, singleton.buttom+10, Color.ORANGE);
        
        // �A�C�e���̐���	
        singleton.item[0] = new Item(10, 10, 100, singleton.buttom, Color.BLUE);

        // �G�l�~�[�̐���	
        singleton.enemy = new Enemy(30, 10, 200, singleton.buttom, Color.RED);
      return singleton;
    }
  
    // �e�v�f�̕`��
    public void draw(Graphics g, Player p) {
      p.draw(g, player_x);
        //g.fillRect(0, buttom+p.height+1, 600, 100);
        drawStructure(g, floor1, p);
        drawStructure(g, floor2, p);
        
        if ( item[0].isVisible() ) {
          drawItem(g, item[0], p);  //�ǉ�
        }
      if (enemy.isAlive()) {
        drawEnemy(g, enemy, p);
      }
      //floor1.draw(g, getRelativePosition(floor1.getX(), p));
      //floor2.draw(g, getRelativePosition(floor2.getX(), p));
    }
  
    // �v���C���̈ړ�
    public void playerMove(Player p) {
      try {
        // �v���C���̈ړ������̎擾
        Vector v = p.getMoveDir();
          
          // �d�͂ɂ�闎��
          v.vertical += 1; 
          
          // ���C�͂ɂ�錴��
          v.vertical += 1; 
          
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
          if ( p.collidWithStructure(floor1) || p.collidWithStructure(floor2) ) {
            p.setY(buttom);
              v.vertical = 0;
              p.landing();
          }

        // �G�l�~�[�Փ˔���
        if ( p.collidWithEnemy(enemy) && enemy.isAlive() ) {
          if ( p.enemyStamp(enemy) ) {
            enemy.Dead();
          } else {
            p.Dead();
          }
        }

        // �A�C�e���Փ˔���
        if ( p.colidWithItem(item[0]) ) {
          item[0].toInvisible();
        }
      } catch( MaterialsException e ) {
      }
    }
  public void enemyMove() {
    try {
      Vector v = enemy.getMoveDir();

      enemy.setX(enemy.getX()+v.horizontal);
      enemy.setY(enemy.getY()+v.vertical);
    } catch( MaterialsException e ) {
    }
  }
  
    public int getRelativePosition(int x, Player p) { //�v���C���Ƃ̑��Έʒu
      return x - p.getX() + player_x;
    }
  
    public void drawStructure(Graphics g, Structure s, Player p) {
      
        if ( isInScreen(s, p) ) {
          s.draw(g, getRelativePosition(s.getLeft(), p));
        }
    }
  
    public void drawItem(Graphics g, Item i, Player p) {
      if ( isInScreen(i,p) ) {
        i.draw(g, getRelativePosition(i.getLeft(), p));
      }
    }

  public void drawEnemy(Graphics g, Enemy enemy, Player p) {
    if ( isInScreen(enemy,p) ) {
      enemy.draw(g, getRelativePosition(enemy.getLeft(), p));
    }
  }
  
    public boolean isInScreen(AbstractMaterial m, Player p) {
      int left, right;
     
      left = getRelativePosition(m.getLeft(), p);
      right = getRelativePosition(m.getRight(), p);
      
      if ( left > width ) { return false; }
      if ( right < 0 ) { return false; }
      
      return true;
    }
}
