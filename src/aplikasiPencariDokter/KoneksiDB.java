package aplikasiPencariDokter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KoneksiDB { 
	
	private static String url = "jdbc:mysql://localhost:3306/doctor_finder";
    private static String username = "root";
    private static String password = "";
    private static Connection con;
    
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public void addDoctor(Dokter dokter) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        String sql = "INSERT INTO dokter (kode_dokter, nama_dokter, spesialisasi, jadwal_praktik) VALUES ( ?, ?, ?, ?)";
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
        String sql = "SELECT * FROM dokter";
        try (Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String kodeDokter = resultSet.getString("kode_dokter");
                String namaDokter = resultSet.getString("nama_dokter");
                String spesialisasi = resultSet.getString("spesialisasi");
                String jadwalPraktik = resultSet.getString("jadwal_praktik");
                Dokter dokter;
                if (spesialisasi.isEmpty()) {
                    dokter = new DokterUmum(kodeDokter, namaDokter, spesialisasi, jadwalPraktik);
                } else {
                    dokter = new DokterSpesialis(kodeDokter, namaDokter, spesialisasi, jadwalPraktik); 
                }

                daftarDokter.add(dokter);
            }
        }
        return daftarDokter;
    }

	public void addAppointment(Appointment appointment) throws SQLException {
		  String sql = "INSERT INTO appointment(id_pasien, kode_dokter, tanggal_janji, keluhan_pasien) VALUES (?, ?, ?, ?)";
		  PreparedStatement statement = con.prepareStatement(sql);

		  statement.setString(1, appointment.getIdPasien());
		  statement.setString(2, appointment.getKodeDokter());
		  statement.setString(3, appointment.getTanggalJanji());
		  statement.setString(4, appointment.getKeluhanPasien());

		  try {
		    statement.executeUpdate();
		  } catch (SQLException e) {
		    System.out.println("Error adding appointment: " + e.getMessage());
		    throw e; 
		  }


		  statement.close();
	}

	public void deleteAppointment(String idPasien, String id_janji) throws SQLException {
		  String sql = "DELETE FROM appointment WHERE id_pasien = ? AND keluhan_pasien = ?";
		  PreparedStatement statement = con.prepareStatement(sql);

		  statement.setString(1, idPasien);
		  statement.setString(2, id_janji);

		  try {
		    statement.executeUpdate();
		  } catch (SQLException e) {
		    System.out.println("Error deleting appointment: " + e.getMessage());
		    throw e; 
		  }

		  statement.close();		
	}


	public List<Appointment> getPatientAppointments(String idPasien) throws SQLException {
		  String sql = "SELECT * FROM appointment WHERE id_pasien = ?";
		  PreparedStatement statement = con.prepareStatement(sql);

		  statement.setString(1, idPasien);

		  try (ResultSet resultSet = statement.executeQuery()) {
		    List<Appointment> appointments = new ArrayList<>();

		    while (resultSet.next()) {
		      Appointment appointment = new Appointment();
		      appointment.setIdPasien(resultSet.getString("id_pasien"));
		      appointment.setNamaPasien(resultSet.getString("nama_pasien"));
		      appointment.setKodeDokter(resultSet.getString("kode_dokter"));
		      appointment.setTanggalJanji(resultSet.getString("tanggal_janji"));
		      appointment.setKeluhanPasien(resultSet.getString("keluhan_pasien"));

		      appointments.add(appointment);
		    }

		    return appointments;
		  }
	}

	public Dokter getDoctorById(String kodeDokter) throws SQLException {
	    String sql = "SELECT * FROM dokter WHERE kode_dokter = ?";
	    try (PreparedStatement statement = con.prepareStatement(sql)) {
	        statement.setString(1, kodeDokter);
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                String namaDokter = resultSet.getString("nama_dokter");
	                String spesialisasi = resultSet.getString("spesialisasi");
	                String jadwalPraktik = resultSet.getString("jadwal_praktik");

	                Dokter dokter;
	                if (spesialisasi.isEmpty()) {
	                    dokter = new DokterUmum(kodeDokter, namaDokter, spesialisasi, jadwalPraktik);
	                } else {
	                    dokter = new DokterSpesialis(kodeDokter, namaDokter, spesialisasi, jadwalPraktik); 
	                }

	                return dokter;
	            }
	        }
	    }
	    return null;
	}
	
	public int addPatient(pasien pasien) throws SQLException {
		String sql = "INSERT INTO pasien (namaPasien) VALUES (?)";
	    PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    statement.setString(1, pasien.getNamaPasien());
	    statement.executeUpdate();

	    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	        if (generatedKeys.next()) {
	            return generatedKeys.getInt(1);  
	        } else {
	            throw new SQLException("Failed to retrieve generated ID for pasien");
	        }
	    } finally {
	        statement.close();
	    }
	  }
	
}
