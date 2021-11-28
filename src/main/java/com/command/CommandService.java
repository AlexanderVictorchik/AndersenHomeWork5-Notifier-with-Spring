package com.command;

import java.util.ArrayList;
import java.util.List;

public class CommandService {

    private static UserService getService() {
        UserService userService = new UserServiceImplementationService()
                .getPort(UserService.class);
        return userService;
    }

    public static List<String> getUsersByRoleAndGroup(String role, String group) {
        List<User> users = getService().findByRoleAndGroup(role, group);
        List<String> stringUsers = new ArrayList<>();
        for (User u : users) {
            stringUsers.add(
                    u.getId() + "|"
                            + u.getUsername() + "|"
                            + u.getFirstName() + "|"
                            + u.getLastName() + "|"
                            + u.getRole() + "|"
                            + u.getGroup()
            );
        }
        return stringUsers;
    }

    public static List<String> getNotAdminUsers() {
        List<User> users = getService().findAll();
        List<String> stringUsers = new ArrayList<>();
        for (User u : users) {
            if (u.getRole().equals("student")) {
                stringUsers.add(
                        String.valueOf(u.getId())
                );
            }
        }
        return stringUsers;
    }

    public static String getLeadByIdGroup(String userId) {
        User user = getService().findById(userId);
        String stringUser = user.getGroup();
        List<String> list = getUsersByRoleAndGroup("lead", stringUser);
        if (list.isEmpty()) {
            System.out.println("There is no lead for this group");
            return "";
        }
        String s = list.get(0);
        String[] split = getFieldsFromString(s);
        return split[0];
    }

    public static String getFirstNameById(String userId) {
        User user = getService().findById(userId);
        String stringUser = user.getFirstName();
        return stringUser;
    }

    public static String getLastNameById(String userId) {
        User user = getService().findById(userId);
        String stringUser = user.getLastName();
        return stringUser;
    }

    public static String getGroupById(String userId) {
        User user = getService().findById(userId);
        String stringUser = user.getGroup();
        return stringUser;
    }


    public static String[] getFieldsFromString(String ingoing) {
        String[] result = ingoing.split("\\|");
        return result;
    }
}