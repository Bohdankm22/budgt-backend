package com.trio.budgt.controller;

import com.trio.budgt.data.model.UserData;
import com.trio.budgt.data.repository.UserDataRepository;
import com.trio.budgt.exception.UserDataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * API for user data requests.
 */
@RestController
@RequestMapping("/api/v1")
public class UserDataController {

    private final UserDataRepository userDataRepository;

    @Autowired
    public UserDataController(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    /**
     * Get data for single user.
     * @param userId of the require user.
     * @return userData entity.
     */
    @GetMapping("/usersdata/{id}")
    public UserData getUserDataById(@PathVariable(value = "id") Long userId) {
        return userDataRepository.findById(userId)
                .orElseThrow(() -> new UserDataNotFoundException(userId));
    }

    /**
     * Update data for single user.
     * @param userData of the updated user.
     */
    @PutMapping("/usersdata/{id}")
    public UserData updateUserData(@PathVariable("id") long id, @RequestBody UserData userData) {
        userDataRepository.findById(id).orElseThrow(() -> new UserDataNotFoundException(id));
        userData.setId(id);
        return userDataRepository.save(userData);
    }

}