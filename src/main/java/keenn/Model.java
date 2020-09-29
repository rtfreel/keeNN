package keenn;

import keenn.layers.Layer;
import keenn.layers.ILayer;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Model objects store neural networks itselves which are able to run and learn
 * @author Tkachenko Roman
 */
public class Model {
    //contains layers in this model
    private ArrayList<ILayer> layers;

    /**
     * Creates default Model object
     */
    public Model(){
        this.layers = new ArrayList<>();
    }

    /**
     * Runs model layer by layer and returns output of the last one
     */
    public Matrix run(){
        for(int layer = 0; layer < this.layers.size() - 1; layer++){
            this.layers.get(layer + 1).setInput(this.layers.get(layer).solve());
            this.layers.get(layer + 1).activate();
        }
        return this.layers.get(this.layers.size() - 1).getOutput();
    }

    /**
     * Sets a Matrix object as an input for the first (input) layer
     * @param matrix a Matrix object
     */
    public void setInput(Matrix matrix){
        if(this.layers.size() > 0){
            this.layers.get(0).setInput(matrix);
        }
    }

    /**
     * Adds new layer to the model
     * @param layer
     */
    public void addLayer(ILayer layer){
        if(layer != null){
            if(this.layers.size() > 0){
                this.layers.get(this.layers.size() - 1).setOutputSize(layer.getSize());
            }
            this.layers.add(layer);
        }
    }
    
    /**
     * Adds one or multiple layers to the model
     * @param layers
     */
    public void addLayers(ILayer...layers){
        for(int layer = 0; layer < layers.length; layer++){
            if(layers[layer] != null){
                if(this.layers.size() > 0){
                    this.layers.get(this.layers.size() - 1).setOutputSize(layers[layer].getSize());
                }
                this.layers.add(layers[layer]);
            }
        }
    }

    /**
     * Sets new layer object into specific position.
     * If specified position is bigger than amount of layers in the model 
     * it will add default Layer objects till that position 
     * @param layer ILayer object
     * @param position
     */
    public void setLayer(ILayer layer, int position){
        if(this.layers.size() > position){
            this.layers.remove(position);
            this.layers.add(position, layer);
        }
        while(this.layers.size() <= position){
            if(this.layers.size() == position){
                this.layers.add(layer);
            }else{
                this.layers.add(new Layer());
            }
        }
    }

    /**
     * Returns layer at specified position
     * @param position
     * @return
     */
    public ILayer getLayer(int position){
        return this.layers.get(position);
    }

    /**
     * Sets multiple layers into the model
     * @param layers an ArrayList<ILayer> object
     */
    public void setLayers(ArrayList<ILayer> layers){
        this.layers = layers;
    }

    /**
     * Sets multiple layers into the model
     * @param layers an array of Layer objects
     */
    public void setLayers(Layer[] layers){
        Collections.addAll(this.layers, layers);
    }

    /**
     * Returns ArrayList of layers in the model
     * @return an ArrayList<ILayer> object with layers
     */
    public ArrayList<ILayer> getLayers(){
        return this.layers;
    }

    /**
     * Randomizes every layer apart of output one
     */
    public void randomize(){
        for(int layer = 0; layer < this.layers.size() - 1; layer++){
            this.layers.get(layer).randomize();
        }
    }
}
