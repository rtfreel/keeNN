package keenn.layers;

import keenn.Matrix;
import keenn.functions.IFunction;

/**
 * ILayer is an interface for every type of layer implemented
 * @author Tkachenko Roman
 */
public interface ILayer{
    /**
     * Sets input for neurons in the layer
     */
    public void setInput(Matrix input);
    
    /**
     * Returns Matrix object with a matrix of current signals of every neuron
     * @return
     */
    public Matrix getOutput();

    /**
     * Sets function for all neurons
     * @param function activation or another function object
     */
    public void setFunction(IFunction function);

    /**
     * Returns current IFunction object
     * @return IFunction object
     */
    public IFunction getFunction();

    /**
     * Activates neurons in the layer or applies function
     */
    public void activate();
    
    /**
     * Returns result of interaction between neurons and connections with the next layer
     * @return
     */
    public Matrix solve();

    /**
     * Randomizes different values in the layer
     */
    public void randomize();

    /**
     * Returns size of layer
     * @return layer size
     */
    public int getSize();
    
    /**
     * Sets size of the output layer
     * @param size
     */
    public void setOutputSize(int size);

    /**
     * Returns type of the layer
     * @return Type
     */
    public Type getType();
}