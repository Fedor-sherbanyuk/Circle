import javax.swing.*;
import java.awt.*;

public class Solution {

    public static void main(String[] args) {
        System.out.println("Begin game");
        JFrame window = new JFrame("Window game");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(400, 400);
        window.setLayout(new BorderLayout());
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        Circle oval=new Circle();
        window.add(oval);
        System.out.println("Game over");

    }

}
