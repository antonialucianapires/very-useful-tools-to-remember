package br.com.alps.vuttr.repositories;

import br.com.alps.vuttr.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByLogin(String login);
}
