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



import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.symphonyoss.vb.services.VoteBotService;
import org.symphonyoss.vb.services.VotingSession;

import javax.inject.Singleton;

/**
 * Created by frank.tarsillo on 8/29/2016.
 */
public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(VoteApiService.class).to(VoteApiService.class);
        bind(VoteBotService.class).to(VoteBotService.class).in(Singleton.class);
        bind(VotingSession.class).to(VotingSession.class);

    }
}