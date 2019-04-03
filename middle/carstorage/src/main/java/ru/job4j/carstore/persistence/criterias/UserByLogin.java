package ru.job4j.carstore.persistence.criterias;

import ru.job4j.carstore.models.annotated.User;

/**
 * The class implements <code>{@link Criteria<User>}</code> for selection restricted
 * by user login.
 */
public class UserByLogin implements Criteria<User> {

    private final User entity;

    /**
     * Constructor with one parameter.
     *
     * @param entity the entity based on that prepared restricted part of query.
     */
    public UserByLogin(User entity) {
        this.entity = entity;
    }

    @Override
    public String queryRestriction() {
        return String.format(" WHERE login = \'%s\';", this.entity.getLogin());
    }
}
