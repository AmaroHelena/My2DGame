package Main;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * Painel principal do jogo que gerencia a tela, as configurações de resolução,
 * e implementa a interface Runnable para criar e controlar a Thread principal do jogo (Game Loop).
 */
public class GamePanel extends JPanel implements Runnable {

    // Configurações de tamanho dos blocos (Tiles)
    final int originalTileSize = 16; // Tamanho base de cada sprite/tile (16x16 pixels) padrão de jogos retrô
    final int scale = 3;             // Fator de escala para adaptar a resolução a monitores modernos

    // Tamanho final do tile escalado: 16 * 3 = 48x48 pixels
    final int tileSize = originalTileSize * scale; 
    
    // Configurações da matriz da tela (Proporção 4:3)
    final int maxScreenCol = 16; // Quantidade de colunas horizontais de tiles
    final int maxScreenRow = 12; // Quantidade de linhas verticais de tiles
    
    // Dimensões totais da área de renderização do jogo
    final int screenWidth = tileSize * maxScreenCol;  // Largura final: 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // Altura final: 576 pixels

    // Thread principal do jogo. Responsável por manter o loop de execução 
    // em paralelo com a Thread de eventos da interface gráfica (EDT - Event Dispatch Thread)
    Thread gameThread;

    /**
     * Construtor da classe. Define as propriedades físicas e comportamentais do painel de desenho.
     */
    public GamePanel() {
        // Define a dimensão preferida do painel utilizando as constantes calculadas de resolução
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        
        // Define a cor de fundo padrão do painel como preta antes da renderização dos elementos do jogo
        this.setBackground(Color.black);
        
        // Ativa o mecanismo de Double Buffering. Faz com que todo o desenho seja processado em uma 
        // memória em segundo plano (off-screen buffer) antes de ser jogado na tela de uma vez, 
        // eliminando o efeito de cintilação (flickering) e rasgos na imagem (screen tearing).
        this.setDoubleBuffered(true);
    }

    /**
     * Instancia e inicializa o fluxo de execução assíncrono (Thread) dedicado ao jogo.
     */
    public void startGameThread() {
        // Cria um novo objeto Thread passando este painel ('this') como o alvo (Runnable) da execução
        gameThread = new Thread(this);
        
        // Inicia a execução da nova Thread em paralelo no processador. Internamente, 
        // o método start() invoca de forma automática o método run() implementado abaixo.
        gameThread.start();
    }

    /**
     * Ponto de entrada obrigatório da interface Runnable. É o corpo de execução da gameThread.
     * Aqui será implementado o "Game Loop" (Loop Principal), que dita o ritmo das atualizações e renderizações do jogo.
     */
    @Override
    public void run() {
        // TODO: Implementar a lógica do Game Loop (ex: Delta Time ou Sleep Method)
        // 1. UPDATE: Atualizar informações do jogo (como posições de personagens, física, etc.)
        // 2. DRAW: Desenhar os elementos atualizados na tela (renderização gráfica)
    }

}