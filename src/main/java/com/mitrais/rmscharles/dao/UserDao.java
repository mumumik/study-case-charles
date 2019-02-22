package com.mitrais.rmscharles.dao;

import com.mitrais.rmscharles.model.User;

import java.util.Optional;

/**
 * Provides interface specific to {@link User} data
 */
public interface UserDao extends Dao<User, Long>
{
    /**
     * Find {@link User} by its username
     * @param userName username
     * @return user
     */
    Optional<User> findByUserName(String userName);
}
