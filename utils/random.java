package utils;

/**
 * Provides utility methods for random number generation, including the
 * generation of numbers based on a negative exponential distribution.
 */
public class random 
{
    // Singleton instance of MersenneTwister for random number generation.
    private static MersenneTwister mt = new MersenneTwister(System.currentTimeMillis());

    /**
     * Sets the seed of the random number generator to ensure reproducibility.
     *
     * @param seed The seed value to initialize the random number generator.
     */
    public static void setSeed(long seed) 
    {
        mt.setSeed(seed);
    }

    /**
     * Retrieves the singleton instance of the MersenneTwister random number generator.
     *
     * @return The singleton instance of MersenneTwister.
     */
    public static MersenneTwister getInstance() 
    {
        return mt;
    }

    /**
     * Generates a random number based on a negative exponential distribution.
     *
     * @param inMean The mean value for the distribution.
     * @return A random integer derived from the specified negative exponential distribution.
     */
    public static int negExp(double inMean) 
    {
        return (int) (-inMean * Math.log(1 - mt.nextDouble()));
    }
}
