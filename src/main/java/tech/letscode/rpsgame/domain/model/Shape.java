package tech.letscode.rpsgame.domain.model;

import tech.letscode.rpsgame.shared.Args;

import javax.annotation.Nonnull;
import java.util.Optional;

/**
 * Oleg Pavlov <oleg.pavlov@aol.com>
 */
public enum Shape
{
    ROCK
            {
                @Override
                public boolean beats(@Nonnull Shape otherShape)
                {
                    Args.notNull(otherShape, "otherShape is required");
                    return otherShape == SCISSOR;
                }
            },
    PAPER
            {
                @Override
                public boolean beats(@Nonnull Shape otherShape)
                {
                    Args.notNull(otherShape, "otherShape is required");
                    return otherShape == ROCK;
                }
            },
    SCISSOR
            {
                @Override
                public boolean beats(@Nonnull Shape otherShape)
                {
                    Args.notNull(otherShape, "otherShape is required");
                    return otherShape == PAPER;
                }
            };

    public abstract boolean beats(@Nonnull Shape otherShape);

    public static Optional<Shape> ofString(@Nonnull String shape)
    {
        Args.notNull(shape, "shape is required");
        try
        {
            return Optional.of(valueOf(shape.toUpperCase()));
        } catch (IllegalArgumentException e)
        {
            return Optional.empty();
        }
    }
}
