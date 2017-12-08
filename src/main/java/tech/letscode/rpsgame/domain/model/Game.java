package tech.letscode.rpsgame.domain.model;

import javax.annotation.Nonnull;

/**
 * Represents the interface of rock-paper-scissor game.
 *
 * Oleg Pavlov <oleg.pavlov@aol.com>
 */
public interface Game
{
    /**
     * Runs the game.
     *
     * @param outcome callback to notify a client about the result.
     */
    void play(@Nonnull Outcome outcome);
}
