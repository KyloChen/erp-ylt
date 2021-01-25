package com.loohos.stategrid.common.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Menu {
    private int id;
    private int tokenId;
    private String menuName;
    private String name;
    private String srvName;

    public Menu() {
    }

    public Menu(String menuName, String srvName, String name, int tokenId) {
        this.tokenId = tokenId;
        this.menuName = menuName;
        this.name = name;
        this.srvName = srvName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getSrvName() {
        return srvName;
    }

    public void setSrvName(String srvName) {
        this.srvName = srvName;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", tokenId=" + tokenId +
                ", menuName='" + menuName + '\'' +
                ", name='" + name + '\'' +
                ", srvName='" + srvName + '\'' +
                '}';
    }
}
