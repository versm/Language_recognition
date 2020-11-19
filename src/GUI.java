import javax.swing.*;

public class GUI {

    NeuralNetwork neuralNetwork;

    public GUI(NeuralNetwork neuralNetwork){

        this.neuralNetwork = neuralNetwork;
        SwingUtilities.invokeLater(()->create());

    }
    void create(){

        JFrame jFrame= new JFrame("Language guessing");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(700,500);
        jFrame.setLocation(100,100);
        jFrame.setVisible(true);

        JPanel jPanel= new JPanel();
        jPanel.setSize(700,500);
        jPanel.setLayout(null);


        JLabel jLabel1 = new JLabel("Enter text");
        jLabel1.setBounds(10,10,70,20);
        jPanel.add(jLabel1);

        JTextArea jTextArea = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setBounds(10,40,600,300);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jPanel.add(jScrollPane);

        JButton jButton = new JButton("Guess the language");
        jButton.setBounds(10,350,170,20);
        jPanel.add(jButton);


        JLabel jLabel2 = new JLabel();
        jLabel2.setBounds(10,380,200,20);
        jPanel.add(jLabel2);

        jButton.addActionListener(e -> {
            String result = getLanguage(jTextArea.getText());
            jLabel2.setText(result);
        });

        jFrame.add(jPanel);

    }


    public String getLanguage(String text){

        text=text.trim();
        StringBuilder stringBuilder = new StringBuilder(text);

        text=TextEditing.deleteNonASCII(stringBuilder);
        String vector= TextEditing.countCharacters(text);

        String[] tmp = vector.split(" ");
        double[] values = new double[tmp.length];

        for (int i = 0; i < values.length; i++)
            values[i] = Double.valueOf(tmp[i]);

        return neuralNetwork.classifyLanguage(values);

    }
}
