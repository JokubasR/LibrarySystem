package com.mif.jora0644.Factory;

/**
 * Created by jokub_000 on 12/9/13.
 */

import Library.*;
import Models.Resources.*;

public class UserFactory {
    public static Library.Db.DbItem createUser() {
        return createUser(Role.DEFAULT);
    }

    public static Library.Db.DbItem createUser(Models.Resources.Role role) {
        Library.Db.DbItem user = null;

        switch (role) {
            case Models.Resources.Role.USER:
                user = new Models.Resources.User();
                break;
            case Models.Resources.Role.WORKER:
                user = new Models.Resources.User();
                break;
        }

        return user;
    }

}
