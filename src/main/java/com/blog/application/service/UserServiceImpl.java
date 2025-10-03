package com.blog.application.service;

import com.blog.application.contract.UserService;
import com.blog.application.dto.req.UserRegisterDto;
import com.blog.application.dto.req.UserUpdateRequest;
import com.blog.application.dto.resp.UserResponseDto;
import com.blog.application.dto.resp.UserShortDto;
import com.blog.application.exception.UserAlreadyExistsException;
import com.blog.application.exception.UserNotFoundException;
import com.blog.application.mapper.PostMapper;
import com.blog.application.mapper.UserMapper;
import com.blog.domain.User;
import com.blog.infrastructure.dao.UserDao;
import com.blog.infrastructure.view.UserResponseView;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserMapper userMapper;
    private final PostMapper postMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Transactional
    @Override
    public void save(UserRegisterDto dto) {
        userDao.save(User.builder()
            .username(dto.getUsername())
            .email(dto.getEmail())
            .passwordHash(hashPassword(dto.getPassword()))
            .build()
        );
    }

    private String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public void checkUserAndPassword(String email, String password) {
        User u = userDao.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException(
                "A user with this email or nickname already not exists"));

        if (!checkPassword(password, u.getPasswordHash())) {
            throw new UserAlreadyExistsException("Incorrect email, nickname, or password");
        }
    }

    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return bCryptPasswordEncoder.matches(plainPassword, hashedPassword);
    }

    @Transactional
    @Override
    public UserResponseDto update(UserUpdateRequest dto) {
        User user = userDao.findById(dto.getId())
            .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!user.getUsername().equals(dto.getUsername())) {
            user.setUsername(dto.getUsername());
        }
        if (!user.getEmail().equals(dto.getEmail())) {
            user.setEmail(dto.getEmail());
        }

        // Транзакція має автоматично зберегти користувача (Dirty Checking)
        return userMapper.userToUserResponseDto(user);
    }

    @Override
    public UserShortDto getCurrentUserInfo() {
        var a = SecurityContextHolder.getContext().getAuthentication();
        User targetUser = (User) a.getPrincipal();
        return postMapper.userToUserShortDto(targetUser);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        userDao.deleteById(id);
    }

    @Transactional
    @Override
    public void isExistsUserByEmailAndUsername(String email, String username) {
        if (userDao.existsByEmailAndUsername(email, username)) {
            throw new UserAlreadyExistsException(
                "A user with this email or nickname already exists");
        }
    }

    @Override
    public User findById(UUID id) {
        return userDao.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Transactional
    @Override
    public UserResponseDto findByUsername(String username) {
        UserResponseView view = userDao.findUserByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.userResponseViewToUserResponseDto(view);
    }

    @Transactional
    @Override
    public UUID getUserIdFindByEmail(String email) {
        UserResponseView view = userDao.findUserByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        return view.getId();
    }

    @Transactional
    @Override
    public boolean existsByUsername(String username) {
        return userDao.existsByUsername(username);
    }

    @Transactional
    @Override
    public boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }
}
