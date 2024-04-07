package infection;

import model.Individual;
import model.State;
import grid.Grid;
import java.util.List;
import utils.MersenneTwister;
import utils.random;

/**
 * InfectionManager class is responsible for managing infection processes within a grid.
 * It calculates infection probabilities and updates individuals' states accordingly.
 */
public class InfectionManager 
{
    private Grid 			grid; // The grid where individuals are placed
    private MersenneTwister mt = random.getInstance(); // Random number generator for infection simulation
    
    /**
     * Constructs an InfectionManager with a reference to the grid.
     *
     * @param grid The grid that contains individuals.
     */
    public InfectionManager(Grid grid) 
    {
        this.grid = grid;
    }
    
    /**
     * Tries to infect an individual based on the number of infected neighbors and a probability function.
     *
     * @param individual The individual to possibly infect.
     */
    public void tryToInfect(Individual individual) 
    {
    	int 	nbVoisinsMalade = calculateTotalInfectionsAround(individual.getParameters().getX(), individual.getParameters().getY());
    	double 	probability = 1 - Math.exp((-1) * 0.5 * nbVoisinsMalade);
        if (mt.nextDouble() < probability) 
        {
            individual.setState(State.EXPOSED); 
        }
    }

    /**
     * Calculates the total number of infections around a given location.
     *
     * @param i The x-coordinate of the location.
     * @param j The y-coordinate of the location.
     * @return The total number of infected individuals around the given location.
     */
    public int calculateTotalInfectionsAround(int i, int j) 
    {
        int totalInfections = 0; 

        // Check all neighboring cells (including the cell itself)
        for (int x = i - 1; x <= i + 1; x++) 
        {
            for (int y = j - 1; y <= j + 1; y++) 
            {
                List<Individual> individuals = grid.getIndividualsAt(x, y);
                long infectionsCount = individuals.stream()
                                                  .filter(ind -> ind.getState() == State.INFECTED)
                                                  .count();
                totalInfections += infectionsCount;
            }
        }

        return totalInfections; 
    }
}
