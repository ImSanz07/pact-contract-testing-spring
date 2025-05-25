package com.example.demo;

import java.time.Instant;
import java.util.Objects;

public class CancelMissionCommand {

    private String missionId;
    private Instant stateTime;
    private String requestedByUserId;

    public CancelMissionCommand() {
        // Default constructor for deserialization
    }

    public CancelMissionCommand(String missionId, Instant stateTime, String requestedByUserId) {
        this.missionId = missionId;
        this.stateTime = stateTime;
        this.requestedByUserId = requestedByUserId;
    }

    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    public Instant getStateTime() {
        return stateTime;
    }

    public void setStateTime(Instant stateTime) {
        this.stateTime = stateTime;
    }

    public String getRequestedByUserId() {
        return requestedByUserId;
    }

    public void setRequestedByUserId(String requestedByUserId) {
        this.requestedByUserId = requestedByUserId;
    }

    @Override
    public String toString() {
        return "CancelMissionCommand{" +
                "missionId='" + missionId + '\'' +
                ", stateTime=" + stateTime +
                ", requestedByUserId='" + requestedByUserId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CancelMissionCommand)) return false;
        CancelMissionCommand that = (CancelMissionCommand) o;
        return Objects.equals(missionId, that.missionId) &&
                Objects.equals(stateTime, that.stateTime) &&
                Objects.equals(requestedByUserId, that.requestedByUserId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(missionId, stateTime, requestedByUserId);
    }
}
