package com.splitify.mvc

import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

import javax.servlet.http.HttpServletResponse

@Controller
class MvcController {

    private static final Logger logger = LogManager.getLogger("HelloWorld");

    @RequestMapping(value = "/")
    def @ResponseBody hello() {
        logger.info("Hello World")
        return "Hello World!"
    }

    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    void receive(@RequestBody String event, HttpServletResponse response) {
        logger.info(event)
        response.status = HttpServletResponse.SC_ACCEPTED
    }

}
