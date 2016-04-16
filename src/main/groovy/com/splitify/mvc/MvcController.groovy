package com.splitify.mvc

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MvcController {
    @RequestMapping(value = "/")
    def @ResponseBody hello() {
        return "Hello World!"
    }

    @RequestMapping(value = "/test")
    def @ResponseBody test() {
        return "Hello World! 2"
    }



}
