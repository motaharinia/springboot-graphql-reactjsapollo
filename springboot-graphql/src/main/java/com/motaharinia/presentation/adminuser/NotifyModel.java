package com.motaharinia.presentation.adminuser;

public class NotifyModel {

    public NotifyModel(Integer notifyCounter) {
        this.notifyCounter = notifyCounter;
    }

    private Integer notifyCounter;

    public Integer getNotifyCounter() {
        return notifyCounter;
    }

    public void setNotifyCounter(Integer notifyCounter) {
        this.notifyCounter = notifyCounter;
    }
}
