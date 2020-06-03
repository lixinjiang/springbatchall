package cn.bonusli.batch.exception;

/**
 * @Author lxj
 */
public class MyJobExecutionException extends Exception {
    private static final long serialVersionUID = 7168487913507656106L;

    public MyJobExecutionException(String message) {
        super(message);
    }
}
