package keenn.neurons;

/**
 * Neuron is container for signals in neural network
 * @author Tkachenko Roman
 */
public class Neuron{
    //current signal of neuron
    float signal;
    //individual number to modify signal
    float bias;

    /**
     * Creates default Neuron object
     */
    public Neuron(){
        this.signal = 0.0f;
        this.bias = 0.0f;
    }

    /**
     * Creates default Neuron object with initial signal specified
     */
    public Neuron(float signal){
        this.signal = signal;
        this.bias = 0.0f;
    }

    /**
     * Activates neuron with chosen activation function
     * @param function (implementation of IActivationFunction)
     */
    public void activate(keenn.functions.IFunction function){
        signal = function.calc(signal + bias);
    }

    /**
     * Sets bias value randomly between -1 and 1
     */
    public void setBias(){
        this.bias = ((float)Math.random() * 2.0f) - 1.0f;
    }

    /**
     * Sets bias value randomly between -limit1 and limit2
     * @param limit1
     * @param limit2
     */
    public void setBias(float limit1, float limit2){
        this.bias = ((float)Math.random() * (limit2 - limit1)) + limit1;
    }

    /**
     * 
     * @param bias value needed for this neuron
     */
    public void setBias(float bias){
        this.bias = bias;
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