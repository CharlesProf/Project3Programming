module fi.tuni.prog3.sisu {
    opens fi.tuni.prog3.sisu;

    requires javafx.controls;
    exports fi.tuni.prog3.sisu;
    requires com.google.gson;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;

}
