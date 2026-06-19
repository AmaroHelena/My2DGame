package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Gerenciador de Entradas (Input Manager).
 * Implementa a interface KeyListener do Swing para monitorar e capturar 
 * os eventos físicos do teclado de forma assíncrona.
 */
public class KeyHandler implements KeyListener {
    
    // Flags booleanas de estado que expõem se as direções estão ativas ou não
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    /**
     * Invocado quando uma tecla é digitada (pressionada e solta) gerando um caractere Unicode.
     * Não utilizado no contexto de movimentação em tempo real.
     */
    public void keyTyped(KeyEvent e) {
        // Método obrigatório da interface, mantido vazio.
    }

    @Override
    /**
     * Invocado no momento exato em que uma tecla física é pressionada.
     * Altera o estado das flags para 'true' para sinalizar a intenção de movimento.
     */
    public void keyPressed(KeyEvent e) {
        
        // Obtém o código inteiro associado à tecla física pressionada
        int code = e.getKeyCode();
        
        // Mapeamento das teclas WASD
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    @Override
    /**
     * Invocado no momento exato em que o usuário solta uma tecla física.
     * Reseta as flags para 'false' para cessar o movimento imediatamente.
     */
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();
        
        // Desativação dos estados de movimento ao liberar as teclas WASD
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
