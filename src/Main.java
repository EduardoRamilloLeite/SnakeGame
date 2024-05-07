import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        jogo game = new jogo();

        JFrame janelaprincipal = new JFrame("Cobrinha");

        janelaprincipal.add(game);
        janelaprincipal.pack();
        janelaprincipal.setLocationRelativeTo(null);
        janelaprincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaprincipal.setResizable(false);
        janelaprincipal.setVisible(true);



        new Thread(game).start();
    }
}