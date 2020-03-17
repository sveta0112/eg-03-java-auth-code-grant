# Java: Authorization Code Grant Examples

### Github repo: eg-03-java-auth-code-grant
## Introduction
This repo is a Java Spring Boot application that demonstrates:

* Authentication with DocuSign via the
  [Authorization Code Grant flow](https://developers.docusign.com/esign-rest-api/guides/authentication/oauth2-code-grant).
  The Spring Security library is used.

1. **Embedded Signing Ceremony.**
   [Source.](./src/main/java/com/docusign/controller/examples/EG001ControllerEmbeddedSigning.java)
   This example sends an envelope, and then uses an embedded signing ceremony for the first signer.
   With embedded signing, the DocuSign signing ceremony is initiated from your website.
1. **Send an envelope with a remote (email) signer and cc recipient.**
   [Source.](./src/main/java/com/docusign/controller/examples/EG002ControllerSigningViaEmail.java)
   The envelope includes a pdf, Word, and HTML document.
   Anchor text ([AutoPlace](https://support.docusign.com/en/guides/AutoPlace-New-DocuSign-Experience)) is used to position the signing fields in the documents.
1. **List envelopes in the user's account.**
   [Source.](./src/main/java/com/docusign/controller/examples/EG003ControllerListEnvelopes.java)
1. **Get an envelope's basic information.**
   [Source.](./src/main/java/com/docusign/controller/examples/EG004ControllerEnvelopeInfo.java)
   The example lists the basic information about an envelope, including its overall status.
1. **List an envelope's recipients and their current status.**
   [Source.](./src/main/java/com/docusign/controller/examples/EG005ControllerEnvelopeRecipients.java)
1. **List an envelope's documents.**
   [Source.](./src/main/java/com/docusign/controller/examples/EG006ControllerEnvelopeDocs.java)
1. **Download an envelope's documents.** The example can download individual
   documents, the documents concatenated together, or a zip file of the documents.
   [Source.](./src/main/java/com/docusign/controller/examples/EG007ControllerEnvelopeGetDoc.java)
1. **Programmatically create a template.**
   [Source.](./src/main/java/com/docusign/controller/examples/EG008ControllerCreateTemplate.java)
1. **Send an envelope using a template.**
   [Source.](./src/main/java/com/docusign/controller/examples/EG009ControllerUseTemplate.java)
1. **Send an envelope and upload its documents with multipart binary transfer.**
   [Source.](./src/main/java/com/docusign/controller/examples/EG010ControllerSendBinaryDocs.java)
   Binary transfer is 33% more efficient than using Base64 encoding.
1. **Embedded sending.**
   [Source.](./src/main/java/com/docusign/controller/examples/EG011ControllerEmbeddedSending.java)
1. **Embedded DocuSign web tool (NDSE).**
   [Source.](./src/main/java/com/docusign/controller/examples/EG012ControllerEmbeddedConsole.java)
1. **Embedded Signing Ceremony from a template with an added document.**
   [Source.](./src/main/java/com/docusign/controller/examples/EG013ControllerAddDocToTemplate.java)
   This example sends an envelope based on a template.
   In addition to the template's document(s), the example adds an
   additional document to the envelope by using the
   [Composite Templates](https://developers.docusign.com/esign-rest-api/guides/features/templates#composite-templates)
   feature.
1. **Payments example: an order form, with online payment by credit card.**
   [Source.](./src/main/java/com/docusign/controller/examples/EG014ControllerCollectPayment.java)
1. **Get the envelope tab data.** (Coming soon...)
1. **Set envelope tab values.** (Coming soon...)
1. **Set template tab values.** (Coming soon...)
1. **Get the envelope custom field data (metadata).** (Coming soon...)
1. **Requiring an Access Code for a Recipient**
   [Source.](./src/main/java/com/docusign/controller/examples/EG019ControllerAccessCodeAuthentication.java)
   This example sends and envelope that requires an access-code for the purpose of multi-factor authentication.
1. **Requiring SMS authentication for a recipient**
   [Source.](./src/main/java/com/docusign/controller/examples/EG020ControllerSmsAuthentication.java)
   This example sends and envelope that requires entering in a six digit code from an text message for the purpose of multi-factor authentication.
1. **Requiring Phone authentication for a recipient**
   [Source.](./src/main/java/com/docusign/controller/examples/EG021ControllerPhoneAuthentication.java)
   This example sends and envelope that requires entering in a voice-based response code for the purpose of multi-factor authentication.
1. **Requiring Knowledge-Based Authentication (KBA) for a Recipient**
   [Source.](./src/main/java/com/docusign/controller/examples/EG022ControllerKBAAuthentication.java)
   This example sends and envelope that requires passing a Public records check to validate identity for the purpose of multi-factor authentication.
1. **Requiring ID Verification (IDV) for a recipient**  (Coming soon...)  
1. **Creating a permission profile** (Coming soon...)
1. **Setting a permission profile**
   [Source.](./src/main/java/com/docusign/controller/examples/EG025ControllerPermissionSetUserGroups.java)
   This code example demonstrates how to set a user group's permission profile using the [Update Group](https://developers.docusign.com/esign-rest-api/reference/UserGroups/Groups/update) method. 
   You must have already created permissions profile and group of users.
1. **Updating individual permission settings** (Coming Soon...)
1. **Deleting a permission profile**
   [Source.](./src/main/java/com/docusign/controller/examples/EG027ControllerPermissionDelete.java)
   This code example demonstrates how to an account's permission profile using the [Delete AccountPermissionProfiles](https://developers.docusign.com/esign-rest-api/reference/Accounts/AccountPermissionProfiles/delete) method. 
1. **Creating a brand**
   [Source.](./src/main/java/com/docusign/controller/examples/EG028ControllerCreateBrand.java)
   This example creates brand profile for an account using the [Create Brand](https://developers.docusign.com/esign-rest-api/reference/Accounts/AccountBrands/create) method.
1. **Applying a brand to an envelope**
   [Source.](./src/main/java/com/docusign/controller/examples/EG029ControllerApplyBrandToEnvelope.java)
   This code example demonstrates how to apply a brand you've created to an envelope using the [Create Envelope](https://developers.docusign.com/esign-rest-api/reference/Envelopes/Envelopes/create) method. 
   First, creates the envelope and then applies brand to it.
   Anchor text ([AutoPlace](https://support.docusign.com/en/guides/AutoPlace-New-DocuSign-Experience)) is used to position the signing fields in the documents.
1. **Applying a brand to a template**
   [Source.](./src/main/java/com/docusign/controller/examples/EG030ControllerApplyBrandToTemplate.java)
   This code example demonstrates how to apply a brand you've created to a template using using the [Create Envelope](https://developers.docusign.com/esign-rest-api/reference/Envelopes/Envelopes/create) method. 
   You must have at least one created template and brand.
   Anchor text ([AutoPlace](https://support.docusign.com/en/guides/AutoPlace-New-DocuSign-Experience)) is used to position the signing fields in the documents.
1. **Bulk sending envelopes to multiple recipients** (Coming soon...)


## Installation

### Prerequisites
1. A DocuSign Developer Sandbox account (email and password) on [demo.docusign.net](https://demo.docusign.net).
   Create a [free account](https://go.docusign.com/sandbox/productshot/?elqCampaignId=16533).
1. A DocuSign Integration Key (a client ID) that is configured to use the
   OAuth Authorization Code flow.
   You will need the **Integration Key** itself, and its **secret**.

   If you use this example on your own workstation,
   the Integration key must include a **Redirect URI** of `http://localhost:8080/login`

   If you will not be running the example on your own workstation,
   use the appropriate DNS name and port instead of `localhost:8080`.
   An example Redirect URI: http://myserver.it.mycompany.com/login

1. Java 11.
1. A name and email for a signer, and a name and email for a cc recipient.
   The signer and the cc email cannot be the same.
1. Maven

### Short installation instructions
* Download or clone this repository.
* The project includes a Maven pom file.
* Create a file `eg-03-java-auth-code-grant/src/main/resources/application-dev.properties` and configure the project by overriding necessary properties from the `application.properties` file. **Don't add this file into the Git index.**
* Add VM argument `-Dspring.profiles.active=dev` to your IDE
* Note that IntelliJ Community Edition does not directly support
  Spring Boot applications.  

### Build and run
Examples are built as a dedicated application with embedded TomCat server. Build:  
``` bash
$ cd eg-03-java-auth-code-grant
$ mvn package
```
Run:  
``` bash
$ cd target
$ java -Dspring.profiles.active=dev -jar eg-03-java-auth-code-grant-1.0-SNAPSHOT.war
```

### IntelliJ Ultimate installation

See the [IntelliJ Ultimate instructions](https://github.com/docusign/eg-03-java-auth-code-grant/blob/master/docs/Readme_IntelliJ_Ultimate.md).

## Configure the example

Configure the example via the properties file:
`eg-03-java-auth-code-grant/src/main/resources/application-dev.properties`.

Add the client id, secret, signer name and email to the file.
Also add the URL that the application will use (the **DS_APP_URL** setting).
By default, this is http://localhost:8080

You must also add a **Redirect URI** to the client id's settings in
DocuSign. The Redirect URI is `/login` appended to the DS_APP_URL setting.
Eg http://localhost:8080/login  

### Payments code example
To use the payments example, create a
test payments gateway for your developer sandbox account.

See the
[PAYMENTS_INSTALLATION.md](https://github.com/docusign/eg-03-java-auth-code-grant/blob/master/PAYMENTS_INSTALLATION.md)
file for instructions.

Then add the payment gateway account id to the **application.properties** file.

## Using the examples with other authentication flows

The examples in this repository can also be used with the
JWT OAuth flow.
See the [Authentication guide](https://developers.docusign.com/esign-rest-api/guides/authentication)
for information on choosing the right authentication flow for your application.

## License and additional information

### License
This repository uses the MIT License. See the LICENSE file for more information.

### Pull Requests
Pull requests are welcomed. Pull requests will only be considered if their content
uses the MIT License.
