package grid;

import model.Individual;
import java.util.ArrayList;
import java.util.List;

/**
 * Grid class represents a grid where individuals can be placed and moved.
 * It stores individuals in a 2D list based on their positions.
 */
public class Grid 
{
    // Constants should be in uppercase, but here width and height are not constants.
    private final int 				width; // Width of the grid
    private final int 				height; // Height of the grid
    private List<Individual>[][] 	grid; // 2D array storing lists of individuals

    /**
     * Constructs a Grid object with specified width and height.
     * Initializes each cell of the grid with an empty list of individuals.
     *
     * @param width  Width of the grid
     * @param height Height of the grid
     */
    @SuppressWarnings("unchecked")
    public Grid(int width, int height) 
    {
        this.width = width;
        this.height = height;
        grid = new List[width][height];
        for (int i = 0; i < width; i++) 
        {
            for (int j = 0; j < height; j++) 
            {
                grid[i][j] = new ArrayList<>();
            }
        }
    }

    /**
     * Places an individual at the specified (x, y) location.
     * The location is wrapped around the grid dimensions.
     *
     * @param individual The individual to place
     * @param x          X-coordinate of the location
     * @param y          Y-coordinate of the location
     */
    public void placeIndividual(Individual individual, int x, int y) 
    {
        int wrappedX = Math.floorMod(x, width);
        int wrappedY = Math.floorMod(y, height);
        grid[wrappedX][wrappedY].add(individual);
    }

    /**
     * Returns a list of individuals at the specified (x, y) location.
     * The location is wrapped around the grid dimensions.
     *
     * @param x X-coordinate of the location
     * @param y Y-coordinate of the location
     * @return List of individuals at the specified location
     */
    public List<Individual> getIndividualsAt(int x, int y) 
    {
        int wrappedX = Math.floorMod(x, width);
        int wrappedY = Math.floorMod(y, height);
        return grid[wrappedX][wrappedY];
    }

    /**
     * Moves an individual from one location to another.
     * Both the original and new locations are wrapped around the grid dimensions.
     *
     * @param individual The individual to move
     * @param oldX       Original X-coordinate
     * @param oldY       Original Y-coordinate
     * @param newX       New X-coordinate
     * @param newY       New Y-coordinate
     */
    public void moveIndividual(Individual individual, int oldX, int oldY, int newX, int newY) 
    {
        int wrappedOldX = Math.floorMod(oldX, width);
        int wrappedOldY = Math.floorMod(oldY, height);
        grid[wrappedOldX][wrappedOldY].remove(individual);

        int wrappedNewX = Math.floorMod(newX, width);
        int wrappedNewY = Math.floorMod(newY, height);
        grid[wrappedNewX][wrappedNewY].add(individual);
    }

    // Getters for grid dimensions
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
