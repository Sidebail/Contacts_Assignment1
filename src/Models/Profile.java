package Models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class Profile {

    private String firstName, lastName, eMail, bio, address, phone, sex, birthday;
    private String arr[] = {"Male", "Female", "Other"};
    private List<String> sexes = Arrays.asList(arr);
    private ArrayList<String> failureArray;


    /**
     * Constructor for Profile class
     * @param firstName
     * @param lastName
     * @param eMail
     * @param bio
     * @param address
     * @param phone
     * @param sex
     * @param birthday
     */
    public Profile(String firstName,String lastName,String eMail,String bio,String address,String phone,String sex,String birthday ){
        failureArray = new ArrayList<>();
        setFirstName(firstName);
        setLastName(lastName);
        setEMail(eMail);
        setBio(bio);
        setAddress(address);
        setPhone(phone);
        setSex(sex);
        setBirthday(birthday);


    }

    public String getFirstName() {
        return firstName;

    }

    /**
     * firstName setter with validation
     * @param firstName
     */
    public void setFirstName(String firstName) {
        if(firstName.equals("")){
            failureArray.add("firstName");
        }else{
            this.firstName = firstName;

        }

    }

    public String getLastName() {
        return lastName;
    }

    /**
     * lastName setter with validation
     * @param lastName
     */
    public void setLastName(String lastName) {
        if(lastName.equals("")){
            failureArray.add("lastName");
        }else{
            this.lastName = lastName;

        }
    }

    public String getEMail() {
        return eMail;
    }

    /**
     * EMail setter with validation
     * @param eMail
     */
    public void setEMail(String eMail) {

        /*
        Code part with email validation is gotten from https://www.geeksforgeeks.org/check-email-address-valid-not-java/
         */
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        //End of copied code

        if(eMail.equals("") || !pat.matcher(eMail).matches() ){
            failureArray.add("eMail");
        }else{
            this.eMail = eMail;

        }
    }

    public String getBio() {
        return bio;
    }


    /*
    Some of the further variable setters are made WITHOUT validation if they are left blank. That's because the application is a form of social network profile creation.

    Users ARE NOT forced to input their personal data, except ones that are required for registration
     */
    /**
    bio setter, Bio CAN be blank
     */
    public void setBio(String bio) {

            this.bio = bio;


    }

    public String getAddress() {
        return address;
    }

    /**
     * address setter, address CAN BE BLANK
     * @param address
     */
    public void setAddress(String address) {

            this.address = address;

    }

    public String getPhone() {
        return phone;
    }

    /**
    phone setter, Phone CAN be blank
     */
    public void setPhone(String phone) {
        /*
        if(phone.equals("")){
            failureArray.add("phone");
        }else{
            this.phone = phone;

        }
        */
        String regexStr = "^[0-9]*$";
        Pattern pat = Pattern.compile(regexStr);

        if(pat.matcher(phone).matches()) {
            this.phone = phone;
        }else{
            failureArray.add("phone");
        }
    }

    public String getSex() {
        return sex;
    }

    /**
     * sex setter with validation
     * @param sex
     */
    public void setSex(String sex) {
        if(sex.equals("")){
            failureArray.add("sex");
        }else{
            this.sex = sex;

        }
    }

    public String getBirthday() {
        return birthday;
    }

    /**
     birthday sette, Birthday CAN be blank
     */
    public void setBirthday(String birthday) {
        /*
        if(birthday.equals("")){
            failureArray.add("birthday");
        }else{
            this.birthday = birthday;

        }
        */
        this.birthday = birthday;

    }


    /**
     * Getter of a failure array, an array where all strings are saved, which are describing which part had returned an exception
     * @return
     */
    public ArrayList<String> getFailureArray(){
        return failureArray;
    }
}
