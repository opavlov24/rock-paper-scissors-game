package tech.letscode.rpsgame.domain.model.player;

import tech.letscode.rpsgame.domain.model.Shape;

import java.util.Random;

/**
 * Represents a model of the computer player. The choice is generated automatically.
 * <p>
 * Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class ComputerPlayer implements Player
{
    private final Shape chosenShape;

    public ComputerPlayer()
    {
        int choice = new Random().nextInt(Shape.values().length);
        this.chosenShape = Shape.values()[choice];
    }

    @Override
    public Shape choose()
    {
        return this.chosenShape;
    }
}