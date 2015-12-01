package Game;

import java.applet.AudioClip;
import javax.swing.JApplet;
import java.awt.Graphics;
import java.applet.Applet;
import java.awt.Color;
import java.util.ArrayList;
import Env.*;
import Material.*;
import Material.Enemy.*;

public class Map {

  private static Map singleton = new Map();
  
  private GameMaster gm;
  
  private int width;
  private int height;
  private int player_x;
  private int player_y;
  protected int buttom;
  private int check_point = 3000;
  private boolean isGoal;
  private int itemScore;
  private int enemyScore;
      
  private ArrayList<Structure> structures;
  private ArrayList<Item> items;
  private ArrayList<Enemy> enemies;
  private ArrayList<Trap> traps;
  private Structure goal;
    
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
    isGoal = false;
  }  
  
  public static Map getInstance(int w, int h,
    ArrayList<Structure> s, ArrayList<Item> i, ArrayList<Enemy> e, ArrayList<Trap> t, 
    Structure goal, GameMaster g ) {
    singleton.initMap(w, h);
    singleton.gm = g;
    singleton.structures = new ArrayList<Structure>(s); 
    singleton.items = new ArrayList<Item>(i); 
    singleton.enemies = new ArrayList<Enemy>(e);
    singleton.traps = new ArrayList<Trap>(t);
    singleton.goal = goal;
    singleton.itemScore = 0;
    singleton.enemyScore = 0;
    return singleton;
  }
  
  public boolean isGoal() {
    return isGoal;
  }

  public int getItemScore() {
    return itemScore;
  }
  
  public int getEnemyScore() {
    return enemyScore;
  }
  
  // �e�v�f�̕`��
  public void draw(Graphics g, Player p) {
      
    for ( Structure s : structures ) {
      drawMaterial(g, s, p);
    }

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
    
    for ( Trap t : traps ) {
      drawMaterial(g, t, p);
    }
    
    drawMaterial(g, goal, p);

    p.draw(g, player_x);
  }
  
  // �}�b�v��̗v�f�̈ړ�
  public void update(Player p) {
    enemyMove(p);
    playerMove(p);
    structureMove(p);
  }
  
  // �v���C���̈ړ�
  public void playerMove(Player p) {
    characterMove(p);
    gm.se = gm.getAudioClip(gm.getDocumentBase(), "Sound\\kill.wav");
    
    // ���S����
    if ( p.getTop() > height ) {
      p.dead();
      return;
    }
    
    // �G�l�~�[�Փ˔���
    for ( Enemy enemy : enemies ) {
      if ( p.collidWithMaterial(enemy) && enemy.isAlive() ) {
        if ( p.onMaterial(enemy) ) {
          p.jump();
          enemy.dead( gm.se );
          enemyScore += 100;
        } else {
          if ( !p.isImmortal() ) {
            p.dead();
          } else {
            p.toDeadable();
            enemy.dead( gm.se );       
            enemyScore += 100;
          }
        } 
      }
    }

    // �A�C�e���Փ˔���
    for ( Item item : items ) {
      if ( item.isVisible() && p.collidWithMaterial(item) ) {
        gm.se = gm.getAudioClip(gm.getDocumentBase(), "Sound\\get_item.wav");
        gm.se.play();
        p.getItem(item);
        itemScore += 50;
      }
    }
    
    // �g���b�v�Փ˔���
    for ( Trap trap : traps ) {
      if ( p.collidWithMaterial(trap) ) {
        trap.motion(p);
      }
    }
    
    // �S�[������
    if ( p.collidWithMaterial(goal) ) {
      gm.bgm2 = gm.getAudioClip(gm.getDocumentBase(), "Sound\\goal.wav");
      gm.bgm1.stop();
      gm.bgm2.play();
      isGoal = true;
    }
  }
  
  public void enemyMove(Player p) {
    for ( Enemy enemy : enemies ) {
      if ( isInScreen(enemy, p) ) {
        enemy.motion();
        characterMove(enemy);
      }
    }
  }
  
  public void structureMove(Player p) {
    for ( Structure structure : structures ) {
      if ( isInScreen(structure, p) ) {
        structure.motion();
        Vector v;
        v = structure.getMoveDir();
        structure.setX(structure.getX() + v.horizontal);
        structure.setY(structure.getY() + v.vertical);
      }
    }
  }
  
  // �L�����N�^�̈ړ� (�v���C���A�G�l�~�[����)
  public void characterMove(AbstractCharacter c) {
    // �L�����N�^�̈ړ������̎擾
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
        if ( c.onMaterial(structure) ) {
          c.setY(structure.getTop() - c.getHeight());
          v.vertical = 0;
          c.landing();
        }
        if ( c.underMaterial(structure) ) {
          c.setY(structure.getBottom());
          v.vertical = 0;
        }
        if ( c.leftMaterial(structure) ) {
          c.setX( structure.getLeft() - c.getWidth() );
          v.horizontal = 0;
        } 
        if ( c.rightMaterial(structure) ){
          c.setX( structure.getRight() );
          v.horizontal = 0;
        }
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
  
  // enemy�͂��̂܂܁A�`�F�b�N�|�C���g����
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
  
  // �}�b�v�����������A�ŏ�����
  public void reset(Player p, ArrayList<Structure> s, ArrayList<Item> i, ArrayList<Enemy> e) {
    initMap(width, height);
    structures = new ArrayList<Structure>(s); 
    items = new ArrayList<Item>(i); 
    enemies = new ArrayList<Enemy>(e);
    p.setX(player_x);
    p.setY(player_y);
    p.reborn();
    itemScore = 0;
    enemyScore = 0;
  }
}
