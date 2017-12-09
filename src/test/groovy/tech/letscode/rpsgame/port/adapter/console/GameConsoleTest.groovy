package tech.letscode.rpsgame.port.adapter.console

import spock.lang.Specification
import spock.lang.Timeout
import tech.letscode.rpsgame.application.RpsApplication

import java.util.concurrent.TimeUnit

/**
 * Oleg Pavlov <oleg.pavlov@aol.com>
 */
class GameConsoleTest extends Specification
{
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    def "should run computerVsComputer version"()
    {
        given:
        def answers = "1\nn\nq\n"
        def application = Mock(RpsApplication)
        def console = new GameConsole(nullPrintStream(), new ByteArrayInputStream(answers.getBytes()), application)

        when:
        console.run()

        then:
        1 * application.computerPlaysAgainstComputer(_)
    }

    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    def "should run humanVsComputerVersion"()
    {
        given:
        def answers = "2\n1\nn\nq".getBytes()
        def application = Mock(RpsApplication)
        def console = new GameConsole(nullPrintStream(), new ByteArrayInputStream(answers), application)

        when:
        console.run()

        then:
        1 * application.humanPlaysAgainstComputer(_, _)
    }

    def nullPrintStream()
    {
        new PrintStream(new OutputStream() {
            @Override
            void write(int b) throws IOException
            {
                //nop
            }
        })
    }
}
