/*
 * Copyright 2013-2015 the original author or authors.
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

package org.cloudfoundry.client.v2.routes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;
import org.cloudfoundry.client.QueryParameter;
import org.cloudfoundry.client.Validatable;
import org.cloudfoundry.client.ValidationResult;
import org.cloudfoundry.client.v2.FilterParameter;
import org.cloudfoundry.client.v2.PaginatedRequest;

import java.util.List;

/**
 * The request payload for the List all Applications for the Route operation
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public final class ListRouteApplicationsRequest extends PaginatedRequest implements Validatable {

    /**
     * The app id
     *
     * @param appId the app id
     * @return the app id
     */
    @Getter(onMethod = @__(@QueryParameter("app_guid")))
    private final String appId;

    /**
     * The diegos
     *
     * @param diegos the Diegos
     * @return the Diegos
     */
    @Getter(onMethod = @__(@FilterParameter("diego")))
    private final List<String> diegos;

    /**
     * The id
     *
     * @param id the id
     * @return the id
     */
    @Getter(onMethod = @__(@JsonIgnore))
    private final String id;

    /**
     * The names
     *
     * @param names the names
     * @return the names
     */
    @Getter(onMethod = @__(@FilterParameter("name")))
    private final List<String> names;

    /**
     * The organization ids
     *
     * @param organizationIds the organization ids
     * @return the organization ids
     */
    @Getter(onMethod = @__(@FilterParameter("organization_guid")))
    private final List<String> organizationIds;

    /**
     * The space ids
     *
     * @param spaceIds the space ids
     * @return the space ids
     */
    @Getter(onMethod = @__(@FilterParameter("space_guid")))
    private final List<String> spaceIds;

    /**
     * The stack ids
     *
     * @param stackIds the stack ids
     * @return the stack ids
     */
    @Getter(onMethod = @__(@FilterParameter("stack_guid")))
    private final List<String> stackIds;

    @Builder
    ListRouteApplicationsRequest(OrderDirection orderDirection, Integer page, Integer resultsPerPage,
                                 String appId,
                                 @Singular List<String> diegos,
                                 String id,
                                 @Singular List<String> names,
                                 @Singular List<String> organizationIds,
                                 @Singular List<String> spaceIds,
                                 @Singular List<String> stackIds) {
        super(orderDirection, page, resultsPerPage);

        this.appId = appId;
        this.diegos = diegos;
        this.id = id;
        this.names = names;
        this.organizationIds = organizationIds;
        this.spaceIds = spaceIds;
        this.stackIds = stackIds;
    }

    @Override
    public ValidationResult isValid() {
        ValidationResult.ValidationResultBuilder builder = ValidationResult.builder();

        if (this.id == null) {
            builder.message("id must be specified");
        }

        return builder.build();
    }

}
