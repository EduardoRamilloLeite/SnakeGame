import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class comida {
    public static int posicao_X;
    public static int posicao_Y;

    Random random;

    public comida()
    {
        random = new Random();
    }
    public void CriarNovaPosicao()
    {
        posicao_X = random.nextInt (jogo.LARGURA_TELA / jogo.TAMANH0_BLOCO) * jogo.TAMANH0_BLOCO;

        posicao_Y = random.nextInt (jogo.ALTURA_TELA / jogo.TAMANH0_BLOCO) * jogo.TAMANH0_BLOCO;
    }
    public void Desenhar (Graphics g)
    {
        ImageIcon imageIcon = new ImageIcon("C:/Users/eduar/OneDrive/Desktop/Comida.png");
        Image image = imageIcon.getImage();
        g.drawImage (image, posicao_X, posicao_Y, null);
    }
}
