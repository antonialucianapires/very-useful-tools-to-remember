package br.com.alps.vuttr.resources;

import br.com.alps.vuttr.config.validation.exceptions.VttrException;
import br.com.alps.vuttr.domain.User;
import br.com.alps.vuttr.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/tools/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<?> users() {
        try {
            List<User> users = userService.findAll();
            if (users.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(users);
            }
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (VttrException vttrException) {
            return ResponseEntity.status(400).body(vttrException.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User userPost) {

        try {
            User user = userService.saveUser(userPost);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(userService.saveUser(user));
        } catch (VttrException vttrException) {
            return ResponseEntity.status(400).body(vttrException.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }


    }

}
