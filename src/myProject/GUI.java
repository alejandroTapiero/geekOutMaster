package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
import java.awt.event.*;

/**
 * This class is used for ...
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:21/11/2021
 */
public class GUI extends JFrame {

    public static final String MENSAJE_INICIO = "Bienvenido a Geek Out Master\n" +
            "El objetivo de este juego es consegir la mayor cantiad de puntos juntando\n" +
            "dados, cuya cara visible es la cara 42.\n" +
            "Geek Out Masters no es solo suerte, tambien importa la estrategia ya que\n" +
            "una vez que se lanzan los dados TODAS las caras deberan ejecutarse: Los\n" +
            "Meeples permiten relanzar los dados, las Naves Espaciales los eliminaran\n" +
            "y los Superheroes revelaran su lado oculto. Por suerte, los Corazones nos brindaran\n" +
            "un dado extra, pero tambien estan los dragones, quienes causan perdida. El juego\n" +
            "está compuesto por: ¡10 dados de Geek Out!.\n\n"+
            "DINAMICA DEL JUEGO:\n" +
            "De los 10 dados que trae el juego se toman 3 y se colocan en el sector de 'Dados inactivos'.\n" +
            "Los otros 7 dados se tiran y pasan a ser los 'Dados activos'.\n" +
            "se van eligiendo los dados a utilizar segun las habilidades de sus caras y se pasan\n" +
            "al sector de 'Dados utilizados'.\n" +
            "Si como ultimo dado activo queda un dragon, se perderan todos los puntos acumulados.\n" +
            "Este juego lo jugará un unico jugador y ganará si logra sumar 30 pts en 5 rondas\n" +
            "consecutivas del juego.";

    private Header headerProject;
    private JLabel dado1, dado2, dado3, dado4, dado5, dado6, dado7, dado8, dado9, dado10;
    private JPanel pActivos, pInactivos, pUtilizados, pPuntaje;
    private JButton lanzar, ayuda, salir; //carasOpuestas
    private ImageIcon imageDado, imageBoton, imagePrimerDado;
    private JTextArea mensajePorRonda;
    private Escucha escucha;
    private ModelGOM modelGOM;
    private JugarPartida jugarPartida;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Geek Out Masters");
        this.setSize(600,600);
        this.setUndecorated(true);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //Create Listener Object and Control Object
        escucha = new Escucha();
        modelGOM = new ModelGOM();
        jugarPartida = new JugarPartida();

        //Set up JComponents
        headerProject = new Header("Geek Out Masters", Color.BLACK);
        headerProject.setFont(new Font("Monospaced",Font.BOLD,20));
        headerProject.setBackground(Color.YELLOW);
        headerProject.setForeground(Color.BLACK);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        this.add(headerProject, constraints);

        //this.add(headerProject,BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout

        imagePrimerDado = new ImageIcon(getClass().getResource("/resources/Dado.png"));
        dado1 = new JLabel(imagePrimerDado);
        dado2 = new JLabel(imagePrimerDado);
        dado3 = new JLabel(imagePrimerDado);
        dado4 = new JLabel(imagePrimerDado);
        dado5 = new JLabel(imagePrimerDado);
        dado6 = new JLabel(imagePrimerDado);
        dado7 = new JLabel(imagePrimerDado);
        dado8 = new JLabel(imagePrimerDado);
        dado9 = new JLabel(imagePrimerDado);
        dado10 = new JLabel(imagePrimerDado);

        dado1.addMouseListener(escucha);
        dado2.addMouseListener(escucha);
        dado3.addMouseListener(escucha);
        dado4.addMouseListener(escucha);
        dado5.addMouseListener(escucha);
        dado6.addMouseListener(escucha);
        dado7.addMouseListener(escucha);
        dado8.addMouseListener(escucha);
        dado9.addMouseListener(escucha);
        dado10.addMouseListener(escucha);

        ayuda = new JButton("Help");
        ayuda.setFont(new Font("Monospaced",Font.BOLD,13));
        ayuda.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_START;
        this.add(ayuda, constraints);

        salir = new JButton("Exit");
        salir.setFont(new Font("Monospaced",Font.BOLD,13));
        salir.addActionListener(escucha);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_END;
        this.add(salir,constraints);


        lanzar = new JButton("Lanzar");
        lanzar.setFont(new Font("Monospaced",Font.BOLD,13));
        lanzar.addActionListener(escucha);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(lanzar, constraints);

        pActivos = new JPanel();
        pActivos.setPreferredSize(new Dimension(250, 180));
        pActivos.setBorder(BorderFactory.createTitledBorder("Dados activos: "));
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(pActivos, constraints);
        pActivos.setBackground(new Color(255, 255, 255, 0));
        pActivos.setOpaque(false);

        pActivos.add(dado1);
        pActivos.add(dado2);
        pActivos.add(dado3);
        pActivos.add(dado4);
        pActivos.add(dado5);
        pActivos.add(dado6);
        pActivos.add(dado7);
        pActivos.add(dado8);
        pActivos.add(dado9);
        pActivos.add(dado10);

        pInactivos = new JPanel();
        pInactivos.setBorder(BorderFactory.createTitledBorder("Dados inactivos: "));
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(pInactivos, constraints);
        pInactivos.setPreferredSize(new Dimension(300, 150));
        pInactivos.setBackground(new Color(255, 255, 255, 0));

        pInactivos.add(dado1);
        pInactivos.add(dado2);
        pInactivos.add(dado3);

