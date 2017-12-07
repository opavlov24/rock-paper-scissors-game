package tech.letscode.rpsgame.domain.model;

import javax.annotation.Nonnull;

/**
 * Oleg Pavlov <oleg.pavlov@aol.com>
 */
public interface Player
{
    @Nonnull
    Shape choose();
}