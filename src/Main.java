import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        NeuralNetwork neuralNetwork;
        List<Perceptron> languagePerceptron = new ArrayList<>();
        List<String> training_data = new ArrayList<>();

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = createTrainingFileOutputStream("training.txt");

            File folder = new File("treningowe");
            File[] listOfDirectories = folder.listFiles();

            for (File file : listOfDirectories) {
                if (file.isDirectory()) {
                    String language = file.getName();
                    List<String> vectors = readFiles(file);

                    //creating list with training data
                    for (String s : vectors)
                        training_data.add(s + language);

                    //writing training data do file
                    writeToFile(fileOutputStream, vectors, language);

                    Perceptron perceptron = new Perceptron(language);
                    languagePerceptron.add(perceptron);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //training
        for (Perceptron p : languagePerceptron)
            trainPerceptron(p, training_data);

        neuralNetwork = new NeuralNetwork(languagePerceptron);
        GUI gui = new GUI(neuralNetwork);

    }

    public static void trainPerceptron(Perceptron perceptron, List<String> training_data) {

        int count = 0;

        do {
            perceptron.stillLearning = false;
            for (String val : training_data) {

                String[] tmp = val.split(" ");
                String language = tmp[tmp.length - 1];
                double[] values = new double[tmp.length - 1];      //proportions of letters

                for (int i = 0; i < values.length; i++)
                    values[i] = Double.parseDouble(tmp[i]);

                perceptron.train(values, language);
            }
            count++;
        } while (perceptron.stillLearning && count < 500);

    }

    //reading files for a specific language and returning with letters proportions
    public static List<String> readFiles(File directoryName) throws IOException {

        File[] listOfFiles = directoryName.listFiles();
        List<String> vectors = new ArrayList<>();

        for (File f : listOfFiles) {
            if (f.isFile()) {

                BufferedReader br = new BufferedReader(new FileReader(directoryName + "\\" + f.getName()));
                StringBuilder sb = new StringBuilder();
                List<String> lines = new ArrayList<>();
                String line;

                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }

                for (String s : lines)
                    sb.append(s.trim());

                String text = TextEditing.deleteNonASCII(sb);
                String vector = TextEditing.countCharacters(text);
                vectors.add(vector);
            }
        }
        return vectors;
    }

    static FileOutputStream createTrainingFileOutputStream(String path) throws IOException {

        FileOutputStream fileOutputStream;

        Files.delete(Paths.get(path));
        fileOutputStream = new FileOutputStream(path, true);

        return fileOutputStream;
    }

    static void writeToFile(FileOutputStream fileOutputStream, List<String> vectors, String language) throws IOException {

        for (String s : vectors)
            fileOutputStream.write((s + " " + language + "\n").getBytes());
    }


}
