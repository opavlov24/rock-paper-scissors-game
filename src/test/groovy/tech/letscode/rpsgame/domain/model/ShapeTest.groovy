package tech.letscode.rpsgame.domain.model

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Oleg Pavlov <oleg.pavlov@aol.com>
 */
class ShapeTest extends Specification
{
    @Unroll
    def "Should #firstShape beat #secondShape? #firstShapeBeatsSecondShape"()
    {
        expect:
        firstShape.beats(secondShape) == firstShapeBeatsSecondShape

        where:
        firstShape    | secondShape   | firstShapeBeatsSecondShape
        Shape.ROCK    | Shape.SCISSOR | true
        Shape.ROCK    | Shape.PAPER   | false
        Shape.ROCK    | Shape.ROCK    | false
        Shape.SCISSOR | Shape.PAPER   | true
        Shape.SCISSOR | Shape.ROCK    | false
        Shape.SCISSOR | Shape.SCISSOR | false
        Shape.PAPER   | Shape.ROCK    | true
        Shape.PAPER   | Shape.SCISSOR | false
        Shape.PAPER   | Shape.PAPER   | false
    }

    @Unroll
    def "should throw IllegalArgumentException if null is passed to #shape's beat method"()
    {
        when:
        shape.beats(null)

        then:
        thrown IllegalArgumentException

        where:
        shape << [Shape.ROCK, Shape.SCISSOR, Shape.PAPER]
    }

    def "should create shape from string"()
    {
        expect:
        Shape.ofString(shapeAsString) == result

        where:
        shapeAsString | result
        "rock"        | Shape.ROCK
        "scissor"     | Shape.SCISSOR
        "paper"       | Shape.PAPER
        "RoCk"        | Shape.ROCK
    }

    def "should throw IllegalArgumentException if null is passed"()
    {
        when:
        Shape.ofString(null)

        then:
        thrown IllegalArgumentException
    }

    def "should throw IllegalArgumentException if the string cannot be passed to the enum value"()
    {
        when:
        Shape.ofString("wrong_shape")

        then:
        thrown IllegalArgumentException
    }
}
