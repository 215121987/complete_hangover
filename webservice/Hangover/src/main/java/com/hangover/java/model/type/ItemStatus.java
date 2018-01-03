package com.hangover.java.model.type;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 1/30/16
 * Time: 10:53 PM
 * To change this template use File | Settings | File Templates.
 */
public enum ItemStatus {

    AVAILABLE, SOLD;
    
    
    public static ItemStatus get(String name){
        for(ItemStatus itemStatus : ItemStatus.values()){
            if(name.equalsIgnoreCase(itemStatus.name())){
                return itemStatus;
            }
        }
        return null;
    }

}
