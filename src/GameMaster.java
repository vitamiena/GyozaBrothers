import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JApplet;
import javax.swing.JLabel;
import java.util.ArrayList;
import Env.*;
import Material.*;
import Game.*;

import static java.awt.event.KeyEvent.*;

public class GameMaster extends JApplet implements Runnable, KeyListener {
  private Thread thread = null;
  private Player player;
  private Map map;
  private Image img;
  private Graphics offg; //��ʂ̃o�b�t�@
  private int width, height;
  private boolean isPlaying;
  private boolean isStarted;
  //private KeyController keyController = KeyController.getInstance();
  ArrayList<Structure> structures;
  ArrayList<Item> items;
  ArrayList<Enemy> enemies;
  
  @Override
  public void init() {  
    setFocusable(true);
    addKeyListener(this);   
    Dimension size = getSize(); // ��ʃT�C�Y
    width = size.width;
    height = size.height;
    img = createImage(width, height+100);
    offg = img.getGraphics(); // �I�t�X�N���[��
    
    structures = new ArrayList<Structure>();
    structures.add(new Structure(200, 100, 0, height-50, Color.ORANGE));
    structures.add(new Structure(300, 100, 250, height-50, Color.ORANGE));
    structures.add(new Structure(300, 100, 600, height-50, Color.ORANGE));
    structures.add(new Structure(500, 10, 700, height-90, Color.ORANGE));
    structures.add(new Structure(100, 10, 1250, height-90, Color.ORANGE));
    structures.add(new Structure(100, 10, 1380, height-120, Color.ORANGE));
    structures.add(new Structure(100, 10, 1600, height-30, Color.ORANGE));
    structures.add(new Structure(300, 50, 1750, height-30, Color.ORANGE));
    structures.add(new Structure(30,  80, 2050, height-60, Color.ORANGE));
    structures.add(new Structure(30, 110, 2080, height-90, Color.ORANGE));
    structures.add(new Structure(30, 140, 2110, height-120, Color.ORANGE));
    structures.add(new Structure(30, 170, 2140, height-150, Color.ORANGE));
    structures.add(new Structure(180, 200, 2170, height-180, Color.ORANGE));
    structures.add(new Structure(30, 190, 2350, height-170, Color.ORANGE));
    structures.add(new Structure(300, 200, 2380, height-180, Color.ORANGE));
    structures.add(new Structure(20, 190, 2680, height-170, Color.ORANGE));
    structures.add(new Structure(50, 200, 2700, height-180, Color.ORANGE));
    structures.add(new Structure(100, 100, 2750, height-50, Color.ORANGE));
    structures.add(new Structure(400, 100, 2350, -50, Color.ORANGE));
    
    items = new ArrayList<Item>();
    items.add(new Item(10, 10, 100, height-100, Color.BLUE));
    items.add(new Item(10, 10, 400, height-100, Color.BLUE));
    
    enemies = new ArrayList<Enemy>();
    enemies.add(new Enemy(30, 10, 200, height-100, Color.RED));
    enemies.add(new Enemy(30, 10, 700, height-100, Color.RED));    
        
    map = Map.getInstance(width, height, structures, items, enemies);
    player = new Player(10, 10, map.getPlayerX(), map.getPlayerY());
    isPlaying = true;
    isStarted = false;
  }
  
  public void uopdate(Graphics g) {
    paint(g);
  }
  
  @Override
  public void paint(Graphics g) {
    g.drawImage(img, 0, 0, this);
  }
  
  public void keyPressed(KeyEvent e) {
    if ( ! isStarted ) {
      int key = e.getKeyCode();
      if ( key == VK_SPACE ) { isStarted = true; }
    } else if ( isPlaying ) {
      player.keyPressed(e);
    } else {
      char key = e.getKeyChar();
      switch (key) {
        case 'r': ;
        case 'R':
          map.reset(player, structures, items, enemies);
          isPlaying = true;
          break;
        case 'c': 
        case 'C': 
          map.retry(player);
          isPlaying = true;
          break;
        case 's':
        case 'S':
          isStarted = false;
          isPlaying = true;
          map.reset(player, structures, items, enemies);
          break;
        default: ; break;     
      }
    }    
    repaint();
  }
  
  public void keyReleased(KeyEvent e) {
    player.keyReleased(e);
    repaint();
  }
  public void keyTyped(KeyEvent e) {} 

  // �Q�[���̃��[�v
  public void run() {
    Thread thisThread = Thread.currentThread();
    while ( thread == thisThread ) {
      while ( ! isStarted ) {
        showTitle();
        repaint();
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {      
        }
      }
      // �v���C�����������Ă�����胋�[�v
      while ( player.isAlive() ) {  
        offg.clearRect(0, 0, width, height);
        player.move(); // �v���C���̈ړ������̐ݒ�
        map.enemyMove(player);
        map.playerMove(player); // �v���C���̉�ʏ�̈ړ�
        map.draw(offg, player); // �v�f�̕`��
        repaint();  
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {      
        }
      }
      isPlaying = false;
      showGameOverMessage();
      //offg.drawString("Restart  : R", 300, 200);
      repaint();
      while ( ! isPlaying && isStarted ) {
        try {        
          Thread.sleep(10);
        } catch ( InterruptedException e ) {
        }
      }
    }
  }
  
  @Override
  public void start() {
    if ( thread == null ) {
      thread = new Thread(this);
      thread.start();
    }
  }
  
  @Override
  public void stop() {
    thread = null;
  }
  
  private void showTitle() {
    offg.setColor(Color.BLACK);
    offg.fillRect(0, 0, width, height);
    offg.setColor(Color.WHITE);
    offg.setFont(new Font("Arial", Font.BOLD, 60));
    offg.drawString("Gyoza", 150, 100);
    offg.drawString("Brothers", 200, 170);
    offg.setFont(new Font("Arial", Font.PLAIN, 20));
    offg.drawString("Start : SPACE", 180, 250);
  }
  
  private void showGameOverMessage() {    
    offg.setFont(new Font("Arial", Font.BOLD, 40));
    offg.drawString("GameOver", 300, 100);
    offg.setFont(new Font("Arial", Font.PLAIN, 20));
    offg.drawString("Continue : C", 300, 150);
    offg.drawString("Menu : S", 300, 200);
  }
}
