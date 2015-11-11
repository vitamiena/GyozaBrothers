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
  private int check_point = 600;
    
  Structure floor1; 
  Structure floor2;
  private Item item[] = new Item[2];
  private Enemy enemy;
    
  private Map() {
  }
  
  public int getPlayerX() { return player_x; }
    
  public int getPlayerY() { return player_y; }
    
  public void initMap(int w, int h) {
    width = w;
    height = h;
    buttom = height - 50;
    // �v���C���̏����ʒu
    player_x = width/2;
    player_y = buttom;
    
    // ���̐���
    floor1 = new Structure(500, height - buttom, 0, buttom+10, Color.ORANGE);
    floor2 = new Structure(500, height - buttom, 550, buttom+10, Color.ORANGE);
    
    // �A�C�e���̐���	
    item[0] = new Item(10, 10, 100, buttom, Color.BLUE);

    // �G�l�~�[�̐���	
    enemy = new Enemy(30, 10, 200, buttom, Color.RED);
  }  
  
  public static Map getInstance(int w, int h) {
    singleton.initMap(w, h);
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
    characterMove(p);
    
    // ���S����
    if ( p.getTop() > height ) {
      p.dead();
      return;
    }
    
    // �G�l�~�[�Փ˔���
    if ( p.collidWithEnemy(enemy) && enemy.isAlive() ) {
      if ( p.enemyStamp(enemy) ) {
        enemy.dead();
      } else {
        p.dead();
      }
    }

    // �A�C�e���Փ˔���
    if ( p.colidWithItem(item[0]) ) {
      item[0].toInvisible();
    }
  }
  
  public void enemyMove() {
    Vector v = enemy.getMoveDir();
    v.horizontal = -2;
    enemy.setMoveDir(v);
    characterMove(enemy);
  }
  
  // �L�����N�^�̈ړ� (�v���C���A�G�l�~�[����)
  public void characterMove(AbstractCharacter c) {
    // �v���C���̈ړ������̎擾
    Vector v = c.getMoveDir();
      
    // �d�͂ɂ�闎��
    v.vertical += 1; 
      
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
    if ( c.collidWithStructure(floor1) || c.collidWithStructure(floor2) ) {
      c.setY(buttom);
      v.vertical = 0;
      c.landing();
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
  
  public void retry(Player p) {
    if ( p.getLeft() <= check_point ) {
      p.setX(player_x);
      p.setY(player_y);
    } else {
      p.setX(check_point);
      p.setY(player_y);
    }
    p.reborn();
  }
  
  public void reset(Player p) {
    initMap(width, height);
    p.setX(player_x);
    p.setY(player_y);
    p.reborn();
  }
}
