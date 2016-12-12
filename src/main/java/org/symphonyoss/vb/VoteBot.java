

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

package org.symphonyoss.vb;

import org.symphonyoss.vb.config.BotConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.symphonyoss.vb.services.VoteBotService;

import javax.inject.Inject;

import static org.symphonyoss.vb.config.BotConfig.Config;

/**
 * Created by Frank on 9/4/2015.
 */
public class VoteBot {
    private static Logger logger = LoggerFactory.getLogger(VoteBot.class);


    VoteBotService voteBotService = VoteBotService.get();

    public VoteBot() {

        voteBotService = VoteBotService.get();

//
//        Server server = new Server(Config.getInt("jetty.port",8080));
//
//
//        WebAppContext webapp = new WebAppContext();
//        webapp.setContextPath(Config.getString("jetty.contextpath","/symphony"));
//        webapp.setWar(System.getProperty(BotConfig.WAR_FILE));
//        logger.info("Using war file {}", System.getProperty(BotConfig.WAR_FILE));
//
//        webapp.setClassLoader(Thread.currentThread().getContextClassLoader());
//        webapp.setServerClasses(new String[]{"-org.eclipse.jetty.servlet.ServletContextHandler.Decorator"});
//        server.setHandler(webapp);
//
//
//
//        try {
//
//            try {
//                server.start();
//            }catch(IllegalStateException e){}
//
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException e) {
//            }
//
//            server.join();
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//            logger.error("Could not initialize Jetty server.");
//
//        }
    }

    public static void main(String[] args) throws Exception {


        new VoteBot();

    }



}