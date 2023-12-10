package aplikasiPencariDokter;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KoneksiDB { //harus buat db nya
    private final String url = "jdbc:mysql://localhost:3306/doctor_finder";
    private final String username = "root";
    private final String password = "";
    private List<Dokter> daftarDokter;

    public void addDoctor(Dokter dokter) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "INSERT INTO dokter (kode_dokter, nama_dokter, spesialisasi, jadwal_praktik) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, dokter.getKodeDokter());
        statement.setString(2, dokter.getNamaDokter());
        statement.setString(3, dokter.getSpesialisasi());
        statement.setString(4, dokter.getJadwalPraktik());
        statement.executeUpdate();
        connection.close();
    }

    public void updateDoctor(String id, Dokter updatedDokter) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "UPDATE dokter SET nama_dokter = ?, spesialisasi = ?, jadwal_praktik = ? WHERE kode_dokter = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, updatedDokter.getNamaDokter());
        statement.setString(2, updatedDokter.getSpesialisasi());
        statement.setString(3, updatedDokter.getJadwalPraktik());
        statement.setString(4, id);
        statement.executeUpdate();
        connection.close();
    }
    
    public void deleteDoctor(String id) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "DELETE FROM dokter WHERE kode_dokter = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        statement.executeUpdate();
        connection.close();
    }

    public List<Dokter> getAllDoctors() throws SQLException {
        List<Dokter> daftarDokter = new ArrayList<>();
        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM dokter";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            String kodeDokter = resultSet.getString("kode_dokter");
            String namaDokter = resultSet.getString("nama_dokter");
            String spesialisasi = resultSet.getString("spesialisasi");
            String jadwalPraktik = resultSet.getString("jadwal_praktik");
            String gelar = resultSet.getString("gelar");
            Dokter dokter = new DokterUmum(kodeDokter, namaDokter, spesialisasi, jadwalPraktik, gelar);
            daftarDokter.add(dokter);
        }
        connection.close();
        return daftarDokter;
    }

	public void addAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAppointment(String idPasien, String idJanji) {
		// TODO Auto-generated method stub
		
	}

	public boolean appointmentExists(String idPasien, String idJanji) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Appointment> getPatientAppointments(String idPasien) {
		// TODO Auto-generated method stub
		return null;
	}

	public Dokter getDoctorById(String kodeDokter) throws SQLException {
		Dokter foundDoctor = null;

        // Check if the doctor is already in the list
        for (Dokter dokter : daftarDokter) {
            if (dokter.getKodeDokter().equals(kodeDokter)) {
                foundDoctor = dokter;
                break;
            }
        }

        // If not found in memory, fetch from the database
        if (foundDoctor == null) {
            foundDoctor = fetchDoctorFromDatabase(kodeDokter);
            if (foundDoctor != null) {
                daftarDokter.add(foundDoctor);
            }
        }

        return foundDoctor;
	}
	private Dokter fetchDoctorFromDatabase(String kodeDokter) {
        Dokter foundDoctor = null;
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM dokter WHERE kode_dokter = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, kodeDokter);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String namaDokter = resultSet.getString("nama_dokter");
                        String spesialisasi = resultSet.getString("spesialisasi");
                        String jadwalPraktik = resultSet.getString("jadwal_praktik");
                        String gelar = resultSet.getString("gelar");

                        // Assuming there are two types of doctors, you need to check the type from the database
                        // and create the appropriate instance
                        if (gelar != null && !gelar.isEmpty()) {
                            foundDoctor = new DokterUmum(kodeDokter, namaDokter, spesialisasi, jadwalPraktik, gelar);
                        } else {
                            foundDoctor = new DokterSpesialis(kodeDokter, namaDokter, spesialisasi, jadwalPraktik, "Bidang Spesialisasi"); // Replace "Bidang Spesialisasi" with the actual field
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }

        return foundDoctor;
    }
}
