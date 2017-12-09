package tech.letscode.rpsgame.domain.model;

import tech.letscode.rpsgame.shared.Args;

import javax.annotation.Nonnull;

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
                    return otherShape == SCISSORS;
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
    SCISSORS
            {
                @Override
                public boolean beats(@Nonnull Shape otherShape)
                {
                    Args.notNull(otherShape, "otherShape is required");
                    return otherShape == PAPER;
                }
            };

    /**
     * Checks that the current shape can beat the other shape.
     *
     * @param otherShape the shape of the opponent
     * @return {@code true} if the other shape was beaten by the current shape, otherwise - {@code false}
     */
    public abstract boolean beats(@Nonnull Shape otherShape);

    /**
     * Converts the string represented shape to its enum value
     *
     * @param shape string represented shape
     * @return the enum constant
     * @throws IllegalArgumentException if the passed shape is null or it can't be matched.
     */
    public static Shape ofString(@Nonnull String shape)
    {
        Args.notNull(shape, "shape is required");
        return valueOf(shape.toUpperCase());
    }
}
