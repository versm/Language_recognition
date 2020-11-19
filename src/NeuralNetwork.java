import java.util.*;

public class NeuralNetwork {

    List<Perceptron> perceptrons;

    NeuralNetwork(List<Perceptron> list){
        perceptrons=list;
    }

    public String classifyLanguage(double [] values){

        Map<String,Double> map = new HashMap<>();

        for(Perceptron perceptron: perceptrons)
            map.put(perceptron.language,perceptron.check(values));

        List<Map.Entry<String,Double>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);

        String language=list.get(0).getKey();

        return language;

    }
}
