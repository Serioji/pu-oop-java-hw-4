package witch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Vasil
 * @param "модален прозорец"
 */
public class UI extends JDialog {
    public UI(JFrame parent, String title, String message) {
        super(parent, title, true);

        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);
        panel.add(Button);
        panel.add(label);
        getContentPane().add(panel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
    /**
     *
     * @author Vasil
     * @param "създаване на бутон"
     */
    public static JButton Button = new JButton("Restart");

    /**
     *
     * @author Vasil
     * @param "проектиране на модалния прозорец"
     */
    public static void render(JFrame parent, String title, String message) {
        new UI(parent, title, message);
    }
    /**
     *
     * @author Vasil
     * @param "бутона рестарт"
     */
    public static void Restart(GameBoard gameBoard) {
        Button.addActionListener(new ButtonClick());
    }
    /**
     *
     * @author Vasil
     * @param "Override на бутона за извикване на игралната дъска"
     */
    public static class ButtonClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GameBoard gameBoard = new GameBoard();
            gameBoard.setVisible(true);
        }
    }
}
