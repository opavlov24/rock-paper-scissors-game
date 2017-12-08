package tech.letscode.rpsgame.domain.model.player

import spock.lang.Specification

/**
 * Oleg Pavlov <oleg.pavlov@aol.com>
 */
class ComputerPlayerTest extends Specification
{
    def "should return a random shape"()
    {
        given:
        def player = new ComputerPlayer()

        expect:
        player.choose() != null
    }
}
