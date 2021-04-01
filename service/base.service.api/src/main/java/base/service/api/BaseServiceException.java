package base.service.api;

/**
 * Base service specific exception.
 *
 * @author Elvisa Alibasic
 * @since 1.0.0
 */
public class BaseServiceException extends Exception
{
    private static final long serialVersionUID = 5196306337669193027L;

    /**
     * Constructs Base service exception.
     */
    public BaseServiceException()
    {
        super();
    }

    /**
     * Constructs Base service exception with message.
     *
     * @param message
     *            A {@link String} representing the message an exception is going to expose
     */
    public BaseServiceException(String message)
    {
        super(message);
    }

    /**
     * Constructs Base service exception supplied with cause.
     *
     * @param cause
     *            A {@link Throwable} instance representing the cause (A <code>null</code> value is permitted indicates
     *            that the cause is non-existing or unknown)
     */
    public BaseServiceException(Throwable cause)
    {
        super(cause);
    }

    /**
     * Constructs Base service exception supplied with message and cause.
     *
     * @param message
     *            A {@link String} representing the message an exception is going to expose
     * @param cause
     *            A {@link Throwable} instance representing the cause (A <code>null</code> value is permitted indicates
     *            that the cause is non-existing or unknown)
     */
    public BaseServiceException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
