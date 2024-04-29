import java.awt.event.*;
import javax.swing.*;

class PicPuzzle extends JFrame implements ActionListener{
  static final int startX = 75, startY = 80; // PUZZLE GRID STARING COORDINATE
  static final int PUZZLE_ROW = 4, PUZZLE_COL = 4; // NUMBER OF COLS AND ROWS IN PUZZLE GRID
  static final int ICON_SIZE = 100; // ICON SIZE IN PIXEL
  
  JButton btn[][] = new JButton[PUZZLE_ROW][PUZZLE_COL];
  Icon Icon[][] = new Icon[PUZZLE_ROW][PUZZLE_COL];

  PicPuzzle() {
    super("Pic Puzzle by Neeraj Singh");

    for(int i=0, t=0; i < PUZZLE_ROW; i++){
      for(int j=0; j < PUZZLE_COL; j++){
        Icon[i][j] = new ImageIcon("pic/" + (t++) + ".jpg");
        btn[i][j] = new JButton(Icon[i][j]); 
        btn[i][j].setBounds(startX + (ICON_SIZE * j), 
        startY + (ICON_SIZE * i), ICON_SIZE, ICON_SIZE);
        add(btn[i][j]);
        btn[i][j].addActionListener(this);
      }
    }

    JLabel l2 = new JLabel("NOTE:- Icon has power to swap with neighbour icon ->");
    l2.setBounds(5, 15, 400, 20);
    add(l2);

    Icon movIconSample = new ImageIcon("pic/movable.jpg");
    JButton movSample = new JButton(movIconSample);
    movSample.setBounds(390, 5, 50, 50);
    add(movSample);

    setLayout(null);
    setSize(550, 600);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    JButton button = (JButton)e.getSource();
    int col = (button.getX() - startX) / ICON_SIZE;
    int row = (button.getY() - startY) / ICON_SIZE;
    Icon movIcon = Icon[3][2];

    int[][] offsets = {
      {-1, 0}, // ABOVE ICON
      {0, -1}, // LEFT ICON
      {0, 1},  // RIGHT ICON
      {1, 0}   // BELOW ICON
    };

    for (int[] offset : offsets) {
      int newRow = row + offset[0];
      int newCol = col + offset[1];

      if (newRow >= 0 && newRow < PUZZLE_ROW && newCol >= 0 && newCol < PUZZLE_COL) {
        if(btn[newRow][newCol].getIcon() == movIcon) { 
          btn[newRow][newCol].setIcon(btn[row][col].getIcon());
          btn[row][col].setIcon(movIcon);
          return;
        }
      }
    }
  }

  public static void main(String args[]) {
    SwingUtilities.invokeLater(PicPuzzle::new);
  }
}