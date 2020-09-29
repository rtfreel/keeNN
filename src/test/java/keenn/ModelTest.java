package keenn;

import org.junit.Test;

import keenn.layers.Layer;

import static org.junit.Assert.*;

public class ModelTest {
    @Test public void testSomeModelMethod() {
        Model model = new Model();
        assertTrue("model should not be null", model!=null);
    }

    @Test public void testModelRun(){
        Model model = new Model();
	    Matrix inputs = new Matrix();
	    Layer input = new Layer(784);
	    Layer hidden1 = new Layer(16);
	    Layer hidden2 = new Layer(16);
	    Layer output = new Layer(10);
	    model.addLayer(input);
	    model.addLayer(hidden1);
	    model.addLayer(hidden2);
        model.addLayer(output);
        //or model.addLayers(input, hidden1, hidden2, output); but needs to be tested
	    model.randomize();
	    inputs.randomize(0.0f, 1.0f);
	    model.setInput(inputs);
	    Matrix result = model.run();
    }
}
