package com.example.faridaziz.kopinema.models;

import com.google.firebase.database.PropertyName;

public class BoardJ {
    @PropertyName("id")
    private String id;

    @PropertyName("is_active")
    private boolean isActive;

    @PropertyName("on_process")
    private boolean onProcess;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isOnProcess() {
        return onProcess;
    }

    public void setOnProcess(boolean onProcess) {
        this.onProcess = onProcess;
    }
}
