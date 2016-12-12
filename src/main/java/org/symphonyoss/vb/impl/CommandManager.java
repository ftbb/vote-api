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

package org.symphonyoss.vb.impl;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.symphonyoss.client.ai.*;
import org.symphonyoss.client.util.MlMessageParser;
import org.symphonyoss.symphony.clients.model.SymMessage;
import org.symphonyoss.symphony.clients.model.SymUser;
import org.symphonyoss.vb.services.VotingSession;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by frank.tarsillo on 9/23/2016.
 */
public class CommandManager extends AiCommandListener implements AiAction, AiPermission {

    private boolean affirmed = false;
    private final Logger logger = LoggerFactory.getLogger(CommandManager.class);
    private VotingSession votingSession;
    private boolean endCall = false;


    public CommandManager(VotingSession votingSession) {
        super(votingSession.getSymClient());
        this.votingSession = votingSession;

        init();
    }

    public void init() {

        AiCommand affirmCommand = new AiCommand("affirm", 1, "");
        affirmCommand.setArgument(0, "ticketId");
        affirmCommand.addAction(this);
        affirmCommand.addPermission(this);
        getActiveCommands().add(affirmCommand);


    }

    @Override
    public AiResponseSequence respond(MlMessageParser mlMessageParser, SymMessage message, AiCommand command) {

        logger.info("RESPONSE RECEIVED: {}", command.getCommand());

        AiResponseSequence responseSequence = new AiResponseSequence();
        AiResponse aiResponse = null;

        String[] chunks = mlMessageParser.getTextChunks();

        List<SymUser> symUsers = new ArrayList<>();
        symUsers.add(message.getSymUser());


        aiResponse = new AiResponse("Sorry, you must /affirm trade first.", SymMessage.Format.TEXT, symUsers);
        responseSequence.addResponse(aiResponse);
        return responseSequence;


    }


    @Override
    public boolean userHasPermission(Long userID) {
        return true;
//        logger.info("Permission check: {} {}", userID, buySideSymUser.getId());
//        return buySideSymUser.getId().equals(userID);

    }


}
