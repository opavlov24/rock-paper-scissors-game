package tech.letscode.rpsgame.domain.model;

import tech.letscode.rpsgame.domain.model.player.Player;

import javax.annotation.Nonnull;

/**
 * A factory class to create an instance object of {@link DefaultGame}
 *
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class DefaultGameFactory implements GameFactory
{
    /**
     * Creates an instance of {@link DefaultGame}
     *
     * @param firstPlayer  the first player
     * @param secondPlayer the second player
     * @return An instance of {@link DefaultGame}
     * @throws IllegalArgumentException if one/both arguments is/are null
     */
    @Override
    public Game create(@Nonnull Player firstPlayer, @Nonnull Player secondPlayer)
    {
        return new DefaultGame(firstPlayer, secondPlayer);
    }
}
