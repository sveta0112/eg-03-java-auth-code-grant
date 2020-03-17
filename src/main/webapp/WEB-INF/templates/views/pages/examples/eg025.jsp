<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../../partials/head.jsp"/>

<h4>25. ${title}.</h4>

<p>
    Permission profiles are collections of account settings that determine the behavior and actions
    available to the user groups to which they're applied. This code example demonstrates how to
    the permission profile for the group with the eSignature REST API.
</p>

<c:if test="${showDoc}">
    <p><a target='_blank' href='${documentation}'>Documentation</a> about this example.</p>
</c:if>

<p>API method used:
    <a target='_blank' rel="noopener noreferrer" href="https://developers.docusign.com/esign-rest-api/reference/UserGroups/Groups/update">Groups::update</a>.
</p>

<p>
    View source file <a target="_blank" href="${sourceUrl}">${sourceFile}</a> on GitHub.
</p>


<form class="eg" action="" method="post" data-busy="form">
    <div class="form-group">
        <label for="profileId">Select permission profile to update</label>
        <select id="profileId" name="profileId" class="form-control">
            <c:forEach items="${listProfiles}" var="profile">
                <option value="${profile.permissionProfileId}">${profile.permissionProfileName}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="groupId">Select permission profile to update</label>
        <select id="groupId" name="groupId" class="form-control">
            <c:forEach items="${listGroups}" var="group">
                <option value="${group.groupId}">${group.groupName}</option>
            </c:forEach>
        </select>
    </div>

    <input type="hidden" name="_csrf" value="${csrfToken}">
    <button type="submit" class="btn btn-primary">Submit</button>
</form>

<jsp:include page="../../partials/foot.jsp"/>