package com.inf.admin;

import com.inf.ICoreAction;

public interface IActionsAdmin extends ICoreAction{

// Action for Admin
    public static final String _LISTUSERS  = "_LISTUSERS";
    public static final String _VIEWUSERS  = "_VIEWUSERS";
    public static final String _EDITUSERS  = "_EDITUSERS";

    public static final String _LISTGROUPS = "_LISTGROUPS";
    public static final String _EDITGROUPS = "_EDITGROUPS";
    public static final String _VIEWGROUPS = "_VIEWGROUPS";

    public static final String _LISTDEPARTMENTS = "_LISTDEPARTMENTS";
    public static final String _VIEWDEPARTMENTS = "_VIEWDEPARTMENTS";
    
    public static final String _LISTAPPS = "_LISTAPPS";

    public static final String _LISTLOGS = "_LISTLOGS";
    public static final String _PREPARE_FORYOURULES = "_PREPARE_FORYOURULES";
    public static final String _PREPARE_DOCRULES    = "_PREPARE_DOCRULES";
    public static final String _PREPARE_REPORTRULES = "_PREPARE_REPORTRULES";
    public static final String _PREPARE_TEMPLATES   = "_PREPARE_TEMPLATES";
    public static final String _LIST_SMS= "_LIST_SMS";
    
}
