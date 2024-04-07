package simulation;

import model.Individual;
import model.Parameters;
import model.State;
import grid.Grid;
import infection.InfectionManager;
import utils.MersenneTwister;
import utils.random;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the simulation of disease spread within a population over a grid.
 */
public class SimulationManager 
{
    public Grid 				grid;
    private Parameters 			parameters;
    private int 				day = 0;
    private MersenneTwister 	mt = random.getInstance();
    private List<Individual> 	data = new ArrayList<>();

    /**
     * Initializes a simulation with a specified grid size.
     *
     * @param width  The width of the grid.
     * @param height The height of the grid.
     */
    public SimulationManager(int width, int height) 
    {
        this.grid = new Grid(width, height);
        initializePopulation();
    }

    /**
     * Places an individual in the grid based on their parameters.
     *
     * @param individual The individual to place.
     */
    private void initPlace(Individual individual) 
    {
        grid.placeIndividual(individual, individual.getParameters().getX(), individual.getParameters().getY());
    }

    /**
     * Initializes the population with individuals at random locations on the grid.
     */
    private void initializePopulation() 
    {
        // Initially, all individuals are susceptible except for a few infected ones
        for (int i = 0; i < 19980; i++) 
        {
            int x = mt.nextInt(grid.getWidth());
            int y = mt.nextInt(grid.getHeight());
            
            parameters = new Parameters(x, y);
            Individual individualS = new Individual(State.SUSCEPTIBLE, parameters);
            initPlace(individualS);
            data.add(individualS);
        }
        // Seed the population with a small number of infected individuals
        for (int i = 19980; i < 20000; i++) 
        {
            int x = mt.nextInt(grid.getWidth());
            int y = mt.nextInt(grid.getHeight());
            
            parameters = new Parameters(x, y);
            Individual individualI = new Individual(State.INFECTED, parameters);
            initPlace(individualI);
            data.add(individualI);
        }
    }

    /**
     * Simulates one step/day of the simulation, moving individuals and updating their states.
     */
    public void simulateOneStep() 
    {
        Collections.shuffle(data, mt);

        for (Individual individual : data) 
        {
            int x = mt.nextInt(grid.getWidth());
            int y = mt.nextInt(grid.getHeight());

            grid.moveIndividual(individual, individual.getParameters().getX(), individual.getParameters().getY(), x, y);

            individual.getParameters().setX(x);
            individual.getParameters().setY(y);

            updateState(individual);
        }

        day++;
    }

    /**
     * Updates the state of an individual based on their current state and surrounding conditions.
     *
     * @param individual The individual whose state is to be updated.
     */
    public void updateState(Individual individual) 
    {
        InfectionManager infectionManager = new InfectionManager(grid);
        int timeInState = individual.getTimeInState() + 1;
        individual.setTimeInState(timeInState);

        switch (individual.getState()) 
        {
            case EXPOSED:
                if (timeInState > individual.getParameters().getDE()) 
                {
                    individual.setState(State.INFECTED);
                    individual.setTimeInState(0);
                }
                break;
            case INFECTED:
                if (timeInState > individual.getParameters().getDI()) 
                {
                    individual.setState(State.RECOVERED);
                    individual.setTimeInState(0);
                }
                break;
            case RECOVERED:
                if (timeInState > individual.getParameters().getDR()) 
                {
                    individual.setState(State.SUSCEPTIBLE);
                    individual.setTimeInState(0);
                }
                break;
            case SUSCEPTIBLE:
            default:
                infectionManager.tryToInfect(individual);
                break;
        }
    }

    /**
     * Counts the number of individuals in a given state.
     *
     * @param state The state to count in the population.
     * @return The count of individuals in the given state.
     */
    public int getStateCount(State state) 
    {
        return (int) data.stream().filter(individual -> individual.getState() == state).count();
    }

    /**
     * Runs the simulation for a specified number of steps and writes the results to a CSV file.
     *
     * @param simulationNumber The simulation run number.
     * @throws IOException If there is an error writing to the file.
     */
    public void runSimulation(int simulationNumber) throws IOException 
    {
        String directoryPath = "./simulation_results";
        File directory = new File(directoryPath);
        if (!directory.exists()) 
        {
            directory.mkdirs();
        }

        String fileName = directoryPath + "/simulation_result_" + simulationNumber + ".csv";
        try (FileWriter csvWriter = new FileWriter(fileName)) 
        {
            csvWriter.append("Iteration,S,E,I,R\n");

            for (int iteration = 0; iteration < 730; iteration++) 
            {
                simulateOneStep();

                int sCount = getStateCount(State.SUSCEPTIBLE);
                int eCount = getStateCount(State.EXPOSED);
                int iCount = getStateCount(State.INFECTED);
                int rCount = getStateCount(State.RECOVERED);

                csvWriter.append(String.format("%d,%d,%d,%d,%d\n", iteration, sCount, eCount, iCount, rCount));
            }

            csvWriter.flush();
        }
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
