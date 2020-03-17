package com.docusign.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {

    private static final String ATTR_ENVELOPE_ID = "qpEnvelopeId";
    private static final String ATTR_STATE = "state";
    private static final String ATTR_EVENT = "event";
    private static final String ATTR_TITLE = "title";


    @GetMapping(path = "/")
    public String index(ModelMap model) {
        model.addAttribute(ATTR_TITLE,"Home");
        return "pages/index";
    }

    @GetMapping(path = "/ds/mustAuthenticate")
    public String mustAuthenticateController(ModelMap model) {
        model.addAttribute(ATTR_TITLE, "Authenticate with DocuSign");
        return "pages/ds_must_authenticate";
    }

    @GetMapping(path = "/ds-return")
    public String returnController(@RequestParam(value=ATTR_STATE, required = false) String state,
                                   @RequestParam(value=ATTR_EVENT, required = false) String event,
                                   @RequestParam(value="envelopeId", required = false) String envelopeId,
                                   ModelMap model) {
        model.addAttribute(ATTR_TITLE , "Return from DocuSign");
        model.addAttribute(ATTR_EVENT, event);
        model.addAttribute(ATTR_STATE, state);
        model.addAttribute(ATTR_ENVELOPE_ID, envelopeId);
        return "pages/ds_return";
    }
}
