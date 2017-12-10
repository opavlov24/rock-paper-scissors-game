package tech.letscode.rpsgame.application

import spock.lang.Specification
import tech.letscode.rpsgame.domain.model.Game
import tech.letscode.rpsgame.domain.model.GameFactory
import tech.letscode.rpsgame.domain.model.Outcome
import tech.letscode.rpsgame.domain.model.Shape
import tech.letscode.rpsgame.domain.model.player.Player

/**
 * Oleg Pavlov <oleg.pavlov@aol.com>
 */
class RpsApplicationTest extends Specification
{
    def "should throw IllegalArgumentException if choiceOfHuman is null (humanPlaysAgainstComputer)"()
    {
        when:
        new RpsApplication().humanPlaysAgainstComputer(null, Mock(HumanPlaysAgainstComputerCallback))

        then:
        thrown IllegalArgumentException
    }

    def "should throw IllegalArgumentException if the passed callback is null (humanPlaysAgainstComputer)"()
    {
        when:
        new RpsApplication().humanPlaysAgainstComputer("rock", null)

        then:
        thrown IllegalArgumentException
    }

    def "should call personWon if firstPlayerWon method is called"()
    {
        given:
        def application = new RpsApplication(createGameFactoryThatReturnGame(new GameWhereFirstPlayerAlwaysWin()))
        def callback = Mock(HumanPlaysAgainstComputerCallback)

        when:
        application.humanPlaysAgainstComputer("rock", callback)

        then:
        1 * callback.personWin(_)
        0 * callback.computerWin(_)
        0 * callback.isTied(_)
    }

    def "should call computerWon if secondPlayer method is called"()
    {
        given:
        def application = new RpsApplication(createGameFactoryThatReturnGame(new GameWhereSecondPlayerAlwaysWin()))
        def callback = Mock(HumanPlaysAgainstComputerCallback)

        when:
        application.humanPlaysAgainstComputer("rock", callback)

        then:
        0 * callback.personWin(_)
        1 * callback.computerWin(_)
        0 * callback.isTied(_)
    }

    def "should call isTied if isTied method is called"()
    {
        given:
        def application = new RpsApplication(createGameFactoryThatReturnGame(new GameWhereAlwaysNoOneWin()))
        def callback = Mock(HumanPlaysAgainstComputerCallback)

        when:
        application.humanPlaysAgainstComputer("rock", callback)

        then:
        0 * callback.personWin(_)
        0 * callback.computerWin(_)
        1 * callback.isTied(_)
    }

    def "should throw IllegalArgumentException if callback is null (ComputerPlaysAgainstComputerCallback)"()
    {
        when:
        new RpsApplication().computerPlaysAgainstComputer(null)

        then:
        thrown IllegalArgumentException
    }

    def "should call firstComputerPlayerWon if firstPlayer is won"()
    {
        given:
        def application = new RpsApplication(createGameFactoryThatReturnGame(new GameWhereFirstPlayerAlwaysWin()))
        def callback = Mock(ComputerPlaysAgainstComputerCallback)

        when:
        application.computerPlaysAgainstComputer(callback)

        then:
        1 * callback.firstComputerPlayerWin(_, _)
        0 * callback.secondComputerPlayerWin(_, _)
    }

    def "should call secondComputerPlayerWon if secondPlayer is won"()
    {
        given:
        def application = new RpsApplication(createGameFactoryThatReturnGame(new GameWhereSecondPlayerAlwaysWin()))
        def callback = Mock(ComputerPlaysAgainstComputerCallback)

        when:
        application.computerPlaysAgainstComputer(callback)

        then:
        0 * callback.firstComputerPlayerWin(_, _)
        1 * callback.secondComputerPlayerWin(_, _)
    }

    def "should repeat game while someone win"()
    {
        given:
        def gameFactory = Mock(GameFactory) {
            create(_ as Player, _ as Player) >>> [new GameWhereAlwaysNoOneWin(), new GameWhereFirstPlayerAlwaysWin()]
        }
        def application = new RpsApplication(gameFactory)
        def callback = Mock(ComputerPlaysAgainstComputerCallback)

        when:
        application.computerPlaysAgainstComputer(callback)

        then:
        1 * callback.firstComputerPlayerWin(_, _)
        0 * callback.secondComputerPlayerWin(_, _)
    }

    def createGameFactoryThatReturnGame(Game game)
    {
        Mock(GameFactory) {
            create(_ as Player, _ as Player) >> game
        }
    }


    static class GameWhereFirstPlayerAlwaysWin implements Game
    {

        @Override
        void play(Outcome outcome)
        {
            outcome.firstPlayerWin(Shape.ROCK, Shape.SCISSORS)
        }
    }

    static class GameWhereSecondPlayerAlwaysWin implements Game
    {

        @Override
        void play(Outcome outcome)
        {
            outcome.secondPlayerWin(Shape.ROCK, Shape.PAPER)
        }
    }

    static class GameWhereAlwaysNoOneWin implements Game
    {

        @Override
        void play(Outcome outcome)
        {
            outcome.isTied(Shape.ROCK)
        }
    }
}
