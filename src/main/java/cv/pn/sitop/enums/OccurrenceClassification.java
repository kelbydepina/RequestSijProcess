package cv.pn.sitop.enums;

public enum OccurrenceClassification {

    CRI("CRI", "Criminal"),
    ADM("ADM", "Administrativo");

    private final String code;
    private final String description;

    OccurrenceClassification(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
