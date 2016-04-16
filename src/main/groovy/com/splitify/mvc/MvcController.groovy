package com.splitify.mvc

import com.splitify.mvc.feed.FeedService
import com.splitify.mvc.split.SplitRequest
import com.splitify.mvc.split.SplitService
import com.splitify.mvc.webhook.WebhookEvent
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

import javax.servlet.http.HttpServletResponse

@Controller
class MvcController {

    private static final Logger logger = LogManager.getLogger(MvcController);

    @Autowired
    SplitService splitService

    @Autowired
    FeedService feedService

    @RequestMapping(value = "/")
    def @ResponseBody hello() {
        logger.info("Hello World")
        return "Hello World!"
    }

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="transactionId", required=true) String transactionId,
                           @RequestParam(value="accountId", required=true) String accountId,
                           Model model) {
        model.addAttribute("transactionId", transactionId)
        model.addAttribute("accountId", accountId)
        model.addAttribute("friends", [[userId: "11111", name: "Name 1"],[userId: "22222", name: "Name 2"],[userId: "33333", name: "Name 3"]])
    //    model.addAttribute("splitRequest", new SplitRequest())
        return "greetingView"
    }

    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    void receive(@RequestBody String payload, HttpServletResponse response) {
        logger.info("Event received through WebHook: " + payload)

        WebhookEvent event = WebhookEvent.parse(payload)
        logger.info(event)

        feedService.createDummyFeed(event.accountId)

        response.status = HttpServletResponse.SC_ACCEPTED
    }

    @RequestMapping(value = "/split", method = RequestMethod.POST)
    void split(@ModelAttribute SplitRequest splitRequest, HttpServletResponse response) {
        logger.info("Splitting order " + splitRequest)

        splitService.split(splitRequest)

        response.status = HttpServletResponse.SC_CREATED
    }
}

