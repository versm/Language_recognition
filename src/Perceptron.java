public class Perceptron {

    double [] weights;
    double learningRate=0.05;
    double threshold;
    String language;
    boolean stillLearning =false;

    Perceptron(String language){

        this.language=language;
        weights= new double[26];

        for (int i = 0; i < weights.length; i++)
            weights[i]=Math.random()*10;

        threshold= Math.random()*10;
    }

    public void train(double[] values, String language){

        double s=0;
        int output;
        int desiredOutput;

        for (int i = 0; i < values.length; i++)
            s+=values[i]*weights[i];

        output= s >= threshold ? 1 : 0;
        desiredOutput = this.language.equals(language)? 1 : 0;

        while (((output!=1 && this.language.equals(language)) || (output!=0 && !this.language.equals(language)))){

            deltaRule(values,desiredOutput,output);

            s=0;
            for (int i = 0; i < values.length; i++)
                s+=values[i]*weights[i];

            output= s >= threshold ? 1 : 0;

        }
    }


    public void deltaRule(double [] values, int desiredOutput, int realOutput){

        stillLearning =true;

        for (int i = 0; i < weights.length; i++)
            weights[i]= weights[i]+(desiredOutput-realOutput)*learningRate*values[i];

        threshold= threshold+(desiredOutput-realOutput)*learningRate*1;
    }

    public double check(double [] values){

        double s=0;

        for (int i = 0; i < values.length; i++)
            s+=values[i]*weights[i];

        return s;
    }
}
