package com.docusign.controller.examples;

import com.docusign.DSConfiguration;
import com.docusign.esign.api.AccountsApi;
import com.docusign.esign.api.EnvelopesApi;
import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.model.DoneExample;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;


/**
 * Abstract base class for all controllers. It handles all requests which are
 * registered by the derived classes and delegates an example specific action
 * back to the appropriate controllers. If a user had not been authorized, it
 * redirects him onto an authentication page. Derived classes must override an
 * abstract method {@link #doWork(WorkArguments, ModelMap, HttpServletResponse)}
 * to do something. This method is called from the POST request. Optionally you
 * can override method {@link #onInitModel(WorkArguments, ModelMap)} to add
 * example specific attributes into a page.
 */
@Controller
public abstract class AbstractController {

    protected static final String MODEL_ENVELOPE_OK = "envelopeOk";
    private static final String REDIRECT_PREFIX = "redirect:";
    private static final String REDIRECT_AUTHENTICATION_PAGE = REDIRECT_PREFIX + "/ds/mustAuthenticate";
    private static final String EXAMPLE_PAGES_PATH = "pages/examples/";
    protected static final String BEARER_AUTHENTICATION = "Bearer ";
    protected static final String DONE_EXAMPLE_PAGE = "pages/example_done";
    protected static final String DONE_EXAMPLE_PAGE_COMPARE = "pages/example_done_compare";
    protected static final String ERROR_PAGE = "error.jsp";

    protected final String exampleName;
    protected final String title;
    private final String pagePath;
    protected final DSConfiguration config;


    public AbstractController(DSConfiguration config, String exampleName, String title) {
        this.config = config;
        this.exampleName = exampleName;
        pagePath = EXAMPLE_PAGES_PATH + exampleName;
        this.title = title;
    }

    @GetMapping
    public String get(WorkArguments args, ModelMap model) {
        if (!checkToken()) {
            return REDIRECT_AUTHENTICATION_PAGE;
        }

        try {
            onInitModel(args, model);
            return pagePath;
        } catch (ApiException exception) {
            return handleException(exception, model);
        }
    }

    @PostMapping
    public Object create(WorkArguments args, ModelMap model, HttpServletResponse response) {
        if (!checkToken()) {
            return REDIRECT_AUTHENTICATION_PAGE;
        }

        try {
            return doWork(args, model, response);
        } catch (IOException|ApiException exception) {
            return handleException(exception, model);
        }
    }

    /**
     * This method is called from the GET request and it should initialize a
     * model. Override this method if it is necessary to add example specific
     * attributes into the model.
     * @param args the session attributes
     * @param model the model data
     * @throws ApiException if calling eSign API has failed
     */
    protected void onInitModel(WorkArguments args, ModelMap model) throws ApiException {
        Class<?> clazz = Objects.requireNonNullElse(getClass().getEnclosingClass(), getClass());
        String srcPath = String.join("", config.getExampleUrl(), clazz.getName().replace('.', '/'), ".java");
        model.addAttribute("csrfToken", "");
        model.addAttribute("title", title);
        model.addAttribute("sourceFile", clazz.getSimpleName() + ".java");
        model.addAttribute("sourceUrl", srcPath);
        model.addAttribute("documentation", config.getDocumentationPath() + exampleName);
    }

    /**
     * This method is called from the POST request. Example pages must override
     * it. Overridden method should return an Object means URL to redirect.
     * @param args the work arguments got from the current page
     * @param model page model holder
     * @param response for HTTP-specific functionality in sending a response
     * @return {@link Object}. Possible types and values: <code>null</code>,
     * {@link String} representation of the URL or Spring RedirectView object,
     * (see {@link org.springframework.web.servlet.view.RedirectView RedirectView})
     * @throws ApiException if calling eSignature API has failed
     * @throws IOException if I/O operation has failed
     */
    protected abstract Object doWork(WorkArguments args, ModelMap model,
            HttpServletResponse response) throws ApiException, IOException;

    /**
     * Creates new instance of the eSignature API client.
     * @param basePath URL to eSignature REST API
     * @param userAccessToken user's access token
     * @return an instance of the {@link ApiClient}
     */
    protected static ApiClient createApiClient(String basePath, String userAccessToken) {
        ApiClient apiClient = new ApiClient(basePath);
        apiClient.addDefaultHeader(HttpHeaders.AUTHORIZATION, BEARER_AUTHENTICATION + userAccessToken);
        // it is a workaround to NPE, see DSPW-61
        apiClient.addAuthorization("docusignAccessCode", null);
        return apiClient;
    }

    /**
     * Creates a new instance of the eSignature EnvelopesApi. This method
     * creates an instance of the ApiClient class silently.
     * @param basePath URL to eSignature REST API
     * @param userAccessToken user's access token
     * @return an instance of the {@link EnvelopesApi}
     */
    protected static EnvelopesApi createEnvelopesApi(String basePath, String userAccessToken) {
        ApiClient apiClient = createApiClient(basePath, userAccessToken);
        return new EnvelopesApi(apiClient);
    }

    /**
     * Creates a new instance of the eSignature AccountsApi. This method
     * creates an instance of the ApiClient class silently.
     * @param basePath URL to eSignature REST API
     * @param userAccessToken user's access token
     * @return an instance of the {@link AccountsApi}
     */
    protected static AccountsApi createAccountsApi(String basePath, String userAccessToken) {
        ApiClient apiClient = createApiClient(basePath, userAccessToken);
        return new AccountsApi(apiClient);
    }

    private String handleException(Exception exception, ModelMap model) {
        new DoneExample()
                .withTitle(exampleName)
                .withName(title)
                .withMessage(exception.getMessage())
                .withStackTrace(exception.getStackTrace())
                .addToModel(model);
        return ERROR_PAGE;
    }

    private static boolean checkToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof OAuth2Authentication)) {
            return false;
        }

        OAuth2Authentication oauth = (OAuth2Authentication) authentication;
        return oauth.getUserAuthentication().isAuthenticated();
    }
}
