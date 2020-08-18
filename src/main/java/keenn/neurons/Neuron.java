package keenn.neurons;

/**
 * Neuron is container for signals in neural network
 * @author Tkachenko Roman
 */
public class Neuron{
    //current signal of neuron
    float signal;

    /**
     * Activates neuron with chosen activation function
     * @param function (implementation of IActivationFunction)
     */
    public void activate(keenn.functions.IFunction function){
        signal = function.calc(signal);
    }

    /**
     * Sets input signal to a neuron
     * @param input
     */
    public void setInput(float input){
        this.signal = input;
    }

    /**
     * Returns value of current signal
     * @return signal
     */
    public float getOutput(){
        return this.signal;
    }
}