package tech.letscode.rpsgame.domain.model;

import tech.letscode.rpsgame.shared.Args;

import javax.annotation.Nonnull;

/**
 * This class models Rock-Paper-Scissor game. To play this game you need to pass two players and call @{Game#play}
 * method and pass there @{Outcome} callback to be notified about the result.
 * Mechanics. Each of the players is asked to choose one of the shapes (Rock, Paper, Scissor) and after that,
 * the winner will be computed.
 * For example. If the first player decided to play rock and the second player decided to play paper then
 * the second player is won, because paper covers rock.
 * <p>
 * Full description of the game you can find here https://en.wikipedia.org/wiki/Rock%E2%80%93paper%E2%80%93scissors
 * <p>
 * Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class Game
{
    private final Player firstPlayer;

    private final Player secondPlayer;

    public Game(@Nonnull Player firstPlayer, @Nonnull Player secondPlayer)
    {
        this.firstPlayer = Args.notNull(firstPlayer, "firstPlayer is required");
        this.secondPlayer = Args.notNull(secondPlayer, "secondPlayer is required");
    }

    /**
     * Runs the game.
     *
     * @param outcome Callback to notify a client about the result. Only one of the methods will be called.
     * @throws IllegalArgumentException if outcome is null or @{Player#choose} returns null.
     */
    public void play(@Nonnull Outcome outcome)
    {
        Args.notNull(outcome, "outcome is required");
        Shape firstPlayerChoice = Args.notNull(this.firstPlayer.choose(), "The first player must choose a shape");
        Shape secondPlayerChoice = Args.notNull(this.secondPlayer.choose(), "The second player must choose a shape");

        if (firstPlayerChoice.beats(secondPlayerChoice))
        {
            outcome.firstPlayerWon();
        } else if (secondPlayerChoice.beats(firstPlayerChoice))
        {
            outcome.secondPlayerWon();
        } else
        {
            outcome.isTied();
        }
    }
}
