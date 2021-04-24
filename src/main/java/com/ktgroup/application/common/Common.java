package com.ktgroup.application.common;

public class Common {

    public static String covertRole(int role) {
        switch (role) {
        case Constant.ROLE_ADMIN_I:
            return Constant.ROLE_ADMIN;
        case Constant.ROLE_TEAM_LEAD_I:
            return Constant.ROLE_TEAM_LEAD;
        case Constant.ROLE_CHECKER_I:
            return Constant.ROLE_CHECKER;
        case Constant.ROLE_MEMBER_I:
            return Constant.ROLE_MEMBER;
        default:
            return Constant.ROLE_MEMBER;
        }
    }
}
