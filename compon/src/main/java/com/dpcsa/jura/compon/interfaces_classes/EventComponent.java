package com.dpcsa.jura.compon.interfaces_classes;

import com.dpcsa.jura.compon.base.BaseComponent;

public class EventComponent {
    public int eventSenderId;
    public BaseComponent eventReceiverComponent;
    public EventComponent(int sender, BaseComponent receiver) {
        eventSenderId = sender;
        eventReceiverComponent = receiver;
    }
}
