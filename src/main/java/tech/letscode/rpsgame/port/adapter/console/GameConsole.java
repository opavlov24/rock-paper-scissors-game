package tech.letscode.rpsgame.port.adapter.console;

import tech.letscode.rpsgame.application.ComputerPlaysAgainstComputerCallback;
import tech.letscode.rpsgame.application.HumanPlaysAgainstComputerCallback;
import tech.letscode.rpsgame.application.RpsApplication;
import tech.letscode.rpsgame.shared.Args;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Simple console UI for Rock-Paper-Scissors game.
 *
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class GameConsole
{
    private static final String INCORRECT_ANSWER_MESSAGE = "Incorrect answer! Please, try again...";

    private final PrintStream printStream;

    private final InputStream inputStream;

    private final RpsApplication application;

    public GameConsole(@Nonnull PrintStream printStream, @Nonnull InputStream inputStream,
                       @Nonnull RpsApplication application)
    {
        this.printStream = Args.notNull(printStream, "printStream is required");
        this.inputStream = Args.notNull(inputStream, "inputStream is required");
        this.application = Args.notNull(application, "application is required");
    }

    public void run()
    {
        tell("Welcome to Rock-Paper-Scissor game!");
        try (Scanner scanner = new Scanner(this.inputStream))
        {
            String answer;
            do
            {
                tell("Choose the version of the game! Or enter q to exit.");
                tell("\t(1) - Computer Vs Computer");
                answer = ask("\t(2) - Human Vs Computer", scanner);
                switch (answer)
                {
                    case "1":
                        playComputerVsComputer(scanner);
                        break;
                    case "2":
                        playHumanVsComputer(scanner);
                        break;
                    case "q":
                        break;
                    default:
                        tell(INCORRECT_ANSWER_MESSAGE);
                }
            } while (dontWantToExit(answer));
        }
    }

    private void playHumanVsComputer(Scanner scanner)
    {
        String answer;
        String humanChoice = null;
        do
        {
            do
            {
                tell("Ok. Choose the shape (or enter (b) to back to the previous menu):");
                tell("\t(1) - Rock");
                tell("\t(2) - Paper");
                answer = ask("\t(3) - Scissors", scanner);
                switch (answer)
                {
                    case "1":
                        humanChoice = "Rock";
                        break;
                    case "2":
                        humanChoice = "Paper";
                        break;
                    case "3":
                        humanChoice = "Scissors";
                        break;
                    case "b":
                        return;
                    default:
                        tell(INCORRECT_ANSWER_MESSAGE);
                }
            } while (humanChoice == null);

            this.application.humanPlaysAgainstComputer(humanChoice, new HumanPlaysAgainstComputerCallback()
            {
                @Override
                public void humanWins(String choiceOfComputer)
                {
                    tell("You win! The computer choice was " + choiceOfComputer);
                }

                @Override
                public void computerWins(String choiceOfComputer)
                {
                    tell("You lose! The computer choice was " + choiceOfComputer);
                }

                @Override
                public void isTied(String choiceOfComputer)
                {
                    tell("No one wins! You both chose " + choiceOfComputer);
                }
            });
        } while (doYouWantToPlayAgain(scanner));
    }

    private void playComputerVsComputer(Scanner scanner)
    {
        do
        {
            tell("Nothing depends on you. Just sit and enjoy the game!");
            this.application.computerPlaysAgainstComputer(new ComputerPlaysAgainstComputerCallback()
            {

                @Override
                public void firstComputerPlayerWins(String choiceOfFirstComputer, String choiceOfSecondComputer)
                {
                    tell(
                            String.format(
                                    "The first computer player wins. The first chose - %s, and the second - %s",
                                    choiceOfFirstComputer, choiceOfSecondComputer
                            )
                    );
                }

                @Override
                public void secondComputerPlayerWins(String choiceOfFirstComputer, String choiceOfSecondComputer)
                {
                    tell(
                            String.format(
                                    "The second computer player wins. The first chose - %s, and the second - %s",
                                    choiceOfFirstComputer, choiceOfSecondComputer
                            )
                    );
                }
            });
        } while (doYouWantToPlayAgain(scanner));
    }

    private boolean doYouWantToPlayAgain(Scanner scanner)
    {
        return "y".equals(ask("Do you want to try again? (Y/N)", scanner));
    }

    private boolean dontWantToExit(String answer)
    {

        return !"q".equals(answer);
    }

    private void tell(String message)
    {
        this.printStream.println(message);
    }

    private String ask(String message, Scanner scanner)
    {
        this.printStream.println(message);
        this.printStream.print("-> ");
        return scanner.nextLine().toLowerCase();
    }

}
