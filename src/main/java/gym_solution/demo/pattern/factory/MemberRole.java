package gym_solution.demo.pattern.factory;

/**
 * Canonical role names used across the application.
 * Centralizes the string constants so they are not duplicated as magic strings.
 */
public enum MemberRole {

    ADMIN("ADMIN"),
    MEMBER("MEMBER"),
    TRAINER("TRAINER");

    private final String roleName;

    MemberRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
