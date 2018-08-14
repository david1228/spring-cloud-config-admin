package com.didispace.scca.rest.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by David.Liu on 2018/8/7.
 */
@RestController
public class WebController {
   @Autowired
   private Config config;

    @GetMapping("/list")
    public Config list() {
        return config;
    }


}
