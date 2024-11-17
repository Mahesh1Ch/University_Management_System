package com.identity.identity_service.config;

import com.identity.identity_service.utility.OtpUtility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig
{
    @Bean
    public OtpUtility getOtpUtility()
    {
        return new OtpUtility();
    }
}
