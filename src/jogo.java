import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class jogo extends JPanel implements Runnable
{
    public static final int LARGURA_TELA = 1300;
    public static final int ALTURA_TELA = 750;
    public static final int TAMANH0_BLOCO = 25;
    public static final int UNIDADES = (LARGURA_TELA * ALTURA_TELA / (TAMANH0_BLOCO * TAMANH0_BLOCO));
    public static final int INTERVALO = 200;
    public static final String NOME_FONTE = "Ink Free";
    public boolean GameOver = false;
    cobrinha objetoCobra;
    comida objetoComida;
    Random random;
    public static Semaphore Mutex;

    public jogo()
    {
        setPreferredSize (new Dimension (LARGURA_TELA, ALTURA_TELA));
        setBackground (Color.WHITE);
        setFocusable (true);

        addKeyListener(new teclado());
        objetoCobra = new cobrinha();
        objetoComida = new comida();
        objetoComida.CriarNovaPosicao();
        GameOver = false;
        Mutex = new Semaphore(1);
    }
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        try {
            desenharTela(g);
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    public void desenharTela(Graphics g) throws LineUnavailableException, IOException, UnsupportedAudioFileException
    {
        if (GameOver == false)
        {
            objetoComida.Desenhar (g);
            objetoCobra.Desenhar(g);
            g.setColor(Color.red);
            g.setFont (new Font (NOME_FONTE, Font.BOLD, 40));
            FontMetrics metrics  = g.getFontMetrics(g.getFont());
            String texto = "PONTOS: " + objetoCobra.QuantidadedeComida;
            g.drawString(texto, (LARGURA_TELA - metrics.stringWidth(texto)) / 2, g.getFont() .getSize());
        }
        else
        {
            fimDeJogo(g);
        }
    }
    public void fimDeJogo(Graphics g) throws LineUnavailableException, IOException, UnsupportedAudioFileException
    {
        g.setColor (Color.red);
        g.setFont (new Font (NOME_FONTE, Font.BOLD, 75));
        FontMetrics fonteFinal = getFontMetrics(g.getFont());
        g.drawString(" Fim de jogo.", (LARGURA_TELA - fonteFinal.stringWidth("fim de jogo")) / 2, ALTURA_TELA / 2);

        Clip clip = AudioSystem.getClip();

        clip.start();

    }

    public void run(){
        while(GameOver == false){
            try{
                objetoCobra.andar();
            } catch (InterruptedException e1){
                e1.printStackTrace();
            }

            try{
                if(objetoCobra.alcancouComida() == true){
                    objetoComida.CriarNovaPosicao();
                }
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e){
                e.printStackTrace();
            }

            GameOver = objetoCobra.VerificarGameOver();
            repaint();

            try{
                Thread.sleep(50);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