        pUtilizados = new JPanel();
        pUtilizados.setPreferredSize(new Dimension(300, 150));
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(pUtilizados, constraints);
        pUtilizados.setBorder(BorderFactory.createTitledBorder("Dados utilizados: "));
        pUtilizados.setBackground(new Color(255, 255, 255, 0));

        pPuntaje = new JPanel();
        pPuntaje.setPreferredSize(new Dimension(300, 150));
        constraints.gridx = 1;//2
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        this.add(pPuntaje, constraints);
        pPuntaje.setBorder(BorderFactory.createTitledBorder("Tu puntaje: "));

        mensajePorRonda = new JTextArea();
        mensajePorRonda.setBorder(BorderFactory.createTitledBorder("Ronda numero: " + jugarPartidaronda()));
        pPuntaje.setPreferredSize(new Dimension(150, 90));
        mensajePorRonda.setEditable(false);
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(mensajePorRonda, constraints);
        mensajePorRonda.setBackground(new Color(255, 255, 255, 0));
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }
    /*
        /**
         * inner class that extends an Adapter Class or implements Listeners used by GUI class
         */
    private class Escucha implements ActionListener, MouseListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == ayuda){
                JOptionPane.showMessageDialog(null, MENSAJE_INICIO);
            }else{
                if(e.getSource() == lanzar){
                    int[] caraDados = modelGOM.getCaras();
                    modelGOM.calcularCara();
                    imageDado = new ImageIcon(getClass().getResource("/resources/" + caraDados[0] + ".png"));
                    dado1.setIcon(imageDado);
                    imageDado = new ImageIcon(getClass().getResource("/resources/" + caraDados[1] + ".png"));
                    dado2.setIcon(imageDado);
                    imageDado = new ImageIcon(getClass().getResource("/resources/" + caraDados[2] + ".png"));
                    dado3.setIcon(imageDado);
                    imageDado = new ImageIcon(getClass().getResource("/resources/" + caraDados[3] + ".png"));
                    dado4.setIcon(imageDado);
                    imageDado = new ImageIcon(getClass().getResource("/resources/" + caraDados[4] + ".png"));
                    dado5.setIcon(imageDado);
                    imageDado = new ImageIcon(getClass().getResource("/resources/" + caraDados[5] + ".png"));
                    dado6.setIcon(imageDado);
                    imageDado = new ImageIcon(getClass().getResource("/resources/" + caraDados[6] + ".png"));
                    dado7.setIcon(imageDado);
                    imageDado = new ImageIcon(getClass().getResource("/resources/" + caraDados[7] + ".png"));
                    dado8.setIcon(imageDado);
                    imageDado = new ImageIcon(getClass().getResource("/resources/" + caraDados[8] + ".png"));
                    dado9.setIcon(imageDado);
                    imageDado = new ImageIcon(getClass().getResource("/resources/" + caraDados[9] + ".png"));
                    dado10.setIcon(imageDado);
                }else{
                    if(e.getSource() == salir){
                        System.exit(0);
                    }
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() != pInactivos){
                if (e.getSource() == dado1) {
                    dado1.addMouseListener(escucha);
                    pActivos.remove(dado1);
                    pUtilizados.add(dado1);
                    //pInactivos.removeAll();
                    revalidate();
                    repaint();
                }else{
                    if(e.getSource() == dado2 && e.getClickCount() == 2) {
                        dado1.addMouseListener(escucha);
                        pActivos.remove(dado2);
                        pUtilizados.add(dado2);

                        revalidate();
                        repaint();
                    }else{
                        if(e.getSource() == dado3 && e.getClickCount() == 2){
                            dado1.addMouseListener(escucha);
                            pActivos.remove(dado3);
                            pUtilizados.add(dado3);
                            revalidate();
                            repaint();
                        }else{
                            if(e.getSource() == dado4 && e.getClickCount() == 2){
                                dado1.addMouseListener(escucha);
                                pActivos.remove(dado4);
                                pUtilizados.add(dado4);
                                revalidate();
                                repaint();
                            }else{
                                if(e.getSource() == dado5 && e.getClickCount() == 2){
                                    dado1.addMouseListener(escucha);
                                    pActivos.remove(dado5);
                                    pUtilizados.add(dado5);
                                    revalidate();
                                    repaint();
                                }else{
                                    if(e.getSource() == dado6 && e.getClickCount() == 2){
                                        dado1.addMouseListener(escucha);
                                        pActivos.remove(dado6);
                                        pUtilizados.add(dado6);
                                        revalidate();
                                        repaint();
                                    }else{
                                        if(e.getSource() == dado7 && e.getClickCount() == 2){
                                            dado1.addMouseListener(escucha);
                                            pActivos.remove(dado7);
                                            pUtilizados.add(dado7);
                                            revalidate();
                                            repaint();
                                        }else{
                                            if(e.getSource() == dado8 && e.getClickCount() == 2){
                                                dado1.addMouseListener(escucha);
                                                pActivos.remove(dado8);
                                                pUtilizados.add(dado8);
                                                revalidate();
                                                repaint();
                                            }else{
                                                if(e.getSource() == dado9 && e.getClickCount() == 2){
                                                    dado1.addMouseListener(escucha);
                                                    pActivos.remove(dado9);
                                                    pUtilizados.add(dado9);
                                                    revalidate();
                                                    repaint();
                                                }else{
                                                    if(e.getSource() == dado10 && e.getClickCount() == 2) {
                                                        dado1.addMouseListener(escucha);
                                                        pActivos.remove(dado10);
                                                        pUtilizados.add(dado10);
                                                        revalidate();
                                                        repaint();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                revalidate();
                repaint();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
