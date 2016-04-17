package com.splitify.mvc

import com.splitify.mvc.feed.FeedService
import com.splitify.mvc.friends.FriendsRepository
import com.splitify.mvc.split.SplitRequest
import com.splitify.mvc.split.SplitService
import com.splitify.mvc.transaction.TransactionHelper
import com.splitify.mvc.webhook.WebhookEvent
import com.splitify.mvc.webhook.WebhookService
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

    @Autowired
    FriendsRepository friendsRepository

    @Autowired
    WebhookService webhookService

    @RequestMapping(value = "/")
    def @ResponseBody
    hello() {
        logger.info("Hello World")
        return "Hello World!"
    }

    @RequestMapping(value = "/splitAsk", method = RequestMethod.GET)
    public String splitAsk(@RequestParam(value = "transactionId", required = true) String transactionId,
                           @RequestParam(value = "accountId", required = true) String accountId,
                           @RequestParam(value = "amount", required = true) String amount,
                           Model model) {
        model.addAttribute("transactionId", transactionId)
        model.addAttribute("accountId", accountId)
        model.addAttribute("amount", amount)
        model.addAttribute("myFriends", friendsRepository.retrieveFriendsExcludingByAccount(accountId))
        model.addAttribute("splitRequest", new SplitRequest())
        return "splitAskView"
    }

    @RequestMapping(value = "/notify", method = RequestMethod.POST)
    void receive(@RequestBody String payload, HttpServletResponse response) {
        logger.info("Event received through WebHook: " + payload)

        WebhookEvent event = WebhookEvent.parse(payload)
        logger.info(event)

        if (isSplitApplicable(event)) { //TODO it should be in a service later
            feedService.sendSplitAsk(event)
        }

        response.status = HttpServletResponse.SC_OK
    }

    @RequestMapping(value = "/split", method = RequestMethod.POST)
    String split(@ModelAttribute SplitRequest splitRequest, HttpServletResponse response) {
        logger.info("Splitting order " + splitRequest)

        splitService.split(splitRequest)

        response.status = HttpServletResponse.SC_CREATED
        return "splitView"
    }

    @RequestMapping(value = "/moneyAsk", method = RequestMethod.GET)
    String moneyAsk(@RequestParam(value = "transactionId", required = true) String transactionId,
                    @RequestParam(value = "amountToPay", required = true) String amountToPay,
                    Model model) {
        logger.info("Asking for the transaction " + transactionId)

        //TODO retrieve information for view: Name of person, phone number, description of merchant, date
        model.addAttribute("name", "Juan")
        model.addAttribute("amountToPay", amountToPay)
        model.addAttribute("phoneNumber", "+447557932500")
        model.addAttribute("merchantName", "Starbucks")
        model.addAttribute("dateTransaction", "17/04/2016")
        model.addAttribute("timeTransaction", "12:21")
        return "moneyAskView"
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    void register(HttpServletResponse response) {
        friendsRepository.friends.each { friend ->
            webhookService.registerWebhook(friend)
        }
        response.status = HttpServletResponse.SC_OK
    }

    @RequestMapping(value = "/unregister", method = RequestMethod.POST)
    void unregister(HttpServletResponse response) {
        friendsRepository.friends.each { friend ->
            webhookService.unregisterWebhook(friend)
        }
        response.status = HttpServletResponse.SC_OK
    }

    private boolean isSplitApplicable(WebhookEvent webhookEvent) {
        return TransactionHelper.isDebit(webhookEvent.amount)
    }
}

