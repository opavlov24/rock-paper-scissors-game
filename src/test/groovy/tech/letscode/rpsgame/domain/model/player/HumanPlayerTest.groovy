package tech.letscode.rpsgame.domain.model.player

import spock.lang.Specification
import tech.letscode.rpsgame.domain.model.Shape

/**
 * Oleg Pavlov <oleg.pavlov@aol.com>
 */
class HumanPlayerTest extends Specification
{
    def "should throw IllegalArgumentException if null is passed"()
    {
        when:
        new HumanPlayer(null)

        then:
        thrown IllegalArgumentException
    }

    def "should return the passed shape"()
    {
        given:
        def player = new HumanPlayer(Shape.ROCK)

        expect:
        player.choose() == Shape.ROCK
    }
}
