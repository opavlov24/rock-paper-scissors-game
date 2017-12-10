package tech.letscode.rpsgame.domain.model;

import tech.letscode.rpsgame.domain.model.player.Player;

import javax.annotation.Nonnull;

/**
 * An factory interface to create instance objects of {@link Game}
 *
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public interface GameFactory
{
    Game create(@Nonnull Player firstPlayer, @Nonnull Player secondPlayer);
}