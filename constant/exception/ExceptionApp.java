package exception;

import java.sql.SQLException;

public class ExceptionApp extends SQLException {
    // Konstruktor dengan pesan custom
    public ExceptionApp(String pesan) {
        super(pesan);
    }

    // Konstruktor dengan pesan custom dan penyebab
    public ExceptionApp(String pesan, Throwable penyebab) {
        super(pesan, penyebab);
    }

    // Konstruktor dengan penyebab saja
    public ExceptionApp(Throwable penyebab) {
        super(penyebab);
    }


    public String getPesanKesalahanDetail() {
        return "Terjadi kesalahan pada database: " + getMessage();
    }
}
