package Game;

import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Color;
import java.util.ArrayList;
import Env.*;
import Material.*;

public class Map {

  private static Map singleton = new Map();
    
  private int width;
  private int height;
  private int player_x;
  private int player_y;
  protected int buttom;
  private int check_point = 600;
      
  private ArrayList<Structure> structures;
  private ArrayList<Life> lifes;
  private ArrayList<Item> items;
  private ArrayList<Enemy> enemies;
  
    
  private Map() {
  }
  
  public int getPlayerX() { return player_x; }
    
  public int getPlayerY() { return player_y; }
    
  public void initMap(int w, int h) {
    width = w;
    height = h;
    
    // �v���C���̏����ʒu
    player_x = 100;
    player_y = 0;
    
  }  
  
  public static Map getInstance(int w, int h, ArrayList<Structure> s, ArrayList<Item> i, ArrayList<Enemy> e) {
    singleton.initMap(w, h);
    singleton.structures = new ArrayList<Structure>(s); 
    singleton.items = new ArrayList<Item>(i); 
    singleton.enemies = new ArrayList<Enemy>(e);
    return singleton;
  }
  
  // �e�v�f�̕`��
  public void draw(Graphics g, Player p) {
    //g.fillRect(0, buttom+p.height+1, 600, 100);
    //drawStructure(g, floor1, p);
    //drawStructure(g, floor2, p);
      
    for ( Structure s : structures ) {
      drawMaterial(g, s, p);
    }

      /*
    if ( item[0].isVisible() ) {
      drawItem(g, item[0], p);  //�ǉ�
    }
    if (enemy.isAlive()) {
      drawEnemy(g, enemy, p);
    }*/
    
    for ( Item i : items ) {
      if ( i.isVisible() ) { 
        drawMaterial(g, i, p);
      }
    }
    
    for ( Enemy e : enemies ) {
      if ( e.isAlive() ) {
        drawMaterial(g, e, p);
      }
    }
    //floor1.draw(g, getRelativePosition(floor1.getX(), p));
    //floor2.draw(g, getRelativePosition(floor2.getX(), p));
    p.draw(g, player_x);
  }
  
  // �v���C���̈ړ�
  public void playerMove(Player p) {
    characterMove(p);
    
    // ���S����
    if ( p.getTop() > height ) {
      p.dead();
      return;
    }
    
    // �G�l�~�[�Փ˔���
    for ( Enemy enemy : enemies ) {
      if ( p.collidWithMaterial(enemy) && enemy.isAlive() ) {
        if ( p.onMaterial(enemy) ) {
          enemy.dead();
        } else {
          if ( !p.isImmortal() ) {
            p.dead();
          } else {
            p.toDeadable();
            enemy.dead();
          }
        } 
      }
    }

    // �A�C�e���Փ˔���
    for ( Item item : items ) {
      if ( item.isVisible() && p.collidWithMaterial(item) ) {
        p.getItem(item);
      }
    }
  }
  
  public void enemyMove(Player p) {
    for ( Enemy enemy : enemies ) {
      if ( isInScreen(enemy, p) ) {
        Vector v = enemy.getMoveDir();
        v.horizontal = -2;
        enemy.setMoveDir(v);
        characterMove(enemy);
      }
    }
  }
  
  // �L�����N�^�̈ړ� (�v���C���A�G�l�~�[����)
  public void characterMove(AbstractCharacter c) {
    // �v���C���̈ړ������̎擾
    Vector v = c.getMoveDir();
      
    // �d�͂ɂ�闎��
    if ( v.vertical < 5 ) {
      v.vertical += 1; 
    }
      
    // ���C�͂ɂ�錸��
    if ( v.horizontal > 0 ) {
      v.horizontal -=1;
    } else if ( v.horizontal < 0 ) {
      v.horizontal += 1;
    }
    
    // ���R�̗v�f(�d�́A���C��)�ɂ��ړ������̏C��
    c.setMoveDir(v);
    c.setX(c.getX() + v.horizontal);
    c.setY(c.getY() + v.vertical);
    // ���n����
    for ( Structure structure : structures ) {
      if ( c.collidWithMaterial(structure) ) {
        c.setY(structure.getTop() - c.getHeight());
        v.vertical = 0;
        c.landing();
      }
    }
  }
  
  public int getRelativePosition(int x, Player p) { //�v���C���Ƃ̑��Έʒu
    return x - p.getX() + player_x;
  }
  
  public void drawMaterial(Graphics g, AbstractMaterial m, Player p) {
      if ( isInScreen(m, p) ) {
        m.draw(g, getRelativePosition(m.getLeft(), p));
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
  
  public void retry(Player p) {
    for ( Item i : items ) {
      i.reset();
    }

    if ( p.getLeft() <= check_point ) {
      p.setX(player_x);
      p.setY(player_y);
    } else {
      p.setX(check_point);
      p.setY(player_y);
    }
    p.reborn();
  }
  
  // reset�Ɠ�������
  public void reset(Player p, ArrayList<Structure> s, ArrayList<Item> i, ArrayList<Enemy> e) {
    initMap(width, height);
    structures = new ArrayList<Structure>(s); 
    items = new ArrayList<Item>(i); 
    enemies = new ArrayList<Enemy>(e);
    p.setX(player_x);
    p.setY(player_y);
    p.reborn();
  }
}
