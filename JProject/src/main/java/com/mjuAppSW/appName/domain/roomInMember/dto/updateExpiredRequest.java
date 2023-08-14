package com.mjuAppSW.appName.domain.roomInMember.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class updateExpiredRequest {
    @JsonProperty("roomId")
    @NotNull
    private long roomId;
    @JsonProperty("memberId")
    @NotNull
    private long memberId;
    @JsonProperty("expired")
    @NotNull
    private String expired;
}