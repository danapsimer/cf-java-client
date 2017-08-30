/*
 * Copyright 2013-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.cloudfoundry.reactor.networking;

import org.cloudfoundry.networking.policies.CreatePoliciesRequest;
import org.cloudfoundry.networking.policies.DeletePoliciesRequest;
import org.cloudfoundry.networking.policies.Destination;
import org.cloudfoundry.networking.policies.ListPoliciesRequest;
import org.cloudfoundry.networking.policies.ListPoliciesResponse;
import org.cloudfoundry.networking.policies.Policy;
import org.cloudfoundry.networking.policies.Ports;
import org.cloudfoundry.networking.policies.Source;
import org.cloudfoundry.reactor.InteractionContext;
import org.cloudfoundry.reactor.TestRequest;
import org.cloudfoundry.reactor.TestResponse;
import org.cloudfoundry.reactor.networking.policies.ReactorPolicies;
import org.junit.Test;
import reactor.test.StepVerifier;

import java.time.Duration;

import static io.netty.handler.codec.http.HttpMethod.GET;
import static io.netty.handler.codec.http.HttpMethod.POST;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;

public final class ReactorNetworkingClientTest extends AbstractNetworkingApiTest {

    private final ReactorPolicies policies = new ReactorPolicies(CONNECTION_CONTEXT, this.root, TOKEN_PROVIDER);

    @Test
    public void create() {
        mockRequest(InteractionContext.builder()
            .request(TestRequest.builder()
                .method(POST).path("/v1/external/policies")
                .payload("fixtures/networking/policies/POST_request.json")
                .build())
            .response(TestResponse.builder()
                .status(OK)
                .build())
            .build());

        this.policies
            .create(CreatePoliciesRequest.builder()
                .policy(Policy.builder()
                    .destination(Destination.builder()
                        .id("38f08df0-19df-4439-b4e9-61096d4301ea")
                        .protocol("tcp")
                        .ports(Ports.builder()
                            .end(1235)
                            .start(1234)
                            .build())
                        .build())
                    .source(Source.builder()
                        .id("1081ceac-f5c4-47a8-95e8-88e1e302efb5")
                        .build())
                    .build())
                .policy(Policy.builder()
                    .destination(Destination.builder()
                        .id("308e7ef1-63f1-4a6c-978c-2e527cbb1c36")
                        .protocol("tcp")
                        .ports(Ports.builder()
                            .end(1235)
                            .start(1234)
                            .build())
                        .build())
                    .source(Source.builder()
                        .id("308e7ef1-63f1-4a6c-978c-2e527cbb1c36")
                        .build())
                    .build())
                .build())
            .as(StepVerifier::create)
            .expectComplete()
            .verify(Duration.ofSeconds(5));
    }

    @Test
    public void delete() {
        mockRequest(InteractionContext.builder()
            .request(TestRequest.builder()
                .method(POST).path("/v1/external/policies/delete")
                .payload("fixtures/networking/policies/POST_delete_request.json")
                .build())
            .response(TestResponse.builder()
                .status(OK)
                .build())
            .build());

        this.policies
            .delete(DeletePoliciesRequest.builder()
                .policy(Policy.builder()
                    .destination(Destination.builder()
                        .id("38f08df0-19df-4439-b4e9-61096d4301ea")
                        .protocol("tcp")
                        .ports(Ports.builder()
                            .end(1235)
                            .start(1234)
                            .build())
                        .build())
                    .source(Source.builder()
                        .id("1081ceac-f5c4-47a8-95e8-88e1e302efb5")
                        .build())
                    .build())
                .policy(Policy.builder()
                    .destination(Destination.builder()
                        .id("308e7ef1-63f1-4a6c-978c-2e527cbb1c36")
                        .protocol("tcp")
                        .ports(Ports.builder()
                            .end(1235)
                            .start(1234)
                            .build())
                        .build())
                    .source(Source.builder()
                        .id("308e7ef1-63f1-4a6c-978c-2e527cbb1c36")
                        .build())
                    .build())
                .build())
            .as(StepVerifier::create)
            .expectComplete()
            .verify(Duration.ofSeconds(5));
    }

    @Test
    public void list() {
        mockRequest(InteractionContext.builder()
            .request(TestRequest.builder()
                .method(GET).path("/v1/external/policies")
                .build())
            .response(TestResponse.builder()
                .status(OK)
                .payload("fixtures/networking/policies/GET_response.json")
                .build())
            .build());

        this.policies
            .list(ListPoliciesRequest.builder()
                .build())
            .as(StepVerifier::create)
            .expectNext(ListPoliciesResponse.builder()
                .totalPolicies(2)
                .policy(Policy.builder()
                    .destination(Destination.builder()
                        .id("38f08df0-19df-4439-b4e9-61096d4301ea")
                        .protocol("tcp")
                        .ports(Ports.builder()
                            .end(1235)
                            .start(1234)
                            .build())
                        .build())
                    .source(Source.builder()
                        .id("1081ceac-f5c4-47a8-95e8-88e1e302efb5")
                        .build())
                    .build())
                .policy(Policy.builder()
                    .destination(Destination.builder()
                        .id("308e7ef1-63f1-4a6c-978c-2e527cbb1c36")
                        .protocol("tcp")
                        .ports(Ports.builder()
                            .end(1235)
                            .start(1234)
                            .build())
                        .build())
                    .source(Source.builder()
                        .id("308e7ef1-63f1-4a6c-978c-2e527cbb1c36")
                        .build())
                    .build())
                .build())
            .expectComplete()
            .verify(Duration.ofSeconds(5));
    }

}
