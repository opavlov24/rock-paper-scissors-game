package tech.letscode.rpsgame.domain.model;

import tech.letscode.rpsgame.domain.model.player.Player;
import tech.letscode.rpsgame.shared.Args;

import javax.annotation.Nonnull;

/**
 * {@link DefaultGame} models rock-paper-scissor game. To play this game you need to pass two players
 * and call {@link DefaultGame#play(Outcome)} method and pass there {@link Outcome} callback to be notified about
 * the result.<br>
 * Mechanics: Each of the players is asked to choose one of the shapes (Rock, Paper, Scissor) and after that,
 * the winner is computed.<br>
 * Example: If the first player decides to play rock and the second player decides to play paper then
 * the second player wins, because paper covers rock.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Rock%E2%80%93paper%E2%80%93scissors">Full description of the game</a>
 *
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class DefaultGame implements Game
{
    private final Player firstPlayer;

    private final Player secondPlayer;

    DefaultGame(@Nonnull Player firstPlayer, @Nonnull Player secondPlayer)
    {
        this.firstPlayer = Args.notNull(firstPlayer, "firstPlayer is required");
        this.secondPlayer = Args.notNull(secondPlayer, "secondPlayer is required");
    }

    /**
     * Runs the game.
     *
     * @param outcome callback to notify a client about the result. Only one of the methods is called.
     * @throws IllegalArgumentException if outcome is null or {@link Player#choose()} returns null.
     */
    @Override
    public void play(@Nonnull Outcome outcome)
    {
        Args.notNull(outcome, "outcome is required");
        Shape firstPlayerChoice = Args.notNull(this.firstPlayer.choose(), "The first player must choose a shape");
        Shape secondPlayerChoice = Args.notNull(this.secondPlayer.choose(), "The second player must choose a shape");

        if (firstPlayerChoice.beats(secondPlayerChoice))
        {
            outcome.firstPlayerWins(firstPlayerChoice, secondPlayerChoice);
        } else if (secondPlayerChoice.beats(firstPlayerChoice))
        {
            outcome.secondPlayerWins(firstPlayerChoice, secondPlayerChoice);
        } else
        {
            outcome.isTied(firstPlayerChoice);
        }
    }
}
