package com.niit.challenge1_Muzix_Appli.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND , reason = "Track with specified id is not found !!")
public class TrackNotFoundException extends Exception{
}
