package database.mahasiwa;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;


import java.sql.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MenuMahasiwa extends JFrame {

	private JPanel contentPane;
	private JTextField txtNPM;
	private JTextField txtNama;
	private JTextArea textAlamat;
	private JComboBox cbJurusan;
	private JTable table;
	private JLabel lblWall;
	private JLabel lblIcon;
	
	
	String header[] = {"Npm","Nama","Jurusan","Alamat"};
	DefaultTableModel tabelModel;
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					MenuMahasiwa frame = new MenuMahasiwa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuMahasiwa() {
		setTitle("TUGAS JAVA KELOMPOK 3");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNpm = new JLabel("Npm :");
		lblNpm.setBounds(10, 11, 46, 14);
		contentPane.add(lblNpm);
		
		JLabel lblNama = new JLabel("Nama :");
		lblNama.setBounds(10, 51, 46, 14);
		contentPane.add(lblNama);
		
		JLabel lblJurusan = new JLabel("Jurusan :");
		lblJurusan.setBounds(10, 76, 46, 14);
		contentPane.add(lblJurusan);
		
		JLabel lblAlamat = new JLabel("Alamat :");
		lblAlamat.setBounds(10, 113, 46, 14);
		contentPane.add(lblAlamat);
		
		txtNPM = new JTextField();
		txtNPM.setBounds(61, 8, 169, 29);
		contentPane.add(txtNPM);
		txtNPM.setColumns(10);
		
		txtNama = new JTextField();
		txtNama.setBounds(61, 44, 169, 29);
		contentPane.add(txtNama);
		txtNama.setColumns(10);
		
		final JComboBox cbJurusan = new JComboBox();
		cbJurusan.setModel(new DefaultComboBoxModel(new String[] {"TI", "SI", "Ekonomi"}));
		cbJurusan.setBounds(61, 76, 110, 26);
		contentPane.add(cbJurusan);
		
		
		
		final JTextArea textAlamat = new JTextArea();
		textAlamat.setBounds(61, 109, 199, 78);
		contentPane.add(textAlamat);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 189, 333, 85);
		contentPane.add(scrollPane);
		
		tabelModel = new DefaultTableModel(null,header);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getData();
			}
		});
		table.setModel(tabelModel);
		scrollPane.setViewportView(table);
		
		JButton btnSimpan = new JButton("SIMPAN");
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String jurusan = "";
				if(cbJurusan.getSelectedIndex() == 0)
				{
				jurusan = "TI";
				} else if(cbJurusan.getSelectedIndex() == 1)
				{
				jurusan = "SI";
				} else if(cbJurusan.getSelectedIndex() == 2)
				{
				jurusan = "Ekonomi";
				} try
				{
				Connection konek = KONEKSI.getKONEKSI();
				String query = "INSERT INTO mahasiswa VALUES(?,?,?,?)";
				PreparedStatement prepare =
				konek.prepareStatement(query);
				prepare.setInt(1,Integer.parseInt(txtNPM.getText()));
				prepare.setString(2, txtNama.getText());
				prepare.setString(3, jurusan);
			    prepare.setString(4, textAlamat.getText());
				
				prepare.executeUpdate();
				JOptionPane.showMessageDialog(null,"Data berhasil ditambahkan ke database");
				} catch(Exception ex)
				{
				JOptionPane.showMessageDialog(null,"Data gagal ditambahkan ke database");
				System.out.println(ex);
				}
				finally
				{ getDataTable();
				}
				
			}
		});
		btnSimpan.setIcon(new ImageIcon("C:\\Users\\IRMAN\\Desktop\\mahasiswa\\src\\database\\Gambar\\Simpan.png"));
		btnSimpan.setBounds(61, 285, 101, 33);
		contentPane.add(btnSimpan);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String jurusan = "";
				if(cbJurusan.getSelectedIndex() == 0)
				{
				jurusan = "TI";
				} else if(cbJurusan.getSelectedIndex() == 1)
				{
				jurusan = "SI";
				} else if(cbJurusan.getSelectedIndex() == 2)
				{
				jurusan = "Ekonomi";
				} try
				{
				Connection konek = KONEKSI.getKONEKSI();
				String query = "UPDATE mahasiswa SET Nama = ?,Jurusan = ?, Alamat = ? WHERE Npm = ?";
				PreparedStatement prepare =
				konek.prepareStatement(query);
				prepare.setString(1, txtNama.getText());
				prepare.setString(2, jurusan);
				prepare.setString(3, textAlamat.getText());
				prepare.setInt(4,Integer.parseInt(txtNPM.getText()));
				prepare.executeUpdate();
				JOptionPane.showMessageDialog(null,"Data berhasil diupdate");
				prepare.close();
				} catch(Exception ex)
				{
				JOptionPane.showMessageDialog(null,"Data gagal diupdate");
				System.out.println(ex);
				}
				finally
				{
				getDataTable();
			}}
		});
		btnUpdate.setIcon(new ImageIcon("C:\\Users\\IRMAN\\Desktop\\mahasiswa\\src\\database\\Gambar\\Ubah.png"));
		btnUpdate.setBounds(171, 285, 101, 33);
		contentPane.add(btnUpdate);
		
		JButton btnHapus = new JButton("HAPUS");
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{ 
				Connection konek = KONEKSI.getKONEKSI();
				String query = "DELETE FROM mahasiswa WHERE Npm = ?";
				PreparedStatement prepare = konek.prepareStatement(query);
				prepare.setInt(1,Integer.parseInt(txtNPM.getText()));
				prepare.executeUpdate();
				JOptionPane.showMessageDialog(null,"Data berhasil dihapus");
				prepare.close();
				} catch(Exception ex)
				{
				JOptionPane.showMessageDialog(null,"Data gagal dihapus");
				System.out.println(ex);
				} finally
				{
				getDataTable();
				}
				
			}
		});
		btnHapus.setIcon(new ImageIcon("C:\\Users\\IRMAN\\Desktop\\mahasiswa\\src\\database\\Gambar\\Hapus.png"));
		btnHapus.setBounds(282, 285, 112, 33);
		contentPane.add(btnHapus);
		
		
		JButton btnClean = new JButton("CLEAN");
		btnClean.setIcon(new ImageIcon("C:\\Users\\IRMAN\\Desktop\\mahasiswa\\src\\database\\Gambar\\refresh1.png"));
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNPM.setText("");
				txtNama.setText("");
				cbJurusan.setToolTipText("");
				textAlamat.requestFocus();
				cleanTable();
			}
		});
		btnClean.setForeground(Color.BLACK);
		btnClean.setBounds(404, 285, 110, 33);
		contentPane.add(btnClean);
		
		lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon("C:\\Users\\IRMAN\\Desktop\\mahasiswa\\src\\database\\Gambar\\gunadarma.png"));
		lblIcon.setBounds(316, 11, 150, 155);
		contentPane.add(lblIcon);
		
		lblWall = new JLabel("");
		lblWall.setIcon(new ImageIcon("C:\\Users\\IRMAN\\Desktop\\mahasiswa\\src\\database\\Gambar\\wall.jpg"));
		lblWall.setBounds(0, -11, 618, 419);
		contentPane.add(lblWall);
		setLocationRelativeTo(null);
	}
	
		public void cleanTable()
		{
			int baris = tabelModel.getRowCount();
			for(int a=0;a<baris;a++){
				tabelModel.removeRow(0);
			}		
}
		public void getDataTable()
		{
			tabelModel.getDataVector().removeAllElements();
			tabelModel.fireTableDataChanged();
		try
		{
		Connection konek = KONEKSI.getKONEKSI();
		Statement state = konek.createStatement();
		String query = "SELECT * FROM mahasiswa";
		ResultSet rs = state.executeQuery(query);
		while(rs.next())
		{
		Object obj[] = new Object[4];
		obj[0] = rs.getInt(1);
		obj[1] = rs.getString(2);
		obj[2] = rs.getString(3);
		obj[3] = rs.getString(4);
		tabelModel.addRow(obj);
		} rs.close();
		state.close();
		} catch(Exception ex)
		{
			
			}
		}
		

		public void getData()
		{
			JTable tabel = null;
			int pilih = tabel.getSelectedRow();
			if(pilih == -1)
			{
				return;
			}
			int npm = (int) tabelModel.getValueAt(pilih, 0);
			txtNPM.setText("" + npm);
			String nama = (String) tabelModel.getValueAt(pilih, 1);
			txtNama.setText(nama);
			String jurusan = (String) tabelModel.getValueAt(pilih, 2);
			cbJurusan.setSelectedItem(jurusan);
			String alamat = (String) tabelModel.getValueAt(pilih, 3);
			textAlamat.setText(alamat);
		}
}


