
/*
 *
 *
 * Copyright 2016 The Symphony Software Foundation
 *
 * Licensed to The Symphony Software Foundation (SSF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package org.symphonyoss.vb.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public final class BotConfig {

    public static final Configuration Config;
    private final static Logger logger = LoggerFactory.getLogger(BotConfig.class);


    //Properties
    public final static String CONFIG_DIR = "bot.config.dir";
    public final static String CONFIG_FILE = "bot.properties";
    public final static String WAR_FILE = "war.file";
    public final static String MCP_MTM_USER = "mcp.mtm.user";
    public final static String MCP_MTM_PASSWORD = "mcp.mtm.password";
    public final static String KEYSTORE_PASSWORD = "keystore.password";
    public final static String TRUSTSTORE_PASSWORD = "truststore.password";
    public final static String SESSIONAUTH_URL = "sessionauth.url";
    public final static String KEYAUTH_URL = "keyauth.url";
    public final static String SYMPHONY_POD = "symphony.agent.pod.url";
    public final static String SYMPHONY_AGENT = "symphony.agent.agent.url";
    public final static String CERTS_DIR = "certs.dir";
    public final static String TRUSTSTORE_FILE = "truststore.file";
    public final static String BOT_USER = "bot.user";
    public final static String BOT_DOMAIN = "bot.domain";
    public final static String ADMIN_USER = "admin.user";
    public final static String FILES_JSON = "proxybot.files.json";


    //Env
    public final static String KEYSTORE_PASSWORD_ENV = "KEYSTORE_PASSWORD";
    public final static String TRUSTSTORE_PASSWORD_ENV = "TRUSTSTORE_PASSWORD";
    public final static String SESSIONAUTH_URL_ENV = "SESSION_AUTH";
    public final static String KEYAUTH_URL_ENV = "KEY_AUTH";
    public final static String SYMPHONY_POD_ENV = "SYMPHONY_POD";
    public final static String SYMPHONY_AGENT_ENV = "SYMPHONY_AGENT";
    public final static String CERTS_DIR_ENV = "CERTS";
    public final static String TRUSTSTORE_FILE_ENV = "TRUSTSTORE_FILE";
    public final static String BOT_USER_ENV = "BOT_USER";
    public final static String BOT_DOMAIN_ENV = "BOT_DOMAIN";
    public final static String MCP_MTM_USER_ENV = "MCP_MTM_USER";
    public final static String MCP_MTM_PASSWORD_ENV = "MCP_MTM_PASSWORD";


    static {


        String configDir = null;
        String propFile = null;


        Configuration c = null;


        try {


            configDir = System.getProperty(CONFIG_DIR);
            if (configDir == null)
                configDir = "config";

            if (propFile == null)
                propFile = CONFIG_FILE;

            propFile = configDir + "/" + propFile;

            logger.info("Using bot.properties file location: {}", propFile);

            c = new PropertiesConfiguration(propFile);


        } catch (ConfigurationException e) {

            logger.error("Configuration Init Exception: ", e);
            c = null;
        }

        Config = c;

        init();


    }


    public static Configuration getConfig() {
        return Config;
    }

    private static void init() {

        //The following defines the order of variables and configuration from Env variables, to System properties,
        // to Config driven.
        // System properties->Env Variables->Conig driven
        // Note: Both System and/or Env will be pushed into local "Config" properties.


        if (System.getProperty(KEYSTORE_PASSWORD) == null) {

            if (System.getenv(KEYSTORE_PASSWORD_ENV) != null) {
                System.setProperty(KEYSTORE_PASSWORD, System.getenv(KEYSTORE_PASSWORD_ENV));
            } else {
                System.setProperty(KEYSTORE_PASSWORD, Config.getString(KEYSTORE_PASSWORD));
            }

        }

        if (System.getProperty(TRUSTSTORE_PASSWORD) == null) {

            if (System.getenv(TRUSTSTORE_PASSWORD_ENV) != null) {
                System.setProperty(TRUSTSTORE_PASSWORD, System.getenv(TRUSTSTORE_PASSWORD_ENV));
            } else {
                System.setProperty(TRUSTSTORE_PASSWORD, Config.getString(TRUSTSTORE_PASSWORD));
            }

        }

        if (System.getProperty(SESSIONAUTH_URL) == null) {

            if (System.getenv(SESSIONAUTH_URL_ENV) != null) {
                System.setProperty(SESSIONAUTH_URL, System.getenv(SESSIONAUTH_URL_ENV));
            } else {
                System.setProperty(SESSIONAUTH_URL, Config.getString(SESSIONAUTH_URL));
            }

        }

        if (System.getProperty(KEYAUTH_URL) == null) {

            if (System.getenv(KEYAUTH_URL_ENV) != null) {
                System.setProperty(KEYAUTH_URL, System.getenv(KEYAUTH_URL_ENV));
            } else {
                System.setProperty(KEYAUTH_URL, Config.getString(KEYAUTH_URL));
            }

        }

        if (System.getProperty(SYMPHONY_POD) == null) {

            if (System.getenv(SYMPHONY_POD_ENV) != null) {
                System.setProperty(SYMPHONY_POD, System.getenv(SYMPHONY_POD_ENV));
            } else {
                System.setProperty(SYMPHONY_POD, Config.getString(SYMPHONY_POD));
            }

        }

        if (System.getProperty(SYMPHONY_AGENT) == null) {

            if (System.getenv(SYMPHONY_AGENT_ENV) != null) {
                System.setProperty(SYMPHONY_AGENT, System.getenv(SYMPHONY_AGENT_ENV));
            } else {
                System.setProperty(SYMPHONY_AGENT, Config.getString(SYMPHONY_AGENT));
            }

        }

        if (System.getProperty(CERTS_DIR) == null) {

            if (System.getenv(CERTS_DIR_ENV) != null) {
                System.setProperty(CERTS_DIR, System.getenv(CERTS_DIR_ENV));
            } else {
                System.setProperty(CERTS_DIR, Config.getString(CERTS_DIR));
            }

        }

        if (System.getProperty(TRUSTSTORE_FILE) == null) {

            if (System.getenv(TRUSTSTORE_FILE_ENV) != null) {
                System.setProperty(TRUSTSTORE_FILE, System.getenv(TRUSTSTORE_FILE_ENV));
            } else {
                System.setProperty(TRUSTSTORE_FILE, Config.getString(TRUSTSTORE_FILE));
            }

        }

        if (System.getProperty(BOT_USER) == null) {

            if (System.getenv(BOT_USER_ENV) != null) {
                System.setProperty(BOT_USER, System.getenv(BOT_USER_ENV));
            } else {
                System.setProperty(BOT_USER, Config.getString(BOT_USER));
            }

        }


        if (System.getProperty(BOT_DOMAIN) == null) {

            if (System.getenv(BOT_DOMAIN_ENV) != null) {
                System.setProperty(BOT_DOMAIN, System.getenv(BOT_DOMAIN_ENV));
            } else {
                System.setProperty(BOT_DOMAIN, Config.getString(BOT_DOMAIN));
            }

        }


        if (System.getProperty(MCP_MTM_USER) == null) {

            if (System.getenv(MCP_MTM_USER_ENV) != null) {
                System.setProperty(MCP_MTM_USER, System.getenv(MCP_MTM_USER_ENV));
            } else {
                System.setProperty(MCP_MTM_USER, Config.getString(MCP_MTM_USER));
            }
        }


        if (System.getProperty(MCP_MTM_PASSWORD) == null) {

            if (System.getenv(MCP_MTM_PASSWORD_ENV) != null) {
                System.setProperty(MCP_MTM_PASSWORD, System.getenv(MCP_MTM_PASSWORD_ENV));
            } else {
                System.setProperty(MCP_MTM_PASSWORD, Config.getString(MCP_MTM_PASSWORD));
            }
        }


        if (System.getProperty(WAR_FILE) == null)
            System.setProperty(WAR_FILE, Config.getString(WAR_FILE));

    }

}
