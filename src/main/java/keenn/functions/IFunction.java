package keenn.functions;

/**
 * IFunction is an interface for every activation function implemented and other formulas
 * @author Tkachenko Roman
 */
public interface IFunction{
    /**
     * Calculates value according to special formula
     * @param argument
     * @return result value
     */
    public float calc(float arg);
}