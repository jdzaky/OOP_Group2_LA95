package aplikasiPencariDokter;

import java.sql.SQLException;
import java.util.List;

public class AppointmentManager {
	private DoctorManager doctorManager;
	private KoneksiDB koneksiDB;

	public AppointmentManager(DoctorManager doctorManager) {
        this.doctorManager = doctorManager;
        this.koneksiDB = new KoneksiDB();
    }

    public void bookAppointment(Appointment appointment) throws SQLException {
        Dokter dokter = doctorManager.getDoctorById(appointment.getKodeDokter());
        if (!dokter.getJadwalPraktik().contains(appointment.getTanggalJanji())) {
            System.out.println("Dokter tidak tersedia pada tanggal tersebut.");
        }
        koneksiDB.addAppointment(appointment);

    }
}
