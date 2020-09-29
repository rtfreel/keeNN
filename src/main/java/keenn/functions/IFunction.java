package keenn.functions;

import keenn.neurons.Neuron;

/**
 * IFunction is an interface for every activation function implemented and other formulas
 * @author Tkachenko Roman
 */
public interface IFunction{
    /**
     * Prepares function object before applying it to a particular neuron
     * @param neurons array of neurons in the layer
     */
    public void prepare(Neuron[] neurons);

    /**
     * Calculates value according to special formula
     * @param argument
     * @return result value
     */
    public float calc(float arg);
}