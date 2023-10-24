package com.example.nt.logic;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {

    private static final Model instance = new Model();

    private final Map<Integer, User> model;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        model = new HashMap<>();

        model.put(1, new User("Vladimir", "Yazev", 1000));
        model.put(2, new User("Peter", "Koh", 800));
        model.put(3, new User("Elena", "Krasnova", 1200));
        model.put(4, new User("Maria", "Chernih", 900 ));

    }

    public void add(User user, int id) {
        model.put(id, user);
    }

    public Map<Integer, User> getFromList() {
        return model;
    }
}
