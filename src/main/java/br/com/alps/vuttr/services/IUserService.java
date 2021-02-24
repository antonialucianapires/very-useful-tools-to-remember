package br.com.alps.vuttr.services;

import br.com.alps.vuttr.domain.User;

import java.util.List;

public interface IUserService {

    List<User> findAll();
    User saveUser(User user);

}
