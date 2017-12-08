package tech.letscode.rpsgame.domain.model.player;

import tech.letscode.rpsgame.domain.model.Shape;
import tech.letscode.rpsgame.shared.Args;

import javax.annotation.Nonnull;

/**
 * Represents a model of the human player. The choice is predefined and must be passed through the constructor.
 * <p>
 * Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class HumanPlayer implements Player
{
    private final Shape chosenShape;

    /**
     * @param chosenShape choice of the person
     */
    public HumanPlayer(@Nonnull Shape chosenShape)
    {
        this.chosenShape = Args.notNull(chosenShape, "chosenShape is required");
    }

    @Override
    public Shape choose()
    {
        return this.chosenShape;
    }
}
