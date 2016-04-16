package com.splitify.mvc

import com.splitify.mvc.webhook.WebhookEvent
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestParam
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

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name)
        return "greetingView"
    }

    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    void receive(@RequestBody String payload, HttpServletResponse response) {
        logger.info("Event received through WebHook: " + payload)

        WebhookEvent event = WebhookEvent.parse(payload)
        logger.info(event)

        response.status = HttpServletResponse.SC_ACCEPTED
    }
}

