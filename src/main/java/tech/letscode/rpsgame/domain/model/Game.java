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
     * @param outcome callback to notify a client about the result. Only one of the methods will be called.
     */
    void play(@Nonnull Outcome outcome);
}
