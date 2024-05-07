import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class cobrinha {
    public static char direcao = 'D';
    private int TamanhoDaCobra;
    public int QuantidadedeComida;
    private final int[] eixoX = new int[jogo.UNIDADES];
    private final int[] eixoY = new int[jogo.UNIDADES];

    public cobrinha() {
        TamanhoDaCobra = 6;
        QuantidadedeComida = 0;

    }

    public void Desenhar(Graphics g) {
        for (int i = 0; i < TamanhoDaCobra; i++) {
            if (i == 0) {
                g.setColor(Color.black);
                g.fillRect(eixoX[0], eixoY[0], jogo.TAMANH0_BLOCO, jogo.TAMANH0_BLOCO);

            } else {
                g.setColor(new Color(69, 180, 0));
                g.fillRect(eixoX[i], eixoY[i], jogo.TAMANH0_BLOCO, jogo.TAMANH0_BLOCO);

            }
        }
    }

    public void andar() throws InterruptedException {
        for (int i = TamanhoDaCobra; i > 0; i--) {
            eixoX[i] = eixoX[i - 1];
            eixoY[i] = eixoY[i - 1];
        }
        jogo.Mutex.acquire();

        switch (direcao) {

            case 'C':
                eixoY[0] = eixoY[0] - jogo.TAMANH0_BLOCO;
                break;
            case 'B':
                eixoY[0] = eixoY[0] + jogo.TAMANH0_BLOCO;
                break;
            case 'E':
                eixoX[0] = eixoX[0] - jogo.TAMANH0_BLOCO;
                break;
            case 'D':
                eixoX[0] = eixoX[0] + jogo.TAMANH0_BLOCO;
                break;
            default: break;
        }
        jogo.Mutex.release();
    }

    public void TocarEfeito() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        File arquivo = new File("");
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(arquivo);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }

    public boolean alcancouComida() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        if ((eixoX[0] == comida.posicao_X) && (eixoY[0] == comida.posicao_Y)) {
            TamanhoDaCobra++;
            QuantidadedeComida++;
            return true;
        } else return false;
    }
    public boolean VerificarGameOver()
    {
        boolean perdeu = false;
        for (int i = TamanhoDaCobra; i > 0; i--)
        {
            if ((eixoX[0] == eixoX[i]) && (eixoY[0] == eixoY[i]))
            {
                perdeu = true;
                break;
            }
        }
        if ((eixoX[0] < 0) || (eixoX[0] > jogo.LARGURA_TELA))
        {
            perdeu = true;
        }
        if (( eixoY[0] < 0) || (eixoY[0] > jogo.ALTURA_TELA))
        {
            perdeu = true;
        }
        return perdeu;
    }

}


