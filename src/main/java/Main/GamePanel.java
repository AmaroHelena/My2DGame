package Main;

import Entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


/**
 * Painel principal do jogo. Gerencia a resolução da tela, entrada de dados,
 * e implementa o Game Loop principal através da interface Runnable.
 */
public class GamePanel extends JPanel implements Runnable {

    // --- CONFIGURAÇÕES DE DIMENSÃO E ESCALA (SISTEMA DE TILES) ---
    final int originalTileSize = 16; // Tamanho base do sprite (16x16 pixels)
    final int scale = 3;             // Fator de escala para telas modernas

    // Resolução final de cada Tile: 48x48 pixels
   public final int tileSize = originalTileSize * scale; 
    
    // Configuração da matriz de exibição (Proporção 4:3)
    final int maxScreenCol = 16; 
    final int maxScreenRow = 12; 
    
    // Resolução total da janela do jogo (768x576 pixels)
    final int screenWidth = tileSize * maxScreenCol;  
    final int screenHeight = tileSize * maxScreenRow; 

    // --- CONTROLE DE TEMPO E ENTRADA ---
    int FPS = 60;
    KeyHandler keyH = new KeyHandler(); // Gerenciador de eventos de teclado
    
    // Thread dedicada para execução do Game Loop de forma assíncrona à EDT do Swing
    Thread gameThread;
   
    
    // --- INSTÂNCIA DA ENTIDADE DO JOGADOR ---
    // CORREÇÃO: Declarando o objeto player que será gerenciado pelo painel
    Player player = new Player(this, keyH);
  
    
    // --- ATRIBUTOS DO JOGADOR (ESTADO) ---
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    
    /**
     * Construtor: Inicializa os componentes físicos e propriedades de renderização do painel.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Otimiza a renderização, evitando flickering (cintilação)
        this.addKeyListener(keyH);     // Registra o escutador de teclas
        this.setFocusable(true);       // Permite que o painel receba o foco de entrada do teclado
    }

    /**
     * Inicializa e dispara a Thread principal do jogo.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); // Invoca implicitamente o método run()
    }

    @Override
    /**
     * Implementação do Game Loop utilizando o método "Delta Time / Accumulator".
     * Garante atualizações de estado e renderizações sincronizadas à taxa de FPS desejada.
     */
    public void run() {
        double drawInterval = 1000000000 / FPS; // Intervalo de tempo por quadro em nanossegundos
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime; 
        long timer = 0;
        int drawCount = 0;
        
        while (gameThread != null) {
            currentTime = System.nanoTime();
            
            // Acumula o tempo decorrido fracionado pelo intervalo do alvo de FPS
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            // Quando o acumulador atinge 1, uma atualização física e gráfica é executada
            if (delta >= 1) {
                update();   // 1. Atualiza a lógica do jogo
                repaint();  // 2. Solicita a renderização na tela (chama paintComponent)
                delta--;
                drawCount++;
            }
            
            // Monitor de desempenho: Exibe a taxa real de FPS a cada segundo no console
            if (timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
     
    /**
     * Processa a lógica de negócios e atualiza os estados (coordenadas) dos elementos do jogo.
     */
    public void update() {
         
    player.update();
        
    }
    
  @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        Graphics2D g2 = (Graphics2D) g;
        
        // CORREÇÃO: Agora 'player' existe e pode ser desenhado perfeitamente
        player.draw(g2);
       
        g2.dispose(); 
    }
}