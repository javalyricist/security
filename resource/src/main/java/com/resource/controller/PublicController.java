package com.resource.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @RequestMapping("/resource")
    public String resource1() {
        return "Here is public resource. This is to show that when a resource "
        		+ "is configured for public access, it doesn't need to go through the OAuth flow.";
    }



}
