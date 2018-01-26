package com.ajp.yourgrade;

/**
 * Not in all use cases is it needed to parse all fields from the models to json.
 * To filter out fields that are not for the public eye the @JsonView annotation
 * is used to specify levels of authorisation.
 */
public class View {

    //What can be seen by everyone
    public interface Public {}

    //What can be seen by the teacher, extending on the public information
    public interface Teacher extends Public {}

}
