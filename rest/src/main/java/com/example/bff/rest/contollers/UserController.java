package com.example.bff.rest.contollers;

import com.example.bff.api.operations.user.changePassword.ChangePasswordInput;
import com.example.bff.api.operations.user.changePassword.ChangePasswordOperation;
import com.example.bff.api.operations.user.changePassword.ChangePasswordOutput;
import com.example.bff.api.operations.user.logInUser.LogInUserInput;
import com.example.bff.api.operations.user.logInUser.LogInUserOperation;
import com.example.bff.api.operations.user.logInUser.LogInUserOutput;
import com.example.bff.api.operations.user.registerUser.RegisterUserInput;
import com.example.bff.api.operations.user.registerUser.RegisterUserOperation;
import com.example.bff.api.operations.user.registerUser.RegisterUserOutput;
import com.example.zoostoreproject.api.operations.item.removeTagFromItem.RemoveTagFromItemInput;
import com.example.zoostoreproject.api.operations.item.removeTagFromItem.RemoveTagFromItemOutput;
import com.example.zoostoreproject.api.operations.vendor.addVendor.AddVendorInput;
import com.example.zoostoreproject.api.operations.vendor.addVendor.AddVendorOutput;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    private final RegisterUserOperation registerUserOperation;
    private final LogInUserOperation logInUserOperation;
    private final ChangePasswordOperation changePasswordOperation;

    @PostMapping
    public ResponseEntity<RegisterUserOutput> registerUser(@RequestBody RegisterUserInput input)  {

        RegisterUserOutput response =  registerUserOperation.process(input);
        return ResponseEntity.status(201).body(response);
    }

    @PatchMapping(path = "/password")
    public ResponseEntity<ChangePasswordOutput> changePassword(@Valid @RequestBody ChangePasswordInput input) {
        ChangePasswordOutput response = changePasswordOperation.process(input);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LogInUserOutput> logInUser(@RequestBody @Valid LogInUserInput input) {
        LogInUserOutput result = this.logInUserOperation.process(input);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", result.getJwt());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
