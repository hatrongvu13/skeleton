package com.jax.security.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Test controller")
@CrossOrigin (value = "*", maxAge = 3600)
public class TestController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "Test user controller")
    public String user() {
        return "you is a user";
    }

    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Test admin controller")
    public String admin() {
        return "you is a admin";
    }

    @RequestMapping(value = "/jax", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('JAX')")
    @ApiOperation(value = "Jax Controller test")
    public String jax() {
        return "You is a jax";
    }
}
