package biz.managers3.managers3.model.enums;

public enum ManagersStatus {
    ACTIVE,
    INACTIVE;

    public ManagersStatus toggle() {
        return this == ACTIVE ? INACTIVE : ACTIVE;
    }
    }
