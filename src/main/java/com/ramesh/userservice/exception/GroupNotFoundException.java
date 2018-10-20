package com.ramesh.userservice.exception;

public class GroupNotFoundException extends RuntimeException{
    public GroupNotFoundException(String msg){
        super(msg);
    }
}
