package keenn.functions;

/**
 * Sigmoid is a simple activation function which gives result between 0 and 1
 * @author Tkachenko Roman
 */
public class Sigmoid implements IFunction{
    /**
     * Receives any value and returns value within [0, 1]
     * @param argument
     * @return value between 0 and 1
     */
    public float calc(float arg){
        return (float)(1 / (1 + Math.pow(Math.E, arg * (-1))));
    }
}