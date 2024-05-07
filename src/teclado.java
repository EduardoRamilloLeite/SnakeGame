import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class teclado extends KeyAdapter {
    public void keyPressed(KeyEvent e)
    {

        try {
            jogo.Mutex.acquire();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (cobrinha.direcao != 'D')
                    cobrinha.direcao = 'E';
                break;

            case KeyEvent.VK_RIGHT:
                if (cobrinha.direcao != 'E')
                    cobrinha.direcao = 'D';
                break;
            case KeyEvent.VK_UP:
                if (cobrinha.direcao != 'B')
                    cobrinha.direcao = 'C';
                break;
            case KeyEvent.VK_DOWN:
                if (cobrinha.direcao != 'C')
                    cobrinha.direcao = 'B';
                break;
            default:
                break;
        }
        jogo.Mutex.release();
    }
}
