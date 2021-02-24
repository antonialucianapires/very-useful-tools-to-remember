package br.com.alps.vuttr.services;

import br.com.alps.vuttr.config.validacao.errors.validation.exceptions.VttrException;
import br.com.alps.vuttr.domain.User;
import br.com.alps.vuttr.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User saveUser(User user) {
        try {
            User newUser = User.builder()
                    .login(user.getLogin())
                    .password(new BCryptPasswordEncoder().encode(user.getPassword()))
                    .build();
            return repository.save(newUser);
        } catch (Exception exception)  {
            throw new VttrException("Failed to save user. Ex: " + exception.getMessage());
        }

    }
}
