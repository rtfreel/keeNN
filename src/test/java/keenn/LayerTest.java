package keenn;

import org.junit.Test;
import keenn.Matrix;
import keenn.neurons.Neuron;
import keenn.layers.Layer;
import keenn.exceptions.MatrixDifferentSizeException;

import static org.junit.Assert.*;

public class LayerTest{
    float[][][] arrays = {
        {
            {0.1f, 0.5f}, 
            {0.2f, 0.6f},
            {0.3f, 0.7f}
        },

        {
            {1.0f, 5.0f, 2.0f},
            {3.0f, 7.0f, 4.0f}
        }
    };

    float[][][] vectors = {
        {
            {1.0f},
            {2.0f},
            {3.0f}
        },
        {
            {0.9f},
            {0.8f}
        }
    };

    @Test public void testLayerConstructor(){
        int testSize = 0;
        Layer l = new Layer();
        assertTrue("default layer shouldn't have any neurons", l.getSize() == testSize);
        testSize = 16;
        l = new Layer(testSize);
        assertTrue("new layer should have specified amount of neurons", l.getSize() == testSize);
    }

    @Test public void testLayerIO(){
        for(int test = 0; test < 2; test++){
            Matrix input = new Matrix(vectors[test]);
            Layer l = new Layer(vectors[test].length);
            try{
                l.setInput(input);
            }catch(MatrixDifferentSizeException mdse){
                fail("got an unexpected MatrixDifferentSizeException");
            }
            assertTrue("output matrix should be equals to the input one", input.equals(l.getNeuronsOutput()));
        }
    }

    @Test public void testLayerWeights(){
        for(int test = 0; test < 2; test++){
            try{
                Matrix neurons = new Matrix(vectors[test]);
                Matrix weights = new Matrix(arrays[test]);
                Layer l = new Layer(vectors[test].length);
                l.setInput(neurons);
                l.setWeights(weights);
                for(int row = 0; row < arrays[test].length; row++){
                    for(int col = 0; col < arrays[test][0].length; col++){
                        assertTrue("", arrays[test][row][col] == l.getWeight(row, col));
                    }
                }
            }catch(MatrixDifferentSizeException mdse){
                fail("got an unexpected MatrixDifferentSizeException");
            }
        }
        Layer l = new Layer(16);
        l.setOutputSize(8, false);
        for(int row = 0; row < 16; row++){
            for(int col = 0; col < 8; col++){
                assertTrue("every weight should be equal to 0", l.getWeight(row, col) == 0.0f);
            }
        }
    }

    @Test public void testLayerSetNeurons(){
        Layer l = new Layer(16);
        assertTrue("layer size should be 16", l.getSize() == 16);
        for(int test = 0; test < 2; test++){
            Neuron[] neurons = new Neuron[this.vectors[test].length];
            for(int neuron = 0; neuron < neurons.length; neuron++){
                neurons[neuron] = new Neuron(this.vectors[test][neuron][0]);
            }
            l.setNeurons(neurons);
            assertTrue("layer size should be equal to size of neurons array", l.getSize() == neurons.length);
            for(int neuron = 0; neuron < neurons.length; neuron++){
                assertTrue("output of every neuron should be equal to initial value", l.getNeuron(neuron).getOutput() == this.vectors[test][neuron][0]);
            }
            assertTrue("initial vector should be equal to vector from the layer", l.toVector().equals(new Matrix(this.vectors[test])));
        }
    }
}