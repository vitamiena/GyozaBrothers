import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.JApplet;
import javax.swing.JLabel;
import Env.*;
import Material.*;
import Game.*;

import static java.awt.event.KeyEvent.*;

public class GameMaster extends JApplet implements Runnable, KeyListener {
  private Thread thread = null;
  JLabel lb = new JLabel();
  int x = 10, y = 100;
  private Player player;
  private Map map;
  private Image img;
  private Graphics offg; //��ʂ̃o�b�t�@
  private int width, height;
  //private KeyController keyController = KeyController.getInstance();
  
  @Override
  public void init() {  
    setFocusable(true);
    addKeyListener(this);   
    Dimension size = getSize(); // ��ʃT�C�Y
    width = size.width;
    height = size.height;
    img = createImage(width, height);
    offg = img.getGraphics(); // �I�t�X�N���[��
    
    map = Map.getInstance(width, height);
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

  // �Q�[���̃��[�v
  public void run() {
    Thread thisThread = Thread.currentThread();
    while ( thread == thisThread ) {
      // �v���C�����������Ă�����胋�[�v
      while ( player.isAlive() ) {  
        offg.clearRect(0, 0, width, height);
        player.move(); // �v���C���̈ړ������̐ݒ�
        map.enemyMove();
        map.playerMove(player); // �v���C���̉�ʏ�̈ړ�
        map.draw(offg, player); // �v�f�̕`��
        repaint();  
        try {
          Thread.sleep(50);
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
