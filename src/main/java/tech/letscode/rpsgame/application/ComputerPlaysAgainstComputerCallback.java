package tech.letscode.rpsgame.application;

/**
 * A callback interface to notify clients about the result in ComputeVsComputer version.
 *
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public interface ComputerPlaysAgainstComputerCallback
{
    /**
     * Called when the first computer player wins
     *
     * @param choiceOfFirstComputer  choice of the first computer player
     * @param choiceOfSecondComputer choice of the second computer player
     */
    void firstComputerPlayerWins(String choiceOfFirstComputer, String choiceOfSecondComputer);

    /**
     * Called when the second computer player wins
     *
     * @param choiceOfFirstComputer  choice of the first computer player
     * @param choiceOfSecondComputer choice of the second computer player
     */
    void secondComputerPlayerWins(String choiceOfFirstComputer, String choiceOfSecondComputer);
}
