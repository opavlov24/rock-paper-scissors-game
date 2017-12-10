package tech.letscode.rpsgame.application;

/**
 * A callback interface to notify clients about the result in HumanVsComputer version.
 *
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public interface HumanPlaysAgainstComputerCallback
{
    /**
     * Called when the human player wins
     *
     * @param choiceOfComputer choice of the computer player
     */
    void humanWins(String choiceOfComputer);

    /**
     * Called when the computer player wins
     *
     * @param choiceOfComputer choice of the computer player
     */
    void computerWins(String choiceOfComputer);

    /**
     * Called when no one wins, because both players chose the same shape.
     *
     * @param choiceOfComputer choice of the computer player
     */
    void isTied(String choiceOfComputer);
}
