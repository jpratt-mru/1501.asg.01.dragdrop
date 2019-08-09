public class Result {

    private int exitStatus;
    private String msg;

    public Result(int exitStatus, String msg) {
        this.exitStatus = exitStatus;
        this.msg = msg;
    }

    public String msg() {
        return msg;
    }

    public int exitStatus() {
        return exitStatus;
    }

}
