package com.innowisegroup.sensorsmonitorapi.repository;

import com.innowisegroup.sensorsmonitorapi.entity.User;

public interface UserRepository extends Repository<User> {

    User findByLogin(String login);
}
