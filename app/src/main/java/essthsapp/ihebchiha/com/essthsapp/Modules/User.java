package essthsapp.ihebchiha.com.essthsapp.Modules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

        @SerializedName("CIN")
        @Expose
        private String username;
        @SerializedName("nom_client")
        @Expose
        private String password;

        public User(String cIN, String nomClient) {
            this.username = cIN;
            this.password = nomClient;

        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }
