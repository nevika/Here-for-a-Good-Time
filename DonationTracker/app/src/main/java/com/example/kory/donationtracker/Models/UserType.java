package com.example.kory.donationtracker.Models;

public enum UserType {

    USER("User", false, false, false, false),
    EMPLOYEE("Employee", false, false, true, false),
    MANAGER("Manager", true, false, true, true),
    ADMIN("Administrator", true, true, true, true);

    private String _type;
    private boolean _canAddUser;
    private boolean _canLockUser;
    private boolean _canUpdateLoc;
    private boolean _canManageLoc;

    UserType(String type, boolean add, boolean lock, boolean update, boolean manage) {
        _type = type;
        _canAddUser = add;
        _canLockUser = lock;
        _canUpdateLoc = update;
        _canManageLoc = manage;
    }

    public String getStringType() { return _type; }

    public boolean canAddUser() { return _canAddUser; }
    public boolean canLockUser() { return _canLockUser; }
    public boolean canUpdateLocation() { return _canUpdateLoc; }
    public boolean canManageLocations() { return _canManageLoc; }
}
