package com.ajp.yourgrade.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "branding")
public class ConfigProperties {

    @Value("${branding.survey-link}")
    private String SurveyLink;

    public String getSurveyLink() {
        return SurveyLink;
    }

    public void setSurveyLink(String surveyLink) {
        SurveyLink = surveyLink;
    }
}
