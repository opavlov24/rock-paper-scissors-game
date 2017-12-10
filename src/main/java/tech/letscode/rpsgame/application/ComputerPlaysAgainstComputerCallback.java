package tech.letscode.rpsgame.application;

/**
 * Oleg Pavlov <oleg.pavlov@aol.com>
 */
public interface ComputerPlaysAgainstComputerCallback
{
    /**
     * Called when the first computer player wins
     *
     * @param choiceOfFirstComputer  choice of the first computer player
     * @param choiceOfSecondComputer choice of the second computer player
     */
    void firstComputerPlayerWin(String choiceOfFirstComputer, String choiceOfSecondComputer);

    /**
     * Called when the second computer player wins
     *
     * @param choiceOfFirstComputer  choice of the first computer player
     * @param choiceOfSecondComputer choice of the second computer player
     */
    void secondComputerPlayerWin(String choiceOfFirstComputer, String choiceOfSecondComputer);
}
