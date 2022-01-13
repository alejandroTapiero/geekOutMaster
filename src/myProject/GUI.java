package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for ...
 * @author Alejandro Tapiero Triana 202043737 alejandro.tapiero@correounivalle.edu.co
 * @version v.1.0.0 date:29/11/2021
 */
public class GUI extends JFrame {
    private static final String MENSAJE_INICIO="Bienvenido a Geek Out Master\n" +
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
    private JTextArea mensajeSalida;
    private ModelGOM modelGOM;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Geek Out Masters");
        //this.setSize(200,100);
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
        //Create Listener Object and Control Object
        modelGOM = new ModelGOM();
        //Set up JComponents
        headerProject = new Header("Header ...", Color.BLACK);
        this.add(headerProject,BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout

        mensajeSalida = new JTextArea(7,31);
        mensajeSalida.setText(MENSAJE_INICIO);
        //mensajesSalida.setBorder(BorderFactory.createTitledBorder("Que debes hacer"));
        JScrollPane scroll = new JScrollPane(mensajeSalida);
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

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            modelGOM.calcularTiro();
            int[] caras = modelGOM.getCaras();
            //put select icon dado

            modelGOM.determinarJuego();


            mensajeSalida.setRows(4);
            mensajeSalida.setText(modelGOM.getEstadotoString()[1]);
            revalidate();
            repaint();
        }
    }
}
