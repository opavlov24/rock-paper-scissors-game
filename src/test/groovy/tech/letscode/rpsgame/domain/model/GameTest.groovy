package tech.letscode.rpsgame.domain.model

import spock.lang.Specification

/**
 * Oleg Pavlov <oleg.pavlov@aol.com>
 */
class GameTest extends Specification
{
    def "should call satisfied callback method"()
    {
        given:
        def firstPlayer = Mock(Player) {
            choose() >> firstPlayerChoice
        }
        def secondPlayer = Mock(Player) {
            choose() >> secondPlayerChoice
        }
        def game = new Game(firstPlayer, secondPlayer)
        def outcome = Mock(Outcome)

        when:
        game.play(outcome)

        then:
        with(outcome)
                {
                    numFirstPlayerWon * firstPlayerWon()
                    numSecondPlayerWon * secondPlayerWon()
                    numIsTied * isTied()
                }

        where:
        firstPlayerChoice | secondPlayerChoice | numFirstPlayerWon | numSecondPlayerWon | numIsTied
        Shape.ROCK        | Shape.SCISSOR      | 1                 | 0                  | 0
        Shape.ROCK        | Shape.PAPER        | 0                 | 1                  | 0
        Shape.ROCK        | Shape.ROCK         | 0                 | 0                  | 1
    }

    def "should throw IllegalArgumentException if the first player didn't made a choice"()
    {
        given:
        def firstPlayer = Mock(Player)
        def secondPlayer = Mock(Player) {
            choose() >> Shape.ROCK
        }
        def game = new Game(firstPlayer, secondPlayer)
        def outcome = Mock(Outcome)

        when:
        game.play(outcome)

        then:
        thrown IllegalArgumentException
    }

    def "should throw IllegalArgumentException if the second player didn't made a choice"()
    {
        given:
        def firstPlayer = Mock(Player) {
            choose() >> Shape.ROCK
        }
        def secondPlayer = Mock(Player)
        def game = new Game(firstPlayer, secondPlayer)
        def outcome = Mock(Outcome)

        when:
        game.play(outcome)

        then:
        thrown IllegalArgumentException
    }

    def "should throw IllegalArgumentException if the firstPlayer wasn't passed to the constructor"()
    {
        when:
        new Game(null, Mock(Player))

        then:
        thrown IllegalArgumentException
    }

    def "should throw IllegalArgumentException if the secondPlayer wasn't passed to the constructor"()
    {
        when:
        new Game(Mock(Player), null)

        then:
        thrown IllegalArgumentException
    }
}