package com.waggieetales.androidapp.playfootball.contracts;

public interface IFactory<T> {
    public void getList(IVolleyListner listner);
    public void getOfflineList(IVolleyListner listner);
}
