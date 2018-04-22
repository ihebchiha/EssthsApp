package essthsapp.ihebchiha.com.essthsapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

        @SerializedName("cin")
        @Expose
        private String cin;
        @SerializedName("fname")
        @Expose
        private String fname;

         @SerializedName("lname")
         @Expose
         private String lname;
         @SerializedName("username")
         @Expose
         private String username;
         @SerializedName("imglink")
         @Expose
         private String imglink;

        public User(String cin, String fname, String lname, String username, String imglink) {
            this.cin = cin;
            this.fname = fname;
            this.lname = lname;
            this.username = username;
            this.imglink = imglink;


        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCin() {
            return cin;
        }

        public void setCin(String password) {
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

    public String getImglink() {
        return imglink;
    }

    public void setImglink(String imglink) {
        this.imglink = imglink;
    }
}
