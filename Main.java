import java.io.IOException;

import simulation.SimulationManager;
import utils.random;

public class Main 
{
    public static void main(String[] args) 
    {

        for (int i = 0; i < 100; i++) 
        {
        	random.setSeed(i);
            try 
            {
                SimulationManager simulationManager = new SimulationManager(300, 300);
                simulationManager.runSimulation(i+1); 

                System.out.println("simulation " + (i + 1) + "wait 1min...");
                Thread.sleep(1 * 60 * 1000); 
            } 
            catch (InterruptedException e) 
            {
                System.out.println("Pause interrupted:" + e.getMessage());
            } catch (IOException e) 
            {
                System.out.println("An IO error occurred:" + e.getMessage());
                e.printStackTrace();
            }
        }
    }    
}
