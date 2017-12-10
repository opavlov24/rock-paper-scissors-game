package tech.letscode.rpsgame.domain.model;

/**
 * Oleg Pavlov <oleg.pavlov@aol.com>
 */
public interface Outcome
{
    /**
     * Called when the first player wins
     *
     * @param firstPlayerChoice  choice of the first player
     * @param secondPlayerChoice choice of the second player
     */
    void firstPlayerWins(Shape firstPlayerChoice, Shape secondPlayerChoice);

    /**
     * Called when the second player wins
     *
     * @param firstPlayerChoice  choice of the first player
     * @param secondPlayerChoice choice of the second player
     */
    void secondPlayerWins(Shape firstPlayerChoice, Shape secondPlayerChoice);

    /**
     * Called if both players chose the same shape
     *
     * @param choiceOfPlayers choice of the players
     */
    void isTied(Shape choiceOfPlayers);
}