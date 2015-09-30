package me.thomas.security;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by zhaoxs on 2015/9/29 0029.
 */
public class SecurityServicesFactoryBean implements FactoryBean<SecurityServices> {

    protected SecurityServiceConfiguration securityServiceConfiguration;

    public SecurityServices getObject() throws Exception {
        return securityServiceConfiguration.buildSecurityServices();
    }

    public Class<?> getObjectType() {
        return SecurityServices.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public SecurityServiceConfiguration getSecurityServiceConfiguration() {
        return securityServiceConfiguration;
    }

    public void setSecurityServiceConfiguration(SecurityServiceConfiguration securityServiceConfiguration) {
        this.securityServiceConfiguration = securityServiceConfiguration;
    }
}
