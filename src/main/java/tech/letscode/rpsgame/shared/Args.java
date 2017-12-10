package tech.letscode.rpsgame.shared;

/**
 * Some utils to validate input arguments.
 *
 * @author Oleg Pavlov <oleg.pavlov@aol.com>
 */
public class Args
{
    /**
     * throw IllegalArgumentException if the criteria is not satisfied
     *
     * @param value            value to check
     * @param exceptionMessage message to include in the exception
     * @return the same value that was passed if the criteria is satisfied
     */
    public static <T> T notNull(T value, String exceptionMessage)
    {
        if (value == null)
        {
            throw new IllegalArgumentException(exceptionMessage);
        }
        return value;
    }
}
