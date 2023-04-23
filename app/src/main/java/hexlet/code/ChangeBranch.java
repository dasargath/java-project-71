package hexlet.code;

public class ChangeBranch {
    private final String changeType;
    private final String key;
    private final Object oldValue;
    private final Object newValue;

    public ChangeBranch(String type, String thisKey, Object newVal, Object oldVal) {
        this.changeType = type;
        this.key = thisKey;
        this.oldValue = oldVal;
        this.newValue = newVal;
    }

    public final String getChangeType() {
        return changeType;
    }

    public final String getKey() {
        return key;
    }

    public final Object getOldValue() {
        return oldValue;
    }

    public final Object getNewValue() {
        return newValue;
    }
}
