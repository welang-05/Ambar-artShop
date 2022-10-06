package com.challengerFinal.arte.payments;

import com.challengerFinal.arte.dtos.ItemDTO;

import java.util.List;

public class Preference {
    private List<ItemDTO> items;
    private back_urls back_urls = new back_urls();
    private String auto_return = "approved";
    private String notification_url = "myapp.com/webhook";
    private String statement_descriptor = "ArteApp/MindHub";
    private String external_reference = "ArteApp";
    public class back_urls {
        private String success = "http://localhost:8080/";
        private String failure = "http://localhost:8080/";
        private String pending = "http://localhost:8080/";
        public back_urls() {
            this.success = success;
            this.failure = failure;
            this.pending = pending;
        }

        public String getSuccess() {
            return success;
        }

        public String getFailure() {
            return failure;
        }

        public String getPending() {
            return pending;
        }
    }


    public Preference() {
    }

    public Preference(List<ItemDTO> items) {
        this.items = items;
        this.back_urls = back_urls;
        this.auto_return = auto_return;
        this.notification_url = notification_url;
        this.statement_descriptor = statement_descriptor;
        this.external_reference = external_reference;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    public String getAuto_return() {
        return auto_return;
    }

    public String getNotification_url() {
        return notification_url;
    }

    public String getStatement_descriptor() {
        return statement_descriptor;
    }

    public String getExternal_reference() {
        return external_reference;
    }

    public Preference.back_urls getBack_urls() {
        return back_urls;
    }
}
