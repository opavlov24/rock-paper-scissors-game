package tech.letscode.rpsgame.domain.model;

import tech.letscode.rpsgame.domain.model.player.Player;

import javax.annotation.Nonnull;

/**
 * Oleg Pavlov <oleg.pavlov@aol.com>
 */
public interface GameFactory
{

    Game create(@Nonnull Player firstPlayer, @Nonnull Player secondPlayer);
}