package magnus.dust.infrastructure.service.enums;

public enum SessionAttributeEnum {

    SESSION_LOGIN("LoginSessionAttribute");

    String name;

    SessionAttributeEnum(String s) {
        this.name = s;
    }

    public String getName() {
        return name;
    }
}
