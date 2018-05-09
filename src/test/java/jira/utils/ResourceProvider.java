package jira.utils;

import gmaps.utils.PayLoadProvider;

public class ResourceProvider {

    public String provideHost(){
        return PayloadProvider.provideHostValue().getProperty("HOST");
    }

    public String provideSessionResource(){
        return "/rest/auth/1/session";
    }

    public String provideCreateIssueResource(){
        return "/rest/api/2/issue";
    }

    public String provideAddCommentResource(){
        return "/rest/api/2/issue/{issueIdOrKey}/comment";
    }
}
