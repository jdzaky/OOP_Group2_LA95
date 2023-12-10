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
        // Check doctor availability
        Dokter dokter = doctorManager.getDoctorById(appointment.getKodeDokter());
        if (!dokter.getJadwalPraktik().contains(appointment.getTanggalJanji())) {
            System.out.println("Dokter tidak tersedia pada tanggal tersebut.");
        }
        // Implement logic to store appointment data
        koneksiDB.addAppointment(appointment);

    }

    public List<Appointment> getPatientAppointments(String idPasien) throws SQLException {
        List<Appointment> retrievedAppointments = koneksiDB.getPatientAppointments(idPasien);  
        return retrievedAppointments;
    }

    public void cancelAppointment(String idPasien, String idJanji) throws SQLException {
    	if (!koneksiDB.appointmentExists(idPasien, idJanji)) {
    		System.out.println("Janji temu tidak ditemukan.");
        }

        // Delete appointment from database
        koneksiDB.deleteAppointment(idPasien, idJanji);

    }
}
