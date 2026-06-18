package Main;

import javax.swing.JFrame;

/**
 *
 * @author Helena Amaro
 */
public class Main {

    public static void main(String[] args) {

        // Instancia o contêiner principal da interface gráfica (Janela nativa do sistema operacional)
        JFrame window = new JFrame();
        
        // Define o comportamento padrão de fechamento: encerra o processo da JVM (Java Virtual Machine) 
        // imediatamente quando o usuário clica no botão "X" de fechar
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Desabilita o redimensionamento manual da janela pelo usuário, garantindo a consistência 
        // da proporção lógica dos elementos visuais e da matriz de renderização (resolução fixa)
        window.setResizable(false);
        
        // Define o texto que será exibido na barra de título da janela
        window.setTitle("2D Adventure");

        // Instancia o painel customizado que contém o loop do jogo, lógica de atualização (update) e renderização (draw)
        GamePanel gamePanel = new GamePanel();
        
        // Adiciona o componente GamePanel (que herda de JPanel) ao contêiner interno (Content Pane) do JFrame
        window.add(gamePanel);

        // Ajusta automaticamente o tamanho do JFrame para que ele se molde perfeitamente 
        // ao tamanho preferido (PreferredSize) dos componentes internos (neste caso, o GamePanel de 768x576 pixels)
        window.pack();

        // Posiciona a janela em relação a um componente. Ao passar 'null', o Java centraliza 
        // a janela automaticamente no meio da tela do monitor do usuário. 
        // NOTA: Deve ser chamado obrigatoriamente APÓS o método pack() para calcular a posição corretamente baseada no tamanho final
        window.setLocationRelativeTo(null);
        
        // Torna a janela e seus subcomponentes visíveis na tela, iniciando a exibição da interface gráfica
        window.setVisible(true);

    }
}