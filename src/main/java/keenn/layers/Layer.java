package keenn.layers;

import keenn.Matrix;
import keenn.neurons.Neuron;
import keenn.functions.Sigmoid;
import keenn.functions.IFunction;
import keenn.exceptions.MatrixDifferentSizeException;

/**
 * Layer is simple implementation of ILayer interface.
 * Every neuron is connected neurons with every neuron in the next layer
 * @author Tkachenko Roman
 */
public class Layer{
    //contains neurons in this layer
    Neuron[] neurons;
    //contains weights of synapses leading to every neuron in the next layer
    Matrix weights;
    //contains activation function to be used for every neuron in this layer
    IFunction function;
    //contains amount of neurons in this layer
    int size;
    //contains amount of neurons in the next layer
    int outputSize;

    /**
     * Creaters default empty Layer object
     */
    public Layer(){
        this.createNeurons(0);
        this.outputSize = 0;
        this.function = new Sigmoid();
    }
    
    /**
     * Creates new Layer object with specified amount of neurons
     * @param size amount of neurons
     */
    public Layer(int size){
        this.createNeurons(size);
        this.outputSize = 0;
        this.function = new Sigmoid();
    }

    /**
     * Performs calculations to move forward to the next layer
     * @return Matrix object with the vector of signals going to the next layer
     */
    public Matrix solve(){
        Matrix result = null;
        result = Matrix.solve(weights, this.toVector());
        return result;
    }

    /**
     * Activates every neuron in the layer or applies specified function
     */
    public void activate(){
        this.function.prepare(this.neurons);
        for(int neuron = 0; neuron < this.size; neuron++){
            this.neurons[neuron].activate(this.function);
        }
    }

    /**
     * Sets input value for every neuron.
     * If input size is smaller than neurons amount extra neurons has zero as an input
     * @param input Matrix object, only first row is matters, as a vector 
     */
    public void setInput(Matrix input) throws MatrixDifferentSizeException{
        int col = 0;
        int values = Math.min(this.size, input.getRows());
        for(int val = 0; val < values; val++){
            this.neurons[val].setInput(input.getVal(val, col));
        }
    }

    /**
     * Returns Matrix object with a vector of current signals of every neuron
     * @return Matrix object with a vector
     */
    public Matrix getNeuronsOutput(){
        Matrix result = new Matrix(this.size, 1);
        for(int neuron = 0; neuron < this.size; neuron++){
            result.setVal(neuron, 0, this.neurons[neuron].getOutput());
        }
        return result;
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
     * Returns weight value of specified synapse
     * @param from neuron where synapse begins
     * @param to neuron where synapse ends
     */
    public float getWeight(int from, int to){
        return this.weights.getVal(from, to);
    }

    /**
     * Sets weight value for specified synapse
     * @param from neuron where synapse begins
     * @param to neuron where synapse ends
     * @param value weight value needed
     */
    public void setWeight(int from, int to, float value){
        this.weights.setVal(from, to, value);
    }

    /**
     * Returns Matrix object with weights of synapces in this layer
     * @return
     */
    public Matrix getWeights(){
        return this.weights;
    }

    /**
     * Sets all new weights values for every synapse in the current layer.
     * Amount of rows in the weights matix should be the same with amount of neurons in the layer
     * @param weights Matrix object, that contains new weight values for every synapse
     */
    public void setWeights(Matrix weights) throws MatrixDifferentSizeException{
        if(size == 0){
            this.createNeurons(weights.getRows());
            this.weights = weights;
        }else if(this.size == weights.getRows() && (this.outputSize == 0 || this.outputSize == weights.getColumns())){
            this.weights = weights;
        }else{
            throw new MatrixDifferentSizeException("Matrix should have as many rows as many neurons is in that layer");
        }
    }

    public void setNeurons(Neuron[] neurons){
        if(neurons == null){
            this.neurons = neurons;
        }else{
            this.createNeurons(neurons.length);
            for(int neuron = 0; neuron < this.size; neuron++){
                this.setNeuron(neurons[neuron], neuron);
            }
        }
    }

    /**
     * Returns array of neurons in the layer
     * @return
     */
    public Neuron[] getNeurons(){
        return this.neurons;
    }
    
    /**
     * Sets particular Neuron object into the layer at the specified position
     * @param neuron object to set
     * @param position
     */
    public void setNeuron(Neuron neuron, int position){
        if(this.size > position){
            if(neuron != null){
                this.neurons[position] = neuron;
            }else{
                this.neurons[position] = new Neuron();
            }
        }
    }

    /**
     * Returns Neuron object from particular position in the layer
     * @param position 
     * @return
     */
    public Neuron getNeuron(int position){
        return this.neurons[position];
    }

    /**
     * Sets amount of neurons in the next layer and creates new weights matrix with this parameter
     * @param outputSize amount of neurons in the next layer
     * @param randomize if true weights matrix will be full of random values
     */
    public void setOutputSize(int outputSize, boolean randomize){
        this.outputSize = outputSize;
        if(this.weights == null){
            try{
                this.setWeights(new Matrix(this.size, this.outputSize));
            }catch(MatrixDifferentSizeException mdse){
                mdse.printStackTrace();
            }
        }else{
            this.weights.createMatrix(this.size, this.outputSize);
        }
        if(randomize){
            this.weights.randomize();
        }
    }

    /**
     * Sets zero as a value for every neuron in the layer
     */
    public void clear(){
        updateSize();
        for(int neuron = 0; neuron < this.size; neuron++){
            this.neurons[neuron].setInput(0.0f);
        }
        this.weights.clear();
    }

    /**
     * Returns amount of neurons
     * @return layer size
     */
    public int getSize(){
        updateSize();
        return this.size;
    }

    /**
     * Collects signals of all neurons in the layer into the vector
     * @return Matrix object with the vector
     */
    public Matrix toVector(){
        Matrix result = new Matrix(this.size, 1);
        for(int row = 0; row < this.size; row++){
            result.setVal(row, 0, this.neurons[row].getOutput());
        }
        return result;
    }

    /**
     * Fills neurons array with objects
     * @param amount amount of neurons
     */
    private void createNeurons(int amount){
        this.neurons = new Neuron[amount];
        for(int neuron = 0; neuron < amount; neuron++){
            this.neurons[neuron] = new Neuron();
        }
        this.updateSize();
    }

    /**
     * Updates amount of neurons
     */
    private void updateSize(){
        this.size = this.neurons == null ? 0 : this.neurons.length;
    }
}