/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.alipay.mazexiang.main.controller;

import org.springframework.boot.context.properties.source.ConfigurationProperty;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.origin.Origin;
import org.springframework.boot.origin.OriginLookup;
import org.springframework.core.env.PropertySource;

import java.util.Iterator;

/**
 *
 * @author mazexiang
 * @version $Id: MyConfigurationPropertySourcesPropertySource.java, v 0.1 2018Äê12ÔÂ08ÈÕ 18:54 mazexiang Exp $
 */
public class MyConfigurationPropertySourcesPropertySource extends PropertySource<Iterable<ConfigurationPropertySource>>
        implements OriginLookup<String> {
    public MyConfigurationPropertySourcesPropertySource(String name, Iterable<ConfigurationPropertySource> source) {
        super(name, source);
    }
    public Object getProperty(String name) {
        ConfigurationProperty configurationProperty = this.findConfigurationProperty(name);
        return configurationProperty == null?null:configurationProperty.getValue();
    }

    public Origin getOrigin(String name) {
        return Origin.from(this.findConfigurationProperty(name));
    }

    private ConfigurationProperty findConfigurationProperty(String name) {
        try {
            if(ConfigurationPropertyName.isValid(name)) {
                return this.findConfigurationProperty(ConfigurationPropertyName.of(name));
            }
        } catch (Exception var3) {
            ;
        }

        return null;
    }

    private ConfigurationProperty findConfigurationProperty(ConfigurationPropertyName name) {
        if(name == null) {
            return null;
        } else {
            Iterator var2 = ((Iterable)this.getSource()).iterator();

            ConfigurationProperty configurationProperty;
            do {
                if(!var2.hasNext()) {
                    return null;
                }

                ConfigurationPropertySource configurationPropertySource = (ConfigurationPropertySource)var2.next();
                configurationProperty = configurationPropertySource.getConfigurationProperty(name);
            } while(configurationProperty == null);

            return configurationProperty;
        }
    }
}