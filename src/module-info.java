module travel.system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.csv;
    requires jbcrypt;
    requires com.zaxxer.hikari;
    opens com.yyc.TourGuideQueryInterface to javafx.fxml;
    exports com.yyc.TourGuideQueryInterface;
}