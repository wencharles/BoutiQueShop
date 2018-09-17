package com.sopia.boutiqueshop;

/**
 * @author Sopia  on  4:03 PM 18-Oct-17.
 * @project BoutiQueShop
 */
public class BQShopException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BQShopException()
    {
        super();
    }

    public BQShopException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BQShopException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public BQShopException(String message)
    {
        super(message);
    }

    public BQShopException(Throwable cause)
    {
        super(cause);
    }

}
