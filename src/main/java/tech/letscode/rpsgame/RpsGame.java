package tech.letscode.rpsgame;

import tech.letscode.rpsgame.application.RpsApplication;
import tech.letscode.rpsgame.port.adapter.console.GameConsole;

/**
 * Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class RpsGame
{
    public static void main(String[] args)
    {
        new GameConsole(System.out, System.in, new RpsApplication()).run();
    }
}
