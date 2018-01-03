/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hangover.ashqures.hangover.exception;

import com.hangover.ashqures.hangover.dto.StatusDTO;

import java.util.ArrayList;
import java.util.List;

/**
 *  Wrapper around Exceptions used to manage default errors.
 */
public class DefaultErrorBundle extends Throwable implements ErrorBundle {

    private static final String DEFAULT_ERROR_MSG = "Unknown error";

    private final Exception exception;

    private final Integer code;

    private final String message;

    private final List<String> errors;

    public DefaultErrorBundle(Exception exception) {
        this.exception = exception;
        this.code = 400;
        this.message = exception != null? this.exception.getMessage() : DEFAULT_ERROR_MSG;
        this.errors = null;
    }

    public DefaultErrorBundle(StatusDTO statusDTO) {
        this.exception = null;
        this.code = statusDTO.getCode();
        this.message = statusDTO.getMessage();
        this.errors = statusDTO.getErrors();
    }


    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public Throwable getException() {
        return this;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public List<String> getErrors() {
        return this.errors;
    }


}
