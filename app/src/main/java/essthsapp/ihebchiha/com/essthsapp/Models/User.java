package essthsapp.ihebchiha.com.essthsapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

        @SerializedName("CIN")
        @Expose
        private int cin;
        @SerializedName("fname")
        @Expose
        private String fname;

         @SerializedName("lname")
         @Expose
         private String lname;

        @SerializedName("email")
        @Expose
        private String email;

         @SerializedName("username")
         @Expose
         private String username;

         @SerializedName("password")
         @Expose
         private String password;

        public User(int cin, String fname, String lname, String email, String username, String password) {
            this.cin = cin;
            this.fname = fname;
            this.lname = lname;
            this.email=email;
            this.username = username;
            this.password=password;



        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getCin() {
            return cin;
        }

        public void setCin(int cin) {
            this.cin = cin;
        }

        public String getFname() {
        return fname;
    }

        public void setFname(String fname) {
        this.fname = fname;
    }

        public String getLname() {
        return lname;
    }

        public void setLname(String lname) {
        this.lname = lname;
    }

         public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
         }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
