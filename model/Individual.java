package model;

/**
 * Individual class represents an individual with a state, the time spent in that state,
 * and associated parameters.
 */
public class Individual 
{

    private State 		state; // Current state of the individual
    private int 		timeInState; // Time the individual has spent in the current state
    private Parameters 	parameters; // Parameters associated with the individual

    /**
     * Constructs an Individual with a given initial state and parameters.
     *
     * @param initialState The initial state of the individual.
     * @param parameters   The parameters associated with the individual.
     */
    public Individual(State initialState, Parameters parameters) 
    {
        this.state = initialState;
        this.timeInState = 0; // Initializing time in state to 0
        this.parameters = parameters;
    }
    
    /**
     * Returns the current state of the individual.
     *
     * @return The current state.
     */
    public State getState() 
    {
        return state;
    }

    /**
     * Sets the state of the individual and resets the time in state to 0.
     *
     * @param state The new state to set.
     */
    public void setState(State state) 
    {
        this.state = state;
        this.timeInState = 0; // Resetting time in state upon state change
    }
    
    /**
     * Returns the time the individual has spent in the current state.
     *
     * @return The time in the current state.
     */
    public int getTimeInState() 
    {
        return timeInState;
    }
    
    /**
     * Sets the time the individual has spent in the current state.
     *
     * @param timeInState The new time in state.
     * @return The updated time in state.
     */
    public int setTimeInState(int timeInState) 
    {
        this.timeInState = timeInState;
        return this.timeInState;
    }
    
    /**
     * Returns the parameters associated with the individual.
     *
     * @return The parameters of the individual.
     */
    public Parameters getParameters() 
    {
        return parameters;
    }
    
}
