import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Color;
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
  private Graphics offg; //画面のバッファ
  private int width, height;
  //private KeyController keyController = KeyController.getInstance();
  
  @Override
  public void init() {  
    setFocusable(true);
    addKeyListener(this);   
    Dimension size = getSize(); // 画面サイズ
    width = size.width;
    height = size.height;
    img = createImage(width, height+100);
    offg = img.getGraphics(); // オフスクリーン
    
    ArrayList<Structure> structures = new ArrayList<Structure>();
    structures.add(new Structure(500, 100, 0, height-50, Color.ORANGE));
    structures.add(new Structure(500, 100, 550, height-50, Color.ORANGE));
    
    ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    enemies.add(new Enemy(30, 10, 200, height-100, Color.RED));
    enemies.add(new Enemy(30, 10, 700, height-100, Color.RED));    
    
    ArrayList<Item> items = new ArrayList<Item>();
    items.add(new Item(10, 10, 100, height-100, Color.BLUE));
    items.add(new Item(10, 10, 400, height-100, Color.BLUE));
    
    map = Map.getInstance(width, height, structures, items, enemies);
    player = new Player(10, 10, map.getPlayerX(), map.getPlayerY());
  }
  
  public void uopdate(Graphics g) {
    paint(g);
  }
  
  @Override
  public void paint(Graphics g) {
    g.drawImage(img, 0, 0, this);
  }
  
  public void keyPressed(KeyEvent e) {
    player.keyPressed(e);
    repaint();
  }
  
  public void keyReleased(KeyEvent e) {
    player.keyReleased(e);
    repaint();
  }
  public void keyTyped(KeyEvent e) {} 

  // ゲームのループ
  public void run() {
    Thread thisThread = Thread.currentThread();
    while ( thread == thisThread ) {
      // プレイヤが生存している限りループ
      while ( player.isAlive() ) {  
        offg.clearRect(0, 0, width, height);
        player.move(); // プレイヤの移動方向の設定
        map.enemyMove(player);
        map.playerMove(player); // プレイヤの画面上の移動
        map.draw(offg, player); // 要素の描画
        repaint();  
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {      
        }
      }
      stop();
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
}
