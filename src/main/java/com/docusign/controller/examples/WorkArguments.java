package com.docusign.controller.examples;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.Data;


@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@Data
public class WorkArguments {
    private String item;
    private String quantity;
    private String signerEmail;
    private String signerName;
    private String ccEmail;
    private String ccName;
    private String signerEmail2;
    private String signerName2;
    private String ccEmail2;
    private String ccName2;
    private String status;
    private String startingView;
    private String dsReturnUrl;
    private String dsPingUrl;
    private String signerClientId;
    private String docSelect;
    private String accessCode;
    private String brandName;
    private String language;
    private String brandId;
    private String templateId;
    private String profileId;
    private String groupId;
}
