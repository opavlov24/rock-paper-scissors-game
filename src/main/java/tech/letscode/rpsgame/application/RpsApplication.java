package tech.letscode.rpsgame.application;

import tech.letscode.rpsgame.domain.model.DefaultGameFactory;
import tech.letscode.rpsgame.domain.model.Game;
import tech.letscode.rpsgame.domain.model.GameFactory;
import tech.letscode.rpsgame.domain.model.Outcome;
import tech.letscode.rpsgame.domain.model.Shape;
import tech.letscode.rpsgame.domain.model.player.ComputerPlayer;
import tech.letscode.rpsgame.domain.model.player.HumanPlayer;
import tech.letscode.rpsgame.domain.model.player.Player;
import tech.letscode.rpsgame.shared.Args;

import javax.annotation.Nonnull;

/**
 * Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class RpsApplication
{
    private final GameFactory gameFactory; // This is necessary to simplify unit testing

    public RpsApplication()
    {
        this(new DefaultGameFactory());
    }

    protected RpsApplication(@Nonnull GameFactory gameFactory)
    {
        Args.notNull(gameFactory, "gameFactory is required");
        this.gameFactory = gameFactory;
    }

    /**
     * Models the scenario when a human plays against the computer. You must pass the shape that can be
     * chosen from the next values: Rock, Scissors or Paper and callback to be notified about the result.
     * Only one method of {@link HumanPlaysAgainstComputerCallback} will be called.
     *
     * @param choiceOfHuman the shape that is chosen by the human.
     * @param callback      callback to notify a client about the result.
     * @throws IllegalArgumentException if one of the args is null or has invalid format.
     */
    public void humanPlaysAgainstComputer(@Nonnull String choiceOfHuman,
                                          @Nonnull HumanPlaysAgainstComputerCallback callback)
    {
        Args.notNull(choiceOfHuman, "choiceOfHuman is required");
        Args.notNull(callback, "callback is required");

        Player personPlayer = new HumanPlayer(Shape.ofString(choiceOfHuman));
        Player computerPlayer = new ComputerPlayer();

        Game game = this.gameFactory.create(personPlayer, computerPlayer);
        game.play(new Outcome()
        {

            @Override
            public void firstPlayerWins(Shape firstPlayerChoice, Shape secondPlayerChoice)
            {
                callback.humanWins(secondPlayerChoice.toString());
            }

            @Override
            public void secondPlayerWins(Shape firstPlayerChoice, Shape secondPlayerChoice)
            {
                callback.computerWins(secondPlayerChoice.toString());
            }

            @Override
            public void isTied(Shape choiceOfPlayers)
            {
                callback.isTied(choiceOfPlayers.toString());
            }
        });
    }

    /**
     * Models the scenario when the computer plays against the computer. You must pass only
     * {@link ComputerPlaysAgainstComputerCallback} to be notified about the result.
     *
     * @param callback callback to notify a client about the result.
     * @throws IllegalArgumentException if the passed arg is null.
     */
    public void computerPlaysAgainstComputer(@Nonnull ComputerPlaysAgainstComputerCallback callback)
    {
        Args.notNull(callback, "callback is required");

        final boolean[] someoneWon = {false};
        do //Maybe, it should be extracted to the domain model.
        {
            Player firstComputerPlayer = new ComputerPlayer();
            Player secondComputerPlayer = new ComputerPlayer();

            Game game = this.gameFactory.create(firstComputerPlayer, secondComputerPlayer);
            game.play(new Outcome()
            {

                @Override
                public void firstPlayerWins(Shape firstPlayerChoice, Shape secondPlayerChoice)
                {
                    someoneWon[0] = true;
                    callback.firstComputerPlayerWins(firstPlayerChoice.toString(), secondPlayerChoice.toString());
                }

                @Override
                public void secondPlayerWins(Shape firstPlayerChoice, Shape secondPlayerChoice)
                {
                    someoneWon[0] = true;
                    callback.secondComputerPlayerWins(firstPlayerChoice.toString(), secondPlayerChoice.toString());
                }

                @Override
                public void isTied(Shape choiceOfPlayers)
                {
                    //nop
                }
            });

        } while (!someoneWon[0]);
    }

}
