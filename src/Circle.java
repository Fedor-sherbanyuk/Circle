import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Circle extends JComponent {
    public static final int FIELD = 0;
    public static final int FIELD_X = 10;
    public static final int FIELD_O = 200;

    Random random = new Random();
    public int count;
    int[][] field;
    boolean isStep;
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, getWidth(), getHeight());
        drawGrid(g);
        drawXNoll(g);

    }

    public void drawGrid(Graphics g) {
        int w = getWidth();
        int h = getHeight();
        int dh = h / 3;
        int dw = w / 3;
        g.setColor(Color.RED);
        for (int i = 1; i < 3; i++) {
            g.drawLine(0, dh * i, w, dh * i);
            g.drawLine(dw * i, 0, dw * i, h);

        }
    }

    public Circle() {
 enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        field = new int[3][3];
        count=0;
        initGame();
    }

    boolean isCellValid(int x, int y) {
        if (x < 0 || y < 0 || x >= 3|| y >= 3){
            return false;
        }
        return true;
    }


    @Override
    public void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);

        if (e.getButton() == MouseEvent.BUTTON1) {

            int x = e.getX();
            int y = e.getY();
            int i = (int) ((float) x / getWidth() * 3);
            int j = (int) ((float) y / getHeight() * 3);
            if (field[i][j] == FIELD) {
                field[i][j] = isStep ? FIELD_X : FIELD_O;
                isStep = !isStep;
                repaint();
                    int res = checkState();
                    if (res != 0) {
                        if (res == FIELD_O * 3) {
                            JOptionPane.showMessageDialog(this, "НОЛИКИ ВЫИГРАЛИ!", "ПОБЕДА!", JOptionPane.INFORMATION_MESSAGE);
                        } else if (res == FIELD_X * 3) {
                            JOptionPane.showMessageDialog(this, "КРЕСТИКИ ВЫИГРАЛИ!", "ПОБЕДА!", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "НИЧЬЯ", "ПОБЕДИЛА ДРУЖБА!", JOptionPane.INFORMATION_MESSAGE);
                        }
                        initGame();
                        repaint();
                    }
                }
            }
        }

    public void initGame() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                field[i][j] = FIELD;
            }
        }

        isStep = true;
    }

    public void drawX(int i, int j, Graphics g) {

        g.setColor(Color.BLUE);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        g.drawLine(x, y, x + dw, y + dh);
        g.drawLine(x, y + dh, x + dw, y);

    }

    public void drawNoll(int i, int j, Graphics g) {
        g.setColor(Color.ORANGE);
        int dw = getWidth() / 3;
        int dh = getHeight() / 3;
        int x = i * dw;
        int y = j * dh;
        g.drawOval(x + 5 * dw / 100, y, dw * 9 / 10, dh);

    }

    public void drawXNoll(Graphics g) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (field[i][j] == FIELD_X) {
                    drawX(i, j, g);
                } else if (field[i][j] == FIELD_O) {
                    drawNoll(i, j, g);
                }

            }
        }

    }

    public int checkState() {
        int diag = 0;
        int diag2 = 0;
        for (int i = 0; i < 3; i++) {
            diag += field[i][i];
            diag2 += field[i][2 - i];
        }

        if (diag == FIELD_X * 3 || diag == FIELD_O * 3) {
            return diag;
        }
        if (diag2 == FIELD_X * 3 || diag2 == FIELD_O * 3) {
            return diag2;
        }
        int check_i = 0;
        int check_j = 0;
        boolean isEmpty = false;

        for (int i = 0; i < 3; i++) {
            check_i = 0;
            check_j = 0;
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 0) {
                    isEmpty = true;
                }
                check_i += field[i][j];
                check_j += field[j][i];
            }
            if (check_i == FIELD_O * 3 || check_i == FIELD_X * 3) {

                return check_i;

            }
            if (check_j == FIELD_O * 3 || check_j == FIELD_X * 3) {
                return check_j;

            }
        }
        if (isEmpty) {
            return 0;
        } else {
            return -1;
        }

    }

}
