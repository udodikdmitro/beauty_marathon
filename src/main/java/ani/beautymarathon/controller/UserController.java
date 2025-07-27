package ani.beautymarathon.controller;

import ani.beautymarathon.entity.DeletedState;
import ani.beautymarathon.entity.User;
import ani.beautymarathon.service.UserService;
import ani.beautymarathon.view.CreateUserView;
import ani.beautymarathon.view.GetUserView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public GetUserView createUser(@Valid @RequestBody CreateUserView newUserView) {
        final User newUser = new User();
        newUser.setName(newUserView.name());
        newUser.setStartWeight(newUserView.startWeight());
        newUser.setTargetWeight(newUserView.targetWeight());
        newUser.setDeletedState(DeletedState.NOT_DELETED);
        newUser.setCreationDate(LocalDate.now());

        User savedUser = userService.save(newUser);
        return createUserView(savedUser);
    }

    @GetMapping("/all")
    public List<GetUserView> findAll(){
        List<User> users = userService.findAll();
        return users.stream()
                .map(this::createUserView)
                .toList();
    }

    @GetMapping("/{id}")
    public GetUserView getById(@PathVariable long id) {
        User user = userService.getById(id);
        return createUserView(user);
    }

    private GetUserView createUserView(User user) {
        return new GetUserView(
                user.getId(),
                user.getName(),
                user.getStartWeight(),
                user.getTargetWeight(),
                user.getCreationDate(),
                user.getDeletedState()
        );
    }
}
