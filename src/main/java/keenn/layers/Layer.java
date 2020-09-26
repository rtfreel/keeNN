package keenn.layers;

import keenn.Matrix;
import keenn.neurons.Neuron;
import keenn.functions.IFunction;
import keenn.functions.Sigmoid;

/**
 * Layer is simple implementation of ILayer interface.
 * Every neuron is connected neurons with every neuron in the next layer
 * @author Tkachenko Roman
 */
public class Layer{
    //contains neurons in this layer
    Neuron[] neurons;
    //contains synapses leadin to every neuron in the next layer
    Matrix synapses;
    //contains activation function to be used for every neuron in this layer
    IFunction function;
    //contains amount of neurons on this layer
    int size;

    /**
     * Creaters default empty Layer object
     */
    Layer(){
        this.size = 0;
        this.function = new Sigmoid();
    }
    
    /**
     * Creates new Layer object with specified amount of neurons
     * @param size amount of neurons
     */
    Layer(int size){
        this.neurons = new Neuron[size];
        this.function = new Sigmoid();
    }

    /**
     * Sets input value for every neuron.
     * If input size is smaller than neurons amount extra neurons has zero as an input
     * @param input Matrix object, only first row is matters, as a vector 
     */
    public void setInput(Matrix input){
        int row = 0;
        int values = Math.min(this.size, input.getColumns());
        for(int val = 0; val < values; val++){
            this.neurons[val].setInput(input.getVal(row, val));
        }
    }

    /**
     * Sets activation function to be used for every neuron in this layer 
     * (Sigmoid by default)
     * @param function activatin function object
     */
    public void setFunction(IFunction function){
        this.function = function;
    }

    /**
     * Sets zero as a value for every neuron in the layer
     */
    public void clear(){
        updateSize();
        for(int neuron = 0; neuron < this.size; neuron++){
            this.neurons[neuron].setInput(0.0f);
        }
    }

    /**
     * Updates amount of neurons
     */
    private void updateSize(){
        this.size = this.neurons.length;
    }

    /**
     * Returns amount of neurons
     * @return layer size
     */
    public int getSize(){
        updateSize();
        return this.size;
    }
}