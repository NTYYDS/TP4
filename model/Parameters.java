package model;

import utils.random;

/**
 * Parameters class stores the parameters associated with an individual,
 * including durations for different states and location coordinates.
 */
public class Parameters 
{
    // Durations in various states based on negative exponential distribution
    private final int 	dE; // Duration in Exposed state
    private final int 	dI; // Duration in Infected state
    private final int 	dR; // Duration in Recovered state
    private int 		x; // X-coordinate of the individual's location
    private int 		y; // Y-coordinate of the individual's location

    /**
     * Constructs a Parameters object with specified location and randomly
     * generated durations for different states.
     *
     * @param x The initial x-coordinate of the location.
     * @param y The initial y-coordinate of the location.
     */
    public Parameters(int x, int y) 
    {
        this.dE = random.negExp(3);
        this.dI = random.negExp(7);
        this.dR = random.negExp(365);
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the duration in the exposed state.
     *
     * @return Duration in the exposed state.
     */
    public int getDE() 
    {
        return dE;
    }

    /**
     * Returns the duration in the infected state.
     *
     * @return Duration in the infected state.
     */
    public int getDI() 
    {
        return dI;
    }

    /**
     * Returns the duration in the recovered state.
     *
     * @return Duration in the recovered state.
     */
    public int getDR() 
    {
        return dR;
    }

    /**
     * Sets a new x-coordinate for the location.
     *
     * @param newX The new x-coordinate.
     * @return The updated x-coordinate.
     */
    public int setX(int newX) 
    {
        this.x = newX;
        return x;
    }

    /**
     * Sets a new y-coordinate for the location.
     *
     * @param newY The new y-coordinate.
     * @return The updated y-coordinate.
     */
    public int setY(int newY) 
    {
        this.y = newY;
        return y;
    }

    /**
     * Returns the x-coordinate of the location.
     *
     * @return The x-coordinate.
     */
    public int getX() 
    {
        return x;
    }

    /**
     * Returns the y-coordinate of the location.
     *
     * @return The y-coordinate.
     */
    public int getY() 
    {
        return y;
    }
}
