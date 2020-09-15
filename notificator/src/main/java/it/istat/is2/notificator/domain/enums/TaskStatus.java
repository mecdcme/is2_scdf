package it.istat.is2.notificator.domain.enums;

public enum TaskStatus {
    CREATED(0),
    STARTED(10),
    ENDED(20),
    ERROR(90);

    private int code;

    TaskStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static TaskStatus fromCode(int code) {
        switch (code) {
            case 0:
                return CREATED;
            case 10:
                return STARTED;
            case 20:
                return ENDED;
            case 90:
                return ERROR;
            default:
                throw new IllegalArgumentException("Cannot find TaskStatus with code:" + code);
        }
    }
}
