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

package org.symphonyoss.vb.services;



import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.symphonyoss.client.SymphonyClient;
import org.symphonyoss.client.model.Chat;
import org.symphonyoss.client.services.ChatListener;
import org.symphonyoss.client.util.MlMessageParser;
import org.symphonyoss.exceptions.SymException;
import org.symphonyoss.symphony.clients.model.SymMessage;
import org.symphonyoss.symphony.clients.model.SymUser;
import org.symphonyoss.vb.impl.CommandManager;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by frank.tarsillo on 9/2/2016.
 */
@Service
@ApplicationScoped
public class VotingSession implements ChatListener {
    private final Logger logger = LoggerFactory.getLogger(VotingSession.class);


    SymphonyClient symClient;
    MlMessageParser mlMessageParser;
    private CommandManager commandManager;
    private VoteBotService voteBotService;
    private boolean completed = false;




    Chat chat;
    String id;

    public VotingSession() {
    }

    public VotingSession(Chat chat, VoteBotService voteBotService) {
        this.chat = chat;
        this.voteBotService = voteBotService;
        this.symClient = voteBotService.getSymClient();
        mlMessageParser = new MlMessageParser(symClient);
        chat.addListener(this);
        commandManager = new CommandManager(this);
        chat.addListener(commandManager);



    }


    @Override
    public void onChatMessage(SymMessage message) {

        try {

            mlMessageParser.parseMessage(message.getMessage());

            SymUser symUser = symClient.getUsersClient().getUserFromId(message.getFromUserId());


            logger.info("New Message:" + message.getMessage());


        } catch (SymException e) {
            logger.error("Unable to parse message..", e);
        } catch (Exception e1) {

            e1.printStackTrace();
        }


    }


    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




    public SymphonyClient getSymClient() {
        return symClient;
    }

    public void endCall(){
        logger.info("Shutting down VotingSession for id [{}]",id);
        chat.removeListener(this);
        chat.removeListener(commandManager);
        voteBotService.removeCallSession(id);
    }

    public boolean isCompleted(){
        return completed;
    }


}
