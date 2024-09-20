import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Tela {
    boolean ligado = true;
    JTextField texto;
    JTextField texto2;
    int x = -150;
    JLabel xxx;
    boolean parado = true;
    private Carro carro;
    private Timer timer;
    String img2;
    String img;
    float velocidade = 0;
    boolean acelerando = false;  
    JLabel km;

    public Tela(Carro carro) {
        this.carro = carro;
        JFrame tela = new JFrame();
        tela.setSize(500, 500);
        tela.setResizable(false);
        tela.setLocation(1400, 0);
        tela.setDefaultCloseOperation(tela.EXIT_ON_CLOSE);
        tela.setLayout(null);

        texto2 = new JTextField("CARRO PARADO.");
        texto2.setBounds(140, 130, 200, 30);
        tela.add(texto2);

        texto = new JTextField("CARRO DESLIGADO.");
        texto.setBounds(140, 100, 200, 30);
        tela.add(texto);

        JLabel label1 = new JLabel("SISTEMA DO CARRO");
        label1.setBounds(130, 0, 250, 50);
        label1.setFont(new Font("ARIAL", Font.BOLD, 20));

        JButton botao1 = new JButton("LIGAR");
        botao1.setBounds(20, 350, 100, 40);
        botao1.setBackground(new Color(20, 225, 0));
        botao1.setForeground(new Color(255, 255, 255));
        botao1.setFont(new Font("ARIAL", Font.BOLD, 20));

        tela.add(label1);
        tela.add(botao1);

        JButton botao2 = new JButton("ACELERAR");
        botao2.setBounds(200, 350, 200, 40);
        botao2.setBackground(new Color(255, 0, 0));
        botao2.setForeground(new Color(255, 255, 255));
        botao2.setFont(new Font("ARIAL", Font.BOLD, 20));

        JButton botao3 = new JButton("PARAR");
        botao3.setBounds(200, 400, 120, 40);
        botao3.setBackground(new Color(20, 0, 255));
        botao3.setForeground(new Color(255, 255, 255));
        botao3.setFont(new Font("ARIAL", Font.BOLD, 20));

        texto.setFont(new Font("ARIAL", Font.BOLD, 12));
        texto2.setFont(new Font("ARIAL", Font.BOLD, 12));

        tela.add(botao3);
        tela.add(botao2);
        botao1.addActionListener(this::ligar);
        botao2.addActionListener(this::acelerar);
        botao3.addActionListener(this::frear);

        tela.getContentPane().setBackground(new Color(2, 56, 50));
        img2 = "C:\\Users\\Pichau\\VSCODE\\javaaaaaaaaaaaaa\\src\\image2.png";
        img = "C:\\Users\\Pichau\\VSCODE\\javaaaaaaaaaaaaa\\src\\image.png";

        xxx = new JLabel(new ImageIcon(img));
        xxx.setBounds(x, 100, 600, 300);
        tela.add(xxx);
        km = new JLabel((int)velocidade + "km/h");
        km.setBounds(10, 150, 700, 70);
        km.setForeground(new Color(255, 255, 255));
        km.setFont(new Font("ARIAL", Font.BOLD, 40));
        tela.add(km);

        tela.setVisible(true);
    }

    private void ligar(ActionEvent actionevent1) {
        carro.ligar();
        if (ligado) {
            texto.setText("CARRO LIGADO!!!!.");
            xxx.setIcon(new ImageIcon(img2));
            xxx.setBounds(x, 100, 600, 300);
            ligado = false;
        } else {
            ligado = true;
            acelerando = false; 
            parado = true;
            iniciarTimer();
            texto.setText("CARRO DESLIGADO!!!!.");
            texto2.setText("CARRO PARADO!!!.");
            xxx.setIcon(new ImageIcon(img));
            xxx.setBounds(x, 100, 600, 300);
        }
    }

    private void iniciarTimer() {
        if (timer == null || !timer.isRunning()) {
            timer = new Timer(20, e -> {
                // Aceleração
                if (acelerando) {
                    velocidade += 0.1; 
                    x += velocidade;
                    km.setText((int)velocidade + "km/h");
                } 
                // Frenagem
                else if (velocidade > 0) {
                    velocidade -= 0.2; 
                    km.setText((int)velocidade + "km/h");
                    if (velocidade < 0) {
                        velocidade = 0;
                        
                    }
                    x += velocidade; 
                }

                xxx.setBounds(x, 100, 600, 300);

                if (x > 330) {
                    x = -500;
                    xxx.setBounds(x, 100, 600, 300);
                }

                System.out.println((int) velocidade + "km/h");
            });
            timer.start();
        }
    }

    private void frear(ActionEvent actionevent1) {
        if (!ligado && !parado && velocidade > 0) {
            carro.frear();
            texto2.setText("CARRO FREOU!");
            acelerando = false; 
            parado = true;
            iniciarTimer();
        } else {
            System.out.println("Carro já está parado ou desligado.");
        }
    }

    private void acelerar(ActionEvent actionevent1) {
        if (!ligado) {
            texto2.setText("CARRO ACELEROU!");
            carro.acelerar();
            parado = false;
            acelerando = true; 
            iniciarTimer();
        } else {
            System.out.println("Carro está desligado.");
        }
    }
}
