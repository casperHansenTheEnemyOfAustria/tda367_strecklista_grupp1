package se.cholmers.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {
    private class User {
        String name;
        String nick;
        String phoneNumber;
        Map<UserGroup, Float> saldo = new HashMap<>();
        private List<UserGroup> groups = new ArrayList<>();

        public User(String name, String nick) {
            this.name = name;
            this.nick = nick;
        }

        public void addUserToGroup(Group group) {
            groups.add(group);
        }
    }

    private class UserGroup {
        List<Product> products = new ArrayList<>();
    }

    private class Product {
        String name;
        Float cost;
    }
}
